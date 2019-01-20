package cn.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
/**
 * 管理员异常
 * @author hushuai
 *
 */
public class AdminException extends MyExcetion{

	public AdminException(String exceptionMessage) {
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
