package cn.service;

import javax.servlet.http.HttpServletRequest;
import cn.exception.UserEmailActionException;
import cn.exception.UserEmailException;
import cn.exception.UserFormatException;
/**
 * 邮箱服务接口
 * @author liuqiao
 *
 */
public interface EmailService {
	/**
	 * 判断邮箱是否为空，由于密码找回涉及邮箱操作，所有要督促用户进行邮箱激活
	 * 即读取数据库用户状态如果为0，后续提示用户进行邮箱激活
	 * 为1（已经激活）这不需要提示激活
	 * @param email 邮箱字符串
	 * @param request 通过请求得到session对象来得到当前user对象
	 * @return 返回一个bool值，为空true，不为空false
	 * @throws UserFormatException 信息格式异常
	 */
	public boolean isNotForEmail(HttpServletRequest request)throws UserFormatException;
	
	/**
	 * 邮箱激活验证,点击激活验证
	 * 1.验证邮箱
	 * 2.生成验证码，存储到session
	 * 3.方式验证码到邮箱
	 * @param email 用户请求激活的邮箱
	 * @param request 通过请求得到session对象
	 * @throws UserFormatException 信息格式异常
	 * @throws UserEmailException 邮件发送异常
	 */
	public void emailValid(String email,HttpServletRequest request)throws UserFormatException,UserEmailException;
	
	/**
	 * 邮箱判断是否是可激活状态
	 * 用户输入的验证码和session中保存的验证进行比较
	 * @param token 验证码
	 * @param request 通过请求得到session对象
	 * @return 一个bool值，判断正确true，错误fasle
	 */
	public boolean isEmailAction(String token,HttpServletRequest request);
	
	/**
	 * 邮箱激活提交
	 * 输入邮箱，验证码，点击激活提交
	 * 1调用isEmailAction(String token)判断验证码是否一致
	 * 2写入数据库
	 * @param email 邮箱
	 * @param token 验证码
	 * @param request 通过请求得到session对象
	 * @throws UserEmailActionException 邮箱激活失败异常
	 */
	public boolean emailAction(String email,String token,HttpServletRequest request)throws UserEmailActionException;
	
}
