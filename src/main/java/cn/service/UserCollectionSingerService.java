package cn.service;

import java.util.List;

import cn.exception.CollectionException;
import cn.exception.CollectionIdNotExistException;
import cn.exception.SingerIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.pojo.UserWithSingerPo;
import cn.pojo.vo.UserCollectionSingerVo;
/**
 * 收藏歌手服务类
 * @author liuqiao
 *
 */
public interface UserCollectionSingerService {
	/**
	 * 通过session中的当前userid 和 传递过来的singerid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param singerId 歌手id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SingerIdNotExistException 歌手id不存在异常
	 */
	public boolean notCollectionSinger(int singerId,int userId)throws SingerIdNotExistException;
	
	
	
	/**
	 * 通过session中的当前userid 和 传递过来的singerid 进行歌手收藏，收藏量+1
	 * 写入对应的关联表
	 * @param singerId 歌手id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SingerIdNotExistException 歌手id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionSinger(int singerId,int userId)throws SingerIdNotExistException,CollectionException;
	
	
	/**
	 * 判断歌手是否被收藏
	 * @param singId 歌手id
	 * @param userId 用户id
	 * @return 一个bool值，收藏为true
	 * @throws SingerIdNotExistException 歌手id不存在异常
	 */
	public boolean isConllectionForSingerId(int singId,int userId)throws SingerIdNotExistException;
	
	
	/**
	 * 通过用户id 得到UserWithSinger收藏的所有歌曲拓展类集合
	 * @param userId 用户id
	 * @return 返回一个UserWithSinger收藏的拓展类集合
	 * @throws UserIdNotExistException 用户id不存在异常
	 */
	public List<UserCollectionSingerVo> getUserCollectionById(int userId)throws UserIdNotExistException;
	
	/**
	 * 通过主键id 得到对应UserWithSinger
	 * @param Key 主键
	 * @return 返回一个UserWithSinger收藏的po
	 */
	public UserWithSingerPo getUserCollectionByKey(int Key)throws CollectionIdNotExistException;
	
}
