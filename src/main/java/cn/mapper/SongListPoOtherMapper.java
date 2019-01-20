package cn.mapper;

import java.util.List;
import java.util.Map;

import cn.pojo.PageBasePo;
import cn.pojo.SongListPo;
import cn.pojo.vo.SongListVo;

public interface SongListPoOtherMapper {
	// 歌曲列表扩展Mapper

	// 增加收藏+1记录
	int addCollectionCount(int songListId);

	// 收藏次数-1记录
	int cutCollectionCount(int songListId);

	// 访问次数+1
	int addAccessCount(int songListId);

	// 返回当前的收藏次数
	int getCurrentCollectionCount(int songListId);

	// 返回当前的收藏次数
	int getCurrentAccessCount(int songListId);

	// 获取所有的songListPo对象
	List<SongListPo> getSongListPos();

	// 得到所有歌单拓展类，（添加字段user，song,type），状态为公开
	List<SongListVo> getSongListVos();

	// 根据歌单id得到拓展类
	SongListVo getSongListVoBySongListId(int songListId);

	// 返回歌单的总数
	int getAllCountNum();

	// 对歌单进行分页
	List<SongListVo> getShowSongListVo(Map map);

	// 带条件筛选的进行查询
	List<SongListVo> getShowSongListVoBySearch(PageBasePo<SongListVo> pageBasePo);

	// 带条件筛选的进行查询 通过songlistname查询
	List<SongListVo> getShowSongListVoBySearchBySongListName(
			PageBasePo<SongListVo> pageBasePo);

	// 模糊查询的条数
	int getShowSongListVoBySearchCount(PageBasePo<SongListVo> pageBasePo);

	// 根据用户ID查询歌单
	List<SongListPo> selectSongListByUser(int id);

	// 带条件最新查询
	List<SongListVo> getShowSongListVoBySearch1(
			PageBasePo<SongListVo> pageBasePo);

	// 带条件每日推荐查询
	List<SongListVo> getShowSongListVoBySearch2(
			PageBasePo<SongListVo> pageBasePo);

	// 带条件最热查询
	List<SongListVo> getShowSongListVoBySearch3(
			PageBasePo<SongListVo> pageBasePo);

	// 带条件热藏查询
	List<SongListVo> getShowSongListVoBySearch4(
			PageBasePo<SongListVo> pageBasePo);
	
	//名字搜索条数
		Integer getShowSongListVoBySearchBySongListNameCount(PageBasePo<SongListVo> pageBasePo);

}
