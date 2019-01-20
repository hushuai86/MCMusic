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
import cn.exception.UserNotForSessionException;
import cn.mapper.CDPoMapper;
import cn.pojo.CDPo;
import cn.pojo.UserPo;
import cn.service.UserCollectionCDService;
import cn.service.impl.CDServiceImpl;
import cn.service.impl.SessionServiceImpl;


@Controller
public class SingerCdController {
	@Autowired
	private CDServiceImpl cdServiceImpl;
	
	// 单曲 分页	
	@RequestMapping("/getCdBySingerId")
	@ResponseBody
	public List<CDPo> getCdBySingerId(int singerId, int pageIndex)
			throws SingerIdNotExistException {	 
		return cdServiceImpl.getCDPo(singerId,12,pageIndex);
	}
	
	// 根据歌曲id得到数量	
	@RequestMapping("/getCdBySingerIdConut")
	@ResponseBody
	public int getCdBySingerIdCount(int singerId)
			throws SingerIdNotExistException {	 
		return cdServiceImpl.getCdBySingerIdCount(singerId);
	}

}
