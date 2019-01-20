package cn.exception;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 歌单中歌曲操作异常
 *
 * @author liuqiao
 */
public class SongListAddPutException extends MyExcetion {

    public SongListAddPutException(String exceptionMessage) {
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
