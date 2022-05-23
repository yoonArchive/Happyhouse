package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.dto.UserUpdateRequest;

public interface UserService {

	void registerUser(User user) throws SQLException;

	int idCheck(String checkId) throws Exception;

	User login(User user) throws SQLException;

	String findPwd(User user) throws SQLException;

	int updateUser(String user, UserUpdateRequest userUpdateRequest) throws SQLException, Exception;

	User selectById(String userId) throws SQLException;

	int deleteById(String userId) throws SQLException;

	String findId(String userName, String phone);
}
