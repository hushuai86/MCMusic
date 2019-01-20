package cn.service.admin;

import cn.exception.AdminException;
import cn.pojo.AdminPo;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台管理员服务接口
 *
 * @author hushuai
 */
public interface AdminService {
    /**
     * 修改管理员信息
     *
     * @param AdminPo adminPo
     * @return true or false
     */
    public boolean changeAdminInfo(AdminPo admin) throws AdminException;

    /**
     * 管理员登录
     *
     * @param loginId,password
     * @return adminPo
     */
    public AdminPo adminLogin(String loginId, String password) throws AdminException;

    /**
     * 注销
     *
     * @param AdminPo adminPo
     */
    public void adminLogout(HttpServletRequest request);

    /**
     * 修改密码
     *
     * @return
     */
    public boolean changeAdminPassword(String newPassword, String oldPassword,
                                       HttpServletRequest request) throws AdminException;

    /**
     * 判断是否存在管理员
     */
    public Integer getAdminCount();

    /**
     * 注册
     */
    AdminPo adminRegist(String loginid, String password) throws AdminException;


}
