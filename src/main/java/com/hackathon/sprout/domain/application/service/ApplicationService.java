package com.hackathon.sprout.domain.application.service;

import com.hackathon.sprout.domain.application.domain.Application;
import com.hackathon.sprout.domain.application.dto.ApplicationCreateRequest;
import com.hackathon.sprout.domain.application.dto.ApplicationUpdateRequest;
import com.hackathon.sprout.domain.application.repository.ApplicationRepository;
import com.hackathon.sprout.domain.recruitment.domain.Recruitment;
import com.hackathon.sprout.domain.recruitment.service.RecruitmentService;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final RecruitmentService recruitmentService;
    private final UserService userService;

    public Long registerApplication(ApplicationCreateRequest request){
        User user = userService.getUserFromAuth();
        Recruitment recruitment = recruitmentService.getRecruitment(request.getRecruitmentId());

        return applicationRepository.save(request.toEntity(user,recruitment)).getApplicationId();
    }

    @Transactional(readOnly = true)
    public List<Application> getApplicationList(){
        return applicationRepository.findAllByUser(userService.getUserFromAuth());
    }

    @Transactional(readOnly = true)
    public Application getApplication(Long applicationId){
        return applicationRepository.findById(applicationId).orElseThrow();
    }

    public void update(Long applicationId, ApplicationUpdateRequest request){
        Application application = getApplication(applicationId);
        application.updateState(request.getState());
    }
}
