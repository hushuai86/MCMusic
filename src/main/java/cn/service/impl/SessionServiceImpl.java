package cn.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.UserNotForSessionException;
import cn.mapper.CDPoMapper;
import cn.mapper.SingerPoMapper;
import cn.mapper.SongListPoMapper;
import cn.mapper.SongPoMapper;
import cn.mapper.UserPoMapper;
import cn.pojo.CDPo;
import cn.pojo.SingerPo;
import cn.pojo.SongListPo;
import cn.pojo.SongPo;
import cn.pojo.UserPo;
import cn.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService{
	@Autowired
	CDPoMapper cdPoMapper;
	@Autowired
	SongPoMapper songPoMapper;
	@Autowired
	SingerPoMapper singerPoMapper;
	@Autowired
	SongListPoMapper songListPoMapper;
	
	@Override
	public UserPo getCurrentUserPo(HttpServletRequest request)
			throws UserNotForSessionException {
		//获取请求的session对象
		HttpSession session=request.getSession();
		//通过session来取里面的值
		UserPo userPo=(UserPo) session.getAttribute("user");
		return userPo;
	}

	@Override
	public void setcurrentUserPo(HttpServletRequest request, UserPo user) {
		//获取请求的session对象
		HttpSession session=request.getSession();
		//将user设置到session中
		session.setAttribute("user", user);
	}
	
	@Override
	public void setAllIndexWordForEntity(String index,
			HttpServletRequest request) {
		//获取请求的session对象
		HttpSession session=request.getSession();
		
		//获取session中的关键词indexCD，indexSinger，indexSong，indexSongList 然后根据ID分别进行检索实体对象
		String name=(String) session.getAttribute(index);
		//CDPo
		CDPo cdPo=cdPoMapper.selectByCDName(name);
		System.out.println(cdPo);

		//SingerPo
		SingerPo singerPo=singerPoMapper.selectBySingerName(name);
		System.out.println(singerPo);
		
		//SongPo
		SongPo songPo=songPoMapper.selectBySongName(name);
		System.out.println(songPo);
		//SongListPo
		SongListPo songListPo=songListPoMapper.selectBySongListName(name);
		System.out.println(songListPo);
	}

	@Override
	public void clearIndexWord(HttpServletRequest request) {
		//获取session
		HttpSession session=request.getSession();
		//清空session
		session.invalidate();
		//将session赋值为null
		session.setAttribute("index", null);	
	}

}
