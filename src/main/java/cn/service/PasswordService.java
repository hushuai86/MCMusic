package cn.service;
/**
 * 使用MD5算法进行加密解密服务接口
 * @author liuqiao
 *
 */
public interface PasswordService {
	/**
	 * 进行MD5算法加密
	 * @param password 加密密码
	 * @return 返回一个加密后的字符串
	 * 通过该加密后存储到数据库，判断密码是否相等
	 */
	public String PutPasswordMD5(String password);

}
