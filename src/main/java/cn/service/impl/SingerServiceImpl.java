package cn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.exception.SingerIdNotExistException;
import cn.mapper.SingerPoMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.vo.SingerVo;
import cn.service.SingerService;

/**
 * 歌手服务接口实现
 * 
 * @author xiaoyefeng
 * 
 */
@Service
public class SingerServiceImpl implements SingerService {
	@Autowired
	private SingerPoMapper singerPoMapper;

	@Override
	// @Transactional
	// 访问次数加一 添加事务
	public long addAccessCount(int singerId) throws SingerIdNotExistException {
		// 通过singerId获取歌手实体对象
		SingerPo singerPo = singerPoMapper.selectByPrimaryKey(singerId);
		if (singerPo == null) {
			// 获取失败 抛出SingerIdNotExistException错误
			throw new SingerIdNotExistException("歌手id不存在");
		} else {
			// 获取成功 访问次数加1
			singerPo.setAccesscount(singerPo.getAccesscount() + 1);
			// 更新数据库数据
			singerPoMapper.updateByPrimaryKey(singerPo);
			// 返回当前访问次数
			return singerPo.getAccesscount();
		}

	}

	@Override
	// @Transactional
	// 收藏次数加一 添加事务
	public long addCollectionCount(int singerId)
			throws SingerIdNotExistException {
		// 通过singerId获取歌手实体对象
		SingerPo singerPo = singerPoMapper.selectByPrimaryKey(singerId);
		if (singerPo == null) {
			// 获取失败 抛出SingerIdNotExistException错误
			throw new SingerIdNotExistException("歌手id不存在");
		} else {
			// 获取成功 收藏次数加1
			singerPo.setCollectioncount(singerPo.getCollectioncount() + 1);
			// 更新数据库数据
			singerPoMapper.updateByPrimaryKey(singerPo);
			// 返回当前收藏次数
			return singerPo.getCollectioncount();
		}
	}

	@Override
	// @Transactional
	// 收藏次数减一 添加事务
	public long cutCollectionCount(int singerId)
			throws SingerIdNotExistException {
		// 通过singerId获取歌手实体对象
		SingerPo singerPo = singerPoMapper.selectByPrimaryKey(singerId);
		if (singerPo == null) {
			// 获取失败 抛出SingerIdNotExistException错误
			throw new SingerIdNotExistException("歌手id不存在");
		} else {
			// 获取成功 收藏次数减1
			singerPo.setCollectioncount(singerPo.getCollectioncount() - 1);
			// 更新数据库数据
			singerPoMapper.updateByPrimaryKey(singerPo);
			// 返回当前收藏次数
			return singerPo.getCollectioncount();
		}
	}

	@Override
	// 获取所有歌手实体对象，状态码不为阻塞（不显示）状态
	public List<SingerPo> getShowSingerPo() {
		// 获取所有歌手实体对象，状态码不为阻塞（不显示）状态 ,并返回
		return singerPoMapper.selectAllSingerPoBlock();
	}

	@Override
	// 分页获取所有歌手实体对象，状态码不为阻塞（不显示）状态
	public PageBasePo<SingerPo> getShowSingerPo(int pageSize, int pageIndex) {
		// 将搜索分页条件用map存着
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex - 1) * pageSize);

		// 通过map查询出符合条件的list
		List<SingerPo> list = singerPoMapper
				.selectSingerPoBlockForPagination(map);
		PageBasePo pageBasePo = new PageBasePo();
		// 将list放入pagebasepo中
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	// 根据歌手id获取歌手实体对象，状态码不为阻塞（不显示）状态
	public SingerPo getShowSingerPoById(int signerId)
			throws SingerIdNotExistException {
		// 通过singerId获取歌手实体对象 状态为不阻塞
		SingerPo singerPo = singerPoMapper.selectByPrimaryKey(signerId);
		if (singerPo == null) {
			// 获取失败 抛出SingerIdNotExistException错误
			throw new SingerIdNotExistException("歌手id不存在");
		} else {
			// 获取成功 返回该对象
			return singerPo;
		}
	}

	@Override
	// 歌手分页条件筛选，状态为不阻塞状态,firstletter为歌手名首字母
	// 进入歌手模块将singerPo存储到session中作为查询实体，然后对singerPo进行动态sql查询用于页面检索与筛选
	public PageBasePo<SingerPo> filter(SingerPo singerPo, int pageSize,
			int pageIndex, String firstLetter) {
		// 建立map存储搜索条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex - 1) * pageSize);
		map.put("singername", singerPo.getSingername());
		map.put("areaid", singerPo.getAreaid());
		map.put("singersex", singerPo.getSingersex());
		map.put("firstLetter", firstLetter);
		map.put("accessCount", singerPo.getAccesscount());
		map.put("collectionCount", singerPo.getCollectioncount());
		map.put("debutDate", singerPo.getDebutdate());
		// 获取搜索得到的数据
		List<SingerPo> list = singerPoMapper
				.selectSingerPoBlockForPagination(map);
		PageBasePo pageBasePo = new PageBasePo();
		// 将list放入pagebasepo中
		pageBasePo.setList(list);
		pageBasePo.setAllNum(singerPoMapper.selectSingerPoBlockForPaginationCount(map));
		return pageBasePo;
	}

	@Override
	// 获取所有singerVo对象,状态为不阻塞
	public List<SingerVo> getShowSingerVo() {
		// 获取所有singerPo实体拓展对象 状态为不阻塞 并且返回
		return singerPoMapper.selectAllSingerVo();
	}

	@Override
	// 通过歌手id获取singerVo对象，状态码不为阻塞（不显示）状态
	public SingerVo getShowSingerVoById(int singerId)
			throws SingerIdNotExistException {
		// 通过singerId获取所有singerPo实体拓展对象 状态为不阻塞
		SingerVo singerVo = singerPoMapper.selectSingerVoBySingerId(singerId);
		return singerVo;

	}

	@Override
	/*
	 * 查询非中国国籍歌手
	 */
	public List<SingerPo> getSingerPoForForeign(int pageSize, int pageIndex) {
		Map map = new HashMap<>();
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex - 1) * pageSize);

		return singerPoMapper.selectSingerForForeign(map);
	}

}
