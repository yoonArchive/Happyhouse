package com.ssafy.happyhouse.model.mapper;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.ssafy.happyhouse.domain.HouseLike;
import com.ssafy.happyhouse.dto.user.HouseLikeResponse;
import com.ssafy.happyhouse.dto.user.User;

public interface UserMapper {

	void registerUser(User user) throws SQLException;

	int checkId(String checkId) throws Exception;

	User login(User user) throws SQLException;

	String findPwd(User user) throws SQLException;

	int updateUser(User user) throws SQLException;

	Optional<User> selectById(String userId) throws SQLException;

	int deleteById(String userId) throws SQLException;

	String findId(String userName, String phone);

    Optional<User> checkPwd(String userId, String userPwd);

    int addHouseLike(HouseLike houseLike);

	List<HouseLikeResponse> getHouseLikes(String userId);

	int deleteHouseLike(BigDecimal likeId);
}
