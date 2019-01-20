package cn.mapper;

import java.util.List;
import java.util.Map;

import cn.pojo.SongListWithSongPo;

public interface SongListWithSongPoMapper {
    int deleteByPrimaryKey(Integer slsongid);

    int insert(SongListWithSongPo record);

    int insertSelective(SongListWithSongPo record);

    SongListWithSongPo selectByPrimaryKey(Integer slsongid);

    int updateByPrimaryKeySelective(SongListWithSongPo record);

    int updateByPrimaryKey(SongListWithSongPo record);
    
    //根据歌曲ID查询歌单
    SongListWithSongPo selectBySongId(int songId);
    
  //根据歌单ID查询歌曲
    List<Integer> selectBySongListId(int songListId);
    
    //根据歌曲ID和歌单ID删除歌曲歌单表的数据
    int deleteBySongIdAndSongListId(Map map);
    
    SongListWithSongPo selectById(Map map);
}