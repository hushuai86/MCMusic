package cn.service;

import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.vo.SongListVo;
import cn.pojo.vo.SongVo;

/**
 * 排行榜服务接口
 * @author liuqiao
 *
 */
public interface RankListService {

	/**
	 * 歌曲收藏排行（分页）
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SongVo> getSongCollectionRank(int pageSiz,int pageIndex);
	
	/**
	 * 歌曲热度排行
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SongVo> getSongHotRank(int pageSiz,int pageIndex);
	
	/**
	 * 根据语种的歌曲热度排行
	 * @param language 语种
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SongVo> getSongHotRank(String language,int pageSiz,int pageIndex);
	
	/**
	 * 新歌排行
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SongVo> getNewSongRank(int pageSiz,int pageIndex);
	/**
	 * 根据语种的新歌排行
	 * @param language 语种
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SongVo> getNewSongRank(String language,int pageSiz,int pageIndex);
	
	/**
	 * 经典歌曲排行（发行时间倒序，热度排序）
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SongVo> getOldSongRank(int pageSiz,int pageIndex);
	
	
	
	/**
	 * 歌手热度排行
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SingerPo> getHotSingerRank(int pageSiz,int pageIndex);
	
	/**
	 * 根据地区id歌手热度排序
	 * @param areaId 地区Id
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SingerPo> getHotSingerRank(int areaId,int pageSiz,int pageIndex);
	
	/**
	 * 歌手收藏排行
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SingerPo> getCollectionSingerRank(int pageSiz,int pageIndex);
	
	
	
	/**
	 * 歌单热度排行
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SongListVo> getHotSongListRank(int pageSiz,int pageIndex);
	/**
	 * 歌单收藏排行
	 * @param pageSiz 页面大小
	 * @param pageIndex 页面索引
	 * @return 分页集合
	 */
	public PageBasePo<SongListVo> getCollectionSongListRank(int pageSiz,int pageIndex);
	
	public  PageBasePo<SongVo> getNewSongRankNo(String language, int pageSiz,
				int pageIndex);
	 /**新歌排序 
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public  PageBasePo<SongListVo> getNewHotSongListRank(int pageSize,int pageIndex);
  
}
