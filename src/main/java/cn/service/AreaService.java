package cn.service;

import java.util.List;
import java.util.Map;

import cn.exception.AreaIdNotExistException;
import cn.pojo.AreaPo;
import cn.pojo.vo.AreaVo;
/**
 * 地区服务接口
 * @author liuqiao
 *
 */
public interface AreaService {
	/**
	 * 获取所有地区实体对象
	 * @return 返回一个list容器，填充地区对象
	 */ 
	public List<AreaPo> getAllAreaPo();
	
	/**
	 * 根据地区id到地区实体对象
	 * @param areaId 地区id
	 * @return 地区实体对象
	 * @throws AreaIdNotExistException 地区id不存在异常
	 */
	public AreaPo getAllAreaPoById(int areaId)throws AreaIdNotExistException;
	
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
	 * @throws AreaIdNotExistException 地区id不存在异常
	 */
	public AreaVo getAreaVoById(int areaId)throws AreaIdNotExistException;
}
