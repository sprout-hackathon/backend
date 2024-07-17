package com.hackathon.sprout.domain.chat.repository;

import com.hackathon.sprout.domain.chat.domain.ChatMessage;
import com.hackathon.sprout.domain.chat.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
