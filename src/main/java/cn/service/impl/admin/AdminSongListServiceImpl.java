package cn.service.impl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.AdminException;
import cn.exception.SongListIdNotExistException;
import cn.exception.UserNotForSessionException;
import cn.mapper.SongListPoMapper;
import cn.mapper.SongListPoOtherMapper;
import cn.pojo.PageBasePo;
import cn.pojo.SongListPo;
import cn.pojo.SongPo;
import cn.pojo.UserPo;
import cn.pojo.vo.SongListVo;
import cn.service.admin.AdminSongListService;
import cn.service.impl.SessionServiceImpl;


@Service
public class AdminSongListServiceImpl implements AdminSongListService{

	@Autowired
	SongListPoMapper songListPoMapper;
	@Autowired
	SongListPoOtherMapper songListPoOtherMapper;
	@Override
	public boolean addSongList(SongListPo songListPo) throws AdminException {
		//添加歌单
		 int result=songListPoMapper.insertSelective(songListPo);
		 if(result<=0){
			 throw  new AdminException("添加不成功");
		 }
		 return true;
	}

	@Override
	public boolean updateSongList(SongListPo songListPo) throws AdminException {
		//修改歌单
		int result=songListPoMapper.updateByPrimaryKeySelective(songListPo);
		 if(result<=0){
			 throw  new AdminException("修改不成功");
		 }
		 return true;
		
	}

	@Override
	public boolean deleteSongList(Integer songListId) throws AdminException {
		//单个删除
		int result = songListPoMapper.deleteByPrimaryKey(songListId);
		if(result <=0){
			throw new AdminException("歌单删除失败");
		}else {
			
			return true;
		}
	}

	@Override
	public boolean deleteSongList(List<Integer> list) throws AdminException {
		for (int i = 0; i < list.size(); i++) {
			//通过songId获取歌曲实体对象
			SongListPo songListPo = songListPoMapper.selectByPrimaryKey(list.get(i));
			if (songListPo == null) {
				throw new AdminException("错误:歌曲ID不存在");
			} else {
				//将歌曲状态改为1(阻塞状态)
				songListPo.setSongliststateid(1);
				songListPoMapper.updateByPrimaryKey(songListPo);
				
			}
		}
		return true;
	}

	@Override
	public List<SongListPo> getShowSongPo() {
		//得到所有songListPo集合
		List<SongListPo> po=songListPoOtherMapper.getSongListPos();
		return po;
	}

	@Override
	public SongListPo getShowSongPoById(int songListId)
			throws AdminException {
		//根据id得到一个状态为公开的歌单实体对象
		SongListPo po=songListPoMapper.selectByPrimaryKey(songListId);
		if(po==null){
			throw new AdminException("songListId异常");
		}
				
		return po;
	}
	
	@Autowired
	SessionServiceImpl sessionServiceImpl;
	@Override
	public List<SongListPo> getAllByCurrentUser() {
		try {
			HttpServletRequest request = null;
			//获取当前用户
			UserPo  userPo=sessionServiceImpl.getCurrentUserPo(request);
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
			throws AdminException {
		//根据歌单id得到拓展类
		SongListVo vo=songListPoOtherMapper.getSongListVoBySongListId(songListId);
		if (vo==null) {
			throw new AdminException("songListId异常");
		}
		
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
		map.put("pageIndex", index);
		
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
    
	//获取状态不为阻塞的歌单总数
	@Override
	public int getShowSongListCount() {
		// TODO Auto-generated method stub
		return songListPoMapper.selectShowSongListCount();
	}
	
	@Override
	public PageBasePo<SongListVo> filter1(SongListVo songListVo, int pageSize,
			Integer pageIndex) {
		//新建一个分页对象
		PageBasePo<SongListVo> voPageBasePo=new PageBasePo<SongListVo>();
		voPageBasePo.setIndexEntity(songListVo);
		//创建一个map对象
		Map<String, Object> map=new HashMap<String, Object>();
		
		
		
		SongListPo songListPo=songListVo.getSongListPo();
		if(songListPo.getSonglistname()!=null){
			songListPo.setSonglistname("%"+songListPo.getSonglistname()+"%");
		}
		
		//给map 赋值  然后进行查询
		if(pageIndex==null){
			pageIndex=1;
		}
		
		Integer index=(pageIndex-1)*pageSize;
		
		map.put("pageSize", pageSize);
		map.put("pageIndex", index);
		
		//先将查询条件放入分页对象中
		voPageBasePo.setPageIndex(index);
		
		voPageBasePo.setPageSize(pageSize);
		
		List<SongListVo> list=songListPoOtherMapper.getShowSongListVoBySearchBySongListName(voPageBasePo);
	 
		//对分页对象设置值
		voPageBasePo.setList(list);
		
		//总数量
		int allNum=songListPoOtherMapper.getShowSongListVoBySearchBySongListNameCount(voPageBasePo);
		voPageBasePo.setAllNum(allNum);
		
		//放入页数
		int pageCount=(int) Math.ceil((double)allNum/pageSize);
		
		voPageBasePo.setPageCount(pageCount);
		
		//覆盖前面的index 放入心的pageIndex
		voPageBasePo.setPageIndex(pageIndex);
		
		return voPageBasePo;
	}


}
