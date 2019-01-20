package cn.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.UserFormatException;
import cn.exception.UserLoginStateException;
import cn.exception.UserNotExistException;
import cn.exception.UserNotForSessionException;
import cn.exception.UserPasswordException;
import cn.pojo.UserPo;
import cn.service.PasswordService;
import cn.service.impl.PasswordServiceImpl;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.UserServiceImpl;

@Controller
public class RegistController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	
	@RequestMapping("/getUser")
	@ResponseBody
	public UserPo getUser(HttpServletRequest request) throws UserNotForSessionException{
		return sessionServiceImpl.getCurrentUserPo(request);
	}
	
	
	//是否注册
	@RequestMapping("/isRegister")
	@ResponseBody
	public boolean isRegister(String loginid){
		if(userServiceImpl.isRegistForLoginId(loginid)){
			return false;
		}
		return true;
	}
	
	//注册
	@RequestMapping("/register")
	@ResponseBody
	public  boolean register(UserPo user){
		System.out.println(user);
		//判断是否已注册
		if(userServiceImpl.isRegistForLoginId(user.getLoginid())){
			return false;
		}
	   try {
		userServiceImpl.regist(user);
	} catch (UserFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
	}
	   return true;
	}
	
	//登录
	@RequestMapping("/login")
	@ResponseBody
	public  UserPo login(String loginId, String password,HttpServletRequest request){
		UserPo userPo = new UserPo();
		try {  
				userPo=userServiceImpl.login(loginId, password);
				//把userPo存到session中
				sessionServiceImpl.setcurrentUserPo(request, userPo);
				
		} catch (UserPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserLoginStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userPo;
	}
	
	//退出登录
	@RequestMapping("/userLogout")
	@ResponseBody
	public void  logout(HttpServletRequest request) throws IOException{
       userServiceImpl.userLogout(request);
	}
}
