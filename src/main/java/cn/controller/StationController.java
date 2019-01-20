package cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.exception.TypeIdNotExistException;
import cn.mapper.SongPoMapper;
import cn.pojo.TypePo;
import cn.pojo.vo.SongVo;
import cn.pojo.vo.TypeVo;

import cn.service.SongService;
import cn.service.impl.TypeServiceImpl;


@Controller
public class StationController {
	@Autowired
	TypeServiceImpl typeServiceImpl;
	@Autowired
	private SongService songService;
	@Autowired
	private SongPoMapper mapper;
	@RequestMapping("/selectCountByTypeId")
	public @ResponseBody int selectCountByTypeId(int typeId){
		//歌曲按类型分类和数量
		return mapper.selectCountByTypeId(typeId);
	}
	
	@RequestMapping("/getTypeName")
	public @ResponseBody TypePo getTypeName(int typeId) throws TypeIdNotExistException{
		//歌曲按类型分类和数量
		return typeServiceImpl.getTypePoById(typeId);
	}
	
	@RequestMapping("/getTypeNameForSong")
	public @ResponseBody List<SongVo> getTypeNameForSong(int typeId,int pageSize,int pageIndex){
		//歌曲按类型分类和数量
		return songService.getRadioStation(typeId,pageSize,pageIndex);
	}
	
}
