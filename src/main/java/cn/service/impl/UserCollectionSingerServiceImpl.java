package cn.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.CollectionException;
import cn.exception.CollectionIdNotExistException;
import cn.exception.SingerIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.mapper.SingerPoMapper;
import cn.mapper.UserWithSingerPoMapper;
import cn.pojo.SingerPo;
import cn.pojo.UserWithSingerPo;
import cn.pojo.vo.UserCollectionSingerVo;
import cn.service.UserCollectionSingerService;

@Service
public class UserCollectionSingerServiceImpl implements UserCollectionSingerService {

	@Autowired
	UserWithSingerPoMapper userWithSingerPoMapper;
	@Autowired
	SingerPoMapper singerPoMapper;
	
	@Override
	public boolean notCollectionSinger(int singerId, int userId)
			throws SingerIdNotExistException {
		
		Map<String, Integer> map= new HashMap<String, Integer>();
		
		map.put("singerId", singerId);
		map.put("userId", userId);
		
		//取消收藏
		int result=userWithSingerPoMapper.deleteByUserIdAndSingerId(map);
		
		if(result>0){
			//收藏-1
			SingerPo singerPo=singerPoMapper.selectByPrimaryKey(singerId);
			
			if(singerPo==null){
				throw new SingerIdNotExistException("sigerId不存在");
			}
			
			int collectioncount=singerPo.getCollectioncount()-result;
			
			if(collectioncount<=0){
				collectioncount=0;
			}			
			singerPo.setCollectioncount(collectioncount);
			
			singerPoMapper.updateByPrimaryKey(singerPo);
			
			return true;
		}else{		
			return false;
		}
	}

	@Override
	public boolean CollectionSinger(int singerId, int userId)
			throws SingerIdNotExistException, CollectionException {
		//是否存在
		if(isConllectionForSingerId(singerId, userId)){
			return false;
		}
		//创建关联对象
		UserWithSingerPo po=new UserWithSingerPo();
		
		po.setSingerid(singerId);
		po.setUserid(userId);
		po.setCollectiondate(new Date());
		
		//收藏歌手
		int result=userWithSingerPoMapper.insert(po);
		
		if(result>0){
			//收藏+1
			SingerPo singerPo=singerPoMapper.selectByPrimaryKey(singerId);
			
			if(singerPo==null){
				throw new SingerIdNotExistException("sigerId不存在");
			}
			
			int collectioncount=singerPo.getCollectioncount()+result;

			singerPo.setCollectioncount(collectioncount);
			
			singerPoMapper.updateByPrimaryKey(singerPo);
				
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isConllectionForSingerId(int singerId, int userId)
			throws SingerIdNotExistException {
		//查询关联表
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("singerId", singerId);
		map.put("userId", userId);
		UserWithSingerPo po=userWithSingerPoMapper.selectBySingerIdAndUserId(map);
		if(po==null){
			return  false;
		}	
		else{
			return true;
		}

	}

	@Override
	public List<UserCollectionSingerVo> getUserCollectionById(int userId)
			throws UserIdNotExistException {
		//查询list集
		List<UserCollectionSingerVo> list=userWithSingerPoMapper.getSingerUserWithSingerVo(userId);	
		if (list==null) {
			throw new UserIdNotExistException("userId不存在");
		}
		
		return list;
	}

	@Override
	public UserWithSingerPo getUserCollectionByKey(int Key)
			throws CollectionIdNotExistException {
		
		//根据主键查询关联表
		UserWithSingerPo po=userWithSingerPoMapper.selectByPrimaryKey(Key);
		
		if(po==null){
			throw new CollectionIdNotExistException("collectionId不存在");
		}
		
		return po;
	}
	
}
