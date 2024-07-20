package com.hackathon.sprout.domain.workhistory.repository;

import com.hackathon.sprout.domain.user.domain.User;
import com.hackathon.sprout.domain.workhistory.domain.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {
    List<WorkHistory> findAllByUser(User user);
}
