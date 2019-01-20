package cn.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.exception.CDIdNotExistException;
import cn.exception.SingerIdNotExistException;
import cn.exception.SongDownStateException;
import cn.exception.SongIdNotExistException;
import cn.mapper.SongPoMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SongPo;
import cn.pojo.vo.SongVo;
import cn.service.SongService;

/**
 * 歌曲服务接口实现
 * @author xiaoyefeng
 *
 */
@Service
public class SongServiceImpl implements SongService {
	@Autowired
	private SongPoMapper songPoMapper;
	
	@Override
	//播放次数加一 添加事务
	public long addPlayCount(int songId) throws SongIdNotExistException {
		//通过id获取songPo对象
		SongPo songPo = songPoMapper.selectByPrimaryKey(songId);
		//判断歌曲id是否存在
		if (songPo == null) { 
			//不存在 抛出SongIdNotExistException错误
			throw new SongIdNotExistException("歌曲id不存在");
		} else {
			//存在  播放次数加1
			songPo.setPlaycount(songPo.getPlaycount() + 1);
			//更新数据库数据
			songPoMapper.updateByPrimaryKey(songPo);
			//返回当前次数
			return songPo.getPlaycount();
		}
	}

	@Override
	//下载次数加一 添加事务
	public long addDownloadCount(int songId) throws SongIdNotExistException {
		//通过id获取songPo对象
		SongPo songPo = songPoMapper.selectByPrimaryKey(songId);
		//判断歌曲id是否存在
		if (songPo == null) {
			//不存在 抛出SongIdNotExistException错误
			throw new SongIdNotExistException("歌曲id不存在");
		} else {
			//存在  下载次数加1
			songPo.setDownloadcount(songPo.getDownloadcount() + 1);
			//更新数据库数据
			songPoMapper.updateByPrimaryKey(songPo);
			//返回当前下载次数
			return songPo.getDownloadcount();
		}

	}

	@Override
	//下载次数加一 添加事务
	public long addCollectionCount(int songId) throws SongIdNotExistException {
		//通过id获取songPo对象
		SongPo songPo = songPoMapper.selectByPrimaryKey(songId);
		//判断歌曲id是否存在
		if (songPo == null) {
			//不存在 抛出SongIdNotExistException错误
			throw new SongIdNotExistException("歌曲id不存在");
		} else {
			//存在  收藏次数加1
			songPo.setCollectioncount(songPo.getCollectioncount() + 1);
			//更新数据库数据
			songPoMapper.updateByPrimaryKey(songPo);
			//返回当前收藏次数
			return songPo.getCollectioncount();
		}
	}

	@Override
	//收藏次数减一 添加事务
	public long cutCollectionCount(int songId) throws SongIdNotExistException {
		//通过id获取songPo对象
		SongPo songPo = songPoMapper.selectByPrimaryKey(songId);
		//判断歌曲id是否存在
		if (songPo == null) {
			//不存在 抛出SongIdNotExistException错误
			throw new SongIdNotExistException("歌曲id不存在");
		} else {
			//存在  收藏次数减1
			songPo.setCollectioncount(songPo.getCollectioncount() - 1);
			//更新数据库数据
			songPoMapper.updateByPrimaryKey(songPo);
			//返回当前收藏次数
			return songPo.getCollectioncount();
		}
	}

	@Override
	//获取所有歌曲实体对象，状态码不为阻塞（不显示）状态
	public List<SongPo> getShowSongPo() {
		//返回所有歌手实体对象
		return songPoMapper.selectAllSongNoBlock();
	}

	@Override
	//通过歌曲id得到一个实体对象，状态码不为阻塞（不显示）状态
	public SongPo getShowSongPoById(int songId) throws SongIdNotExistException {
		//通过id获取歌手实体对象
		SongPo songPo = songPoMapper.selectByPrimaryKeyNoBlock(songId);
		//判断是否存在该songPo对象
		if (songPo == null) {
			//不存在 抛出SongIdNotExistException错误
			throw new SongIdNotExistException("歌曲id不存在");
		} else {
			//返回歌手实体对象
			return songPo;
		}
	}

	@Override
	//通过当前用户权限和歌曲状态，判断是否可以下载
	public boolean isSongDown(int songId) throws SongDownStateException {
		//判断当前歌曲状态
		if (songPoMapper.selectSongStateId(songId) != 2) {
			return true;
		} else {
			throw new SongDownStateException("歌曲状态异常");
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
	public SongVo getShowSongVoById(int songId) throws SongIdNotExistException {
		//通过id获取songVo对象
		SongVo songVo = songPoMapper.selectSongVoBySongId(songId);
		//判断是否存在该songVo对象
		if (songVo == null) {
			//不存在 抛出SongIdNotExistException错误
			throw new SongIdNotExistException("歌曲id不存在");
		} else {
			//存在 返回songVo对象
			return songVo;
		}

	}

	@Override
	//根据歌手id得到其所有歌曲数量
	public int getSongCountBySingerId(int SingerId)
			throws SingerIdNotExistException {
		//通过id得到其所有歌曲数量
		int count = songPoMapper.countBySingerId(SingerId);
		return count;

	} 

	@Override
	//得到热门歌曲（热度排序）默认得到20首歌曲
	public List<SongVo> getHonSongBySingerId(int SingerId)
			throws SingerIdNotExistException {
		// 通过id获取songvo集合对象
		List<SongVo> songVos = songPoMapper.selectSongVoBySingerId(SingerId);
		//判断songVos对象中是否存在数据
		if (songVos.size() == 0) {
			return null;
		} else {
			return songVos;
		}
	}

	@Override
	//分页检索，关键字获取对应名称的songVo对象，状态码不为阻塞（不显示）状态
	public PageBasePo<SongVo> filter(SongVo songVo, int pageSize, int pageIndex) {
		//创建map对象存储搜索条件
		PageBasePo pageBasePo = new PageBasePo();
		pageBasePo.setIndexEntity(songVo); 
		Map map = new HashMap();
		map.put("songname", songVo.getSongPo().getSongname());
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex-1)*pageSize);
		//通过搜索条件搜索到所需数据
		List<SongVo> list = songPoMapper.selectAllSongVoForPagination(map);
		//将数据存入分页对象
		pageBasePo.setList(list);
		
		pageBasePo.setAllNum(list.size());  //总数量
		 
		pageBasePo.setPageCount((int) Math.ceil((double)list.size()/pageSize));  //页数量
		
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setPageSize(pageSize);
		return pageBasePo;
	}

	@Override
	//通过typeId来获取歌曲集合，然后打乱顺序当电台播放
	public List<SongVo> getRadioStation(int typeId,int pageSize,int pageIndex) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("typeId", typeId);
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex-1)*pageSize);
		//通过typeId查询歌曲集合(分页)
		List<SongVo> songVos = songPoMapper.selectSongVoByTypeId(map);
		// Collections.shuffle(); 对list中数据随机排序 
		//打断歌曲集合
		Collections.shuffle(songVos);
		//返回打乱顺序后的歌曲集合,用于电台播放
		return songVos;
	}

	@Override
	//歌曲日推，随机获取歌曲集合
	public List<SongVo> getSongRecommend() {
		// 获取所有SongVo
		List<SongVo> list = songPoMapper.selectAllSongVo();
		// Collections.shuffle(); 对list中数据随机排序
		Collections.shuffle(list);
		List<SongVo> songVos = new ArrayList<SongVo>();
		// 为随机歌曲集合添加数据 默认20首
		for (int i = 0; i < 20; i++) {
			songVos.add(list.get(i));
		}
		return songVos;
	}
	@Override
	public int count() {	 
		return getShowSongVo().size();
	}

	
	@Override
	//根据singerId 得到songPo对象 分页
	public List<SongPo> getSongPoBySingerId(int singerId,int pageSize,int pageIndex)
			throws SingerIdNotExistException {
		Map map = new HashMap<>();
		map.put("singerid", singerId);
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex-1)*pageSize);
		// 通过id获取songvo集合对象
		List<SongPo> songPo = songPoMapper.selectBySingerIdByPag(map);
		//判断songVos对象中是否存在数据
		if (songPo.size() == 0) {
			//不存在 抛出SongIdNotExistException错误
			throw new SingerIdNotExistException("歌手id不存在");
		} else {
			return songPo;
		}
	}

	@Override
	public List<SongVo> getSongByCDId(int cdId) throws CDIdNotExistException {
		return songPoMapper.selectSongVosByCDId(cdId);
	}
}
