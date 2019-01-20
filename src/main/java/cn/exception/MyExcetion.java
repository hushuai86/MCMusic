package cn.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
/**
 * MyExcetion抽象类，对Exception类进行继承
 * 拓展了Exception
 * 所有该系统自定义异常均对该类进行继承，并注册到异常管理器ExceptionManage中
 * MyExcetion抽象类只有一个抽象方法，即resolveException，异常解析，实现该方法通过参数进行异常情况的解析。自定义ModelAndView的编写
 * @author liuqiao
 *
 */
public abstract class MyExcetion extends Exception{
	/**
	 * 普通代码块
	 * 功能：将所实现该类的子类异常注册到异常管理器中
	 * 存储发送为Class类
	 */
	{
		ExceptionManage.registExcetion(this.getClass());
	}
	
	/**
	 * 
	 * @param request HttpServletRequest：请求参数，传递于spring自定义异常处理器
	 * @param response HttpServletResponse：请求参数，传递于spring自定义异常处理器
	 * @param hander hander出现异常的处理控制器对象，传递于spring自定义异常处理器
	 * @return  返回一个ModelAndView对象，即数据模型和视图，传递给spring自定义异常处理器
	 */
	public abstract ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object hander);
	
	/**
	 * MyExcetion唯一构造方法
	 * @param exceptionMessage 参数：异常信息，通过自定义异常解析，作为参数存储到ModelAndView，最终显示到页面
	 */
	public MyExcetion(String exceptionMessage){
		super(exceptionMessage);
	}
}
