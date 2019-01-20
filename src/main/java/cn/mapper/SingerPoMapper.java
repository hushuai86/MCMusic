package cn.mapper;

import java.util.List;
import java.util.Map;
import cn.pojo.SingerPo;
import cn.pojo.vo.SingerVo;

public interface SingerPoMapper {
    int deleteByPrimaryKey(Integer singerid);

    int insert(SingerPo record);

    int insertSelective(SingerPo record);
    //通过singerId获取歌手实体对象
    SingerPo selectByPrimaryKey(Integer singerid);

    SingerPo selectByPrimaryKey1(Integer singerid);
    
    int updateByPrimaryKeySelective(SingerPo record);

    int updateByPrimaryKeyWithBLOBs(SingerPo record);

    int updateByPrimaryKey(SingerPo record);
    //得到名称
    String selectByPrimaryKeyForName(Integer singerid);
    
    //获取所有歌手实体对象
    List<SingerPo> selectAllSinger();
    
    //获取所有歌手实体对象，状态码不为阻塞（不显示）状态 
    List<SingerPo> selectAllSingerPoBlock();
    
    //分页获取所有歌手实体对象，状态码不为阻塞（不显示）状态
    List<SingerPo> selectSingerPoBlockForPagination(Map map);
    
    //分页根据首字母获取所有歌手实体对象，状态码不为阻塞（不显示）状态
    List<SingerPo> selectSingerPoBlockForPaginationByFirstLetter(Map map);
    
    //获取所有singerVo对象 状态为不阻塞
    List<SingerVo> selectAllSingerVo();
    
    //通过singerId获取所有singerVo对象 状态为不阻塞
    SingerVo selectSingerVoBySingerId(Integer singerid);
    
    //歌手热度排行
    List<SingerPo> selectSingerPoBlockForPaginationByHotSingerRank(Map map);
    
    //根据地区id歌手热度排序
    List<SingerPo> selectSingerPoBlockForPaginationByHotSingerRankAndAreaId(Map map);
    
    //条件查询总条数
    int selectSingerPoBlockForPaginationCount(Map map);
  
    //歌手收藏排行
    List<SingerPo> selectSingerPoBlockForPaginationByCollectionCount(Map map);
    
    //通过地区Id查询歌手实体对象集合
    List<SingerPo> selectByAreaId(Integer  areaid);
    
    //通过歌手名查询歌手实体对象
    SingerPo selectBySingerName(String name);
    
    //非中国国籍 分页
    List<SingerPo> selectSingerForForeign(Map map);
    
    //获取歌曲总数，状态不为阻塞
	int selectShowSingerCount();
	
	//根据singername模糊查询 获取歌手实体对象 状态不阻塞
   List<SingerPo> selectSingerPoBlockBySingerName(String singername);
}