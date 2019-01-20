package cn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.CollectionException;
import cn.exception.SingerIdNotExistException;
import cn.exception.SongListIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.mapper.SongListPoOtherMapper;
import cn.mapper.UserWithSongListPoMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SongListPo;
import cn.pojo.TypePo;
import cn.pojo.UserPo;
import cn.pojo.UserWithSongListPo;
import cn.pojo.vo.SongListVo;
import cn.service.TypeService;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.SongListServiceImpl;
import cn.service.impl.UserCollectionSingerServiceImpl;
import cn.service.impl.UserCollectionSongListServiceImpl;


@Controller
public class SongListController {
	@Autowired
	private UserCollectionSongListServiceImpl userCollectionSongListServiceImpl;
	@Autowired
	private SessionServiceImpl  sessionServiceImpl;
	@Autowired
	SongListServiceImpl songListServiceImpl;
	@Autowired
	TypeService typeService;
	@Autowired
	SongListPoOtherMapper songListPoOtherMapper;
	@Autowired
	UserWithSongListPoMapper mapper;
	
	@RequestMapping("/getAllSongList")
	@ResponseBody
	public List<SongListVo> getAllSongList(int typeId,int pageIndex,int tag){
		//获取传入的排序id:tag 然后在根据传入的typeId 进行筛选
		//参数设置
		SongListVo vo = new SongListVo();
		SongListPo songListPo = new SongListPo();
		if(typeId!=0){
			songListPo.setTypeid(typeId);
		}
		vo.setSongListPo(songListPo);
		
		PageBasePo<SongListVo> pageBasePo=songListServiceImpl.filter(vo,8, pageIndex, tag);
		
		List<SongListVo> list=pageBasePo.getList();
		
		return list ;

	}
	
	@RequestMapping("/getAllSongListCount")
	@ResponseBody
	public int getAllSongListCount(int typeId){
		SongListVo vo = new SongListVo();
		SongListPo songListPo = new SongListPo();
		if(typeId!=0){
			songListPo.setTypeid(typeId);
		}
		vo.setSongListPo(songListPo);
		//新建一个分页对象,并且设置筛选条件
		PageBasePo<SongListVo> voPageBasePo=new PageBasePo<SongListVo>();
		voPageBasePo.setIndexEntity(vo);
		//得到数量
		int allNum=songListPoOtherMapper.getShowSongListVoBySearchCount(voPageBasePo);
		return allNum;

	}
	
	@RequestMapping("/getAllTypes")
	@ResponseBody
	public List<TypePo> getAllTypes(){
		return typeService.getAllTypePo();

	}
	
	//是否已经收藏
	@RequestMapping("/isConllectionForSongList")
	@ResponseBody
	public boolean isConllectionForSongList(int songListId,HttpServletRequest request) throws SongListIdNotExistException, UserNotForSessionException{	
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			return userCollectionSongListServiceImpl.isConllectionForSongListId(songListId, userPo.getUserid());
		}
		return false;
	}
	
	//收藏
	@RequestMapping("/addSongListCollection")
	@ResponseBody
	public boolean addSongListCollection(int songListId,HttpServletRequest request) throws UserNotForSessionException, SongListIdNotExistException, CollectionException{
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			//方法中已经收藏+1
			userCollectionSongListServiceImpl.CollectionSongList(songListId, userPo.getUserid());
			return true;
		}
		return false;
	}
	
	
	//显示自己的歌单
	@RequestMapping("/getMysongList")
	@ResponseBody
	public List<SongListPo> getMysongList(HttpServletRequest request) throws UserNotForSessionException{
		List<SongListPo> list = new ArrayList<SongListPo>();
		Map map = new HashMap();
		//当前用户id
		int userPoId = sessionServiceImpl.getCurrentUserPo(request).getUserid();
		map.put("userId", userPoId);
		//自己的歌单添加
		for(SongListPo po :songListPoOtherMapper.selectSongListByUser(userPoId)){
			map.put("songListId",po.getSonglistid());
			UserWithSongListPo u = mapper.selectBySongListIdAndUserId(map);
			if(u!=null){
				list.add(po);
			}
		}
		return list;
	}
	
}
