package cn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.exception.CDIdNotExistException;
import cn.mapper.CDPoMapper;
import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.vo.CDVo;
import cn.service.CDService;

/**
 * CD服务接口实现
 * 
 * @author hushuai
 * 
 */

@Service
public class CDServiceImpl implements CDService {

	@Autowired
	private CDPoMapper mapper;

	@Override
	/* @Transactional */
	public long addCollectionCount(int cdId) throws CDIdNotExistException {

		CDPo cdPo = mapper.selectByPrimaryKey(cdId);
		// 专辑Id不存在抛异常
		if (cdPo == null) {
			throw new CDIdNotExistException("专辑ID不存在");
		}
		// 收藏次数加一
		cdPo.setCollectioncount(cdPo.getCollectioncount() + 1);
		mapper.updateByPrimaryKey(cdPo);
		// 返回当前收藏数
		return cdPo.getCollectioncount();
	}

	@Override
	/* @Transactional */
	public long cutCollectionCount(int cdId) throws CDIdNotExistException {

		// 收藏数减一
		CDPo cdPo = mapper.selectByPrimaryKey(cdId);
		// 专辑Id不存在抛异常
		if (cdPo == null) {
			throw new CDIdNotExistException("专辑ID不存在");
		}
		cdPo.setCollectioncount(cdPo.getCollectioncount() - 1);
		mapper.updateByPrimaryKey(cdPo);
		// 返回当前收藏数
		return cdPo.getCollectioncount();
	}

	@Override
	public CDPo getShowCDPoById(int CDId) throws CDIdNotExistException {

		// 通过cdid查找状态不为阻塞的专辑
		CDPo cdPo = mapper.selectShowCDPoById(CDId);
		// 专辑Id不存在抛异常
		if (cdPo == null) {
			throw new CDIdNotExistException("专辑ID不存在");
		}
		return cdPo;
	}

	@Override
	public List<CDPo> getShowCDPo() {

		// 获取所有的专辑实体对象,状态不为阻塞状态
		List<CDPo> list = mapper.selectShowCDPo();
		return list;
	}

	@Override
	public CDVo getShowCDVoById(int CDId) throws CDIdNotExistException {

		// 根据专辑id查找到对应的实体拓展类,状态不为阻塞状态
		CDVo vo = mapper.selectShowCDVoById(CDId);
		// 专辑Id不存在抛异常
		if (vo == null) {
			throw new CDIdNotExistException("专辑ID不存在");
		}
		return vo;
	}

	@Override
	public List<CDVo> getShowCDVo() {

		// 查找到所有的实体拓展类,状态不为阻塞状态
		List<CDVo> list = mapper.selectShowCDVo();
		return list;
	}

	@Override
	public PageBasePo<CDVo> getShowCDVo(int pageSize, int pageIndex) {

		// 分页查找所有的实体拓展类,状态不为阻塞状态

		PageBasePo<CDVo> pageBasePo = new PageBasePo<CDVo>();

		// sql语句查寻结果跳过的条数
		int index = (pageIndex - 1) * pageSize;
		// 查询总条数
		int allNum = mapper.selectShowCDVoCount();

		// Map对象存值传值
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", index);
		map.put("pageSize", pageSize);

		// 查询Vo对象集合
		List<CDVo> list = mapper.selectShowCDVoPage(map);

		// PageBasePo<CDVo>对象数据封装
		pageBasePo.setAllNum(allNum);
		pageBasePo.setPageCount((int) Math.ceil((double) allNum / pageSize));
		pageBasePo.setPageSize(pageSize);
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	public PageBasePo<CDVo> filter(CDVo cdVo, int pageSize, int pageIndex) {

		/**
		 * 对cdVo进行动态sql查询用于页面检索与筛选 cdName（模糊查询）
		 * publishdate(时间段查询，时间段为传入日期到传入日期加一年 * collectionCount 排序 返回一个符合条件的分页对象
		 */

		PageBasePo<CDVo> pageBasePo = new PageBasePo<CDVo>();

		// 如果专辑名不为空，进行字符串处理便于模糊查询
		CDPo po = cdVo.getCDPo();
		if (po.getCdname() != null) {
			po.setCdname("%" + po.getCdname() + "%");
		}

		// 将处理后的po对像存到cdVo里
		cdVo.setCDPo(po);

		// 存放索引实体对象
		pageBasePo.setIndexEntity(cdVo);
		// 存放索引的大小
		pageBasePo.setPageSize(pageSize);

		// 存放跳过的条数用于查询
		int index = (pageIndex - 1) * pageSize;
		pageBasePo.setPageIndex(index);

		// 存放总数和页数
		int allNum = mapper.selectFilterCount(cdVo);
		pageBasePo.setAllNum(allNum);
		pageBasePo.setPageCount((int) Math.ceil((double) allNum / pageSize));

		// 调用查询方法
		List<CDVo> list = mapper.selectFilter(pageBasePo);

		// 覆盖索引位置
		pageBasePo.setPageIndex(pageIndex);

		// 存放查询结果集合
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	// 得到热门专辑（热度排序）默认最多得到20本
	public List<CDPo> getHotCD(int singerId) {
		List<CDPo> list = mapper.selecHotCdPosBySingerId(singerId);
		List<CDPo> list2 = new ArrayList<CDPo>();
		int j = 0;
		if(list.size()<20){
			j = list.size();
		}else{
			j = 20;
		}
		for (int i = 0; i < j; i++) {
			if (list.get(i) != null) {
				list2.add(list.get(i));
			}
		}
		return list2;
	}

	@Override
	public List<CDPo> getCDPo(int singerId,int pageSize, int pageIndex) {
		Map map = new HashMap<>();
		map.put("singerid", singerId);
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex-1)*pageSize);
		List<CDPo> list = mapper.selecHotCdPosBySingerIdByPag(map);
		return list;
	}

	@Override
	public int getCdBySingerIdCount(int singerId) {
		return mapper.selecCdBySingerIdCount(singerId);
	}
}
