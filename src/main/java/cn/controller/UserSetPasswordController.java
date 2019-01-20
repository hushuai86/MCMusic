package cn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.UserChangePasswordException;
import cn.exception.UserFormatException;
import cn.exception.UserNotForSessionException;
import cn.mapper.UserPoMapper;
import cn.pojo.UserPo;
import cn.service.PasswordService;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.UserServiceImpl;

@Controller
public class UserSetPasswordController {
	@Autowired
	private UserPoMapper mapper;
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	@Autowired
	private PasswordService passwordService;
	//修改密码
	@RequestMapping("/changeUserPassword")
	@ResponseBody
	public boolean changeUserPassword(String oldPassword,String newPassword,HttpServletRequest request) throws UserNotForSessionException{
		String old = passwordService.PutPasswordMD5(oldPassword);//得到加密后的旧密码
		UserPo po = sessionServiceImpl.getCurrentUserPo(request);//得到当前用户对象
		if(po.getPassword().equals(old)){//密码相等执行修改操作
			po.setPassword(passwordService.PutPasswordMD5(newPassword));
			mapper.updateByPrimaryKeySelective(po);
			return true;
		}
		else{
			return false;
		}
	}
}
