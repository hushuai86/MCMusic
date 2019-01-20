package cn.service;

import java.util.List;

import cn.exception.CollectionException;
import cn.exception.CollectionIdNotExistException;
import cn.exception.SongIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.pojo.UserWithSongPo;
import cn.pojo.vo.UserCollectionSongVo;

/**
 * 收藏歌曲服务类
 * @author liuqiao
 *
 */
public interface UserCollectionSongService {
	/**
	 * 判断歌曲是否被收藏（我喜欢的歌曲）
	 * @param songId 歌单id
	 * @param userId 用户id
	 * @return 一个bool值，收藏为true
	 * @throws SongIdNotExistException 歌曲id不存在异常
	 */
	public boolean isConllectionForSongId(int songId,int userId)throws SongIdNotExistException;
	
	
	/**
	 * 通过session中的当前userid 和 传递过来的songid 进行歌曲收藏，收藏量+1
	 * 写入对应的关联表
	 * @param songId 歌曲id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SongIdNotExistException 歌单id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionSong(int songId,int userId)throws SongIdNotExistException,CollectionException;
	
	/**
	 * 通过session中的当前userid 和 传递过来的songid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param songId 歌单id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SongIdNotExistException 歌单id不存在异常
	 */
	public boolean notCollectionSong(int songId,int userId)throws SongIdNotExistException;
	
	/**
	 * 通过用户id 得到UserWithSong收藏的所有歌曲拓展类集合
	 * @param userId 用户id
	 * @return 返回一个UserWithSong收藏的拓展类集合
	 * @throws UserIdNotExistException 用户id不存在异常
	 */
	public List<UserCollectionSongVo> getUserCollectionById(int userId)throws UserIdNotExistException;
	
	/**
	 * 通过主键id 得到对应UserWithSong
	 * @param Key 主键
	 * @return 返回一个UserWithSong收藏的po
	 */
	public UserWithSongPo getUserCollectionByKey(int Key)throws CollectionIdNotExistException;
}
