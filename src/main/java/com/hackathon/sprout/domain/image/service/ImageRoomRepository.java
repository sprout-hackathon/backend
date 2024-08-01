package com.hackathon.sprout.domain.image.service;

import com.hackathon.sprout.domain.image.domain.ImageRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ImageRoomRepository extends JpaRepository<ImageRoom, Long> {
    List<ImageRoom> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<ImageRoom> findByUser_IdAndCreatedAtBetween(String userId, LocalDateTime start, LocalDateTime end);
}
