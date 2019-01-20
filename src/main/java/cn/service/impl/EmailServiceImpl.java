package cn.service.impl;

import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.exception.UserEmailActionException;
import cn.exception.UserEmailException;
import cn.exception.UserFormatException;
import cn.mapper.UserPoMapper;
import cn.pojo.UserPo;
import cn.service.AsyncSerivce;
import cn.service.EmailService;
import cn.tools.EmailHandler;
@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private AsyncSerivce service;
	@Autowired
	private UserPoMapper mapper;
	@Override
	public void emailValid(String email, HttpServletRequest request)
			throws UserFormatException, UserEmailException{
		// TODO Auto-generated method stub
		//1.验证邮箱
		//邮箱正则表达式
		String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		Pattern p = Pattern.compile(RULE_EMAIL);
	    Matcher m = p.matcher(email); //正则表达式的匹配器
	    boolean emailFlag=  m.matches();//得到匹配结果
	    //邮箱验证,不是正确邮箱抛异常
	    if(!emailFlag){ 
	    	throw new UserFormatException("邮箱格式异常");
	    }
		//2.生成验证码
		String token = UUID.randomUUID().toString(); //通过uuid来得到唯一的验证码
		HttpSession session = request.getSession(); //通过request得到session对象
		session.setAttribute("token", token);//存储验证码到session中
		try { //使用异步方法，设置过期时间
			service.asyncRemoveToken(1000*60*5,request);//设置5分钟内过期
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//3.发送验证码邮件
	    //创建连接对象 连接到邮件服务器
	    try {
			EmailHandler.sendEmail(email,"欢迎使用MC在线音乐网站，邮箱激活码："+token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isNotForEmail(HttpServletRequest request)
			throws UserFormatException {
		UserPo po = (UserPo) request.getSession().getAttribute("user");
		if(po==null)return true;
		if(po.getUserstateid()==0){
			return true; //为空
		}else if(po.getUserstateid()==1){
			return false;
		}else{
			throw new UserFormatException("用户状态错误");
		}

		
	}

	@Override
	public boolean isEmailAction(String token, HttpServletRequest request) {
		String t = (String) request.getSession().getAttribute("token");
		if(t==null) return false;
		if(t.equals(token)){
			return true;
		}
		return false;
	}

	@Override
	public boolean emailAction(String email, String token,
			HttpServletRequest request) throws UserEmailActionException {
		if(isEmailAction(token, request)){//激活成功
			//设置到当前
			UserPo po = (UserPo) request.getSession().getAttribute("user");
			po.setEmail(email);
			po.setUserstateid(1);//修改用户状态
			request.getSession().setAttribute("user", po);
			//设置到数据库
			mapper.updateByPrimaryKeySelective(po);
			return true;
		}else{
			return false;
		}
		
	}


}
