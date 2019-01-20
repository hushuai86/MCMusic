package cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.pojo.PageBasePo;
import cn.pojo.vo.SongListVo;
import cn.service.impl.RankListServiceImpl;

@Controller
public class SongListRankController {
	@Autowired
	RankListServiceImpl rankListServiceImpl;
	
	//热度排行
	@RequestMapping("/getCollectionSongListRank")
	public @ResponseBody List<SongListVo> getCollectionSongListRank(int pageIndex,int pageSize){
		//查询分页对象
		PageBasePo<SongListVo> pageBasePo=rankListServiceImpl.getCollectionSongListRank(pageSize, pageIndex);
		
		//将分页对象中的list 赋值给要返回的Json对象
		List<SongListVo> list=pageBasePo.getList();
		
		return list;	
	}
	//热度排行
	@RequestMapping("/getHotSongListRank")
	public @ResponseBody List<SongListVo> getHotSongListRank(int pageIndex,int pageSize){
		//查询分页对象
		PageBasePo<SongListVo> pageBasePo=rankListServiceImpl.getHotSongListRank(pageSize, pageIndex);
		
		//将分页对象中的list 赋值给要返回的Json对象
		List<SongListVo> list=pageBasePo.getList();
		
		return list;	
	}
	
	//最新排行
	@RequestMapping("/selectSongListBySongListId")
	public @ResponseBody List<SongListVo> selectSongListBySongListId(int pageIndex,int pageSize){
		//查询分页对象
		PageBasePo<SongListVo> pageBasePo=rankListServiceImpl.getNewHotSongListRank(pageSize, pageIndex);
		
		//将分页对象中的list 赋值给要返回的Json对象
		List<SongListVo> list=pageBasePo.getList();
		
		return list;	
	}
	
	
}
