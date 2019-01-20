package cn.service;

import java.util.List;
import cn.exception.TypeIdNotExistException;
import cn.pojo.TypePo;
import cn.pojo.vo.TypeVo;

public interface TypeService {
	/**
	 * 获取所有类型实体对象
	 * @return 返回一个list容器，填充类型实体对象
	 */
	public List<TypePo> getAllTypePo();
	
	/**
	 * 根据类型id得到类型实体
	 * @param typeId 类型id
	 * @return 返回一个类型实体对象
	 * @throws TypeIdNotExistException 类型id不存在异常
	 */
	public TypePo getTypePoById(int typeId)throws TypeIdNotExistException;
	
	/**
	 * 根据类型name得到类型实体
	 * @param typeName 类型name
	 * @return 返回一个类型实体对象
	 */
	public TypePo getTypePoByName(String typeName);
	
	
	
	/**
	 * 获取所有类型实体拓展对象（拓展songList）
	 * @return 返回一个list容器，填充类型实体拓展对象
	 */
	public List<TypeVo> getAllTypeVo();
	
	/**
	 * 根据类型id得到类型实体拓展对象（拓展songList）
	 * @param typeId 类型id
	 * @return 返回一个类型实体拓展对象
	 * @throws TypeIdNotExistException 类型id不存在异常
	 */
	public TypeVo getTypeVoById(int typeId)throws TypeIdNotExistException;
	
	
}
