package cn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.exception.SongListIdNotExistException;
import cn.pojo.PageBasePo;
import cn.pojo.SongListPo;
import cn.pojo.vo.SongListVo;

/**
 * 歌单服务接口
 * @author taz
 *
 */
@Service
public interface SongListService {
	/**
	 * 收藏次数加一
	 * 添加事务
	 * @param songListId 歌单id
	 * @return 返回当前次数
	 * @throws SongListIdNotExistException id存在异常
	 */
	public long addCollectionCount(int songListId) throws SongListIdNotExistException;
	
	/**
	 * 收藏次数减一
	 * 添加事务
	 * @param songListId 歌单id
	 * @return 返回当前次数
	 * @throws SongListIdNotExistException id存在异常
	 */
	public long cutCollectionCount(int songListId)throws SongListIdNotExistException;
	
	/**
	 * 访问次数加一
	 * 添加事务
	 * @param songListId 歌单id
	 * @return 返回当前次数
	 * @throws SongListIdNotExistException id不存在异常
	 */
	public long addAccessCount(int songListId)throws SongListIdNotExistException;
	
	
	
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
	public SongListPo getShowSongPoById(int songListId)throws SongListIdNotExistException;
	
	/**
	 * 得到当前用户的所有歌单，包括私有状态的歌单
	 * 检索当前用户的收藏表和歌单表，如果都存在，则显示。
	 * 如果只存在歌单表，则表示用户已经放弃歌单，不显示
	 * @return 歌单集合
	 */
	public List<SongListPo>getAllByCurrentUser(HttpServletRequest request);
	
	
	
	
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
	public SongListVo getShowSongListVoById(int songListId)throws SongListIdNotExistException;
	
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
	public PageBasePo<SongListVo> filter(SongListVo songListVo,int pageSize,int pageIndex,int term);
	
	/**
	 * 分页检索，关键字获取对应名称的songPo实体拓展对象（添加字段singPo,cdPo,typePo），状态码不为阻塞（不显示）状态  
	 * 根据songlistname模糊查询
	 * @param pageSize 分页大小
	 * @param pageIndex 分页索引
	 * @return 一个分页对象
	 * 作用于accessCount，typeId，collectionCount
	 */ 
	public PageBasePo<SongListVo> filter(SongListVo songListVo, int pageSize,int pageIndex); 
	/**
	 * 添加私人歌单
	 * @param songListPo
	 * @return
	 * @throws SongListIdNotExistException
	 */
	public long addPrivateSongList(SongListPo songListPo) throws SongListIdNotExistException;
	

}
