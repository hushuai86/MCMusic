package cn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pojo.AreaPo;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.SongPo;
import cn.pojo.vo.SongVo;
import cn.service.impl.AreaServiceImpl;
import cn.service.impl.SingerServiceImpl;

@Controller
public class SingerListController {
	@Autowired 
	private SingerServiceImpl serviceImpl;
	@Autowired 
	private AreaServiceImpl areaService;
	@RequestMapping("/allSingers")
	//所有歌手   根据地区id 歌手性别 首字母 查询 根据 出道时间(最新) 收藏量 访问量 排序
	//传入singerPo对象  其中有查询条件   firstLetter代表首字母 pageIndex 索引
	public @ResponseBody  PageBasePo<SingerPo> allSingers( SingerPo singerPo,String firstLetter,int pageIndex){	
		//界面固定出15个歌手  所以pageSize为15
		return serviceImpl.filter(singerPo, 15, pageIndex, firstLetter);
	}
	
	@RequestMapping("/singersCount")
	public @ResponseBody  int singersCount( SingerPo singerPo,String firstLetter,int pageIndex){	
		return serviceImpl.filter(singerPo, 15, pageIndex, firstLetter).getAllNum();
	}

	@RequestMapping("/allArea")
	public @ResponseBody List<AreaPo> allArea(){	
		//所有地区
		return areaService.getAllAreaPo();
	}
}
