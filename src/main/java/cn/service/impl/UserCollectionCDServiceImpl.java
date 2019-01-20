package cn.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.CDIdNotExistException;
import cn.exception.CollectionException;
import cn.exception.CollectionIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.mapper.CDPoMapper;
import cn.mapper.UserWithCDPoMapper;
import cn.pojo.CDPo;
import cn.pojo.UserWithCDPo;
import cn.pojo.UserWithSongPo;
import cn.pojo.vo.UserCollectionCDVo;
import cn.service.UserCollectionCDService;
@Service
public class UserCollectionCDServiceImpl implements UserCollectionCDService {
	
	@Autowired 
	UserWithCDPoMapper userWithCDPoMapper;
	@Autowired
	CDPoMapper cdPoMapper;
	@Override
	public boolean isConllectionForCDId(int CDId, int userId)
			throws CDIdNotExistException {
		//根据CDId查询
		Map map = new HashMap();
		map.put("cdId", CDId);
		map.put("userId", userId);
		UserWithCDPo po = userWithCDPoMapper
				.selectByUserIdAndcdId(map);

		if (po == null) {
			return false;
		} else {
			return true;
		}
			
		
	}

	@Override
	public boolean CollectionCD(int CDId, int userId)
			throws CDIdNotExistException, CollectionException {
		//是否存在
		if(isConllectionForCDId(CDId, userId)){
			return false;
		}else{
			UserWithCDPo po= new UserWithCDPo();
			po.setCdid(CDId);
			po.setUserid(userId);
			po.setCollectiondate(new Date());
			
			int result=userWithCDPoMapper.insert(po);
			//收藏次数+1
			CDPo cdPo=cdPoMapper.selectByPrimaryKey(CDId);
			
			cdPo.setCollectioncount(cdPo.getCollectioncount()+result);
			
			cdPoMapper.updateByPrimaryKeySelective(cdPo);
		}
		return true;
	}

	@Override
	public boolean notCollectionCD(int CDId, int userId)
			throws CDIdNotExistException {
		
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("CDId", CDId);
		map.put("userId", userId);
		
		int result=userWithCDPoMapper.deleteByCDIdAndUserId(map);
		
		if (result>0) {
			//收藏次数-1
			CDPo cdPo=cdPoMapper.selectByPrimaryKey(CDId);
			
			if(cdPo==null){
				throw new CDIdNotExistException("CDId不存在");
			}
			
			int count=cdPo.getCollectioncount()-result;
			if(count<=0){
				count=0;
			}
			cdPo.setCollectioncount(count);
			
			cdPoMapper.updateByPrimaryKeySelective(cdPo);
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<UserCollectionCDVo> getUserCollectionById(int userId)
			throws UserIdNotExistException {		
		// 获取拓展集合
		List<UserCollectionCDVo> cdVos=userWithCDPoMapper.getUserWithCDVos(userId);	
		
		if (cdVos==null) {
			throw new UserIdNotExistException("userId不存在");
		}
		
		return cdVos;
	}

	@Override
	public UserWithCDPo getUserCollectionByKey(int Key)
			throws CollectionIdNotExistException {	
		//通过主键查询Po
		UserWithCDPo  po= userWithCDPoMapper.selectByPrimaryKey(Key);
		if(po==null){
			throw new CollectionIdNotExistException("collectionId不存在");
		}
		
		return po;
	}
	
}
