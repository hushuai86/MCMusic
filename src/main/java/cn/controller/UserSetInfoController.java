package cn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.exception.FileUpSizeOverException;
import cn.exception.FileUpUrlException;
import cn.exception.UserFormatException;
import cn.exception.UserNotForSessionException;
import cn.mapper.UserPoMapper;
import cn.pojo.UserPo;
import cn.service.FileService;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.UserServiceImpl;

@Controller
public class UserSetInfoController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserPoMapper mapper;
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	@Autowired
	private FileService fileService;
	//用户信息修改
	@RequestMapping("/changeUserInfo")
	@ResponseBody
	public void changeUserInfo(UserPo userPo,HttpServletRequest request) throws UserFormatException, UserNotForSessionException{
		userServiceImpl.changeUserInfo(userPo);
		
		UserPo po = sessionServiceImpl.getCurrentUserPo(request);
		po.setUsername(userPo.getUsername());
		po.setSign(userPo.getSign());
		po.setUsersex(userPo.getUsersex());
	}
	
	@RequestMapping("/headImg")
	@ResponseBody
	public Map<String,String> headImg(MultipartFile file,HttpServletRequest request) throws FileUpUrlException, FileUpSizeOverException, UserNotForSessionException{
		String name = fileService.FileUp(file,"userHeadImg", request);
		
		UserPo po = sessionServiceImpl.getCurrentUserPo(request);
		String oldImg = po.getHeadsculptureurl();//得到以前的图片
		//修改到页面
		po.setHeadsculptureurl(name);
		sessionServiceImpl.setcurrentUserPo(request, po);
		//修改到数据库
		mapper.updateByPrimaryKeySelective(po);
		//删除旧图片
		if(oldImg!=null&&oldImg!=""){
			fileService.deletUserImg(oldImg, request);
		}
		//返回到页面显示
		Map<String, String> map = new HashMap<String, String>();
		map.put("imgName", name);
		return map;
	}
}
