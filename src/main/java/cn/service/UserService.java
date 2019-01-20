package cn.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpTypeException;
import cn.exception.FileUpUrlException;
import cn.exception.UserChangePasswordException;
import cn.exception.UserLoginStateException;
import cn.exception.UserNotExistException;
import cn.exception.UserPasswordException;
import cn.exception.UserFormatException;
import cn.pojo.UserPo;
/**
 * 用户服务接口
 * @author liuqiao
 *
 */
public interface UserService {
	/**
	 * 判断用户账号是否进行注册
	 * @param loginId 用户账号
	 * @return 返回一个bool值，如果已经进行注册返回true，没有则返回fasle
	 */
	public boolean isRegistForLoginId(String loginId);
	
	/**
	 * 用户登录接口，接受账号和密码，返回一个实体对象
	 * 1判断账号是否存在，可以使用isRegistForLoginId(String loginId)方法
	 * 2得到该账号实体
	 * 3判断密码是否正确
	 * 4判断状态 0 正常，1表示邮箱激活，2表示注销 
	 * 5正确后存储到session中
	 * @param loginId  用户账号
	 * @param password  用户密码
	 * @return 返回一个用户实体，用于登录成功后存储到session中
	 * @throws UserPasswordException 密码错误异常
	 * @throws UserNotExistException 账号不存在异常
	 * @throws UserLoginStateException 登录状态异常，即状态为2
	 */
	public UserPo login(String loginId,String password)throws UserPasswordException,UserNotExistException
		,UserLoginStateException;
	
	/**
	 * 用户注册，通过页面输入信息对象用户进行注册（先通过页面js验证，然后再该方法进行后台验证）
	 * 1需要后台进行格式校验
	 * 2写入数据库
	 * @param user 注册传递输入信息所组成的实体对象
	 * @throws UserFormatException 信息格式异常
	 */
	public void regist(UserPo user)throws UserFormatException;
	
	/**
	 * 修改用户密码
	 * 1判断旧密码是否正确
	 * 2校验密码的格式
	 * 3正确后修改新密码
	 * @param newPassword 新密码
	 * @param oldPassWord 旧密码
	 * @throws UserChangePasswordException 密码不一致
	 * @throws UserFormatException 信息格式异常
	 */
	public void changeUserPassword(String newPassword,String oldPassWord,HttpServletRequest request)throws UserChangePasswordException,UserFormatException;
	
	/**
	 * 对其他的通用用户信息进行修改
	 * 需要进行用户校验
	 * @param user 需要修改的用户信息
	 * @throws UserFormatException 信息格式错误异常
	 */
	public void changeUserInfo(UserPo user)throws UserFormatException;
	
	/**
	 * 上传用户头像
	 * 1在controller中得到头像图片文件
	 * 2大小检验
	 * 3地址检验
	 * 4类型检验
	 * 5保存到路径，使用file服务
	 * 6存储到数据库
	 * @param file 上传的头像文件
	 * @param url 保存地址例如：“/img”
	 * @throws FileUpUrlException 文件地址异常
	 * @throws FileUpSizeOverException 文件大小超过限制
	 * @throws FileUpTypeException 文件类型异常
	 */
	public void SetHeadImg(MultipartFile file,String url)throws FileUpUrlException,FileUpSizeOverException,FileUpTypeException;

	//退出登录
	public void userLogout(HttpServletRequest request);
}
