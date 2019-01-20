package cn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.AreaIdNotExistException;
import cn.exception.CollectionException;
import cn.exception.SingerIdNotExistException;
import cn.exception.SongListAddPutException;
import cn.exception.UserNotForSessionException;
import cn.pojo.AreaPo;
import cn.pojo.CDPo;
import cn.pojo.SingerPo;
import cn.pojo.SongPo;
import cn.pojo.UserPo;
import cn.pojo.vo.SongVo;
import cn.service.SongListService;
import cn.service.impl.AreaServiceImpl;
import cn.service.impl.CDServiceImpl;
import cn.service.impl.RankListServiceImpl;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.SingerServiceImpl;
import cn.service.impl.SongListManagerServiceImpl;
import cn.service.impl.SongServiceImpl;
import cn.service.impl.UserCollectionSingerServiceImpl;

@Controller

public class SingerController {
	@Autowired
	private UserCollectionSingerServiceImpl userCollectionSingerServiceImpl;
	@Autowired
	private SessionServiceImpl  sessionServiceImpl;
	@Autowired
	private SingerServiceImpl singerServiceImpl;
	@Autowired
	private SongServiceImpl songServiceImpl;
	@Autowired
	private SongListManagerServiceImpl songListManageService;
	@Autowired
	private RankListServiceImpl rankListServiceImpl;
	@Autowired
	private CDServiceImpl cdServiceImpl;
	@Autowired
	private AreaServiceImpl areaServiceImpl;
	//根据singerId 查出的 歌手信息
	@RequestMapping("/getSingerInformation")
	@ResponseBody
	public SingerPo getSingerInformation(int singerId) throws SingerIdNotExistException{ 
		return singerServiceImpl.getShowSingerPoById(singerId);
	}
	//根据singerId查出歌手地区
	@RequestMapping("/getSingerArea")
	@ResponseBody
	public AreaPo getSingerArea(int singerId) throws AreaIdNotExistException, SingerIdNotExistException{
		return areaServiceImpl.getAllAreaPoById(singerServiceImpl.getShowSingerPoById(singerId).getAreaid());
	}
	//热度+1
	@RequestMapping("/addAccessCount")
	@ResponseBody
	public long addAccessCount(int singerId) throws SingerIdNotExistException{
		return singerServiceImpl.addAccessCount(singerId);
	}
	

	
	//是否已经收藏
	@RequestMapping("/isConllectionForSingerId")
	@ResponseBody
	public boolean isConllectionForSingerId(int singerId,HttpServletRequest request) throws SingerIdNotExistException, UserNotForSessionException{	
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			return userCollectionSingerServiceImpl.isConllectionForSingerId(singerId,  userPo.getUserid());
		}
		return false;	
	}
	
	//收藏
	@RequestMapping("/addCollection")
	@ResponseBody
	public boolean addcollection(int singerId,HttpServletRequest request) throws CollectionException, SingerIdNotExistException, UserNotForSessionException{
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			//方法中已经收藏+1
			userCollectionSingerServiceImpl.CollectionSinger(singerId, userPo.getUserid());
			return true;
		}
		return false;
	}
	
	
	//是否登录
	@RequestMapping("/isLogin")
	@ResponseBody
	public boolean isLogin(HttpServletRequest request) throws UserNotForSessionException{	
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			return true;
		}else{
			return false;	
		}
	}
	
	//添加到歌单
	@RequestMapping("/aADDSongList")
	@ResponseBody
	public boolean aADDSongList(int songId, int songListId,HttpServletRequest request) throws SongListAddPutException, UserNotForSessionException{
		return songListManageService.addSongForSongList(songId, songListId);	
	}
	
	//添加到歌单
	@RequestMapping("/ADDSongList")
	@ResponseBody
	public boolean aDDSongList(int[] songId, int songListId,HttpServletRequest request) throws SongListAddPutException, UserNotForSessionException{
		for(int id:songId){
			if(!songListManageService.addSongForSongList(id, songListId)) return false;
		}
		return true;	
	}
	

	//单曲数
	@RequestMapping("/getSongCountBySingerId")
	@ResponseBody
	public int getSongCountBySingerId(int singerId) throws SingerIdNotExistException{
		return songServiceImpl.getSongCountBySingerId(singerId);
	}
	//专辑数
	@RequestMapping("/getCdCountBySingerId")
	@ResponseBody
	public int getCdCountBySingerId(int singerId) throws SingerIdNotExistException{
		return singerServiceImpl.getShowSingerVoById(singerId).getCDList().size();
	}
	
	//热门歌曲 前三首
	@RequestMapping("/getHonSongBySingerId")
	@ResponseBody
	public List<SongVo> getHonSongBySingerId(int singerId) throws SingerIdNotExistException{
		List<SongVo> list = songServiceImpl.getHonSongBySingerId(singerId);
		if(list==null){
			return null;
		}
		if(list.size()<=9){
			return list;
		}
		return list.subList(0, 9);
	}
	
	//热门专辑  收藏数前四
	@RequestMapping("/getHotCD")
	@ResponseBody
	public List<CDPo> getHotCD(int singerId){
		List<CDPo> list = cdServiceImpl.getHotCD(singerId);
		if(list==null) return null;
		if(list.size()<=4) return list;
		return list.subList(0, 4);
	}
	
	
	//推荐歌手 热度前5
	@RequestMapping("/getHotSingerRankNum5")
	@ResponseBody
	public List<SingerPo> getHotSingerRankNum5(int singerId){	
		List<SingerPo> list =  rankListServiceImpl.getHotSingerRank(6, 1).getList();
		List<SingerPo> list2 = new ArrayList<SingerPo>();
		for(SingerPo singerPo:list){
			if(singerId!=singerPo.getSingerid()){
				list2.add(singerPo);
			}
		}
		if(list.size()==6) return list2.subList(0, 5);
		return list2;
	}
}
