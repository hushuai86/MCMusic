package cn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.SongListIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.mapper.SongListPoMapper;
import cn.mapper.SongListPoOtherMapper;
import cn.mapper.SongListWithSongPoMapper;
import cn.mapper.SongPoMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SongListPo;
import cn.pojo.SongPo;
import cn.pojo.UserPo;
import cn.pojo.vo.SongListVo;
import cn.service.SongListService;

/**
 * 歌单服务实现类
 * @author taz
 *
 */
@Service
public class SongListServiceImpl implements SongListService{
	
	//注入自定义的pomapper
	@Autowired
	SongListPoOtherMapper songListPoOtherMapper;
	
	//注入自带的pomapper
	@Autowired
	SongListPoMapper songListPoMapper;
	
	@Autowired
	SongListWithSongPoMapper mapper;
	
	@Autowired
	SongPoMapper songMapper;

	/**
	 * 收藏次数加一
	 * 添加事务
	 * @param songListId 歌单id
	 * @return 返回当前次数
	 * @throws SongListIdNotExistException id存在异常
	 */
	@Override
	public long  addCollectionCount(int songListId)
			throws SongListIdNotExistException {
		//收藏次数加一
		System.out.println(songListId);
		songListPoOtherMapper.addCollectionCount(songListId);
		
		//返回当前的收藏次数
		long result=songListPoOtherMapper.getCurrentCollectionCount(songListId);
		if (result<0) {
			throw new SongListIdNotExistException("songListId异常");
		}
		
		return result;
	}
	/**
	 * 收藏次数减一
	 * 添加事务
	 * @param songListId 歌单id
	 * @return 返回当前次数
	 * @throws SongListIdNotExistException id存在异常
	 */
	@Override
	public long cutCollectionCount(int songListId)
			throws SongListIdNotExistException {
		// TODO Auto-generated method stub
		//收藏记录减一
		songListPoOtherMapper.cutCollectionCount(songListId);
		
		//返回当前的收藏次数
		long result=songListPoOtherMapper.getCurrentCollectionCount(songListId);
		
		if (result<0) {
			throw new SongListIdNotExistException("songListId异常");
		}
		
		
		return result;
	}
	

	@Override
	public long addAccessCount(int songListId)
			throws SongListIdNotExistException {
		//访问记录+1
		songListPoOtherMapper.addAccessCount(songListId);
		//返回当前的访问次数
		long result=songListPoOtherMapper.getCurrentAccessCount(songListId);	
		
		if (result<0) {
			throw new SongListIdNotExistException("songListId异常");
		}
		
		return result;
	}
	
	@Override
	public List<SongListPo> getShowSongPo() {
		// 得到所有的songlist实体对象，状态为公开状态0
		
		return songListPoOtherMapper.getSongListPos();
	}
	
	@Override
	public SongListPo getShowSongPoById(int songListId)
			throws SongListIdNotExistException {
		//根据id得到一个状态为公开的歌单实体对象
		SongListPo po=songListPoMapper.selectByPrimaryKey(songListId);
		
		return po;
	}

	/**
	 * 得到当前用户的所有歌单，包括私有状态的歌单
	 * 检索当前用户的收藏表和歌单表，如果都存在，则显示。
	 * 如果只存在歌单表，则表示用户已经放弃歌单，不显示
	 * @return 歌单集合
	 */
	@Autowired
	SessionServiceImpl sessionServiceImpl;
	@Override
	public List<SongListPo> getAllByCurrentUser(HttpServletRequest request){
		
			//获取当前用户
			UserPo userPo;
			try {
				userPo = sessionServiceImpl.getCurrentUserPo(request);
				//通过用户ID查询收藏表和歌单关联的歌单集合
				List<SongListPo> songListPo=songListPoOtherMapper.selectSongListByUser(userPo.getUserid());
				if(songListPo!=null){
					return songListPo;
				}
			} catch (UserNotForSessionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return null;
	}

	@Override
	public List<SongListVo> getShowSongListVo() {	
		//得到所有歌单的扩展类
		
		List<SongListVo> list=songListPoOtherMapper.getSongListVos();
		
		//返回歌单扩展集合
		
		return list;
	}
	
	@Override
	public SongListVo getShowSongListVoById(int songListId)
			throws SongListIdNotExistException {
		
		//根据歌单id得到拓展类
		SongListVo vo=songListPoOtherMapper.getSongListVoBySongListId(songListId);
		//歌曲id
		List<Integer> list = mapper.selectBySongListId(songListId);
		
		List<SongPo> songs = new ArrayList<SongPo>();
		for(int id : list){//得到song的list
			SongPo po = songMapper.selectByPrimaryKey(id);
			songs.add(po);
		}
		
		vo.setList(songs);
		return vo;
	}
	
	@Override
	public PageBasePo<SongListVo> getShowSongListVo(int pageSize, int pageIndex) {
		//新建一个分页对象
		PageBasePo<SongListVo> voPageBasePo=new PageBasePo<SongListVo>();
		
		//创建一个map对象
		Map<String, Object> map=new HashMap<String, Object>();
		//给map 赋值  然后进行查询
		int index=(pageIndex-1)*pageSize;
		map.put("pageSize", pageSize);
		map.put("index", index);
		
		//创建songlist集
		List<SongListVo> songListVos=songListPoOtherMapper.getShowSongListVo(map);
		
		//给每页大小赋值
		voPageBasePo.setPageSize(pageSize);
		
		//给每页位置赋值
		voPageBasePo.setPageIndex(pageIndex);
		
		//总数量
		int allNum=songListPoOtherMapper.getAllCountNum();
		voPageBasePo.setAllNum(allNum);
		
		//页数
		int pageCount=(int) Math.ceil((double)allNum/pageSize);
		voPageBasePo.setPageCount(pageCount);
		
		//赋值songlist集
		voPageBasePo.setList(songListVos);

		
		return voPageBasePo;
	}
	
	@Override
	public PageBasePo<SongListVo> filter(SongListVo songListVo, int pageSize,
			int pageIndex) {
		//新建一个分页对象
		PageBasePo<SongListVo> voPageBasePo=new PageBasePo<SongListVo>();
		voPageBasePo.setIndexEntity(songListVo);
		//创建一个map对象
		Map<String, Object> map=new HashMap<String, Object>();
		
		//给map 赋值  然后进行查询
		int index=(pageIndex-1)*pageSize;
		
		//先将查询条件放入分页对象中
		voPageBasePo.setPageIndex(index);
		
		voPageBasePo.setPageSize(pageSize);
		
		List<SongListVo> list=songListPoOtherMapper.getShowSongListVoBySearch(voPageBasePo);
		
		//对分页对象设置值
		voPageBasePo.setList(list);
		
		//总数量
		int allNum=songListPoOtherMapper.getShowSongListVoBySearchCount(voPageBasePo);
		voPageBasePo.setAllNum(allNum);
		
		//放入页数
		int pageCount=(int) Math.ceil((double)allNum/pageSize);
		voPageBasePo.setPageCount(pageCount);
		
		//覆盖前面的index 放入心的pageIndex
		voPageBasePo.setPageIndex(pageIndex);
		
		return voPageBasePo;
	}
	
	
	@Override
	public PageBasePo<SongListVo> filter(SongListVo songListVo, int pageSize,
			int pageIndex, int term) {
			//新建一个分页对象
			PageBasePo<SongListVo> voPageBasePo=new PageBasePo<SongListVo>();
		
			voPageBasePo.setIndexEntity(songListVo);
			
			//给map 赋值  然后进行查询
			int index=(pageIndex-1)*pageSize;
			
			//先将查询条件放入分页对象中
			voPageBasePo.setPageIndex(index);
			
			voPageBasePo.setPageSize(pageSize);
			
			List<SongListVo> list = new ArrayList<SongListVo>();
			
			//最新
			if(term==1){
				list =songListPoOtherMapper.getShowSongListVoBySearch1(voPageBasePo);
				}
			
			//推荐
			else if(term==2){
				list = songListPoOtherMapper.getShowSongListVoBySearch2( voPageBasePo);
				}
			
			//最热
			else if(term==3){
				list = songListPoOtherMapper.getShowSongListVoBySearch3(voPageBasePo);
				}
			
			//热藏
			else if(term==4){
				list = songListPoOtherMapper. getShowSongListVoBySearch4( voPageBasePo);
				}
			//对分页对象设置值
			voPageBasePo.setList(list);
			
			
			//总数量
			int allNum=songListPoOtherMapper.getShowSongListVoBySearchCount(voPageBasePo);
			voPageBasePo.setAllNum(allNum);
			
			//放入页数
			int pageCount=(int) Math.ceil((double)allNum/pageSize);
			voPageBasePo.setPageCount(pageCount);
			
			//覆盖前面的index 放入心的pageIndex
			voPageBasePo.setPageIndex(pageIndex);
			
			return voPageBasePo;
	}
	
	
	@Override
	public long addPrivateSongList(SongListPo songListPo)
			throws SongListIdNotExistException {
		int result =songListPoMapper.insertSelective(songListPo);
		if (result<=0) {
			throw new SongListIdNotExistException("歌单创建不成功");
		}
		return result;
	}

}
