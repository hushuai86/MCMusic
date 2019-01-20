package cn.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.SongListAddPutException;
import cn.exception.SongListIdNotExistException;
import cn.mapper.SongListPoMapper;
import cn.mapper.SongListWithSongPoMapper;
import cn.mapper.UserWithSongListPoMapper;
import cn.pojo.SongListPo;
import cn.pojo.SongListWithSongPo;
import cn.pojo.UserWithSongListPo;
import cn.service.SongListManageService;

@Service
public class SongListManagerServiceImpl implements SongListManageService {

	@Autowired
	SongListPoMapper songListPoMapper;
	@Autowired
	UserWithSongListPoMapper userWithSongListPoMapper;
	@Autowired
	SongListWithSongPoMapper songListWithSongPoMapper;
	
	@Override
	public boolean createSongList(SongListPo songListPo)
			throws SongListIdNotExistException {
		//创建歌单
		int result=songListPoMapper.insert(songListPo);

		//创建收藏对象
		UserWithSongListPo po=new UserWithSongListPo();
		po.setUserid(songListPo.getUserid());
		po.setUsonglistid(songListPo.getSonglistid());
		po.setCollectiondate(new Date());
		//创建收藏记录
		int insert=userWithSongListPoMapper.insert(po);
		if (insert<=0) {
			throw new SongListIdNotExistException("songListId不存在");
		}
		//判断返回状态
		if(result>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean giveUpSongList(int songListId) {
			//查询该表单
		SongListPo po=songListPoMapper.selectByPrimaryKey(songListId);

		if(po.getCollectioncount()<=1){
			
			//删除收藏表数据
			userWithSongListPoMapper.deleteBySongListId(songListId);
			
			//删除歌单表
			songListPoMapper.deleteByPrimaryKey(songListId);

			return false;
		}else{
			
			//删除收藏表数据
			userWithSongListPoMapper.deleteBySongListId(songListId);
			return true;
		}
	}

	
	@Override
	public boolean isAddSongForSongList(int songId, int songListId) {
		Map map = new HashMap();
		map.put("songId", songId);
		map.put("songListId", songListId);
		//根据歌曲ID查询关联表中Po对象
		SongListWithSongPo po =songListWithSongPoMapper.selectById(map);
		//如果查的对象和songListId则表示已经添加到歌单
		if(po!=null){
			return true;
		}	
		return false;
	}

	@Override
	public boolean addSongForSongList(int songId, int songListId)
			throws SongListAddPutException {
		if(isAddSongForSongList(songId,songListId)){
			return false;
		}else{
			//创建歌单歌曲表对象
			SongListWithSongPo po=new SongListWithSongPo();
			
			//给对象赋值
			po.setSongid(songId);
			po.setSonglistid(songListId);
			po.setCollectiondate(new Date());
			
			//给歌单添加歌曲
			int result=songListWithSongPoMapper.insert(po);
			
			if(result<=0){
				throw new SongListAddPutException("添加异常");
			}
			
			//插入成功返回true
			if(result>0){
				return true;
			}else{
		
			return false;
			}
		}
		
	}
	@Override
	public boolean putSongForSongList(int songId, int songListId)
			throws SongListAddPutException {
		
		Map<String,Integer> map=new HashMap<String, Integer>();
		map.put("songId", songId);
		map.put("songListId", songListId);
		
		//根据ID删除歌曲歌单
		int result=songListWithSongPoMapper.deleteBySongIdAndSongListId(map);
		
		if(result<=0){
			throw new SongListAddPutException("添加异常");
		}
		
		if(result>0){
			return true;
		}
		return false;
	}

}
