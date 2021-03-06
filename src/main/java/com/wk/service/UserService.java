package com.wk.service;

import com.wk.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wk
 * @since 2019-09-09
 */
public interface UserService extends IService<User> {

	//自定义方法和mybatis一样写
	User getUserById(int id);
}
