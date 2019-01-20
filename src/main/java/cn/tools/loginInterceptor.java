package cn.tools;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
import org.springframework.web.method.HandlerMethod;  
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;  

import cn.controller.admin.AdminBeforLoginController;
import cn.controller.admin.AdminController;
public class loginInterceptor extends HandlerInterceptorAdapter{
	/**  
	 * @Description: 登录拦截器  继承拦截器适配器  
	 * @author hushuai 
	 * @date 2014-10-16 下午4:21:49  
	 *  
	 */        
	    private static final Log log = LogFactory.getLog(loginInterceptor.class);  
	  
	    /**   
	     * 在业务处理器处理请求之前被调用   
	     * 如果返回false   
	     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链   
	     *    
	     * 如果返回true   
	     *    执行下一个拦截器,直到所有的拦截器都执行完毕   
	     *    再执行被拦截的Controller   
	     *    然后进入拦截器链,   
	     *    从最后一个拦截器往回执行所有的postHandle()   
	     *    接着再从最后一个拦截器往回执行所有的afterCompletion()   
	     */    
	    @Override  
	    public boolean preHandle(HttpServletRequest request,  
	            HttpServletResponse response, Object handler) throws Exception {           
	        /**  
	         * 这个主要是判断当前连接的是不是业务类的控制器，  
	         * 如果是springmvc静态资源的handler，  
	         * 在转成HandlerMethod时就会报错.  
	         *   
	         */  
	        if(HandlerMethod.class.equals(handler.getClass())){  
	            //获取controller，判断是不是登录前接口的控制器  
	            HandlerMethod method = (HandlerMethod) handler;  
	            Object controller = method.getBean();
	            String classNameString = controller.getClass().getSimpleName();
	          	//判断是否为AdminController 类中的方法      
	            //判断类名是否以Admin开头
	          if(classNameString.startsWith("Admin")){
	          if(controller instanceof AdminBeforLoginController){
	        	  return true;
	           } else{
	                Object admin = request.getSession().getAttribute("admin");  
	                if(admin == null){  
	                  request.getRequestDispatcher("/admin/login.jsp").forward(request, response);   
	                  return false;     
	        }  
	          
	        
	          }  
	            }
	       
	    }
	        return true;
	}  
}

