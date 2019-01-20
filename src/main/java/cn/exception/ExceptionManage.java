package cn.exception;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * ExceptionManage类，自定义异常管理器
 * 对于该系统所有的自定义异常进行统一的管理，与spring的异常统一处理器相对应
 * @author liuqiao
 */
public class ExceptionManage {
	/**
	 * 异常容器
	 * 使用散列表，避免重复。存储方式为<类名(String),类(Class)>
	 * 每个自定义异常实例化前都会进行注册到该容器
	 */
	private static Map<String, Class<? extends MyExcetion>> exceptionList = new HashMap<String, Class<? extends MyExcetion>>();
	
	/**
	 * 异常注册方法，将异常注册到容器，方便统一管理
	 * 每个自定义异常实例化前都会进行该方法注册到该容器
	 * @param exception
	 */
	public static void registExcetion(Class<? extends MyExcetion> exception){
		exceptionList.put(exception.getName(), exception);
	}
	
	/**
	 * 通过异常容器解析values返回一个异常列表
	 * @return list对象，返回所有的异常类list<Class>
	 */
	public static Collection<Class<? extends MyExcetion>> getExceptionClassList(){
		return exceptionList.values();
	}
	
	/**
	 * 通过异常容器解析keys返回一个异常名称列表
	 * @return list对象，返回所有的异常名称list<String>
	 */
	public static Collection<String> getExceptionNameList(){
		return exceptionList.keySet();
	}
}
