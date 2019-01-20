package cn.mapper;

import java.util.List;
import java.util.Map;

import cn.pojo.UserWithCDPo;
import cn.pojo.vo.UserCollectionCDVo;

public interface UserWithCDPoMapper {
    int deleteByPrimaryKey(Integer ucdid);

    int insert(UserWithCDPo record);

    int insertSelective(UserWithCDPo record);

    UserWithCDPo selectByPrimaryKey(Integer ucdid);

    int updateByPrimaryKeySelective(UserWithCDPo record);

    int updateByPrimaryKey(UserWithCDPo record);
    
    //根据用户查询CDPo
    List<UserCollectionCDVo> getUserWithCDVos(int userId);
    
    //查询所有
    List<UserCollectionCDVo> getAllUserWithCDVos();
    
    //根据CDId查询 用户CD
    UserWithCDPo selectByCDId(int CDId);
    
    //根据CDId userId 取消收藏
    int deleteByCDIdAndUserId(Map map);
    
    UserWithCDPo selectByUserIdAndcdId(Map map);
}