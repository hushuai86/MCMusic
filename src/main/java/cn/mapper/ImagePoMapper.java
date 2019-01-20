package cn.mapper;

import java.util.List;
import java.util.Map;

import cn.pojo.ImagePo;
import cn.pojo.PageBasePo;

public interface ImagePoMapper {

    int deleteByPrimaryKey(Integer imageid);

    int insert(ImagePo record);

    int insertSelective(ImagePo record);

    ImagePo selectByPrimaryKey(Integer imageid);

    int updateByPrimaryKeySelective(ImagePo record);

    int updateByPrimaryKey(ImagePo record);
    
    //分页对象
    List<ImagePo> getImagePos(Map<String, Object> map);
    
    int getImagePosCount(Map<String, Object> map);
    
    //模糊查询
    List<ImagePo> getImagePosByName(Map<String, Object> map);
    
    int  getImagePosByNameCount(Map<String, Object> map);

    
    List<ImagePo> getAll();
    
    
    
}