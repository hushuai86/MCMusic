package cn.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpTypeException;
import cn.exception.FileUpUrlException;
import cn.exception.UserChangePasswordException;
import cn.exception.UserFormatException;
import cn.exception.UserLoginStateException;
import cn.exception.UserNotExistException;
import cn.exception.UserNotForSessionException;
import cn.exception.UserPasswordException;
import cn.mapper.UserPoMapper;
import cn.pojo.UserPo;
import cn.service.UserService;
/**
 * 用户实现接口
 * @author TangMei
 *
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserPoMapper mapper;
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	@Autowired
	private PasswordServiceImpl passwordServiceImpl;
	@Autowired
	private FileServiceImpl fileServiceImpl;
     //判断用户账号是否进行注册
	@Override
	public boolean isRegistForLoginId(String loginId) {
		//根据loginId获取user
		UserPo user = mapper.selectByLoginId(loginId);
		//判断user是否存在
		if(user==null){
			return false;
		}
		return true;
	}
	
	//用户登录接口，接受账号和密码，返回一个实体对象
	@Override
	public UserPo login(String loginId, String password)
			throws UserPasswordException, UserNotExistException,
			UserLoginStateException {
		
		//密码MD5加密
		password=passwordServiceImpl.PutPasswordMD5(password);
		//根据loginId获取user
		UserPo user = mapper.selectByLoginId(loginId);
		//判断user是否存在
		if(user!=null){
			//判断user密码是否匹配
			
			if(user.getPassword().equals(password)){
				//判断user状态是否异常
				if (user.getUserstateid() != 2) {
					return user;
				}else {
					throw new UserLoginStateException("用户状态异常...");
				}
			}else {
				throw new UserPasswordException("用户密码错误...");
			}
		}else {
			throw new UserNotExistException("用户不存在...");
		}
	}
     
	
	//用户注册，通过页面输入信息对象用户进行注册
	@Override
	public void regist(UserPo user) throws UserFormatException {
		// TODO Auto-generated method stub
		user.setRegistationdate(new Date());
		//md5密码加密
		  user.setPassword(passwordServiceImpl.PutPasswordMD5(user.getPassword()));
		//添加数据
		mapper.insertSelective(user);
	}
   
	//修改用户密码
	@Override
	public void changeUserPassword(String newPassword, String oldPassWord,HttpServletRequest request)
			throws UserChangePasswordException, UserFormatException {
		
		// TODO Auto-generated method stub
		//获取userPo	
		UserPo po = new UserPo();
		try {
			po =sessionServiceImpl.getCurrentUserPo(request);
		} catch (UserNotForSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserPo userPo = mapper.selectByLoginId(po.getLoginid());
		//密码MD5加密
		oldPassWord= passwordServiceImpl.PutPasswordMD5(oldPassWord);
		newPassword = passwordServiceImpl.PutPasswordMD5(newPassword);
		//判断密码是否匹配
		if (userPo.getPassword().equals(oldPassWord)) {
			//修改密码
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("loginId", po.getLoginid());
			map.put("password", newPassword);
			mapper.updateOldPassWord(map);
		}else {
			throw new UserChangePasswordException("密码不一致");
		}
		
		
	}
    
	//对其他的通用用户信息进行修改
	@Override
	public void changeUserInfo(UserPo user) throws UserFormatException {
		// TODO Auto-generated method stub
	    
        //信息修改
		mapper.updateByPrimaryKeySelective(user);
		
	}
    
	//用户头像上传
	@Override
	public void SetHeadImg(MultipartFile file, String url)
			throws FileUpUrlException, FileUpSizeOverException,
			FileUpTypeException {
		  
		  // TODO Auto-generated method stub
		
	}
	//退出登录
	@Override
	public void userLogout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("user");
	}


}
