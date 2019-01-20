package cn.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * 登录状态异常
 * @author Administrator
 *
 */
public class UserLoginStateException extends MyExcetion{

	public UserLoginStateException(String exceptionMessage) {
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
