package cn.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mapper.CDPoMapper;
import cn.mapper.SingerPoMapper;
import cn.mapper.SongPoMapper;
import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.SongListPo;
import cn.pojo.SongPo;
import cn.pojo.vo.CDVo;
import cn.pojo.vo.SongListVo;
import cn.pojo.vo.SongVo;
import cn.service.impl.CDServiceImpl;
import cn.service.impl.SingerServiceImpl;
import cn.service.impl.SongListServiceImpl;
import cn.service.impl.SongServiceImpl;

@Controller
public class SearchController {
	@Autowired
	private SongServiceImpl songServiceImpl;
	@Autowired
	private SingerServiceImpl singerServiceImpl;
	@Autowired
	private SongListServiceImpl songListServiceImpl;
	@Autowired 
	private CDServiceImpl cdServiceImpl;
	@Autowired
	private SongPoMapper mapper;
	@Autowired
	private SingerPoMapper singerMapper;
	@Autowired
	private CDPoMapper cdMapper;
	// 歌曲查询5
	// 查询条件 search 根据歌曲名模糊查询
	@RequestMapping("/searchSongPosForGood")
	@ResponseBody
	public PageBasePo<SongVo> searchSongPosForGood(String search) {
		SongVo songVo = new SongVo();
		SongPo songPo = new SongPo();
		songPo.setSongname(search + "%");
		songVo.setSongPo(songPo);
		PageBasePo<SongVo> list = songServiceImpl.filter(songVo, 6, 1);
		return list;
	}
	// 歌手查询5
	// 查询条件 search 根据歌手名模糊查询
	@RequestMapping("/searchSingerPosForGood")
	@ResponseBody
	public PageBasePo<SingerPo> searchSingerPosForGood(String search) {
		SingerPo singerPo = new SingerPo();
		singerPo.setSingername(search+"%");
		singerPo.setAccesscount(1);
		return singerServiceImpl.filter(singerPo,6, 1, null);
	}
	
	//专辑查询5
	@RequestMapping("/searchCDPosForGood")
	@ResponseBody
	public PageBasePo<CDVo> searchCDPosForGood(String search){
		CDPo  cdPo = new CDPo();
		CDVo cdVo = new CDVo();
		cdPo.setCdname(search);
		cdVo.setCDPo(cdPo);
		PageBasePo<CDVo> list = cdServiceImpl.filter(cdVo,6, 1);
		return list;
	}
	
	
	// 歌曲查询
	// 查询条件 search 根据歌曲名模糊查询
	@RequestMapping("/searchSongPos")
	@ResponseBody
	public PageBasePo<SongVo> searchSongPos(String search, int pageIndex) {
		SongVo songVo = new SongVo();
		SongPo songPo = new SongPo();
		songPo.setSongname("%" + search + "%");
		songVo.setSongPo(songPo);
		PageBasePo<SongVo> list = songServiceImpl.filter(songVo, 10, pageIndex);
		return list;
	}
	// 歌曲查询总数
	@RequestMapping("/searchSongPosCount")
	@ResponseBody
	public int searchSongPosCount(String search) {
		return mapper.selectAllSongVoForPaginationCount("%" + search + "%");
	}


	// 歌手查询
	// 查询条件 search 根据歌手名模糊查询
	@RequestMapping("/searchSingerPos")
	@ResponseBody
	public PageBasePo<SingerPo> searchSingerPos(String search,int pageIndex) {
		SingerPo singerPo = new SingerPo();
		singerPo.setSingername("%"+search+"%");
		singerPo.setAccesscount(1);
		return singerServiceImpl.filter(singerPo,15, pageIndex, null);
	}
	// 歌手查询总数
	@RequestMapping("/searchSingerPosCount")
	@ResponseBody
	public int searchSingerPosCount(String search) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("singername", "%" + search + "%");
		return singerMapper.selectSingerPoBlockForPaginationCount(map);
	}
	
	
	// 歌单查询
	// 查询条件 search 根据歌单模糊查询
	@RequestMapping("/searchSongListPos")
	@ResponseBody
	public PageBasePo<SongListVo> searchSongListPos(String search, int pageIndex) {
		SongListVo songListVo = new SongListVo();
		SongListPo songListPo = new SongListPo();
		songListPo.setSonglistname("%" + search + "%");
		songListVo.setSongListPo(songListPo);
		PageBasePo<SongListVo> list = songListServiceImpl.filter(songListVo,5, pageIndex);
		return list; 
	}
	// 专辑查询总数
	@RequestMapping("/searchSongListPosCount")
	@ResponseBody
	public int searchSongListPosCount(String search) {
		SongListVo songListVo = new SongListVo();
		SongListPo songListPo = new SongListPo();
		songListPo.setSonglistname("%" + search + "%");
		songListVo.setSongListPo(songListPo);
		PageBasePo<SongListVo> list = songListServiceImpl.filter(songListVo,1, 1);
		return list.getAllNum();
	}
	 
	
	
    //专辑查询
	@RequestMapping("/searchCDPos")
	@ResponseBody
	public PageBasePo<CDVo> searchCDPos(String search,int pageIndex){
		CDPo  cdPo = new CDPo();
		CDVo cdVo = new CDVo();
		cdPo.setCdname(search);
		cdVo.setCDPo(cdPo);
		PageBasePo<CDVo> list = cdServiceImpl.filter(cdVo,15, pageIndex);
		return list;
	}
	// 专辑查询总数
	@RequestMapping("/searchCDPosCount")
	@ResponseBody
	public int searchCDPosCount(String search) {
		CDPo  cdPo = new CDPo();
		CDVo cdVo = new CDVo();
		cdPo.setCdname("%"+search+"%");
		cdVo.setCDPo(cdPo);
		return cdMapper.selectFilterCount(cdVo);
	}
 
}
