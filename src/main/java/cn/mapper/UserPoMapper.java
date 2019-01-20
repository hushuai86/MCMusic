package cn.mapper;

import java.util.List;
import java.util.Map;

import cn.pojo.UserPo;
import cn.pojo.vo.UserCollectionCDVo;


public interface UserPoMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    UserPo selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);
    
    //查询所用用户
    List<UserPo> selectUsers();
    
    //根据用户名查询
    UserPo selectByUserName(String userName);
    

	UserPo selectByLoginId(String loginid);
	
	//修改密码
	int updateOldPassWord(Map<String,Object> map);
	
	 //通过username模糊查询 userpo
    List<UserPo> selectUserPoByUserNameForPagination(Map map);
    //查询  通过username模糊查询userpo 的条数
    int selectCountUserPoByUserNameForPagination(Map map);
    
    //查询总用户数
	int selectUserCount();

	List<UserPo> selectUserPoByUserNameForPaginationEmail(Map map);

	int selectCountUserPoByUserNameForPaginationEmail(Map map);

	List<UserPo> selectUserPoByUserNameForPaginationFreeze(Map map);

	int selectCountUserPoByUserNameForPaginationFreeze(Map map);
}