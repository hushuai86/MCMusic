package cn.mapper;

import java.util.List;
import java.util.Map;

import cn.pojo.UserWithSingerPo;
import cn.pojo.vo.UserCollectionSingerVo;

public interface UserWithSingerPoMapper {
    int deleteByPrimaryKey(Integer usingerid);

    int insert(UserWithSingerPo record);

    int insertSelective(UserWithSingerPo record);

    UserWithSingerPo selectByPrimaryKey(Integer usingerid);

    int updateByPrimaryKeySelective(UserWithSingerPo record);

    int updateByPrimaryKey(UserWithSingerPo record);
    
    List<UserWithSingerPo> selectUserWithSingerPoByUserId(int userId);
    
    //查询singerPo
    List<UserCollectionSingerVo> getSingerUserWithSingerVo(int userId);
    
    //查询所有
    List<UserCollectionSingerVo> getAllSingerUserWithSingerVos();
    
    //取消收藏根据userId singerId
    int deleteByUserIdAndSingerId(Map map);
    

    //根据singerId ,userId查关联表
    UserWithSingerPo selectBySingerIdAndUserId(Map<String,Object> map);
    
    
}