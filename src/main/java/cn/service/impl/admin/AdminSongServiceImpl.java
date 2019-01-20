package cn.service.impl.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.exception.AdminException;
import cn.exception.SingerIdNotExistException;
import cn.exception.SongDownStateException;
import cn.exception.SongIdNotExistException;
import cn.mapper.CDPoMapper;
import cn.mapper.SongPoMapper;
import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.SongPo;
import cn.pojo.vo.SongVo;
import cn.service.admin.AdminSongService;

/**
 * 
 * @author xiaoyefeng
 *
 */
@Service
public class AdminSongServiceImpl implements AdminSongService {
	@Autowired
	private SongPoMapper songPoMapper;
	@Autowired
	private CDPoMapper cdPoMapper;
	@Override
	//添加歌曲
	public boolean addSong(SongPo songPo) throws AdminException {
		//获取所有歌曲实体对象
		List<SongPo> list = songPoMapper.selectAllSong();
		//循环判断歌曲是否重复插入
		for (int i = 0; i < list.size(); i++) {
			if (songPo.getSongname() == list.get(i).getSongname()) {
				if (songPo.getPublishdate() == list.get(i).getPublishdate()) {
					throw new AdminException("错误:歌曲重复插入");
				}
			}
		}
		if(songPo.getCdid()!=null){
		CDPo cdPo = cdPoMapper.selectByPrimaryKey(songPo.getCdid());
		cdPo.setSongcount(cdPo.getSongcount()+1);
		cdPoMapper.updateByPrimaryKey(cdPo);
		}
		songPoMapper.insertSelective(songPo);
		return true;
	}

	@Override
	//修改歌曲信息
	public boolean updateSong(SongPo songPo) throws AdminException {
		//通过songPo中的songid判断是否存在该歌曲
		if (songPoMapper.selectByPrimaryKey(songPo.getSongid()) == null) {
			throw new AdminException("错误:歌曲ID不存在");
		} else {
			songPoMapper.updateByPrimaryKeySelective(songPo);
			return true;
		}
	}

	@Override
	//删除歌曲
	public boolean deleteSong(Integer songId) throws AdminException {
		//通过songId获取歌曲实体对象
		SongPo songPo = songPoMapper.selectByPrimaryKey(songId);		
		if (songPo == null) {
			throw new AdminException("错误:歌曲ID不存在");
		} else {
			CDPo cdPo = cdPoMapper.selectByPrimaryKey(songPo.getCdid());
			//将歌曲状态改为1(阻塞状态)
			songPo.setSongstateid(1);
			songPoMapper.updateByPrimaryKey(songPo);
			cdPo.setSongcount(cdPo.getSongcount()-1);
			cdPoMapper.updateByPrimaryKey(cdPo);
			return true;
		}
	}

	@Override
	//批量删除歌曲
	public int deleteSongs(List<Integer> list) throws AdminException {
		for (int i = 0; i < list.size(); i++) {
			//通过songId获取歌曲实体对象
			SongPo songPo = songPoMapper.selectByPrimaryKey(list.get(i));
			if (songPo == null) {
				throw new AdminException("错误:歌曲ID不存在");
			} else {
				//将歌曲状态改为1(阻塞状态)
				songPo.setSongstateid(1);
				songPoMapper.updateByPrimaryKey(songPo);
				
			}
		}
		return list.size();
	}

	@Override
	//获取所有歌曲实体对象，状态码不为阻塞（不显示）状态
	public List<SongPo> getShowSongPo() {
		//返回所有歌手实体对象
		return songPoMapper.selectAllSongNoBlock();
	}

	@Override
	//通过歌曲id得到一个实体对象，状态码不为阻塞（不显示）状态
	public SongPo getShowSongPoById(int songId) throws AdminException{
		//通过id获取歌手实体对象
		SongPo songPo = songPoMapper.selectByPrimaryKeyNoBlock(songId);
		//判断是否存在该songPo对象
		if (songPo == null) {
			//不存在 抛出SongIdNotExistException错误
			throw new AdminException("歌曲id不存在");
		} else {
			//返回歌手实体对象
			return songPo;
		}
	}

	 

	@Override
	//获取所有songVo对象，状态码不为阻塞（不显示）状态
	public List<SongVo> getShowSongVo() {
		//返回所有歌手实体扩展对象(SongVo)
		return songPoMapper.selectAllSongVo();
	}

	@Override
	//通过歌曲id获取一个songVo对象，状态码不为阻塞（不显示）状态
	public SongVo getShowSongVoById(int songId) throws AdminException {
		//通过id获取songVo对象
		SongVo songVo = songPoMapper.selectSongVoBySongId(songId);
		//判断是否存在该songVo对象
		if (songVo == null) {
			//不存在 抛出SongIdNotExistException错误
			throw new AdminException("歌曲id不存在");
		} else {
			//存在 返回songVo对象
			return songVo;
		}

	}
	//修改专辑
	public void updateCD(SongPo songPo){
	
	songPoMapper.updateByPrimaryKey(songPo);
	}

	 
 

	@Override
	//分页检索，关键字获取对应名称的songVo对象，状态码不为阻塞（不显示）状态
	public PageBasePo<SongVo> filter(SongVo songVo, int pageSize, int pageIndex) {
		//创建map对象存储搜索条件
		PageBasePo pageBasePo = new PageBasePo();
		pageBasePo.setIndexEntity(songVo); 
		Map map = new HashMap();
		if(songVo.getSongPo().getSongname()!=null){			
			map.put("songname", "%"+songVo.getSongPo().getSongname()+"%");
		}
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex-1)*pageSize);
		//通过搜索条件搜索到所需数据
		List<SongVo> list = songPoMapper.selectAllSongVoForPagination(map);
		//将数据存入分页对象
		pageBasePo.setList(list);
		int count = songPoMapper.selectCountAllSongVoForPagination(map);
		pageBasePo.setAllNum(count);  //总数量
		 
		pageBasePo.setPageCount((int) Math.ceil((double)count/pageSize));  //页数量
		
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setPageSize(pageSize);
		return pageBasePo;
	}

	@Override
	// 获取所有songVo对象，状态码不为阻塞（不显示）状态
	public int getShowSongCount() {
		// 返回所有歌手实体扩展对象(SongVo)
		return songPoMapper.selectSongCount();
	}
	
    //专辑Id，歌曲名查询歌曲
	public PageBasePo<SongVo> filterByCDIdAndSongName(Integer cdid, String search,Integer pageIndex, int pageSize) {
		if(pageIndex==null){
			pageIndex=1;
		}
		// TODO Auto-generated method stub
		PageBasePo pageBasePo = new PageBasePo();
		Map map = new HashMap();
		if(search!=null){			
			map.put("songname", "%"+search+"%");
		}
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex-1)*pageSize);
		map.put("cdid", cdid);
		//通过搜索条件搜索到所需数据
		List<SongVo> list = songPoMapper.selectSongByCDIdAndSongName(map);
		//将数据存入分页对象
		pageBasePo.setList(list);
		int count = songPoMapper.selectCountSongByCDIdAndSongName(map);
		pageBasePo.setAllNum(count);  //总数量	 
		pageBasePo.setPageCount((int) Math.ceil((double)count/pageSize));  //页数量
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setPageSize(pageSize);
		return pageBasePo;
	}

	public PageBasePo<SongVo> filterNotInCDBySingId(
			int singerId, Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		if(pageIndex==null){
			pageIndex=1;
		}
		// TODO Auto-generated method stub
		PageBasePo pageBasePo = new PageBasePo();
		Map map = new HashMap();
		map.put("singerid", singerId);
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex-1)*pageSize);
		map.put("cdid",null);
		//通过搜索条件搜索到所需数据
		List<SongVo> list = songPoMapper.selectNotInCDBySingId(map);
		//将数据存入分页对象
		pageBasePo.setList(list);
		int count = songPoMapper.selectCountNotInCDBySingId(map);
		pageBasePo.setAllNum(count);  //总数量	 
		pageBasePo.setPageCount((int) Math.ceil((double)count/pageSize));  //页数量
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setPageSize(pageSize);
		return pageBasePo;
		
	}	
	//查询已删除的歌曲
	public PageBasePo<SongVo> filterDeletedSong(String search,Integer pageIndex,Integer pageSize) {
		// TODO Auto-generated method stub
		if(pageIndex==null){
			pageIndex=1;
		}
		// TODO Auto-generated method stub
		PageBasePo pageBasePo = new PageBasePo();
		Map map = new HashMap();
		if(search!=null){
		search = "%"+search+"%";
		}
		map.put("songname", search);
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex-1)*pageSize);
		//通过搜索条件搜索到所需数据
		List<SongVo> list = songPoMapper.selectDeletedSong(map);
		//将数据存入分页对象
		pageBasePo.setList(list);
		int count = songPoMapper.selectCountDeletedSong(map);
		pageBasePo.setAllNum(count);  //总数量	 
		pageBasePo.setPageCount((int) Math.ceil((double)count/pageSize));  //页数量
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setPageSize(pageSize);
		return pageBasePo;
		
	}	
	
	//专辑删除歌曲
	public boolean CDDeleteSong(Integer cdId, Integer songId){
		 SongPo songPo =  songPoMapper.selectByPrimaryKey(songId);
		  CDPo cdPo =  cdPoMapper.selectByPrimaryKey(cdId);
		  cdPo.setSongcount(cdPo.getSongcount()-1);
		  //专辑中歌曲数量减一
		  cdPoMapper.updateByPrimaryKey(cdPo);
		  //修改专辑id为null，表示歌曲从专辑中删除
		  songPo.setCdid(null);
		  songPoMapper.updateByPrimaryKey(songPo);
		  return true;
	}
	
	//专辑添加歌曲
		public boolean CDAddSong(Integer cdId, Integer songId){
			 SongPo songPo =  songPoMapper.selectByPrimaryKey(songId);
			  CDPo cdPo =  cdPoMapper.selectByPrimaryKey(cdId);	
              songPo.setCdid(cdId);
    		  songPoMapper.updateByPrimaryKey(songPo);
			  //专辑中歌曲数量加一
    		  cdPo.setSongcount(cdPo.getSongcount()+1);
			  cdPoMapper.updateByPrimaryKey(cdPo);	
			  return true;
		}
}
