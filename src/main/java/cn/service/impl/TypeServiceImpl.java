package cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import cn.exception.TypeIdNotExistException;
import cn.mapper.TypePoMapper;
import cn.pojo.TypePo;
import cn.pojo.vo.TypeVo;
import cn.service.TypeService;
/**
 * 类型服务接口实现
 * @author xiaoyefeng
 *
 */
@Service
public class TypeServiceImpl implements TypeService {
	@Autowired
	TypePoMapper typePoMapper;

	@Override
	//获取所有类型实体对象
	public List<TypePo> getAllTypePo() {
		//获取所有类型实体对象
		List<TypePo> list = typePoMapper.selectAllType();
		return list;
	}

	@Override
	//通过类型id获取类型实体对象
	public TypePo getTypePoById(int typeId) throws TypeIdNotExistException {
		//通过typeId获取类型实体对象
		TypePo typePo = typePoMapper.selectByPrimaryKey(typeId);
		if (typePo == null) {
			throw new TypeIdNotExistException("用户ID不存在");
		} else {
			return typePo;
		}
	}

	@Override
	//根据类型name得到类型实体
	public TypePo getTypePoByName(String typeName) {
		//根据typeName获取到类型实体对象并返回
		return typePoMapper.selectByName(typeName);
	}

	@Override
	//获取所有typeVo对象（拓展songList）
	public List<TypeVo> getAllTypeVo() {
		//获取所有类型实体拓展对象（拓展songList）并返回
		return typePoMapper.selectAllTypeVo();
	}

	@Override
	//根据类型id得到typeVo对象（拓展songList）
	public TypeVo getTypeVoById(int typeId) throws TypeIdNotExistException {
		//通过typeId类型实体拓展对象（拓展songList）并返回
		TypeVo typeVo = typePoMapper.selectTypeVoByTypeId(typeId);
		if (typeVo == null) {
			throw new TypeIdNotExistException("用户ID不存在");
		} else {
			return typeVo;
		}
	}
}
