package com.ssafy.happyhouse.model.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.domain.HouseLike;
import com.ssafy.happyhouse.dto.user.HouseLikeAddResponse;
import com.ssafy.happyhouse.dto.user.HouseLikeResponse;
import com.ssafy.happyhouse.dto.user.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.user.User;
import com.ssafy.happyhouse.model.mapper.UserMapper;

import static com.ssafy.happyhouse.common.ErrorMessage.*;

@Service
public class UserServiceImpl implements UserService {

	private static final String ADMIN = "admin";
	private static final String USER = "사용자";

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void registerUser(User user) throws SQLException {
		user.setAuthority(USER);
		user.encodePassword(passwordEncoder);
		userMapper.registerUser(user);
	}

	@Override
	public int checkId(String checkId) throws Exception {
		return userMapper.checkId(checkId);
	}

	@Override
	public User login(User user) throws Exception {
		User loginUser;
		if (user.getUserId().equals(ADMIN)) {
			return userMapper.login(user)
					.orElseThrow(() -> new Exception(USER_NOT_FOUND));
		}
		loginUser = userMapper.selectById(user.getUserId())
				.orElseThrow(() -> new Exception(USER_NOT_FOUND));
		validatePassword(user, loginUser.getUserPwd());
		return loginUser;
	}

	@Override
	public String findPwd(User user) throws SQLException {
		return userMapper.findPwd(user);
	}

	@Override
	public int updateUser(String userId, UpdateRequest updateRequest) throws Exception {
		User user = userMapper.selectById(userId)
				.orElseThrow(() -> new Exception(USER_NOT_FOUND));
		user.setUserPwd(updateRequest.getUserPwd());
		user.setUserName(updateRequest.getName());
		user.setEmail(updateRequest.getEmail());
		user.setPhone(updateRequest.getPhone());
		user.encodePassword(passwordEncoder);
		return userMapper.updateUser(user);
	}

	@Override
	public User selectById(String userId) throws Exception {
		return userMapper.selectById(userId)
				.orElseThrow(() -> new Exception(USER_NOT_FOUND));
	}

	@Override
	public void deleteById(String userId) throws Exception {
		if (userMapper.deleteById(userId) == 0) {
			throw new Exception(USER_NOT_FOUND);
		}
	}

	@Override
	public String findId(String userName, String phone) {
		return userMapper.findId(userName, phone);
	}

	@Override
	public void checkPwd(String userId, String userPwd) throws Exception {
		userMapper.checkPwd(userId, userPwd)
				.orElseThrow(() -> new Exception(FALSE_PASSWORD));
	}

    @Override
    public HouseLikeAddResponse addHouseLike(String userId, BigDecimal aptCode) throws Exception {
		int result = userMapper.addHouseLike(new HouseLike(userId, aptCode));
		if (result == 0) {
			throw new Exception(ADD_HOUSE_LIKE_FAIL);
		}
		return userMapper.getLikeId(new HouseLike(userId, aptCode));
	}

    @Override
    public List<HouseLikeResponse> getHouseLikes(String userId) throws Exception {
		List<HouseLikeResponse> houseLikes = userMapper.getHouseLikes(userId);
		if (houseLikes == null) {
			throw new Exception(LOAD_HOUSE_LIKE_FAIL);
		}
		return houseLikes;
    }

	@Override
	public void deleteHouseLike(String userId, BigDecimal likeId) throws Exception {
		userMapper.selectById(userId)
				.orElseThrow(() -> new Exception(USER_NOT_FOUND));
		if (userMapper.deleteHouseLike(likeId) == 0) {
			throw new Exception(DELETE_HOUSE_LIKE_FAIL);
		}
	}

	@Override
	public List<User> getUsers() {
		return userMapper.getUsers();
	}

	@Override
	public void updateUserAuthority(String userId, UpdateRequest updateRequest) throws Exception {
		User user = userMapper.selectById(userId)
				.orElseThrow(() -> new Exception(USER_NOT_FOUND));
		user.setAuthority(updateRequest.getAuthority());
		if (userMapper.updateUserAuthority(user) == 0) {
			throw new Exception(USER_UPDATE_FAIL);
		}
	}

	private void validatePassword(User user, String password) throws Exception {
		if (!passwordEncoder.matches(user.getUserPwd(), password)) {
			throw new Exception(USER_NOT_FOUND);
		}
	}
}
