package cn.service.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.exception.AdminException;
import cn.exception.TypeIdNotExistException;
import cn.mapper.TypePoMapper;
import cn.pojo.TypePo;
import cn.pojo.vo.TypeVo;
import cn.service.admin.AdminTypeService;

/**
 * 
 * @author xiaoyefeng
 *
 */
@Service
@Transactional
public class AdminTypeServiceImpl implements AdminTypeService {
	@Autowired
	private TypePoMapper typePoMapper;

	@Override
	//添加歌曲类型
	public boolean addType(TypePo typePo) throws AdminException {
		//获取所有类型实体对象
		List<TypePo> list = typePoMapper.selectAllType();
		for (int i = 0; i < list.size(); i++) {
			//循环判断类型是否重复
			if (list.get(i).getTypename() == typePo.getTypename()) {
				throw new AdminException("错误：typeName重复");
			}
		}
		typePoMapper.insertSelective(typePo);
		return true;
	}

	@Override
	//修改歌曲类型信息
	public boolean updateType(TypePo typePo) throws AdminException {
		//根据typePo中的typeId判断该类型是否存在
		if (typePoMapper.selectByPrimaryKey(typePo.getTypeid()) == null) {
			throw new AdminException("错误：typeId不存在");
		} else {
			//执行更新操作
			typePoMapper.updateByPrimaryKey(typePo);
			return true;
		}
		
	}

	@Override
	//删除类型
	public boolean deleteType(Integer typeId) throws AdminException {
		//根据typeId判断该类型是否存在
		if (typePoMapper.selectByPrimaryKey(typeId) == null) {
			throw new AdminException("错误：typeId不存在");
		}else {
			typePoMapper.deleteByPrimaryKey(typeId);
			return true;
		}
		
	}

	@Override
	// 获取所有类型实体对象
	public List<TypePo> getAllTypePo() {
		// 获取所有类型实体对象
		List<TypePo> list = typePoMapper.selectAllType();
		return list;
	}

	@Override
	// 通过类型id获取类型实体对象
	public TypePo getTypePoById(int typeId) throws TypeIdNotExistException {
		// 通过typeId获取类型实体对象
		TypePo typePo = typePoMapper.selectByPrimaryKey(typeId);
		if (typePo == null) {
			throw new TypeIdNotExistException("用户ID不存在");
		} else {
			return typePo;
		}
	}

	@Override
	// 根据类型name得到类型实体
	public TypePo getTypePoByName(String typeName) {
		// 根据typeName获取到类型实体对象并返回
		return typePoMapper.selectByName(typeName);
	}

	@Override
	// 获取所有typeVo对象（拓展songList）
	public List<TypeVo> getAllTypeVo() {
		// 获取所有类型实体拓展对象（拓展songList）并返回
		return typePoMapper.selectAllTypeVo();
	}

	@Override
	// 根据类型id得到typeVo对象（拓展songList）
	public TypeVo getTypeVoById(int typeId) throws TypeIdNotExistException {
		// 通过typeId类型实体拓展对象（拓展songList）并返回
		TypeVo typeVo = typePoMapper.selectTypeVoByTypeId(typeId);
		if (typeVo == null) {
			throw new TypeIdNotExistException("用户ID不存在");
		} else {
			return typeVo;
		}
	}
}
