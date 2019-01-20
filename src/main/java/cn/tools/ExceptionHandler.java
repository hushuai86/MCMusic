package cn.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.exception.ExceptionManage;
import cn.exception.MyExcetion;
/**
 * 自定义异常处理器
 * @author liuqiao
 *
 */
public class ExceptionHandler implements HandlerExceptionResolver{
	/**
	 * 参数：
	 * request请求对象
	 * response响应对象
	 * hander出现异常的处理控制器对象
	 * exception异常对象
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object hander, Exception exception) {
		
		ModelAndView modelAndView = new ModelAndView("error"); 
		//1.处理自定义异常
		/*for(Class<? extends MyExcetion> clas:ExceptionManage.getExceptionClassList()){
			//判断是否是该class的实例，相当于动态instanceof
			if(clas.isInstance(exception)){
				//通过cast方法进行类型转化Exception-MyException,调用resolveException进行处理自定义modelAndView
				modelAndView = clas.cast(exception).resolveException(request, response, hander);
			}
		}*/
		//2.处理系统异常
		/*if(modelAndView == null){
			if(exception instanceof Exception){
				//自定义modelAndView编写，处理系统异常
				modelAndView = new ModelAndView("redirect:/error.htm"); 
				//自定义编写
				//....一般跳转到统一一个错误页面
			}
		}*/
		return modelAndView;
	}

}
