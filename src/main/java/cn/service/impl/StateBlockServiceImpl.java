package cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.StateBlockException;
import cn.mapper.CDPoMapper;
import cn.mapper.SingerPoMapper;
import cn.mapper.SongPoMapper;
import cn.pojo.CDPo;
import cn.pojo.SingerPo;
import cn.pojo.SongPo;
import cn.service.StateBlockService;

@Service
public class StateBlockServiceImpl implements StateBlockService {
	
	
	@Autowired
	SingerPoMapper singerPoMapper;
	@Autowired
	SongPoMapper songPoMapper;
	@Override
	public boolean singerWithSongStateBlock(int singerId)
			throws StateBlockException {
		
		//修改歌手状态
		SingerPo po= singerPoMapper.selectByPrimaryKey(singerId);
		
		po.setSingerstateid(1);
		
		singerPoMapper.updateByPrimaryKeySelective(po);
		 
		//根据singerId修改song的状态
		List<SongPo> pos=songPoMapper.selectSongPosBySingerId(singerId);
		//设置阻塞状态
		for (SongPo songPo : pos) {
			songPo.setSongstateid(1);
			int result=songPoMapper.updateByPrimaryKeySelective(songPo);
			if (result<=0) {
				throw new StateBlockException("阻塞状态失败");
			}
		}
		return true;
	}
	
	@Autowired
	CDPoMapper cdPoMapper;
	@Override
	public boolean singerWithCdStateBlock(int singerId)
			throws StateBlockException {
		//修改歌手状态
		SingerPo po= singerPoMapper.selectByPrimaryKey(singerId);
				
		po.setSingerstateid(1);
				
		singerPoMapper.updateByPrimaryKeySelective(po);
		
		//根据singerId修改CD的状态
		List<CDPo> pos=cdPoMapper.selecCdPosBySingerId(singerId);
		for (CDPo cdPo : pos) {
			cdPo.setCdstateid(1);
			int result=cdPoMapper.updateByPrimaryKeySelective(cdPo);
			if (result<=0) {
				throw new StateBlockException("阻塞状态失败");
			}
		}
		return true;
	}
	
	@Override
	public boolean CdWithSongStateBlock(int CDId) throws StateBlockException {
		//修改CD状态
		CDPo po=cdPoMapper.selectByPrimaryKey(CDId);
		po.setCdstateid(1);
		cdPoMapper.updateByPrimaryKeySelective(po);

		//根据CDid修改song的状态
		List<SongPo> pos=songPoMapper.selectSongPosByCDId(CDId);
		for (SongPo songPo : pos) {
			songPo.setSongstateid(1);
			int result=songPoMapper.updateByPrimaryKeySelective(songPo);
			if (result<=0) {
				throw new StateBlockException("阻塞状态失败");
			}
		}	
		return true;
	}

}
