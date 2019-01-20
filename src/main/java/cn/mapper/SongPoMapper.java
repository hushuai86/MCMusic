package cn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.pojo.SongPo;
import cn.pojo.vo.SongVo;

public interface SongPoMapper {
    int deleteByPrimaryKey(Integer songid);

    int insert(SongPo record);

    int insertSelective(SongPo record);

    SongPo selectByPrimaryKey(Integer songid);

    int updateByPrimaryKeySelective(SongPo record);

    int updateByPrimaryKey(SongPo record);
    
    List<SongPo> selectAllSong();
    //获取所有歌曲实体对象，状态码不为阻塞（不显示）状态
    List<SongPo> selectAllSongNoBlock();
    //通过歌曲id得到一个实体对象，状态码不为阻塞（不显示）状态
    SongPo selectByPrimaryKeyNoBlock(Integer songid);
    
    int selectSongStateId(Integer songid);
    //获取所有songVo对象，状态码不为阻塞（不显示）状态
    List<SongVo> selectAllSongVo();
    
    //歌手Id查歌曲集合
    List<SongPo> selectBySingerId(Integer singerid);
    
    //通过歌曲id获取一个songVo对象，状态码不为阻塞（不显示）状态
    SongVo selectSongVoBySongId(Integer singerid);
    //根据歌手id得到其所有歌曲数量
    int countBySingerId(Integer singerid);	
    //通过typeId查询歌曲集合
    List<SongVo> selectSongVoByTypeId(Integer typeId);
    // 通过id获取songvo集合对象
    List<SongVo> selectSongVoBySingerId(Integer singerid);
    //关键字获取对应名称的songVo对象,状态码不为阻塞（不显示）状态
    List<SongVo> selectAllSongVoForPagination(Map map);
    //关键字获取对应名称的songVo对象的数量,状态码不为阻塞（不显示）状态
    int selectCountAllSongVoForPagination(Map map);  
    //歌曲收藏排行
    List<SongVo> selectAllSongVoForPaginationByCollectionCount(Map map);
    //歌曲热度排行
    List<SongVo> selectAllSongVoForPaginationByHotRank(Map map);
    //根据语种的歌曲热度排行
    List<SongVo> selectAllSongVoForPaginationByHotRankAndLanguage(Map map);
    //新歌排行
    List<SongVo> selectAllSongVoForPaginationByPublishDate(Map map);
    //根据语种的新歌排行
    List<SongVo> selectAllSongVoForPaginationByPublishDateAndLanguage(Map map);
    //经典歌曲排行
    List<SongVo> selectAllSongVoForPaginationByPublishDateAndHotRank(Map map);
    //根据专辑Id查歌曲集合
    List<SongPo> selectByCDId(Integer cdid);
    
    //根据地区Id查询
    List<SongPo> selectByTypeId(Integer typeId);
    
    //歌曲名查询
    SongPo selectBySongName(String name);
    
    //歌手ID
    List<SongPo> selectSongPosBySingerId(int singerId);
    
    //根据CDID查询
    List<SongPo> selectSongPosByCDId(int CDId);
     //排除语种的新歌排行
    List<SongVo> selectAllSongVoForPaginationByPublishDateAndLanguageNo(Map map);
    
    //歌手Id查歌曲集合 并分页
    List<SongPo> selectBySingerIdByPag(Map map);
    
    //获取所有歌曲，状态不为阻塞
    int  selectSongCount();
    
    //获得专辑中的歌曲 通过专辑id和歌曲名
	List<SongVo> selectSongByCDIdAndSongName(Map map);
	
	 //获得专辑中的歌曲数量通过专辑id和歌曲名
	int selectCountSongByCDIdAndSongName(Map map);

	 //获得专辑中的歌曲 通过专辑id和歌曲id
	List<SongVo> selectNotInCDBySingId(Map map);
	
	 //获得专辑中的歌曲数量 通过专辑id和歌曲名
	int selectCountNotInCDBySingId(Map map);
	
	 //获得已删除的歌曲，歌名模糊查询
	List<SongVo> selectDeletedSong(Map map);
	
	//获得已删除的歌曲数量 ，歌名模糊查询
	int selectCountDeletedSong(Map map);
	
	int selectAllSongVoForPaginationCount(@Param(value="songname") String songname);
	
    //根据typeId查询数量
    int selectCountByTypeId(Integer typeId);
    
    //通过typeId查询歌曲集合
    List<SongVo> selectSongVoByTypeId(Map<String, Integer> map);
    //根据CDID查询vo
    List<SongVo> selectSongVosByCDId(int CDId);
   
}
