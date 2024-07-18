package com.hackathon.sprout.domain.user.service;

import com.hackathon.sprout.domain.country.exception.InvalidLanguageCodeException;
import com.hackathon.sprout.domain.country.exception.InvalidNationCodeException;
import com.hackathon.sprout.domain.country.repository.LanguageRepository;
import com.hackathon.sprout.domain.country.repository.NationRepository;
import com.hackathon.sprout.domain.hospital.exception.InvalidHospitalException;
import com.hackathon.sprout.domain.hospital.repository.HospitalRepository;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.dto.UserRegisterRequest;
import com.hackathon.sprout.domain.user.repository.UserRepository;
import com.hackathon.sprout.domain.workhistory.domain.WorkHistory;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryRequest;
import com.hackathon.sprout.domain.workhistory.repository.WorkHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final WorkHistoryRepository workHistoryRepository;
    private final HospitalRepository hospitalRepository;
    private final NationRepository nationRepository;
    private final LanguageRepository languageRepository;
    private final PasswordEncoder passwordEncoder;

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
}