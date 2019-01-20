package cn.mapper;

import java.util.List;
import java.util.Map;
import cn.pojo.UserWithSongListPo;
import cn.pojo.vo.UserCollectionSongListVo;

public interface UserWithSongListPoMapper {
    int deleteByPrimaryKey(Integer usonglistid);

    int insert(UserWithSongListPo record);

    int insertSelective(UserWithSongListPo record);

    UserWithSongListPo selectByPrimaryKey(Integer usonglistid);

    int updateByPrimaryKeySelective(UserWithSongListPo record);

    int updateByPrimaryKey(UserWithSongListPo record);
    
    void deleteBySongListIdAndUserId(Map map);
    
    UserWithSongListPo selectBySongListIdAndUserId(Map map);
    
    List<UserCollectionSongListVo> selectUserCollectionSongListVo();
    
    List<UserCollectionSongListVo> selectUserCollectionSongListVoByUserId(int userId);
    
    //根据歌单id删除
    int deleteBySongListId(int songListId);
    
    //查询收藏人数根据songlistId
    int selectBySongListIdForCount(int songListId);
    
}