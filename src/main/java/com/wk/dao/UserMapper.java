package com.wk.dao;

import com.wk.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wk
 * @since 2019-09-09
 */
public interface UserMapper extends BaseMapper<User> {

	//自定义方法和mybatis一样写
	User getUserById(int id);
}
