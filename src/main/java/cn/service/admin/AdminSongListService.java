package cn.service.admin;

import java.util.List;

import cn.exception.AdminException;
import cn.exception.SongListIdNotExistException;
import cn.pojo.PageBasePo;
import cn.pojo.SongListPo;
import cn.pojo.vo.SongListVo;


/**
 * 后台歌单服务接口
 * @author hushuai
 *
 */
public interface AdminSongListService {
	
	/**
	 * 添加歌单
	 * @param SongListPo  歌单对象
	 * @throws AdminException
	 */
	public boolean addSongList(SongListPo songListPo) throws AdminException;
    
	/**
	 * 修改歌手信息
	 * @param  SongListPo  歌单对象
	 * @throws AdminException
	 */
	public boolean updateSongList(SongListPo songListPo) throws AdminException;
	/**
	 * 删除歌单
	 * SongListStateId 修改为0
	 * @param SongListId 歌手Id
	 * @throws AdminException
	 */
	public boolean deleteSongList(Integer songListId) throws AdminException;
	
	/**
	 * 批量删除歌单
	 * SongListStateId 修改为0
	 * @param List<Integer> 歌单Id集合
	 * @throws AdminException
	 */
	public boolean deleteSongList(List<Integer> list) throws AdminException;
	
	/**
	 * 得到所有的songlist实体对象，状态为公开状态0
	 * @return 一个list集合
	 */
	public List<SongListPo> getShowSongPo();
	
	/**
	 * 根据id得到一个状态为公开的歌单实体对象
	 * @param songListId  歌单id
	 * @return 一个歌单实体对象
	 * @throws SongListIdNotExistException 歌单id不存在
	 */
	public SongListPo getShowSongPoById(int songListId)throws AdminException;
	
	/**
	 * 得到当前用户的所有歌单，包括私有状态的歌单
	 * 检索当前用户的收藏表和歌单表，如果都存在，则显示。
	 * 如果只存在歌单表，则表示用户已经放弃歌单，不显示
	 * @return 歌单集合
	 */
	public List<SongListPo>getAllByCurrentUser();

	/**
	 * 得到所有歌单拓展类，（添加字段user，song,type），状态为公开
	 * @return 歌单拓展类集合
	 */
	public List<SongListVo> getShowSongListVo();
	
	/**
	 * 根据歌单id得到拓展类
	 * @param songListId 歌单id
	 * @return 一个歌单拓展类
	 */
	public SongListVo getShowSongListVoById(int songListId)throws AdminException;
	
	/**
	 * 
	 * @param pageSize 分页大小
	 * @param pageIndex 分页索引
	 * @return 分页对象
	 */
	public PageBasePo<SongListVo> getShowSongListVo(int pageSize,int pageIndex);
	
	/**
	 * 分页检索，关键字获取对应名称的songPo实体拓展对象（添加字段singPo,cdPo,typePo），状态码不为阻塞（不显示）状态
	 * @param pageSize 分页大小
	 * @param pageIndex 分页索引
	 * @return 一个分页对象
	 * 作用于accessCount，typeId，collectionCount
	 */ 
	public PageBasePo<SongListVo> filter(SongListVo songListVo,int pageSize,int pageIndex);

	
	/**
	 * 查询所有歌单数
	 * @return 歌单数
	 */
	int getShowSongListCount();
     
	/**
	 * 通过名字模糊查询
	 * @param songListVo pageSize pageIndex
	 * @return 符合条件页面对象
	 */
	PageBasePo<SongListVo> filter1(SongListVo songListVo, int pageSize,
			Integer pageIndex);
	
	
	
	
}
