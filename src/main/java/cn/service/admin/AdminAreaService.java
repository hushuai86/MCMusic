package cn.service.admin;


import java.util.List;
import cn.exception.AdminException;
import cn.pojo.AreaPo;
import cn.pojo.vo.AreaVo;


/**
 * 后台地区服务接口
 * @author taz
 *
 */
public interface AdminAreaService {
	/**
	 * 单个插入一条数据
	 * 查询是否存在 如果存在返回 false
	 * @param area area对象
	 * @return boolean true
	 */
	
	public boolean addAreaOne(AreaPo area)throws AdminException;
	/**
	 * 批量进行插入
	 * 检索是否有存在的 
	 * 如果存在就跳过存在的
	 * @return boolean true 插入成功
	 */
	public boolean addAreaMutilArea(List<AreaPo> list)throws AdminException;
	
	/**
	 * @param area 
	 * @return true 修改成功
	 */
	public boolean updateArea(AreaPo area) throws AdminException;
	/**
	 * 获取所有地区实体对象
	 * @return 返回一个list容器，填充地区对象
	 */ 
	public List<AreaPo> getAllAreaPo();
	
	/**
	 * 根据地区id到地区实体对象
	 * @param areaId 地区id
	 * @return 地区实体对象
	 * @throws AdminException 地区id不存在异常
	 */
	public AreaPo getAllAreaPoById(int areaId)throws AdminException;
	
	/**
	 * 根据地区Name到地区实体对象
	 * @param areaName 地区name
	 * @return 地区实体对象
	 */
	public AreaPo getAllAreaPoByName(String areaName);
	/**
	 * 获取所有地区拓展对象（添加了字段list<SingerPo>）
	 * @return 返回一个list容器，填充地区拓展对象
	 */
	public List<AreaVo> getAllAreaVo();	
	/**
	 * 根据地区id到地区拓展对象（添加了字段list<SingerPo>）
	 * @return 地区实体拓展对象
	 * @throws AdminException 地区id不存在异常
	 */
	public AreaVo getAreaVoById(int areaId)throws AdminException;
	
	
}
