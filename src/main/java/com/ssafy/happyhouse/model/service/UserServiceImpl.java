package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void registerUser(User user) throws SQLException {
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
	public int updateUser(User user) throws SQLException {
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
