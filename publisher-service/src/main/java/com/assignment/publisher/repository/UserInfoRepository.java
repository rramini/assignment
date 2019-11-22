package com.assignment.publisher.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.publisher.models.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
	Optional<UserInfo> findByUsername(String username);
}
