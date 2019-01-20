package cn.service.admin;

import java.util.List;

import cn.exception.AdminException;
import cn.exception.CDIdNotExistException;
import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.vo.CDVo;



/**
 * 后台CD服务接口
 * @author taz
 *
 */

public interface AdminCDService{

	/**
	 * 添加CDs
	 * @param CDPo
	 * @return true
	 *
	 */
	public boolean addCDs(List<CDPo> pos)throws AdminException;
	
	/**
	 * 删除cds
	 * @param pos
	 * @return
	 */
	public boolean deleteCDs(List<CDPo> pos) throws AdminException;
	
	
	/**
	 * 修改CD
	 * @param po
	 * @return true
	 */
	public boolean updateCD(CDPo po) throws AdminException;
	
	/**
	 * 根据专辑id查找到对应的实体拓展类（添加字段list<SongPo>,singerPo）,状态不为阻塞状态
	 * @param CDId 专辑id
	 * @return 一个专辑实体拓展对象
	 * @throws CDIdNotExistException 专辑id不存在异常
	 */
	/**
	 * 根据专辑id查找到对应的实体类,状态不为阻塞状态
	 * @param CDId 专辑id
	 * @return 一个专辑实体对象
	 * @throws CDIdNotExistException 专辑id不存在异常
	 */
	public CDPo getShowCDPoById(int CDId)throws AdminException;
	
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
	public CDVo getShowCDVoById(int CDId)throws AdminException;
	
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
	public PageBasePo<CDVo> getShowCDVo(Integer pageSize,Integer pageIndex);
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
	public PageBasePo<CDVo> filter(CDVo cdVo,Integer pageSize,Integer pageIndex);
     
	

	/**
	 * 查询所有专辑数,状态不为阻塞状态
	 * @return 专辑总数
	 */
	int getShowCDCount();
	
	/**
	 * 根据cdName模糊查询cdpo对象
	 */
	public List<CDPo> selectCdNameBySearch(String cdname);
}
