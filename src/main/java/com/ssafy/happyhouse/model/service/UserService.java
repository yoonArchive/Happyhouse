package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.dto.user.HouseLikeAddResponse;
import com.ssafy.happyhouse.dto.user.HouseLikeResponse;
import com.ssafy.happyhouse.dto.user.User;
import com.ssafy.happyhouse.dto.user.UpdateRequest;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface UserService {

	void registerUser(User user) throws SQLException;

	int checkId(String checkId) throws Exception;

	User login(User user) throws Exception;

	String findPwd(User user) throws SQLException;

	int updateUser(String user, UpdateRequest updateRequest) throws SQLException, Exception;

	User selectById(String userId) throws Exception;

	int deleteById(String userId) throws SQLException;

	String findId(String userName, String phone);

    void checkPwd(String userId, String userPwd) throws Exception;

    HouseLikeAddResponse addHouseLike(String userId, BigDecimal aptCode) throws Exception;

	List<HouseLikeResponse> getHouseLikes(String userId) throws Exception;

	void deleteHouseLike(String userId, BigDecimal likeId) throws Exception;

	List<User> getUsers();
}
