package cn.service;

import java.util.List;

import cn.exception.CollectionException;
import cn.exception.CollectionIdNotExistException;
import cn.exception.SongListIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.pojo.UserWithSongListPo;
import cn.pojo.vo.UserCollectionSongListVo;

/**
 * 收藏歌单服务类
 * @author liuqiao
 *
 */
public interface UserCollectionSongListService {
	/**
	 * 通过session中的当前userid 和 传递过来的songListid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param songListId 歌单id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SongListIdNotExistException 歌单id不存在异常
	 */
	public boolean notCollectionSongList(int songListId,int userId)throws SongListIdNotExistException;
	
	
	/**
	 * 通过session中的当前userid 和 传递过来的songListid 进行歌单收藏，收藏量+1
	 * 写入对应的关联表
	 * @param songListId 歌单id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SongListIdNotExistException 歌单id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionSongList(int songListId,int userId)throws SongListIdNotExistException,CollectionException;
	
	
	/**
	 * 判断歌单是否被收藏
	 * @param songListId 歌单id
	 * @param userId 用户id
	 * @return 一个bool值，收藏为true
	 * @throws SongListIdNotExistException 歌单id不存在异常
	 */
	public boolean isConllectionForSongListId(int songListId,int userId)throws SongListIdNotExistException;
	
	/**
	 * 通过用户id 得到UserWithSongList收藏的所有歌曲拓展类集合
	 * @param userId 用户id
	 * @return 返回一个UserWithSongList收藏的拓展类集合
	 * @throws UserIdNotExistException 用户id不存在异常
	 */
	public List<UserCollectionSongListVo> getUserCollectionById(int userId)throws UserIdNotExistException;
	
	/**
	 * 通过主键id 得到对应UserWithSongList
	 * @param Key 主键
	 * @return 返回一个UserWithSongList收藏的po
	 */
	public UserWithSongListPo getUserCollectionByKey(int Key)throws CollectionIdNotExistException;
}
