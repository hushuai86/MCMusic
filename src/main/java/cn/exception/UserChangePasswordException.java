package cn.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
/**
 * 密码修改异常，通常判断新旧密码的一致性
 * @author liuqiao
 *
 */
public class UserChangePasswordException extends MyExcetion{

	public UserChangePasswordException(String exceptionMessage) {
		super(exceptionMessage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object hander) {
		// TODO Auto-generated method stub
		return null;
	}

}
