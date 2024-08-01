package com.hackathon.sprout.domain.image.service;

import com.hackathon.sprout.domain.image.domain.ImageMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMessageRepository extends JpaRepository<ImageMessage, Long> {

}
