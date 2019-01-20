package cn.service.admin;

import java.util.List;
import cn.exception.AdminException;
import cn.exception.TypeIdNotExistException;
import cn.pojo.TypePo;
import cn.pojo.vo.TypeVo;


/**
 * 后台类别服务接口
 * @author xiaoyefeng
 *
 */

public interface AdminTypeService {
	
	/**
	 * 添加类型
	 * @param TypePo  类型对象
	 * @throws AdminException
	 * 添加事务 
	 */
	public boolean addType(TypePo typePo) throws AdminException;
	/**
	 * 修改类型信息
	 * @param TypePo  类型对象
	 * @throws AdminException
	 * 添加事务 
	 */
	public boolean updateType(TypePo typePo) throws AdminException;
	/**
	 * 删除类型信息
	 * 
	 * @param typeId 类型ID
	 * @throws AdminException
	 * 添加事务 
	 */
	public boolean deleteType(Integer typeId) throws AdminException;
	
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
