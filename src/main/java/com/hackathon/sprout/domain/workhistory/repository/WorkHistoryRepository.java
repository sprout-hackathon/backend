package com.hackathon.sprout.domain.workhistory.repository;

import com.hackathon.sprout.domain.workhistory.domain.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {
}
