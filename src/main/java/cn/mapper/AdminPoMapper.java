package cn.mapper;

import java.util.Map;

import cn.pojo.AdminPo;

public interface AdminPoMapper {
    int deleteByPrimaryKey(Integer adminid);

    int insert(AdminPo record);

    int insertSelective(AdminPo record);

    AdminPo selectByPrimaryKey(Integer adminid);

    int updateByPrimaryKeySelective(AdminPo record);

    int updateByPrimaryKey(AdminPo record);
//    账号查询
    AdminPo selectByLoginId(String loginid);
//修改密码
	void updateOldPassWord(Map<String, Object> map);

	Integer selectCount();
    
}