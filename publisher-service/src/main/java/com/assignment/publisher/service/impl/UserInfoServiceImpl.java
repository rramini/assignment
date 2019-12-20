//package com.assignment.publisher.service.impl;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.assignment.publisher.models.JwtRequest;
//import com.assignment.publisher.models.UserInfo;
//import com.assignment.publisher.repository.UserInfoRepository;
//import com.assignment.publisher.service.UserInfoService;
//
//@Service
//public class UserInfoServiceImpl implements UserInfoService {
//
//	@Autowired
//	private UserInfoRepository userInfoRepository;
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Override
//	public Optional<UserInfo> getUserByUsername(String username) {
//		return userInfoRepository.findByUsername(username);
//	}
//
//	@Override
//	public UserInfo addUser(JwtRequest user) {
//		UserInfo newUser = new UserInfo();
//		newUser.setUsername(user.getUsername());
//		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//		return userInfoRepository.saveAndFlush(newUser);
//	}
//}
