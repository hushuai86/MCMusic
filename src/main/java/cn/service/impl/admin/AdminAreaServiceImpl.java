package cn.service.impl.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.AdminException;
import cn.exception.AreaIdNotExistException;
import cn.mapper.AreaPoMapper;
import cn.pojo.AreaPo;
import cn.pojo.vo.AreaVo;
import cn.service.admin.AdminAreaService;


/**
 * 后台地区服务接口
 * @author taz
 *
 */
@Service
public class AdminAreaServiceImpl  implements AdminAreaService{
	@Autowired
	private AreaPoMapper mapper;
    
	@Override

	public boolean addAreaOne(AreaPo area) throws AdminException{
		// TODO Auto-generated method stub
		
			int result= mapper.insert(area);
		    if(result==0){
			throw new AdminException("添加方法出错");
		    }
		 
		  return true;
	}

	@Override
	public boolean addAreaMutilArea(List<AreaPo> list) throws AdminException{
		// TODO Auto-generated method stub
		AreaPo areaPo = new AreaPo();
		for(int i=0;i<list.size();i++){
			areaPo = list.get(i);
			if(mapper.selectByAreaName(areaPo.getAreaname())!=null){
				continue;
			}
		      
				int result =  mapper.insert(areaPo);
			
				 if(result==0){
						throw new AdminException("添加方法出错");
				    }
			
		}
		return true;
	}

	@Override
	public boolean updateArea(AreaPo area) throws AdminException{
		// TODO Auto-generated method stub
		int result =  mapper.updateByPrimaryKeySelective(area);
		 if(result==0){
				throw new AdminException("修改方法出错");
			    }
		 return true;
		
	}
    
	@Override
	public List<AreaPo> getAllAreaPo() {
		// TODO Auto-generated method stub
		//获取所有地区实体对象
		List<AreaPo> list = mapper.selectAllAreaPo();
		return list;
	}


	@Override
	public AreaPo getAllAreaPoById(int areaId) throws AdminException{
		// TODO Auto-generated method stub
		//根据地区id到地区实体对象
		AreaPo areaPo = mapper.selectByPrimaryKey(areaId);
		//地区ID不存在抛异常
		if(areaPo==null){
			throw new AdminException("地区ID不存在");
		}
		return areaPo;
		
	}

	@Override
	public AreaPo getAllAreaPoByName(String areaName) {
		// TODO Auto-generated method stub
		//根据地区name得到地区实体
		AreaPo areaPo = mapper.selectByAreaName(areaName);
		return areaPo;
	}

	@Override
	public List<AreaVo> getAllAreaVo() {
		//获取所有地区的扩展类
		List<AreaVo> list =mapper.selectAllAreaVo();
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public AreaVo getAreaVoById(int areaId) throws AdminException {
		// TODO Auto-generated method stub
		//通过地区id获取地区扩展类
     	AreaVo  areaVo = mapper.selectAreaVoById(areaId);
     	//地区ID不存在抛异常
     	if(areaVo==null){
			throw new AdminException("地区ID不存在");
		}
		return areaVo;
	}



	
}
