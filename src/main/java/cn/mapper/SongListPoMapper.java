package cn.mapper;

import java.util.List;
import java.util.Map;
import cn.pojo.SongListPo;
import cn.pojo.TypePo;

public interface SongListPoMapper {
    int deleteByPrimaryKey(Integer songlistid);

    int insert(SongListPo record);

    int insertSelective(SongListPo record);

    SongListPo selectByPrimaryKey(Integer songlistid);

    int updateByPrimaryKeySelective(SongListPo record);

    int updateByPrimaryKey(SongListPo record);
    
    //地区id查询歌单集合
    List<SongListPo> selectByTypeId(TypePo typeid);
    
    //歌单名字获取歌单
    SongListPo selectBySongListName(String name);
    
    //歌单热度排行
    List<SongListPo> selectSongListPoBlockForPaginationByHotSingerRank(Map map);
    
  //歌单收藏排行
    List<SongListPo> selectSongListPoBlockForPaginationByCollectionCount(Map map);
    
    //歌单最新排行
    List<SongListPo> selectSongListPoBlockForPaginationBySongListId(Map map);
    
    //查询歌单数，状态不为阻塞
    int selectShowSongListCount();
    
}