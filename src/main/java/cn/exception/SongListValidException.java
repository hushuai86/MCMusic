package cn.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * 歌单字段验证异常
 * @author liuqiao
 *
 */
public class SongListValidException extends MyExcetion{

	public SongListValidException(String exceptionMessage) {
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
