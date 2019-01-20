package cn.service;

import java.util.List;
import cn.exception.CDIdNotExistException;
import cn.exception.SingerIdNotExistException;
import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.vo.CDVo;

/**
 * CD服务接口
 * @author liuqiao
 *
 */

public interface CDService {
	/**
	 * 收藏次数加一
	 * 添加事务
	 * @param cdId 专辑id
	 * @return 返回当前次数
	 * @throws CDIdNotExistException id存在异常
	 */
	public long addCollectionCount(int cdId)throws CDIdNotExistException;
	
	/**
	 * 收藏次数减一
	 * 添加事务
	 * @param cdId 专辑id
	 * @return 返回当前次数
	 * @throws CDIdNotExistException id存在异常
	 */
	public long cutCollectionCount(int cdId)throws CDIdNotExistException;
	
	/**
	 * 根据专辑id查找到对应的实体类,状态不为阻塞状态
	 * @param CDId 专辑id
	 * @return 一个专辑实体对象
	 * @throws CDIdNotExistException 专辑id不存在异常
	 */
	public CDPo getShowCDPoById(int CDId)throws CDIdNotExistException;
	
	/**
	 * 获取所有的专辑实体对象,状态不为阻塞状态
	 * @return 一个list，填充所有实体对象
	 */
	public List<CDPo> getShowCDPo();

	
	/**
	 * 根据专辑id查找到对应的实体拓展类（添加字段list<SongPo>,singerPo）,状态不为阻塞状态
	 * @param CDId 专辑id
	 * @return 一个专辑实体拓展对象
	 * @throws CDIdNotExistException 专辑id不存在异常
	 */
	public CDVo getShowCDVoById(int CDId)throws CDIdNotExistException;
	
	/**
	 * 查找所有的实体拓展类（添加字段list<SongPo>,singerPo）,状态不为阻塞状态
	 * @return 一个list，填充所有实体拓展对象
	 */
	public List<CDVo> getShowCDVo();
	
	
	/**
	 * 分页查找所有的实体拓展类（添加字段list<SongPo>,singerPo）,状态不为阻塞状态
	 * @param pageSize 分页大小
	 * @param pageIndex 分页索引
	 * @return 一个符合条件的分页对象
	 */
	public PageBasePo<CDVo> getShowCDVo(int pageSize,int pageIndex);
	
	
	/**
	 * cd条件筛选，状态为不阻塞状态
	 * 进入专辑模块将cdVo存储到session中作为查询实体，然后对cdVo进行动态sql查询用于页面检索与筛选
	 * @param cdVo 专辑查询关键字实体对象
	 * @param pageSize 分页大小
	 * @param pageIndex 分页索引
	 * 但是起作用的只有cdName（模糊查询），collectionCount
	 * 其中accessCount，collectionCount用于排序
	 * @return 一个符合条件的分页对象
	 */
	public PageBasePo<CDVo> filter(CDVo cdVo,int pageSize,int pageIndex);
	
	/**
	 * 得到热门专辑（热度排序）默认得到20本 状态不阻塞
	 * @param SingerId 歌手id
	 * @return 专辑
	 */
	public List<CDPo> getHotCD(int singerId);
	/**
	 * 得到CDPo 
	 * @param SingerId 歌手id
	 * @return 专辑
	 */
	public List<CDPo> getCDPo(int singerId,int pageSize, int pageIndex);
	
	public int getCdBySingerIdCount(int singerId);
}
