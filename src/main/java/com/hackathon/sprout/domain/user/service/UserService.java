package com.hackathon.sprout.domain.user.service;

import com.hackathon.sprout.domain.application.dto.ApplicationResponse;
import com.hackathon.sprout.domain.application.repository.ApplicationRepository;
import com.hackathon.sprout.domain.country.exception.InvalidLanguageCodeException;
import com.hackathon.sprout.domain.country.exception.InvalidNationCodeException;
import com.hackathon.sprout.domain.country.repository.LanguageRepository;
import com.hackathon.sprout.domain.country.repository.NationRepository;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.dto.UserLoginRequest;
import com.hackathon.sprout.domain.user.dto.UserRegisterRequest;
import com.hackathon.sprout.domain.user.dto.UserResponse;
import com.hackathon.sprout.domain.user.dto.UserUpdateRequest;
import com.hackathon.sprout.domain.user.exception.InvalidPasswordException;
import com.hackathon.sprout.domain.user.exception.UserNotFoundException;
import com.hackathon.sprout.domain.user.repository.UserRepository;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryRequest;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryResponse;
import com.hackathon.sprout.domain.workhistory.repository.WorkHistoryRepository;
import com.hackathon.sprout.domain.workhistory.service.WorkHistoryService;
import com.hackathon.sprout.global.jwt.JwtProvider;
import com.hackathon.sprout.global.jwt.dto.JwtResponse;
import com.hackathon.sprout.global.shared.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WorkHistoryRepository workHistoryRepository;
    private final WorkHistoryService workHistoryService;
    private final ApplicationRepository applicationRepository;
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
                        .orElseThrow(InvalidNationCodeException::new))
                .languageCode(languageRepository.findById(userRegisterRequest.getLanguageCode())
                        .orElseThrow(InvalidLanguageCodeException::new))
                .proficiency(userRegisterRequest.getProficiency())
                .hasCertification(userRegisterRequest.getHasCertification())
                .certificationCode(userRegisterRequest.getCertificationCode())
                .isDeleted(false)
                .build();

        user = userRepository.save(user);

        for (WorkHistoryRequest workHistoryRequest : userRegisterRequest.getWorkHistories()) {
            workHistoryService.createWorkHistory(user, workHistoryRequest);
        }
    }

    @Transactional
    public JwtResponse login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findById(userLoginRequest.getId())
                .orElseThrow(UserNotFoundException::new);

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
    public void updateUser(Authentication authentication, UserUpdateRequest userUpdateRequest) {
        String userId = (String) authentication.getPrincipal();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        user.setNickname(userUpdateRequest.getNickname());
        user.setNationCode(nationRepository.findById(userUpdateRequest.getNationCode())
                .orElseThrow(InvalidNationCodeException::new));
        user.setLanguageCode(languageRepository.findById(userUpdateRequest.getLanguageCode())
                .orElseThrow(InvalidLanguageCodeException::new));
        user.setProficiency(userUpdateRequest.getProficiency());
        user.setHasCertification(userUpdateRequest.getHasCertification());
        user.setCertificationCode(userUpdateRequest.getCertificationCode());

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.setIsDeleted(true);
        userRepository.save(user);
    }
    public User getUser(String userId){
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserFromAuth() {
        String userId = AuthUtil.getUserIdFromAuth();
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<WorkHistoryResponse> workHistories = workHistoryRepository.findAllByUser(user).stream()
                .map(WorkHistoryResponse::new)
                .collect(Collectors.toList());

        List<ApplicationResponse> applications = applicationRepository.findAllByUser(user).stream()
                .map(ApplicationResponse::new)
                .collect(Collectors.toList());

        int totalWorkMonths = workHistories.stream()
                .mapToInt(wh -> wh.getWorkDuration() == null ? 0 : wh.getWorkDuration())
                .sum();

        return UserResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .nationCode(user.getNationCode().getNationCode())
                .languageCode(user.getLanguageCode().getLanguageCode())
                .proficiency(user.getProficiency())
                .hasCertification(user.getHasCertification())
                .totalWorkYear(totalWorkMonths / 12)
                .totalWorkMonth(totalWorkMonths % 12)
                .applications(applications)
                .workHistories(workHistories)
                .certificationCode(user.getCertificationCode())
                .build();
    }

    @Transactional
    public void removeRefreshToken(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setRefreshToken(null);
        userRepository.save(user);
    }

}