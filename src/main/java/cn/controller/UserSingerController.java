package cn.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.SingerIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.pojo.SingerPo;
import cn.pojo.UserPo;
import cn.pojo.vo.UserCollectionSingerVo;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.UserCollectionSingerServiceImpl;

@Controller
public class UserSingerController {
	@Autowired 
	private  SessionServiceImpl sessionServiceImpl;
	@Autowired
	private UserCollectionSingerServiceImpl userCollectionSingerServiceImpl;
	//用户收藏的歌手
	@RequestMapping("/userSinger")
	@ResponseBody
	public List<SingerPo> getUserSinger(HttpServletRequest request){
		//获取当前用户
		UserPo po = new UserPo();
		try {
			po = sessionServiceImpl.getCurrentUserPo(request);
		} catch (UserNotForSessionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取当前用户收藏歌手扩展对象
		List<UserCollectionSingerVo> list = new ArrayList<UserCollectionSingerVo>();
		try {
			list = userCollectionSingerServiceImpl.getUserCollectionById(po.getUserid());
		} catch (UserIdNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取当前用户收藏的歌手集合
        List<SingerPo> list2= new ArrayList<SingerPo>(); 
        for (UserCollectionSingerVo vo : list) {
        	
			list2.add(vo.getSingerPo());
		}
        return list2;
	}
	
	
	@RequestMapping("/giveupUserSinger")
	@ResponseBody
	public boolean giveupUserSinger(int singerId,HttpServletRequest request) throws UserNotForSessionException, SingerIdNotExistException{
		UserPo po = sessionServiceImpl.getCurrentUserPo(request);
		if(po==null) return false;
		int userId= po.getUserid();
		userCollectionSingerServiceImpl.notCollectionSinger(singerId, userId);
		return true;
	}
}
