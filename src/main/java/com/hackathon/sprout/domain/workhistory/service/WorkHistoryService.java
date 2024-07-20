package com.hackathon.sprout.domain.workhistory.service;

import com.hackathon.sprout.domain.hospital.service.HospitalService;
import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.user.service.UserService;
import com.hackathon.sprout.domain.workhistory.domain.WorkHistory;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryRequest;
import com.hackathon.sprout.domain.workhistory.dto.WorkHistoryUpdateRequest;
import com.hackathon.sprout.domain.workhistory.repository.WorkHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkHistoryService {
    private final HospitalService hospitalService;
    private final WorkHistoryRepository workHistoryRepository;

    public Long createWorkHistory(User user, WorkHistoryRequest workHistoryRequest){
        return workHistoryRepository.save(workHistoryRequest.toEntity(
                hospitalService.getHospital(workHistoryRequest.getHospitalId()),
                user)).getWorkHistoryId();
    }

    @Transactional(readOnly = true)
    public List<WorkHistory> getWorkHistoryList(User user){
        return workHistoryRepository.findAllByUser(user);
    }
    @Transactional(readOnly = true)
    public WorkHistory getWorkHistory(Long workHistoryId) {
        return workHistoryRepository.findById(workHistoryId).orElseThrow();
    }

    public void deleteWorkHistory(Long workHistoryId){
        workHistoryRepository.deleteById(workHistoryId);
    }

    public void update(Long workHistoryId, WorkHistoryUpdateRequest request){
        WorkHistory workHistory = getWorkHistory(workHistoryId);
        Byte workDuration = request.workDuration();
        if(workDuration != null){
            workHistory.updateWorkDuration(workDuration);
        }
        Long hospitalId = request.hospitalId();
        if(hospitalId != null && !workHistory.getHospital().getHospitalId().equals(hospitalId)){
            workHistory.updateHospital(hospitalService.getHospital(hospitalId));
        }
    }
}
