package cn.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.exception.AdminException;
import cn.pojo.AdminPo;
import cn.service.impl.admin.AdminCDServiceImpl;
import cn.service.impl.admin.AdminServiceImpl;
import cn.service.impl.admin.AdminSessionServiceImpl;
import cn.service.impl.admin.AdminSingerServiceImpl;
import cn.service.impl.admin.AdminSongListServiceImpl;
import cn.service.impl.admin.AdminSongServiceImpl;
import cn.service.impl.admin.AdminUserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminBeforLoginController {
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	@Autowired
	private AdminSessionServiceImpl adminSessionServiceImpl;
	@Autowired
	private AdminSongServiceImpl adminSongServiceImpl;
	@Autowired
	private AdminSongListServiceImpl adminSongListServiceImpl;
	@Autowired
	private AdminCDServiceImpl adminCDServiceImpl;
	@Autowired
	private AdminSingerServiceImpl adminSingerServiceImpl;
	@Autowired
	private AdminUserServiceImpl adminUserServiceImpl;

	// 管理员首次登陆判断
		@RequestMapping("/adminAjax")
		@ResponseBody
		public Map<String,Object>  adminAjax() throws AdminException {
		    int result = adminServiceImpl.getAdminCount();
		    Map<String,Object> map = new HashMap<String,Object>();
		    map.put("code", result);
		    if(result==0){
				map.put("msg", "第一次登录请设置账号和密码");
			}
			return map;
		}
		// 管理员注册
		@RequestMapping("/adminRegistAjax")
		@ResponseBody
	public String adminRegistAjax(HttpServletRequest request,String loginid,String password) throws AdminException {
			AdminPo adminPo =adminServiceImpl.adminRegist(loginid, password);
			adminSessionServiceImpl.setcurrentAdminPo(request, adminPo);
			return "adminLogin";
		}
	// 管理员ajax登录验证
	@RequestMapping("/adminLoginAjax")
	@ResponseBody
	public Map<String,Object>  adminLoginAjax(HttpServletRequest request,String loginid,String password) throws AdminException {
		AdminPo adminPo = adminServiceImpl.adminLogin(loginid, password);
		adminSessionServiceImpl.setcurrentAdminPo(request, adminPo);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer code=0;
		String msg;
		if(adminPo!=null){
			code=1;
			msg="adminLogin";	
		}else{
			msg="账号不存在或密码错误";
		}
		map.put("msg",msg);
		map.put("code",code);
		return map;
	}
	// 退出登录
	@RequestMapping("/adminLogout")
	public String adminLogout(HttpServletRequest request) {
		adminServiceImpl.adminLogout(request);
		return "admin/login";
	}

   
}
