package cn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.CollectionException;
import cn.exception.SongListIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.mapper.SongListPoMapper;
import cn.mapper.UserPoMapper;
import cn.mapper.UserWithSongListPoMapper;
import cn.pojo.SongListPo;
import cn.pojo.UserPo;
import cn.pojo.vo.UserCollectionSongListVo;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.SongListServiceImpl;
import cn.service.impl.UserCollectionSongListServiceImpl;

@Controller
public class UserSongListController {
	
	@Autowired
	SessionServiceImpl sessionServiceImpl;
	@Autowired
	UserCollectionSongListServiceImpl userCollectionSongListServiceImpl;
	@Autowired
	SongListServiceImpl songListServiceImpl;
	@Autowired
	UserPoMapper mapper;
	@Autowired
	UserWithSongListPoMapper slMapper;
	@Autowired
	SongListPoMapper spMapper;
	
	//查询用户收藏的歌单
	@RequestMapping("/getUserCollectionSongListVo")
	public @ResponseBody List<UserCollectionSongListVo> getUserCollectionSongListVo(HttpServletRequest request){
		UserPo userPo= new UserPo();
		List<UserCollectionSongListVo> list=new ArrayList<UserCollectionSongListVo>();
		
		try {
			//获取请求的user对象
			userPo =sessionServiceImpl.getCurrentUserPo(request);
			if(userPo==null){return null;}
			//通过userId查询用户收藏的歌单
			list=userCollectionSongListServiceImpl.getUserCollectionById(userPo.getUserid());
		} catch (UserNotForSessionException e) {
			e.printStackTrace();
		}
		catch (UserIdNotExistException e){
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping("/getUserName")
	public @ResponseBody UserPo getUserName(int userId){
		return mapper.selectByPrimaryKey(userId);
	}
	
	//删除歌单
	@RequestMapping("/delSongList")
	public @ResponseBody boolean delSongList(int songListId,HttpServletRequest request) throws UserNotForSessionException, SongListIdNotExistException{
		UserPo po = sessionServiceImpl.getCurrentUserPo(request);
		if(po==null) return false;
		//查询收藏人数
		int count = slMapper.selectBySongListIdForCount(songListId);
		if(count==1){ //只有当前用户收藏，及直接删除歌单
			userCollectionSongListServiceImpl.notCollectionSongList(songListId,po.getUserid());
			spMapper.deleteByPrimaryKey(songListId);	
		}else{//有其他用户收藏，删除收藏
			userCollectionSongListServiceImpl.notCollectionSongList(songListId,po.getUserid());
		}	
		return true;
	}
	
	
	//添加用户歌单
	@RequestMapping("/addUserCollectionSongListVo")
	public @ResponseBody Boolean addUserCollectionSongListVo(String songListName,HttpServletRequest request){
		UserPo userPo= new UserPo();
		try {
			//获取请求的user对象
			userPo =sessionServiceImpl.getCurrentUserPo(request);
			
			SongListPo songListPo=new SongListPo();
			//状态 和歌单名称
			songListPo.setSongliststateid(0);
			songListPo.setSonglistname(songListName);
			songListPo.setUserid(userPo.getUserid());
			songListPo.setTypeid(3);//默认为3欢快
			System.out.println(songListPo);
			//插入私人歌单
			songListServiceImpl.addPrivateSongList(songListPo);
			//关联表也添加收藏
			userCollectionSongListServiceImpl.CollectionSongList(songListPo.getSonglistid(),userPo.getUserid());
			
			return true;	
		} catch (SongListIdNotExistException e) {
			e.printStackTrace();
		}
		 catch (UserNotForSessionException e) {
			e.printStackTrace();
		}
		catch (CollectionException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
