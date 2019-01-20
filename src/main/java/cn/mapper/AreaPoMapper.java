package cn.mapper;

import java.util.List;

import cn.pojo.AreaPo;
import cn.pojo.vo.AreaVo;

public interface AreaPoMapper {
    int deleteByPrimaryKey(Integer areaid);
    
    int insert(AreaPo record);

    int insertSelective(AreaPo record);

    AreaPo selectByPrimaryKey(Integer areaid);

    int updateByPrimaryKeySelective(AreaPo record);

    int updateByPrimaryKey(AreaPo record);
    
    //自写
    
    //地区名查地区实体对象
    AreaPo selectByAreaName(String areaname);
    //获取所有地区实体对象集合
    List<AreaPo> selectAllAreaPo();
    //获取所有地区扩建对象集合
    List<AreaVo> selectAllAreaVo();
    //通过地区Id查地区扩展对象
    AreaVo selectAreaVoById(Integer areaid);
    
   
 }