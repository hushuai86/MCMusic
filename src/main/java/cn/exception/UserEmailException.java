package cn.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * 邮件发送异常
 * @author liuqiao
 *
 */
public class UserEmailException extends MyExcetion{

	public UserEmailException(String exceptionMessage) {
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
