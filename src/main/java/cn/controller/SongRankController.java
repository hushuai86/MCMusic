package cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pojo.PageBasePo;
import cn.pojo.vo.SongVo;
import cn.service.impl.RankListServiceImpl;

@Controller
public class SongRankController {
	@Autowired
	RankListServiceImpl rankListServiceImpl;
	
	//查询所有新歌时间排行
	@RequestMapping("/getNewSongRank")
	public @ResponseBody List<SongVo> getNewSongRank(int pageSize,int pageIndex){
		//查询分页对象
		 PageBasePo<SongVo> pageBasePo=rankListServiceImpl.getNewSongRank(pageSize, pageIndex);
		 
		 //将分页对象中的list 赋值给要返回的Json对象
		 List<SongVo> list=pageBasePo.getList();
		 
		return list;
	}
	
	//收藏排序
	@RequestMapping("/getSongCollectionRank")
	public @ResponseBody List<SongVo> getSongCollectionRank(int pageSize,int pageIndex){
		//查询分页对象
		PageBasePo<SongVo> pageBasePo =rankListServiceImpl.getSongCollectionRank(pageSize, pageIndex);
		
		//将分页对象中的list 赋值给要返回的Json对象
		List<SongVo> list=pageBasePo.getList();
		
		return list;
	}
	
	//时间倒排(经典)
	@RequestMapping("/getOldSongRank")
	public  @ResponseBody List<SongVo> getOldSongRank(int pageSize,int pageIndex){
		//查询分页对象
		PageBasePo<SongVo> pageBasePo =rankListServiceImpl.getOldSongRank(pageSize, pageIndex);
		
		//将分页对象中的list 赋值给要返回的Json对象
		List<SongVo> list=pageBasePo.getList();
		
		return list;	
	}
	
	//热度排序
	@RequestMapping("/getSongHotRank")
	public  @ResponseBody List<SongVo> getSongHotRank(int pageSize,int pageIndex){
		//查询分页对象
		PageBasePo<SongVo> pageBasePo =rankListServiceImpl.getSongHotRank(pageSize, pageIndex);
		//将分页对象中的list 赋值给要返回的Json对象
		List<SongVo> list=pageBasePo.getList();
		
		return list;	
	}
	//热度语种排序
	@RequestMapping("/getSongHotRankByChinese")
	public  @ResponseBody List<SongVo> getSongHotRankByChinese(String language,int pageSize,int pageIndex){
		//查询分页对象
		//传入语种类别language
		PageBasePo<SongVo> pageBasePo =rankListServiceImpl.getSongHotRank(language, pageSize, pageIndex);
		
		//将分页对象中的list 赋值给要返回的Json对象
		List<SongVo> list=pageBasePo.getList();
		
		return list;	
	}
	
}
