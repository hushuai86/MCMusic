package cn.service.impl.admin;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.exception.AdminException;
import cn.exception.SingerIdNotExistException;
import cn.exception.StateBlockException;
import cn.mapper.SingerPoMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.vo.SingerVo;
import cn.service.admin.AdminSingerService;
import cn.service.impl.StateBlockServiceImpl;


/**
 * 
 * @author xiaoyefeng
 *
 */
@Service
public class AdminSingerServiceImpl implements AdminSingerService{
	@Autowired
	private SingerPoMapper singerPoMapper;
	@Autowired
	private StateBlockServiceImpl stateBlockServiceImpl;
	//添加歌手
	@Override
	public boolean addSinger(SingerPo singerPo) throws AdminException {
		//获取所有歌手实体对象
		List<SingerPo> list = singerPoMapper.selectAllSinger();
		//通过singername和debutdate判断歌手是否重复添加
		for (int i = 0; i < list.size(); i++) {
			if (singerPo.getSingername() == list.get(i).getSingername()) {
				if(singerPo.getDebutdate() == list.get(i).getDebutdate()){
					throw new AdminException("错误:歌手重复插入");
				}
			}
		}
		singerPoMapper.insertSelective(singerPo);
		return true;
	}

	//更新歌手
	@Override
	public boolean updateSinger(SingerPo singerPo) throws AdminException {
		//判断所需更新的歌手是否存在
		if(singerPoMapper.selectByPrimaryKey1(singerPo.getSingerid())==null){
			throw new AdminException("歌手id不存在");
		}else {
			singerPoMapper.updateByPrimaryKeySelective(singerPo);
			return true;
		}
	}

	//删除歌手 将SingerStateId 修改为1
	@Override
	public boolean deleteSinger(Integer singerId) throws AdminException, StateBlockException {
		//通过singerId获取歌手实体
		SingerPo singerPo = singerPoMapper.selectByPrimaryKey(singerId);
		if(singerPo==null){
			throw new AdminException("歌手id不存在");
		}else {
			//将状态改为1(阻塞状态)
			stateBlockServiceImpl.singerWithSongStateBlock(singerId);
			return true;
		}
	}
	//批量删除歌手 SingerStateId 修改为1
	@Override	
	public int deleteSingers(List<Integer> list) throws AdminException, StateBlockException {
		for (int i = 0; i < list.size(); i++) {
			//通过singerId获取歌手实体
			SingerPo singerPo = singerPoMapper.selectByPrimaryKey(list.get(i));
			if (singerPo == null) {
				throw new AdminException("错误:歌手ID不存在");
			} else {
				//将状态改为1(阻塞状态)
				stateBlockServiceImpl.singerWithSongStateBlock(singerPo.getSingerid());
			}
		}
		return list.size();
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
			throws AdminException {
		// 通过singerId获取歌手实体对象 状态为不阻塞
		SingerPo singerPo = singerPoMapper.selectByPrimaryKey(signerId);
		if (singerPo == null) {
			// 获取失败 抛出SingerIdNotExistException错误
			throw new AdminException("歌手id不存在");
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
		if(singerPo.getSingername()!=null){		
			map.put("singername", "%"+singerPo.getSingername()+"%");
		}
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
		pageBasePo.setPageCount( (int)Math.ceil(((double)singerPoMapper.selectSingerPoBlockForPaginationCount(map)/pageSize)));
		return pageBasePo;
	}
	
	@Override
	//获取歌曲总数，状态码不为阻塞（不显示）状态
	public int getShowSingerCount(){
		return singerPoMapper.selectShowSingerCount();
	}
	@Override
	//歌手名查歌曲
	public SingerPo getShowSingerPoByName(String singerName) throws AdminException{
        SingerPo po =singerPoMapper.selectBySingerName(singerName);
        if(po==null){
        	throw new AdminException("歌手不存在");
        }
		return po;
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
			throws AdminException {
		// 通过singerId获取所有singerPo实体拓展对象 状态为不阻塞
		SingerVo singerVo = singerPoMapper.selectSingerVoBySingerId(singerId);
		if (singerVo == null) {
			// 获取失败 抛出SingerIdNotExistException错误
			throw new AdminException("歌手id不存在");
		} else {
			// 获取成功 返回
			return singerVo;
		}
	}
	
      //歌手名模糊查询歌手
	@Override
	public List<SingerPo> getSingerPosBySingerName(String singername) {
	 
		return singerPoMapper.selectSingerPoBlockBySingerName("%"+singername+"%");
	}
	
	@Override
	// 歌手分页条件筛选，状态为不阻塞状态,firstletter为歌手名首字母
	// 进入歌手模块将singerPo存储到session中作为查询实体，然后对singerPo进行动态sql查询用于页面检索与筛选
	public PageBasePo<SingerPo> filter1(SingerPo singerPo, int pageSize,
			int pageIndex, String firstLetter) {
		// 建立map存储搜索条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("pageIndex", (pageIndex - 1) * pageSize);
		if(singerPo.getSingername()!=null){		
			map.put("singername", "%"+singerPo.getSingername()+"%");
		}
		map.put("areaid", singerPo.getAreaid());
		map.put("singersex", singerPo.getSingersex());
		map.put("firstLetter", firstLetter);
		map.put("accessCount", singerPo.getAccesscount());
		map.put("collectionCount", singerPo.getCollectioncount());
		map.put("debutDate", singerPo.getDebutdate());
		map.put("singerStateId", 1); 
		// 获取搜索得到的数据
		List<SingerPo> list = singerPoMapper
				.selectSingerPoBlockForPagination(map);
		 
		PageBasePo pageBasePo = new PageBasePo();
		// 将list放入pagebasepo中
		pageBasePo.setList(list);
		pageBasePo.setAllNum(singerPoMapper.selectSingerPoBlockForPaginationCount(map));
		pageBasePo.setPageCount((int)Math.ceil(((double)singerPoMapper.selectSingerPoBlockForPaginationCount(map)/pageSize)));
		return pageBasePo;
	}
	
	@Override
	// 根据歌手id获取歌手实体对象，
	public SingerPo getShowSingerPoById1(int signerId)
			throws AdminException {
		// 通过singerId获取歌手实体对象
		SingerPo singerPo = singerPoMapper.selectByPrimaryKey1(signerId);
		if (singerPo == null) {
			// 获取失败 抛出SingerIdNotExistException错误
			throw new AdminException("歌手id不存在");
		} else {
			// 获取成功 返回该对象
			return singerPo;
		}
	}
	

}

