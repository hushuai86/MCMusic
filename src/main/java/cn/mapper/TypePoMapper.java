package cn.mapper;

import java.util.List;
import java.util.Map;

import cn.pojo.TypePo;
import cn.pojo.vo.TypeVo;

public interface TypePoMapper {
    int deleteByPrimaryKey(Integer typeid);

    int insert(TypePo record);

    int insertSelective(TypePo record);
    //通过类型id获取类型实体对象
    TypePo selectByPrimaryKey(Integer typeid);

    int updateByPrimaryKeySelective(TypePo record);

    int updateByPrimaryKey(TypePo record);
    //获取所有类型实体对象
    List<TypePo> selectAllType();
    //根据类型name得到类型实体
    TypePo selectByName(String typeName);
    //获取所有类型实体拓展对象（拓展songList）
    List<TypeVo> selectAllTypeVo();
    //根据类型id得到类型实体拓展对象（拓展songList）
    TypeVo selectTypeVoByTypeId(Integer typeId);
    

    List<TypePo> selectSomeType(Map<String, Object> map);
    
    
    
}