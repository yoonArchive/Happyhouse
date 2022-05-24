package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.dto.user.User;
import com.ssafy.happyhouse.dto.user.UserUpdateRequest;

public interface UserService {

	void registerUser(User user) throws SQLException;

	int checkId(String checkId) throws Exception;

	User login(User user) throws SQLException;

	String findPwd(User user) throws SQLException;

	int updateUser(String user, UserUpdateRequest userUpdateRequest) throws SQLException, Exception;

	User selectById(String userId) throws SQLException;

	int deleteById(String userId) throws SQLException;

	String findId(String userName, String phone);

    void checkPwd(String userId, String userPwd) throws Exception;
}
