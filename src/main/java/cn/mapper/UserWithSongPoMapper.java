package cn.mapper;

import java.util.List;
import java.util.Map;

import cn.pojo.UserWithSongPo;
import cn.pojo.vo.UserCollectionSongVo;

public interface UserWithSongPoMapper {
    int deleteByPrimaryKey(Integer usongid);

    int insert(UserWithSongPo record);

    int insertSelective(UserWithSongPo record);

    UserWithSongPo selectByPrimaryKey(Integer usongid);

    int updateByPrimaryKeySelective(UserWithSongPo record);

    int updateByPrimaryKey(UserWithSongPo record);
    
    UserWithSongPo selectBySongId(int songid);
    
    UserWithSongPo selectByUserId(int userId);
    
    UserWithSongPo selectByUserIdAndSongId(Map map);
    
    int deleteByUserIdAndSongId(Map map);
    
    List<UserCollectionSongVo> selectUserCollectionSongVo();
    
    List<UserCollectionSongVo> selectUserCollectionSongVoByUserId(int userid);
}