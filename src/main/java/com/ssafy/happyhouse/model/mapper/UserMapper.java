package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import com.ssafy.happyhouse.dto.User;

public interface UserMapper {

	void registerUser(User user) throws SQLException;

	int idCheck(String checkId) throws Exception;

	User login(User user) throws SQLException;

	String findPwd(User user) throws SQLException;

	int updateUser(User user) throws SQLException;

	User selectById(String userId) throws SQLException;

	int deleteById(String userId) throws SQLException;

	String findId(String userName, String phone);
}
