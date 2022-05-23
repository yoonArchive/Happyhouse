package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.dto.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.model.mapper.UserMapper;

import static com.ssafy.happyhouse.common.ErrorMessage.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

	private static final String USER = "사용자";

	@Autowired
	private UserMapper userMapper;

	@Override
	public void registerUser(User user) throws SQLException {
		user.setAuthority(USER);
		userMapper.registerUser(user);
	}

	@Override
	public int idCheck(String checkId) throws Exception {
		return userMapper.idCheck(checkId);
	}

	@Override
	public User login(User user) throws SQLException {
		return userMapper.login(user);
	}

	@Override
	public String findPwd(User user) throws SQLException {
		return userMapper.findPwd(user);
	}

	@Override
	public int updateUser(String userId, UserUpdateRequest userUpdateRequest) throws Exception {
		User user = userMapper.selectById(userId);
		if (user == null) {
			throw new Exception(USER_NOT_FOUND);
		}
		user.setUserPwd(userUpdateRequest.getUserPwd());
		user.setUserName(userUpdateRequest.getName());
		user.setEmail(userUpdateRequest.getEmail());
		user.setPhone(userUpdateRequest.getPhone());
		return userMapper.updateUser(user);
	}

	@Override
	public User selectById(String userId) throws SQLException {
		return userMapper.selectById(userId);
	}

	@Override
	public int deleteById(String userId) throws SQLException {
		return userMapper.deleteById(userId);
	}

	@Override
	public String findId(String userName, String phone) {
		return userMapper.findId(userName, phone);
	}
}
