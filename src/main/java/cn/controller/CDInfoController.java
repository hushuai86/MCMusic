package cn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.CDIdNotExistException;
import cn.exception.CollectionException;
import cn.exception.SingerIdNotExistException;
import cn.exception.SongDownStateException;
import cn.exception.UserNotForSessionException;
import cn.pojo.CDPo;
import cn.pojo.UserPo;
import cn.pojo.vo.CDVo;
import cn.pojo.vo.SingerVo;
import cn.pojo.vo.SongVo;
import cn.service.UserCollectionCDService;
import cn.service.impl.CDServiceImpl;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.SingerServiceImpl;
import cn.service.impl.SongServiceImpl;

@Controller
public class CDInfoController {
	@Autowired
	private  SessionServiceImpl sessionServiceImpl;
	@Autowired 
	private CDServiceImpl cdServiceImpl;
	@Autowired 
	private SingerServiceImpl singerServiceImpl;
	@Autowired 
	private SongServiceImpl songServiceImpl;
	@Autowired
	private SessionServiceImpl sessionServicetionImpl;
	@Autowired
	private UserCollectionCDService userCollectionCDService;
	//专辑信息
	@RequestMapping("/cdInfo")
	public @ResponseBody CDVo cdInfo(int cdId) throws CDIdNotExistException{
		return cdServiceImpl.getShowCDVoById(cdId);
	}
	
    //其他专辑
	@RequestMapping("/otherCD")
	public @ResponseBody SingerVo otherCD(int singerId) throws SingerIdNotExistException{
		return singerServiceImpl.getShowSingerVoById(singerId);
	}
	
	 //专辑所有歌曲
	@RequestMapping("/cdAllSong")
	public @ResponseBody List<SongVo> cdAllSong(int cdId) throws CDIdNotExistException{
		return songServiceImpl.getSongByCDId(cdId);
	}
	
	//是否已经收藏
	@RequestMapping("/isConllectionForCDId")
	@ResponseBody
	public boolean isConllectionForCDId(int cdId,HttpServletRequest request) throws UserNotForSessionException, CDIdNotExistException{	
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			return userCollectionCDService.isConllectionForCDId(cdId, userPo.getUserid());
		}
		return false;
		
	}
	
	//收藏
	@RequestMapping("/addCDCollection")
	@ResponseBody
	public boolean addCDCollection(int cdId,HttpServletRequest request) throws UserNotForSessionException, CDIdNotExistException, CollectionException{
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			//方法中已经收藏+1
			userCollectionCDService.CollectionCD(cdId, userPo.getUserid());
			return true;
		}
		return false;
		
	}
}
