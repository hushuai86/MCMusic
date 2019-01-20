package cn.service.admin;

import java.util.List;

import cn.exception.AdminException;
import cn.exception.UserIdNotExistException;
import cn.pojo.PageBasePo;
import cn.pojo.UserPo;
import cn.pojo.UserWithCDPo;
import cn.pojo.UserWithSingerPo;
import cn.pojo.vo.UserCollectionCDVo;
import cn.pojo.vo.UserCollectionSingerVo;
import cn.pojo.vo.UserCollectionSongListVo;
import cn.pojo.vo.UserCollectionSongVo;

/**
 * 后台用户服务接口
 * @author 
 *
 */
public interface AdminUserService {
	/**
	 * 添加类型
	 * @param UserPo  类型对象
	 * @throws AdminException
	 */
	public boolean addUser(UserPo userPo) throws AdminException;
	/**
	 * 修改类型信息
	 * @param UserPo  类型对象
	 * @throws AdminException
	 */
	public boolean updateUser(UserPo userPo) throws AdminException;
	/**
	 * 删除类型信息
	 * 
	 * @param userId 类型ID
	 * @throws AdminException
	 */
	
	public boolean deleteUser(Integer userId) throws AdminException;
	
	
	/**
	 * 批量删除信息
	 * @param list
	 * @throws AdminException
	 */
	public boolean deleteUser(List<Integer> list) throws AdminException;
	
	/**
	 * 获取所有类型实体对象
	 * @return 返回一个list容器，填充类型实体对象
	 */
	public List<UserPo> getAllUserPo();
	
	/**
	 * 根据类型id得到类型实体
	 * @param userId 类型id
	 * @return 返回一个类型实体对象
	 * @throws UserIdNotExistException 类型id不存在异常
	 */
	public UserPo getUserPoById(int userId)throws AdminException;
	
	/**
	 * 根据类型name得到类型实体
	 * @param UserName 类型name
	 * @return 返回一个类型实体对象
	 */
	public UserPo getUserPoByName(String userName);
	
	
	
	/**
	 * 获取所有类型实体拓展对象（拓展CD）
	 * @return 返回一个list容器，填充类型实体拓展对象
	 */
	public List<UserCollectionCDVo> getAllUserCollectionCDVo();
	
	
	/**
	 * 获取所有类型实体拓展对象（拓展Singer）
	 * @return 返回一个list容器，填充类型实体拓展对象
	 */
	public List<UserCollectionSingerVo> getAllUserCollectionSingerServiceVo();
	
	/**
	 * 获取所有类型实体拓展对象（拓展SongList）
	 * @return 返回一个list容器，填充类型实体拓展对象
	 */
	public List<UserCollectionSongListVo> getAllUserCollectionSongListVo(int uSongListId);
	
	/**
	 * 获取所有类型实体拓展对象（拓展Song）
	 * @return 返回一个list容器，填充类型实体拓展对象
	 */
	public List<UserCollectionSongVo> getAllUserCollectionSongVo(int uSongId);
	
	
	
	/**
	 * 根据类型id得到类型实体拓展对象（拓展CD）
	 * @param key
	 * @return 返回一个类型实体拓展对象
	 * @throws UserIdNotExistException 类型id不存在异常
	 */
	public UserWithCDPo getUserCollectionCDVoById(int key)throws AdminException;
	
	/**
	 * 根据类型id得到类型实体拓展对象（拓展Singer）
	 * @param key
	 * @return 返回一个类型实体拓展对象
	 * @throws UserIdNotExistException 类型id不存在异常
	 */
	public UserWithSingerPo getUserCollectionSingerVoById(int key)throws AdminException;
	
	
	/**
	 * 根据类型id得到类型实体拓展对象（拓展SongList）
	 * @param userId 类型id
	 * @return 返回一个类型实体拓展对象
	 * @throws UserIdNotExistException 类型id不存在异常
	 */
	public List<UserCollectionSongListVo> getUserCollectionSongListVoById(int userId)throws AdminException;
	
	
	/**
	 * 根据类型id得到类型实体拓展对象（拓展Song）
	 * @param userId 类型id
	 * @return 返回一个类型实体拓展对象
	 * @throws UserIdNotExistException 类型id不存在异常
	 */
	public  List<UserCollectionSongVo> getUserCollectionSongVoById(int userId)throws AdminException;
	/**
	 * 根据username模糊查询
	 * @param userPo
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public PageBasePo<UserPo> filter(UserPo userPo, Integer pageSize, Integer pageIndex);
}
