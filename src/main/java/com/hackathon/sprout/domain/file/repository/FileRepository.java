package com.hackathon.sprout.domain.file.repository;

import com.hackathon.sprout.domain.file.domain.File;
import com.hackathon.sprout.domain.chat.domain.ImageMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllByImageMessage(ImageMessage imageMessage);
}