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
import cn.exception.SongListIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.mapper.SongListPoMapper;
import cn.pojo.SongListPo;
import cn.pojo.UserPo;
import cn.service.FileService;
import cn.service.SessionService;
import cn.service.impl.SongListServiceImpl;

@Controller
public class UserSongListEditController {
	@Autowired
	SongListServiceImpl songListServiceImpl;
	@Autowired
	SessionService sessionService;
	@Autowired
	SongListPoMapper mapper;
	@Autowired
	FileService fileService;
	
	@RequestMapping("/editUserSongList")
	public @ResponseBody SongListPo editUserSongList(int songListId){
		//判断作者是否是当前用户
		SongListPo songListPo=new SongListPo();
		try {
			songListPo = songListServiceImpl.getShowSongPoById(songListId);
		} catch (SongListIdNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return songListPo;
	}
	
	//提交操作
	@RequestMapping("/submitEditUserSongList")
	public @ResponseBody boolean submitEditUserSongList(SongListPo po){
		mapper.updateByPrimaryKeySelective(po);
		return true;
	}
	
	//判断权限
	@RequestMapping("/isEditor")
	public @ResponseBody boolean isEditor(int songListId,HttpServletRequest request) throws UserNotForSessionException, SongListIdNotExistException{
		//判断作者是否是当前用户
		UserPo po = sessionService.getCurrentUserPo(request);
		if(po==null) return false;
		int id = songListServiceImpl.getShowSongPoById(songListId).getUserid();
		if(id==po.getUserid()){
			return true;
		}
		return false;
	}
	
	
	@RequestMapping("/editSongListImg")
	@ResponseBody
	public boolean editSongListImg(MultipartFile file,int songListId,HttpServletRequest request) throws FileUpUrlException, FileUpSizeOverException, SongListIdNotExistException{
		String name = fileService.FileUp(file,"songListCoverImg", request);
		SongListPo po = songListServiceImpl.getShowSongPoById(songListId);
		String oldImg = po.getImgurl();//得到以前的图片
		//修改到页面
		po.setImgurl(name);
		//修改到数据库
		mapper.updateByPrimaryKeySelective(po);
		//删除旧图片
		if(oldImg!=null){
			fileService.deletUserImg(oldImg, request);
		}
		
		//返回到页面显示
		return true;
	}
}
