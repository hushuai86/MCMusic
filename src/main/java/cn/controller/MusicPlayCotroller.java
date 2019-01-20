package cn.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.exception.CDIdNotExistException;
import cn.exception.CollectionException;
import cn.exception.SingerIdNotExistException;
import cn.exception.SongIdNotExistException;
import cn.exception.SongListIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.mapper.SingerPoMapper;
import cn.mapper.SongPoMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.SongPo;
import cn.pojo.UserPo;
import cn.pojo.vo.SongVo;
import cn.service.SessionService;
import cn.service.SongService;
import cn.service.UserCollectionSongService;
import cn.service.impl.FileServiceImpl;
import cn.service.impl.RankListServiceImpl;
import cn.service.impl.SessionServiceImpl;
import cn.service.impl.SongListServiceImpl;
import cn.service.impl.UserCollectionSongListServiceImpl;


@Controller
public class MusicPlayCotroller {
	@Autowired
	private SessionServiceImpl  sessionServiceImpl;
	@Autowired
	private SongService songService;
	@Autowired
	private SongPoMapper songMapper;
	@Autowired
	private SongListServiceImpl songListServiceImpl;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private FileServiceImpl fileService;
	@Autowired
	private UserCollectionSongService userCollectionSongService;
	@Autowired
	private SingerPoMapper mapper;
	//播放选中音乐
	@RequestMapping("/musicPlay")
	public String play(int[] songId,HttpServletRequest request) throws SongIdNotExistException, UserNotForSessionException{
		List<SongVo> songList = new ArrayList<SongVo>();
		for(int id:songId){
			SongVo songVo = songService.getShowSongVoById(id);
			//热度+1
			SongPo po = songVo.getSongPo();
			po.setPlaycount(po.getPlaycount()+1);
			songMapper.updateByPrimaryKeySelective(po);
			
			//设置是否收藏并进行状态设置
			UserPo userPo = sessionService.getCurrentUserPo(request);
			if(userPo!=null){
				boolean flag = userCollectionSongService.isConllectionForSongId(id, userPo.getUserid());
				songVo.setCollection(flag);
			}
			songList.add(songVo);
		}

		request.setAttribute("songList", songList);
		return "mc";
	}
	
	//播放歌手歌曲
	@RequestMapping("/musicPlayForSinger")
	public String playForSinger(int singerId,HttpServletRequest request) throws SongIdNotExistException, UserNotForSessionException{
		try {
			List<SongVo> songList = songService.getHonSongBySingerId(singerId);
			//热度+1
			for(SongVo songVo : songList){
				SongPo po = songVo.getSongPo();
				po.setPlaycount(po.getPlaycount()+1);
				songMapper.updateByPrimaryKeySelective(po);
			}
			//传递到页面
			request.setAttribute("songList", songList);
		} catch (SingerIdNotExistException e) {
			e.printStackTrace();
		}
		return "mc";
	}
	
	//播放专辑歌曲
	@RequestMapping("/musicPlayForCD")
	public String PlayForCD(int cdId,HttpServletRequest request) throws CDIdNotExistException{
		List<SongVo> songList = songService.getSongByCDId(cdId);
		//热度+1
		for(SongVo songVo : songList){
			SongPo po = songVo.getSongPo();
			po.setPlaycount(po.getPlaycount()+1);
			songMapper.updateByPrimaryKeySelective(po);
		}
		request.setAttribute("songList", songList);
		return "mc";
	}
	
	//播放歌单歌曲
	@RequestMapping("/musicPlayForList")
	public String PlayForSongList(int songListId,HttpServletRequest request) throws SongListIdNotExistException{
		List<SongVo> songList = new ArrayList<>();
		for(SongPo po : songListServiceImpl.getShowSongListVoById(songListId).getList()){
			//添加歌曲
			SongVo vo = new SongVo();
			vo.setSongid(po.getSongid());
			vo.setSongPo(po);
			//添加歌手名称
			vo.setSingPo(mapper.selectByPrimaryKey(po.getSingerid()));
			songList.add(vo);
		}
		//热度+1
		for(SongVo songVo : songList){
			SongPo po = songVo.getSongPo();
			po.setPlaycount(po.getPlaycount()+1);
			songMapper.updateByPrimaryKeySelective(po);
		}
		request.setAttribute("songList", songList);
		return "mc";
	}
	
	
	
	
	//多文件下载
	@RequestMapping("/musicDown")
	public ResponseEntity<byte[]> down(int[] songId,HttpServletRequest request) throws IOException, SongIdNotExistException{
		List<String> list = new ArrayList<String>();//存储下载文件列表
		for(int id:songId){
			//得到对应的下载地址
			SongPo songPo = songService.getShowSongPoById(id);
			String url = songPo.getSongurl();
			list.add(url);
			//下载次数+1
			songPo.setDownloadcount(songPo.getDownloadcount()+1);
			songMapper.updateByPrimaryKeySelective(songPo);
		}
		return fileService.FileDown(list, request);
	}
	
	//是否已经收藏
	@RequestMapping("/isConllectionForSong")
	@ResponseBody
	public boolean isConllectionForSong(int songId,HttpServletRequest request) throws UserNotForSessionException, SongIdNotExistException{	
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			return userCollectionSongService.isConllectionForSongId(songId, userPo.getUserid());
		}
		return false;
	}
	
	//批量收藏
	@RequestMapping("/addsSongCollection")
	@ResponseBody
	public boolean addsSongCollection(int[] songId,HttpServletRequest request) throws UserNotForSessionException, SongIdNotExistException, CollectionException{
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			//方法中已经收藏+1
			for(int id:songId){
				userCollectionSongService.CollectionSong(id, userPo.getUserid());
			}
			return true;
		}
		return false;
	}
	
	
	//收藏
	@RequestMapping("/addSongCollection")
	@ResponseBody
	public boolean addSongCollection(int songId,HttpServletRequest request) throws UserNotForSessionException, SongIdNotExistException, CollectionException{
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			//方法中已经收藏+1
			userCollectionSongService.CollectionSong(songId, userPo.getUserid());
			return true;
		}
		return false;
	}
	
	//取消收藏
	@RequestMapping("/removeSongCollection")
	@ResponseBody
	public boolean removeSongCollection(int songId,HttpServletRequest request) throws UserNotForSessionException, SongIdNotExistException, CollectionException{
		UserPo userPo = sessionServiceImpl.getCurrentUserPo(request);
		if(userPo!=null){
			//方法中已经收藏+1
			userCollectionSongService.notCollectionSong(songId, userPo.getUserid());
			return true;
		}
		return false;
	}
	
	
	
}
