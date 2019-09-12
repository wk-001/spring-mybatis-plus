package com.wk.service.impl;

import com.wk.pojo.User;
import com.wk.dao.UserMapper;
import com.wk.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wk
 * @since 2019-09-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUserById(int id) {
		return userMapper.getUserById(id);
	}
}
