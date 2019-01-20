package cn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mapper.SingerPoMapper;
import cn.mapper.SongListPoMapper;
import cn.mapper.SongPoMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.SongListPo;
import cn.pojo.vo.SongListVo;
import cn.pojo.vo.SongVo;
import cn.service.RankListService;

/**
 * 排行榜服务接口实现
 * @author xiaoyefeng
 *
 */
@Service
public class RankListServiceImpl implements RankListService {
	@Autowired
	private SongPoMapper songPoMapper;
	@Autowired
	private SingerPoMapper singerPoMapper;
	@Autowired
	private SongListPoMapper songListPoMapper;
	@Autowired
	private AreaServiceImpl areaServiceImpl;
	@Override
	//歌曲收藏排行（分页）
	public PageBasePo<SongVo> getSongCollectionRank(int pageSiz, int pageIndex) {
		//创建map存储搜索条件
		Map map = new HashMap();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);
		//获取通过搜查条件查询出的数据
		List<SongVo> list = songPoMapper
				.selectAllSongVoForPaginationByCollectionCount(map);
		PageBasePo pageBasePo = new PageBasePo();
		//将数据存入pageBasePo对象中
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	//歌曲热度排行
	public PageBasePo<SongVo> getSongHotRank(int pageSiz, int pageIndex) {
		//创建map存储搜索条件
		Map map = new HashMap();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);
		//获取通过搜查条件查询出的数据
		List<SongVo> list = songPoMapper
				.selectAllSongVoForPaginationByHotRank(map);
		PageBasePo pageBasePo = new PageBasePo();
		//将数据存入pageBasePo对象中
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	//根据语种的歌曲热度排行
	public PageBasePo<SongVo> getSongHotRank(String language, int pageSiz,
			int pageIndex) {
		//创建map存储搜索条件
		Map map = new HashMap();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);
		map.put("language", language);
		//获取通过搜查条件查询出的数据
		List<SongVo> list = songPoMapper
				.selectAllSongVoForPaginationByHotRankAndLanguage(map);
		PageBasePo pageBasePo = new PageBasePo();
		//将数据存入pageBasePo对象中
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	//新歌排行
	public PageBasePo<SongVo> getNewSongRank(int pageSiz, int pageIndex) {
		//创建map存储搜索条件
		Map map = new HashMap();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);
		//获取通过搜查条件查询出的数据
		List<SongVo> list = songPoMapper
				.selectAllSongVoForPaginationByPublishDate(map);
		//将数据存入pageBasePo对象中
		PageBasePo pageBasePo = new PageBasePo();
		pageBasePo.setList(list);
		return pageBasePo;

	}

	@Override
	//根据语种的新歌排行
	public PageBasePo<SongVo> getNewSongRank(String language, int pageSiz,
			int pageIndex) {
		//创建map存储搜索条件
		Map map = new HashMap();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);
		map.put("language", language);
		//获取通过搜查条件查询出的数据
		List<SongVo> list = songPoMapper
				.selectAllSongVoForPaginationByPublishDateAndLanguage(map);
		PageBasePo pageBasePo = new PageBasePo();
		//将数据存入pageBasePo对象中
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	//经典歌曲排行
	public PageBasePo<SongVo> getOldSongRank(int pageSiz, int pageIndex) {
		//创建map存储搜索条件
		Map map = new HashMap();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);
		//获取通过搜查条件查询出的数据
		List<SongVo> list = songPoMapper
				.selectAllSongVoForPaginationByPublishDateAndHotRank(map);
		PageBasePo pageBasePo = new PageBasePo();
		//将数据存入pageBasePo对象中
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	//歌手热度排行
	public PageBasePo<SingerPo> getHotSingerRank(int pageSiz, int pageIndex) {
				// 将搜索分页条件用map存着
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("pageSize", pageSiz);
				map.put("pageIndex", (pageIndex-1)*pageSiz);

				// 通过map查询出符合条件的list
				List<SingerPo> list = singerPoMapper 
						.selectSingerPoBlockForPaginationByHotSingerRank(map);
				PageBasePo pageBasePo = new PageBasePo();
				// 将list放入pagebasepo中
				pageBasePo.setList(list);
				return pageBasePo;
	}
    
	@Override
	//排除某个语种语种的新歌排行,
	public PageBasePo<SongVo> getNewSongRankNo(String language, int pageSiz,
			int pageIndex) {
		//创建map存储搜索条件
		Map map = new HashMap();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);
		map.put("language", language);
		//获取通过搜查条件查询出的数据
		List<SongVo> list = songPoMapper
				.selectAllSongVoForPaginationByPublishDateAndLanguageNo(map);
		PageBasePo pageBasePo = new PageBasePo();
		//将数据存入pageBasePo对象中
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	//根据地区id歌手热度排序
	public PageBasePo<SingerPo> getHotSingerRank(int areaId, int pageSiz,
			int pageIndex) {
		// 将搜索分页条件用map存着
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);
		map.put("areaid", areaId);
		// 通过map查询出符合条件的list
		List<SingerPo> list = singerPoMapper 
				.selectSingerPoBlockForPaginationByHotSingerRankAndAreaId(map);
		PageBasePo pageBasePo = new PageBasePo();
		// 将list放入pagebasepo中
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	//歌手收藏排行
	public PageBasePo<SingerPo> getCollectionSingerRank(int pageSiz,
			int pageIndex) {
		// 将搜索分页条件用map存着
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);

		// 通过map查询出符合条件的list
		List<SingerPo> list = singerPoMapper 
				.selectSingerPoBlockForPaginationByCollectionCount(map);
		PageBasePo pageBasePo = new PageBasePo();
		// 将list放入pagebasepo中
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	//歌单热度排行
	public PageBasePo<SongListVo> getHotSongListRank(int pageSiz, int pageIndex){
		      // 将搜索分页条件用map存着
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("pageSize", pageSiz);
				map.put("pageIndex", (pageIndex-1)*pageSiz);
				List<SongListPo> list = songListPoMapper 
						.selectSongListPoBlockForPaginationByHotSingerRank(map);
				PageBasePo pageBasePo = new PageBasePo();
				// 将list放入pagebasepo中
				pageBasePo.setList(list);
				return pageBasePo;

	}

	@Override
	//歌单收藏排行
	public PageBasePo<SongListVo> getCollectionSongListRank(int pageSiz,
			int pageIndex) {
		// 将搜索分页条件用map存着
		Map map = new HashMap();
		map.put("pageSize", pageSiz);
		map.put("pageIndex", (pageIndex-1)*pageSiz);
		List<SongListPo> list = songListPoMapper 
				.selectSongListPoBlockForPaginationByCollectionCount(map);
		PageBasePo pageBasePo = new PageBasePo();
		// 将list放入pagebasepo中
		pageBasePo.setList(list);
		return pageBasePo;

	}
	//歌单最新排行
		@Override
		public PageBasePo<SongListVo> getNewHotSongListRank(int pageSize,
				int pageIndex) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			int index=(pageIndex-1)*pageSize;
			map.put("pageSize", pageSize);
			map.put("pageIndex", index);
			
			List<SongListPo> list = songListPoMapper 
					.selectSongListPoBlockForPaginationBySongListId(map);
			
			PageBasePo pageBasePo = new PageBasePo();
			
			// 将list放入pagebasepo中
			pageBasePo.setList(list);
			
			return pageBasePo;
		}


}
