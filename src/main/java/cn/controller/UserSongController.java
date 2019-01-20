package cn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.SingerIdNotExistException;
import cn.exception.SongIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.pojo.UserPo;
import cn.pojo.vo.UserCollectionSongVo;

import cn.service.SessionService;
import cn.service.SingerService;
import cn.service.impl.UserCollectionSongServiceImpl;

@Controller
public class UserSongController {
	@Autowired
	UserCollectionSongServiceImpl userCollectionSongServiceImpl;
	@Autowired
	SingerService singerService;
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/getUserCollectionSongVos")
	public @ResponseBody List<UserCollectionSongVo> getUserCollectionSongVos(HttpServletRequest request) throws UserNotForSessionException, SingerIdNotExistException{
		UserPo po = sessionService.getCurrentUserPo(request);
		if(po==null) return null;
		int userId = po.getUserid();
		List<UserCollectionSongVo> newList=new ArrayList<UserCollectionSongVo>();
		
		try {
			List<UserCollectionSongVo> list = userCollectionSongServiceImpl.getUserCollectionById(userId);
			for(UserCollectionSongVo vo:list){
				int id = vo.getSongPo().getSingerid();
				//查询得到名字
				String singerName = singerService.getShowSingerPoById(id).getSingername();
				//添加到集合
				vo.setSingerName(singerName);
				newList.add(vo);
			}
		} catch (UserIdNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return newList;	
	}
	
	//放弃收藏音乐
	@RequestMapping("/getupASong")
	public @ResponseBody boolean getupASong(int songId,HttpServletRequest request) throws UserNotForSessionException, SongIdNotExistException{
		UserPo po = sessionService.getCurrentUserPo(request);
		if(po==null) return false;
		int userId = po.getUserid();
		userCollectionSongServiceImpl.notCollectionSong(songId, userId);
		return true;
	}
}
