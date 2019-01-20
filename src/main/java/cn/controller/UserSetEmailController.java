package cn.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.UserEmailActionException;
import cn.exception.UserEmailException;
import cn.exception.UserFormatException;
import cn.exception.UserNotForSessionException;
import cn.pojo.UserPo;
import cn.service.EmailService;
import cn.service.SessionService;

@Controller
public class UserSetEmailController {
	@Autowired
	private EmailService emailservice;
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping("/isNotHavaEmail")
	@ResponseBody
	public boolean isNotHavaEmail(HttpServletRequest request) throws UserFormatException{
		return emailservice.isNotForEmail(request);
	}
	//得到邮箱
	@RequestMapping("/getEmail")
	@ResponseBody
	public UserPo getEmail(HttpServletRequest request) throws UserNotForSessionException{
		return sessionService.getCurrentUserPo(request);
	}
	
	//发送激活码
	@RequestMapping("/sendEmailAction")
	@ResponseBody
	public boolean sendEmailAction(String email,HttpServletRequest request) throws UserFormatException, UserEmailException{
		emailservice.emailValid(email, request);
		return true;
	}
	
	//激活
	@RequestMapping("/emailAction")
	@ResponseBody
	public boolean emailAction(String email,String token,HttpServletRequest request) throws UserEmailActionException{
		return emailservice.emailAction(email, token, request);
	}
}
