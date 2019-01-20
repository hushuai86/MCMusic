package cn.service;

import java.util.List;

import cn.exception.CDIdNotExistException;
import cn.exception.CollectionException;
import cn.exception.SingerIdNotExistException;
import cn.exception.SongIdNotExistException;
import cn.exception.SongListIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.pojo.vo.UserCollectionVo;

/**
 * 用户关联歌手，歌单，专辑服务接口
 * @author liuqiao
 *
 */
public interface UserCollectionService {
	/**
	 * 通过用户id 得到user收藏的一个拓展类，拓展包括songlist，singer，cd
	 * @param userId 用户id
	 * @return 返回一个user收藏的拓展类
	 * @throws UserIdNotExistException 用户id不存在异常
	 */
	public UserCollectionVo getUserCollectionById(int userId)throws UserIdNotExistException;
	
	/**
	 * 得到所有拓展类
	 * @return 得到所有拓展类
	 */
	public List<UserCollectionVo> getAllUserCollectionById();
	
	
	/**
	 * 判断歌手是否被收藏
	 * @param singId 歌手id
	 * @return 一个bool值，收藏为true
	 * @throws SingerIdNotExistException 歌手id不存在异常
	 */
	public boolean isConllectionForSingerId(int singId)throws SingerIdNotExistException;
	/**
	 * 判断cd是否被收藏
	 * @param CDId 专辑id
	 * @return 一个bool值，收藏为true
	 * @throws CDIdNotExistException 专辑id不存在异常
	 */
	public boolean isConllectionForCDId(int CDId)throws CDIdNotExistException;
	/**
	 * 判断歌单是否被收藏
	 * @param songListId 歌单id
	 * @return 一个bool值，收藏为true
	 * @throws SongListIdNotExistException 歌单id不存在异常
	 */
	public boolean isConllectionForSongListId(int songListId)throws SongListIdNotExistException;
	
	/**
	 * 判断歌曲是否被收藏（我喜欢的歌曲）
	 * @param songId 歌单id
	 * @return 一个bool值，收藏为true
	 * @throws SongIdNotExistException 歌曲id不存在异常
	 */
	public boolean isConllectionForSongId(int songId)throws SongIdNotExistException;
	
	
	
	
	
	/**
	 * 通过session中的当前userid 和 传递过来的singerid 进行歌手收藏，收藏量+1
	 * 写入对应的关联表
	 * @param singerId 歌手id
	 * @return 返回一个bool值，成功true
	 * @throws SingerIdNotExistException 歌手id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionSinger(int singerId)throws SingerIdNotExistException,CollectionException;
	
	/**
	 * 通过session中的当前userid 和 传递过来的CDid 进行专辑收藏，收藏量+1
	 * 写入对应的关联表
	 * @param CDId 专辑id
	 * @return 返回一个bool值，成功true
	 * @throws CDIdNotExistException 专辑id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionCD(int CDId)throws CDIdNotExistException,CollectionException;
	
	/**
	 * 通过session中的当前userid 和 传递过来的songListid 进行歌单收藏，收藏量+1
	 * 写入对应的关联表
	 * @param songListId 歌单id
	 * @return 返回一个bool值，成功true
	 * @throws SongListIdNotExistException 歌单id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionSongList(int songListId)throws SongListIdNotExistException,CollectionException;
	
	/**
	 * 通过session中的当前userid 和 传递过来的songid 进行歌曲收藏，收藏量+1
	 * 写入对应的关联表
	 * @param songId 歌曲id
	 * @return 返回一个bool值，成功true
	 * @throws SongIdNotExistException 歌单id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionSong(int songId)throws SongIdNotExistException,CollectionException;
	
	
	
	
	/**
	 * 通过session中的当前userid 和 传递过来的singerid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param singerId 歌手id
	 * @return 返回一个bool值，成功true
	 * @throws SingerIdNotExistException 歌手id不存在异常
	 */
	public boolean notCollectionSinger(int singerId)throws SingerIdNotExistException;
	
	/**
	 * 通过session中的当前userid 和 传递过来的CDid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param CDId 专辑id
	 * @return 返回一个bool值，成功true
	 * @throws CDIdNotExistException 专辑id不存在异常
	 */
	public boolean notCollectionCD(int CDId)throws CDIdNotExistException;
	
	/**
	 * 通过session中的当前userid 和 传递过来的songListid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param songListId 歌单id
	 * @return 返回一个bool值，成功true
	 * @throws SongListIdNotExistException 歌单id不存在异常
	 */
	public boolean notCollectionSongList(int songListId)throws SongListIdNotExistException;
	
	/**
	 * 通过session中的当前userid 和 传递过来的songid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param songId 歌单id
	 * @return 返回一个bool值，成功true
	 * @throws SongIdNotExistException 歌单id不存在异常
	 */
	public boolean notCollectionSong(int songId)throws SongIdNotExistException;
	
	
}
