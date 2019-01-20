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

import cn.exception.SongListAddPutException;
import cn.exception.SongListIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.mapper.SingerPoMapper;
import cn.mapper.SongListPoMapper;
import cn.mapper.SongListWithSongPoMapper;
import cn.mapper.SongPoMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SongListPo;
import cn.pojo.SongPo;
import cn.pojo.UserPo;
import cn.pojo.vo.SongListVo;
import cn.service.SessionService;
import cn.service.SongListManageService;
import cn.service.SongService;
import cn.service.impl.SongListServiceImpl;
import cn.service.impl.UserCollectionSongListServiceImpl;

@Controller
public class SongListInfoController {
	@Autowired
	SongListServiceImpl songListServiceImpl;
	@Autowired
	UserCollectionSongListServiceImpl userCollectionSongListServiceImpl;
	@Autowired
	SongListPoMapper songListMapper;
	@Autowired
	SingerPoMapper mapper;
	@Autowired
	SessionService sessionService;
	@Autowired
	SongListManageService manageService;
	@Autowired
	SongListWithSongPoMapper ssMapper;
	@Autowired
	SongPoMapper songMapper;
	//歌单信息
	@RequestMapping("/getShowSongPoById")
	public @ResponseBody SongListVo getShowSongPoById(int songListId){
		SongListVo songListVo=null;
		try {
			 songListVo = songListServiceImpl.getShowSongListVoById(songListId);
			 //热度+1
			 SongListPo po = songListVo.getSongListPo();
			 po.setAccesscount(po.getAccesscount()+1);
			 songListMapper.updateByPrimaryKeySelective(po);
			 
			 //获取歌曲
			 List<Integer> list = ssMapper.selectBySongListId(songListId);
			 List<SongPo> songList = new ArrayList<SongPo>();
			 for(int id:list){
				 songList.add(songMapper.selectByPrimaryKey(id));
			 }
			 songListVo.setList(songList);
		} catch (SongListIdNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return songListVo;
	}
	
	//得到歌手名称
	@RequestMapping("/getsingerName")
	public @ResponseBody Map<String, String> getsingerName(int singerId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("singerName",mapper.selectByPrimaryKeyForName(singerId));
		return map;
	}
	
	//每日推荐
	@RequestMapping("/getShowSongListVo")
	public @ResponseBody List<SongListVo> getShowSongListVo(){
		//查询分页对象
		PageBasePo<SongListVo> pageBasePo=songListServiceImpl.getShowSongListVo(8, 1);
		//将分页对象中的list 赋值给要返回的Json对象
		List<SongListVo> list=pageBasePo.getList();
		return list;
	}
	
	//歌曲删除
	@RequestMapping("/delSongForSongList")
	public @ResponseBody boolean delSongForSongList(int songId,int songlistId,HttpServletRequest request) throws UserNotForSessionException, SongListIdNotExistException, SongListAddPutException{
		UserPo po = sessionService.getCurrentUserPo(request);
		if(po!=null){
			//判断删除权限
			SongListPo songlist = songListServiceImpl.getShowSongPoById(songlistId);
			if(songlist.getUserid().equals(po.getUserid())){
				//进行删除
				 manageService.putSongForSongList(songId, songlistId);
				 
				 //判断歌曲数量，为0删除歌单
				 List<Integer> list = ssMapper.selectBySongListId(songlistId);
				 if(list.size()==0){
					 userCollectionSongListServiceImpl.notCollectionSongList(songlistId,po.getUserid());
					 songListMapper.deleteByPrimaryKey(songlistId);
				 }
				 return true;
			}
		}
		return false;
	}
}
