package cn.service.impl;


import java.awt.geom.Area;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.AreaIdNotExistException;
import cn.mapper.AreaPoMapper;
import cn.pojo.AreaPo;
import cn.pojo.vo.AreaVo;
import cn.service.AreaService;

/**
 * 地区服务接口实现
 * @author hushuai
 *
 */
@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaPoMapper mapper;
	@Override
	public List<AreaPo> getAllAreaPo() {
		
		//获取所有地区实体对象
		List<AreaPo> list = mapper.selectAllAreaPo();
		return list;
	}

	@Override
	public AreaPo getAllAreaPoById(int areaId) throws AreaIdNotExistException {
		
		//根据地区id到地区实体对象
		AreaPo areaPo = mapper.selectByPrimaryKey(areaId);
		//地区ID不存在抛异常
		if(areaPo==null){
			throw new AreaIdNotExistException("地区ID不存在");
		}
		return areaPo;
		
	}

	@Override
	public AreaPo getAllAreaPoByName(String areaName) {
		
		//根据地区name得到地区实体
		AreaPo areaPo = mapper.selectByAreaName(areaName);
		return areaPo;
	}

	@Override
	public List<AreaVo> getAllAreaVo() {
		//获取所有地区的扩展类
		List<AreaVo> list =mapper.selectAllAreaVo();
		
		return list;
	}

	@Override
	public AreaVo getAreaVoById(int areaId) throws AreaIdNotExistException {
		
		//通过地区id获取地区扩展类
     	AreaVo  areaVo = mapper.selectAreaVoById(areaId);
     	//地区ID不存在抛异常
     	if(areaVo==null){
			throw new AreaIdNotExistException("地区ID不存在");
		}
		return areaVo;
	}

 
}
