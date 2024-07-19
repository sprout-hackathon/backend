package com.hackathon.sprout.domain.user.service;

import com.hackathon.sprout.domain.country.exception.InvalidLanguageCodeException;
import com.hackathon.sprout.domain.country.exception.InvalidNationCodeException;
import com.hackathon.sprout.domain.country.repository.LanguageRepository;
import com.hackathon.sprout.domain.country.repository.NationRepository;
import com.hackathon.sprout.domain.hospital.exception.InvalidHospitalException;
import com.hackathon.sprout.domain.hospital.repository.HospitalRepository;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.dto.UserLoginRequest;
import com.hackathon.sprout.domain.user.dto.UserRegisterRequest;
import com.hackathon.sprout.domain.user.exception.InvalidPasswordException;
import com.hackathon.sprout.domain.user.exception.UserNotFoundException;
import com.hackathon.sprout.domain.user.repository.UserRepository;
import com.hackathon.sprout.domain.workhistory.domain.WorkHistory;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryRequest;
import com.hackathon.sprout.domain.workhistory.repository.WorkHistoryRepository;
import com.hackathon.sprout.global.jwt.JwtProvider;
import com.hackathon.sprout.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WorkHistoryRepository workHistoryRepository;
    private final HospitalRepository hospitalRepository;
    private final NationRepository nationRepository;
    private final LanguageRepository languageRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void register(UserRegisterRequest userRegisterRequest) {
        User user = User.builder()
                .id(userRegisterRequest.getId())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .nickname(userRegisterRequest.getNickname())
                .nationCode(nationRepository.findById(userRegisterRequest.getNationCode())
                        .orElseThrow(() -> new InvalidNationCodeException()))
                .languageCode(languageRepository.findById(userRegisterRequest.getLanguageCode())
                        .orElseThrow(() -> new InvalidLanguageCodeException()))
                .proficiency(userRegisterRequest.getProficiency())
                .hasCertification(userRegisterRequest.getHasCertification())
                .certificationCode(userRegisterRequest.getCertificationCode())
                .isDeleted(false)
                .build();

        user = userRepository.save(user);

        for (WorkHistoryRequest workHistoryRequest : userRegisterRequest.getWorkHistories()) {
            WorkHistory workHistory = WorkHistory.builder()
                    .workDuration(workHistoryRequest.getWorkDuration())
                    .hospitalId(hospitalRepository.findById(workHistoryRequest.getHospitalId())
                            .orElseThrow(() -> new InvalidHospitalException()))
                    .userId(user)
                    .build();
            workHistoryRepository.save(workHistory);
        }
    }

    @Transactional
    public JwtResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findById(userLoginRequest.getId())
                .orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        String accessToken = jwtProvider.createAccessToken(user.getId());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public void deleteUser(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.setIsDeleted(true);
        userRepository.save(user);
    }
}