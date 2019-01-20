package cn.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class AdminController {
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

	// 管理员登录
	@RequestMapping("/adminLogin")
	public String adminLogin( Model model,HttpServletRequest request) throws AdminException {	
		model.addAttribute("admin", adminSessionServiceImpl.getCurrentAdminPo(request));
		return "admin/index";
	}
	 //首页数据显示
		@RequestMapping("/adminIndex")
		@ResponseBody
		public Map<String,Object> adminIndex( Model model,HttpServletRequest request) throws AdminException {
			int userCount = adminUserServiceImpl.getUserCount();
			int songCount = adminSongServiceImpl.getShowSongCount();
			int songListCount = adminSongListServiceImpl.getShowSongListCount();
			int singerCount = adminSingerServiceImpl.getShowSingerCount();
			int cdCount = adminCDServiceImpl.getShowCDCount();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("songCount", songCount);
			map.put("songListCount", songListCount);
			map.put("singerCount", singerCount);
			map.put("cdCount", cdCount);
			map.put("userCount", userCount);
			return map;
		}	
	// 跳转管理员信息修改页面
	@RequestMapping("/toAdminUpdate")
	public String toAdminUpdate(HttpServletRequest request,Model model) throws AdminException{
		AdminPo adminPo = adminSessionServiceImpl.getCurrentAdminPo(request);
		model.addAttribute("admin", adminPo);
		return "admin/admin";
	}
	
	// 管理员信息修改
		@RequestMapping("/adminUpdateInfo")
		@ResponseBody
		public String adminUpdateInfo(HttpServletRequest request,int adminid,String loginid,String adminname) throws AdminException {
			AdminPo adminPo =new AdminPo();
			adminPo.setAdminid(adminid);
			adminPo.setLoginid(loginid);
			adminPo.setAdminname(adminname);
			adminServiceImpl.changeAdminInfo(adminPo);
			adminSessionServiceImpl.setcurrentAdminPo(request, adminServiceImpl.getAdminById(adminid));
			return "";
		}
		
	// 管理员密码修改
	@RequestMapping("/adminUpdatePwd")
	@ResponseBody
	public Map<String,Object> adminUpdatePwd(HttpServletRequest request,String newPassword,String oldPassword) throws AdminException {
		Map<String,Object> map = new HashMap<String,Object>();
		 String msg;
		 int code=0;
		 if(adminServiceImpl.changeAdminPassword(newPassword, oldPassword, request)){
			code=1;
			msg="adminLogout";
		 }else{
			 msg="密码错误";
		 }
		 map.put("code",code);
		 map.put("msg",msg);
		return map;
		}

   
}
