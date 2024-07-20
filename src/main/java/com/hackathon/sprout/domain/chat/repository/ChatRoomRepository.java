package com.hackathon.sprout.domain.chat.repository;

import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<ChatRoom> findByUser_IdAndCreatedAtBetween(String userId, LocalDateTime start, LocalDateTime end);
}
