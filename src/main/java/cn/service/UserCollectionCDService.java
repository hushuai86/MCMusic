package cn.service;

import java.util.List;

import cn.exception.CDIdNotExistException;
import cn.exception.CollectionException;
import cn.exception.CollectionIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.pojo.UserWithCDPo;
import cn.pojo.vo.UserCollectionCDVo;

/**
 * 收藏专辑服务接口
 * @author liuqiao
 *
 */
public interface UserCollectionCDService {

	/**
	 * 判断cd是否被收藏
	 * @param CDId 专辑id
	 * @param userId 用户id
	 * @return 一个bool值，收藏为true
	 * @throws CDIdNotExistException 专辑id不存在异常
	 */
	public boolean isConllectionForCDId(int CDId,int userId)throws CDIdNotExistException;

	/**
	 * 通过session中的当前userid 和 传递过来的CDid 进行专辑收藏，收藏量+1
	 * 写入对应的关联表
	 * @param CDId 专辑id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws CDIdNotExistException 专辑id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionCD(int CDId,int userId)throws CDIdNotExistException,CollectionException;

	/**
	 * 通过session中的当前userid 和 传递过来的CDid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param CDId 专辑id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws CDIdNotExistException 专辑id不存在异常
	 */
	public boolean notCollectionCD(int CDId,int userId)throws CDIdNotExistException;
	
	/**
	 * 通过用户id 得到UserWithCD收藏的所有歌曲拓展类集合
	 * @param userId 用户id
	 * @return 返回一个UserWithCD收藏的拓展类集合
	 * @throws UserIdNotExistException 用户id不存在异常
	 */
	public List<UserCollectionCDVo> getUserCollectionById(int userId)throws UserIdNotExistException;
	
	/**
	 * 通过主键id 得到对应UserWithCD
	 * @param Key 主键
	 * @return 返回一个UserWithCD收藏的po
	 */
	public UserWithCDPo getUserCollectionByKey(int Key)throws CollectionIdNotExistException;
	
	
	
}
