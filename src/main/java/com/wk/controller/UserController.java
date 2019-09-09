package com.wk.controller;


import com.wk.pojo.User;
import com.wk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wk
 * @since 2019-09-09
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("list")
	public String list(Model model){
		model.addAttribute("user",userService.list());
		return "list";
	}

	@RequestMapping("getUser")
	@ResponseBody
	public User getUser(Integer id){
		return userService.getById(id);
	}
}
