package cn.controller.admin;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.exception.AdminException;
import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpUrlException;
import cn.pojo.PageBasePo;
import cn.pojo.UserPo;
import cn.service.impl.PasswordServiceImpl;
import cn.service.impl.admin.AdminFileServiceImpl;
import cn.service.impl.admin.AdminUserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
	@Autowired
	private AdminUserServiceImpl adminUserServiceImpl;
	@Autowired
	private AdminFileServiceImpl adminFileServiceImpl;
	@Autowired
	private PasswordServiceImpl passwordServiceImpl;
	//用户列表
	@RequestMapping("/adminListUser")
	public String adminListUser(HttpServletRequest request,Integer pageIndex,Model model) {
		String search = request.getParameter("search");
		UserPo userPo = new UserPo();
		if(search!=null){
		     userPo.setUsername(search);
		     userPo.setLoginid(search);
		     userPo.setPhone(search);
		     userPo.setUsersex(search);
		}
		 PageBasePo<UserPo>  pageBasePo = adminUserServiceImpl.filter(userPo,5, pageIndex);
		 model.addAttribute("pageBasePo", pageBasePo);
		 model.addAttribute("search", search);
		 return "admin/user" ;	 
	}
   
	//用户分页ajax
		@RequestMapping("/adminPageUser")
		@ResponseBody
		public PageBasePo<UserPo> adminPageUser(String search,Integer pageIndex){
			UserPo userPo = new UserPo();
			if(search!=null){
				 userPo.setUsername(search);
			     userPo.setLoginid(search);
			     userPo.setPhone(search);
			     userPo.setUsersex(search);
			}
			    PageBasePo<UserPo> pageBasePo = adminUserServiceImpl.filter(userPo, 5, pageIndex);
				return pageBasePo;	
		}
	    //跳转用户信息修改页面
		@RequestMapping("/toAdminUpdateUser")
		public String toAdminUpdateUser(int userId,String type,Model model) throws AdminException{
			    UserPo userPo = adminUserServiceImpl.getUserPoById(userId);
				model.addAttribute("userPo", userPo);
				model.addAttribute("type", type);
			    return "admin/editUser";
		}
		//文件上传
		@RequestMapping("/upUserImage")
		@ResponseBody
		public Map<String, String> upFile( HttpServletRequest request,@RequestParam(value="file") MultipartFile file){        
			//图片上传
			String fileName ="";
			try {
			   fileName=adminFileServiceImpl.FileUp(file, "userHeadImg", request);
			} catch (FileUpUrlException e1){
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileUpSizeOverException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Map<String, String> map =new HashMap<String, String>();
			map.put("token",fileName);
			//返回url给页面
	        return map;
		}
		
		//修改用户信息
		@RequestMapping("/adminUpdateUser")
		public String adminUpdateUser(UserPo userPo,HttpServletRequest request) throws AdminException{	
			    String url = request.getParameter("url");
				String type =request.getParameter("type");	
				//日期格式化
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
				      Date	date = sdf.parse(request.getParameter("date"));
					 //日期封装
				     userPo.setRegistationdate(date);
				     //url封装
				     if(url!=null&&url!=""){
				        userPo.setHeadsculptureurl(url);
				     }
				} catch (ParseException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				userPo.setPassword(passwordServiceImpl.PutPasswordMD5(userPo.getPassword()));
				adminUserServiceImpl.updateUser(userPo);
				if(type.equals("freeze")){
					return "forward:toFreezeUser";			
				}else if(type.equals("email")){
					return "forward:toEmailUser";
				}
				return "forward:adminListUser";
		}
	  //添加用户
		@RequestMapping("/adminAddUser")
		public String adminAddUser(UserPo userPo,HttpServletRequest request) throws AdminException{	
			String url = request.getParameter("url");		
			//日期格式化
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
			      Date	date = sdf.parse(request.getParameter("date"));
				 //日期封装
			     userPo.setRegistationdate(date);
			     //url封装
			     if(url!=null&&url!=""){
			     userPo.setHeadsculptureurl(request.getParameter("url"));
			     }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		   //数据插入
				adminUserServiceImpl.addUser(userPo);
			
			   return "forward:adminListUser";
		}
		//跳转已冻结用户
		@RequestMapping("/toFreezeUser")
		public String toFreezeUser(HttpServletRequest request,Integer pageIndex,Model model) {
			String search = request.getParameter("search");
			UserPo userPo = new UserPo();
			if(search!=null){
			     userPo.setUsername(search);
			     userPo.setLoginid(search);
			     userPo.setPhone(search);
			     userPo.setUsersex(search);
			}
			 PageBasePo<UserPo>  pageBasePo = adminUserServiceImpl.filterFreeze(userPo,5, pageIndex);
			 model.addAttribute("pageBasePo", pageBasePo);
			 model.addAttribute("search", search);
			 return "admin/freezeUser" ;	 
		}
		//已冻结用户分页ajax
				@RequestMapping("/adminPageFreezeUser")
				@ResponseBody
				public PageBasePo<UserPo> adminPageFreezeUser(String search,Integer pageIndex){
					UserPo userPo = new UserPo();
					if(search!=null){
						 userPo.setUsername(search);
					     userPo.setLoginid(search);
					     userPo.setPhone(search);
					     userPo.setUsersex(search);
					}
					    PageBasePo<UserPo> pageBasePo = adminUserServiceImpl.filterFreeze(userPo, 5, pageIndex);
						return pageBasePo;
					
				}
		
		//跳转邮箱激活用户
		@RequestMapping("/toEmailUser")
		public String toEmailUser(HttpServletRequest request,Integer pageIndex,Model model){
			String search = request.getParameter("search");
			UserPo userPo = new UserPo();
			if(search!=null){
			     userPo.setUsername(search);
			     userPo.setLoginid(search);
			     userPo.setPhone(search);
			     userPo.setUsersex(search);
			}
			 PageBasePo<UserPo>  pageBasePo = adminUserServiceImpl.filterEmail(userPo,5, pageIndex);
			 model.addAttribute("pageBasePo", pageBasePo);
			 model.addAttribute("search", search);
			 return "admin/emailUser";	 
		}
		
		//邮箱激活用户分页ajax
		@RequestMapping("/adminPageEmailUser")
		@ResponseBody
		public PageBasePo<UserPo> adminPageEmailUser(String search,Integer pageIndex){
			UserPo userPo = new UserPo();
			if(search!=null){
				 userPo.setUsername(search);
			     userPo.setLoginid(search);
			     userPo.setPhone(search);
			     userPo.setUsersex(search);
			}
			    PageBasePo<UserPo> pageBasePo = adminUserServiceImpl.filterEmail(userPo, 5, pageIndex);
				return pageBasePo;
			
		}
}
