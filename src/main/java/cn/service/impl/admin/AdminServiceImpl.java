package cn.service.impl.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.exception.AdminException;
import cn.mapper.AdminPoMapper;
import cn.pojo.AdminPo;
import cn.service.admin.AdminService;
import cn.service.impl.PasswordServiceImpl;

@Service
public class AdminServiceImpl  implements AdminService{
@Autowired
private AdminPoMapper mapper;
@Autowired
private PasswordServiceImpl passwordServiceImpl;
@Autowired
private AdminSessionServiceImpl adminSessionServiceImpl;
	@Override
	public boolean changeAdminInfo(AdminPo admin) throws AdminException{
		// TODO Auto-generated method stub
		try {
			 mapper.updateByPrimaryKeySelective(admin);
		} catch (Exception e) {
			throw new AdminException("修改失败");           
		} 
		  return true;
	}
	//管理员登录接口，接受账号和密码，返回一个实体对象
		@Override
		public AdminPo adminLogin(String loginId, String password)
				throws AdminException{
			//密码MD5加密
		      password=passwordServiceImpl.PutPasswordMD5(password);
			//根据loginId获取user
			AdminPo admin = mapper.selectByLoginId(loginId);
			//判断user是否存在
			if(admin!=null){
				//判断user密码是否匹配
				if(admin.getPassword().equals(password)){
					//判断user状态是否异常
						return admin;
				}else {
				     return null;
				  }
			}else {
				return null;
			}
		}
		//修改用户密码
		@Override
		public boolean changeAdminPassword(String newPassword, String oldPassword,HttpServletRequest request)
				throws AdminException {
			// TODO Auto-generated method stub
			//获取userPo	
			AdminPo adminPo = new AdminPo();
			adminPo =adminSessionServiceImpl.getCurrentAdminPo(request);
			//密码MD5加密
			oldPassword= passwordServiceImpl.PutPasswordMD5(oldPassword);
			newPassword = passwordServiceImpl.PutPasswordMD5(newPassword);
			//判断密码是否匹配
			if (adminPo.getPassword().equals(oldPassword)) {
				//修改密码
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("loginId", adminPo.getLoginid());
				map.put("password", newPassword);
				mapper.updateOldPassWord(map);
				return true;
			}else {
				return false;
			}
			
			
		}	
		@Override
		public Integer getAdminCount(){
		  return mapper.selectCount();
		}
		
		//管理员注册，通过页面输入信息对象用户进行注册
		@Override
		public AdminPo adminRegist(String loginid,String password) throws AdminException{
			// TODO Auto-generated method stub
			
			//md5密码加密
			password=passwordServiceImpl.PutPasswordMD5(password);
			AdminPo adminPo = new AdminPo();
			adminPo.setAdminname("ADMIN");
			adminPo.setLoginid(loginid);
			adminPo.setPassword(password);
			  
			//添加数据
			mapper.insertSelective(adminPo);
			return adminPo;
		}
	   
	//退出登录
	@Override
	public void adminLogout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("admin", null);
	}
	//查询adminPo通过adminid
	public AdminPo getAdminById(int adminid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(adminid);
	}
}
