package cn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.CDIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.mapper.SingerPoMapper;
import cn.pojo.UserPo;
import cn.pojo.vo.UserCollectionCDVo;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.UserCollectionCDServiceImpl;


@Controller
public class UserCdController {
	@Autowired
	private UserCollectionCDServiceImpl userCollectionCDServiceImpl;
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	@Autowired
	private SingerPoMapper mapper;
	//我收藏的专辑
	@RequestMapping("/getCollectionCd")
	@ResponseBody
	public List<UserCollectionCDVo>  getCollectionCd(HttpServletRequest request) throws UserIdNotExistException, UserNotForSessionException{	
		UserPo po = sessionServiceImpl.getCurrentUserPo(request);
		if(po==null) return null;
		int userId = po.getUserid();
		//得到所有收藏对象
		List<UserCollectionCDVo> list = userCollectionCDServiceImpl.getUserCollectionById(userId);
		//注入作者
		List<UserCollectionCDVo> newList = new ArrayList<UserCollectionCDVo>();
		for(UserCollectionCDVo vo : list){
			int singerId = vo.getCdPo().getSingerid();
			String singerName = mapper.selectByPrimaryKey(singerId).getSingername();
			vo.setSingerName(singerName);
			newList.add(vo);
		}
		return 	newList;
	}
	
	
	@RequestMapping("/giveUpCollectionCd")
	@ResponseBody
	public boolean giveUpCollectionCd(int cdId,HttpServletRequest request) throws UserIdNotExistException, UserNotForSessionException, CDIdNotExistException{	
		UserPo po = sessionServiceImpl.getCurrentUserPo(request);
		if(po==null) return false;
		int userId = po.getUserid();
		
		userCollectionCDServiceImpl.notCollectionCD(cdId, userId);
		return true;
	}
}
