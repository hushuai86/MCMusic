package cn.mapper;

import java.util.List;
import java.util.Map;

import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.vo.CDVo;

public interface CDPoMapper {
    int deleteByPrimaryKey(Integer cdid);

    int insert(CDPo record);

    int insertSelective(CDPo record);

    CDPo selectByPrimaryKey(Integer cdid);

    int updateByPrimaryKeySelective(CDPo record);

    int updateByPrimaryKey(CDPo record);
    
    //自写
    //通过cdId查找状态不为阻塞的cd对象
    CDPo selectShowCDPoById(Integer cdid);
    
    //查找所有状态不为阻塞的cd对象
    List<CDPo> selectShowCDPo();
    
    //通过cdId查找 cd扩展对象，状态不为阻塞
    CDVo selectShowCDVoById(Integer cdid);
    
    //查找所有状态不为阻塞的cd扩展对象
    List<CDVo> selectShowCDVo();
    
    //分页查找所有状态不为阻塞的cd扩展对象
    List<CDVo> selectShowCDVoPage(Map<String, Object> map);
    
    //所有状态不为阻塞的cd扩展对象的总数
    int selectShowCDVoCount();
    
    //条件查询cd扩展对象总数
    int selectFilterCount(CDVo cdvo);
    
    //分页条件查询cd扩展对象集合
    List<CDVo> selectFilter(PageBasePo<CDVo> pageBasePo);
    
    
    //按照cdname查询
    CDPo selectByCDName(String cdname);
    
    //按照singerId查询
    List<CDPo> selecCdPosBySingerId(int singerId);
    
    //按照singerId查询 根据收藏量排序 状态不阻塞
    List<CDPo> selecHotCdPosBySingerId(int singerId);
    
    //按照singerId查询  状态不阻塞 分页
    List<CDPo> selecHotCdPosBySingerIdByPag(Map map);
    
    //按照专辑名称模糊查询  状态不阻塞
    List<CDPo> selectCdPoBlockByCdName(String cdname);
    //按照专辑名称模糊查询  状态阻塞
	List<CDVo> selectNotShowCDPosByName(Map<String, Object> map);
    //按照专辑名称模糊查询 专辑数量 状态阻塞
	int selectCountNotShowCDPosByName(Map<String, Object> map);
	//专辑名和手名查询专辑
	List<CDPo> selectByCDNameAndSingerId(Map<String, Object> map);
	
    //得到数量根据cdid
    int selecCdBySingerIdCount(int singerId);
 }