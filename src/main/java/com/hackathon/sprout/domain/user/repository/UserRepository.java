package com.hackathon.sprout.domain.user.repository;

import com.hackathon.sprout.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
