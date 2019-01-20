package cn.service.impl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.AdminException;
import cn.mapper.CDPoMapper;
import cn.mapper.SingerPoMapper;
import cn.pojo.CDPo;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.vo.CDVo;
import cn.service.admin.AdminCDService;



/**
 * 后台CD服务接口
 * @author taz
 *
 */
@Service
public class AdminCDServiceImpl implements AdminCDService{
	@Autowired
	private CDPoMapper mapper;
	@Autowired
	private SingerPoMapper singerPoMapper;
	
	@Override
	public boolean addCDs(List<CDPo> pos) throws AdminException{
		// TODO Auto-generated method stub
		//批量添加
		CDPo cdPo = new CDPo();
		//遍历传过来的集合
		for(int i=0;i<pos.size();i++){
			cdPo = pos.get(i);
			CDPo cdPo2 =mapper.selectByCDName(cdPo.getCdname());
			//判断是否重名
			if(cdPo2!=null){
				//判断是否同发行时间
				if(cdPo2.getPublishdate()==cdPo.getPublishdate()){
			    //如果专辑名称和发行时间和表中某条数据相同判定已存在，跳过该条数据添加操作
				continue;
				}
			}
			//插入数据
		   int result =mapper.insert(cdPo);
		   if(result==0){
					throw new AdminException("添加方法出错");
				    }
		}
		return true;
	}

	@Override
	public boolean deleteCDs(List<CDPo> pos) throws AdminException {
		// TODO Auto-generated method stub
		//批量删除
		CDPo cdPo = new CDPo();
		//遍历传过来的集合
		for(int i=0;i<pos.size();i++){
			cdPo = pos.get(i);
			//将cdStateId 修改为1 
			cdPo.setCdstateid(1);
			int result = mapper.updateByPrimaryKeySelective(cdPo);
			 if(result==0){
					throw new AdminException("添加方法出错");
				    }
		}
		return true;
	}

	@Override
	public boolean updateCD(CDPo po) throws AdminException {
		// TODO Auto-generated method stub
		int result = mapper.updateByPrimaryKeySelective(po);
		 if(result==0){
				throw new AdminException("修改方法出错");
			    }
		 return true;
		
	}

	@Override
	public CDPo getShowCDPoById(int CDId) throws AdminException {
		// TODO Auto-generated method stub
		//通过cdid查找状态不为阻塞的专辑
		 CDPo cdPo = mapper.selectShowCDPoById(CDId);
		//专辑Id不存在抛异常
		 if(cdPo==null){
				throw new AdminException("专辑ID不存在");
			}
		return cdPo;
	}

	@Override
	public List<CDPo> getShowCDPo(){
		// TODO Auto-generated method stub
		//获取所有的专辑实体对象,状态不为阻塞状态
		List<CDPo>  list = mapper.selectShowCDPo();
		return list;
	}

	@Override
	public CDVo getShowCDVoById(int CDId) throws AdminException{
		// TODO Auto-generated method stub
		//根据专辑id查找到对应的实体拓展类,状态不为阻塞状态
		CDVo  vo = mapper.selectShowCDVoById(CDId);
		//专辑Id不存在抛异常
		if(vo==null){
			throw new AdminException("专辑ID不存在");
		}
		return vo;
	}

	@Override
	public List<CDVo> getShowCDVo(){
		// TODO Auto-generated method stub
		//查找到所有的实体拓展类,状态不为阻塞状态
		List<CDVo> list = mapper.selectShowCDVo();
		return list;
	}

	@Override
	public PageBasePo<CDVo> getShowCDVo(Integer pageSize, Integer pageIndex){
		// TODO Auto-generated method stub
        // 分页查找所有的实体拓展类,状态不为阻塞状态
		
		PageBasePo<CDVo>pageBasePo = new PageBasePo<CDVo>();
		
        //sql语句查寻结果跳过的条数
		int index=(pageIndex-1)*pageSize;
        //查询总条数
		int allNum = mapper.selectShowCDVoCount();
		
        //Map对象存值传值
		Map<String,Object> map  =new HashMap<String,Object>();
	    map.put("index",index);
		map.put("pageSize",pageSize);
		
		//查询Vo对象集合		
		List<CDVo> list=mapper.selectShowCDVoPage(map);
		
		//PageBasePo<CDVo>对象数据封装
		pageBasePo.setAllNum(allNum);
		pageBasePo.setPageCount((int)Math.ceil((double)allNum/pageSize));
		pageBasePo.setPageSize(pageSize);
		pageBasePo.setPageIndex(pageIndex);
		pageBasePo.setList(list);
		return pageBasePo;
	}

	@Override
	public PageBasePo<CDVo> filter(CDVo cdVo, Integer pageSize, Integer pageIndex) {
		// TODO Auto-generated method stub
		/**
	     *对cdVo进行动态sql查询用于页面检索与筛选
		 * cdName（模糊查询）
		 * publishdate(时间段查询，时间段为传入日期到传入日期加一年
		 *  * collectionCount 排序
		 * 返回一个符合条件的分页对象
		 */
		if(pageIndex==null){
			pageIndex= 1;
		}
		
		PageBasePo<CDVo> pageBasePo = new PageBasePo<CDVo>();
		
		//如果专辑名不为空，进行字符串处理便于模糊查询
		CDPo po = cdVo.getCDPo();
		if(po.getCdname()!=null){
		po.setCdname("%"+po.getCdname()+"%");
		}
		
		//将处理后的po对像存到cdVo里
		cdVo.setCDPo(po);

		//存放索引实体对象
		pageBasePo.setIndexEntity(cdVo);
		//存放索引的大小 
		pageBasePo.setPageSize(pageSize);
		
		//存放跳过的条数用于查询
		int index = (pageIndex-1)*pageSize;
		pageBasePo.setPageIndex(index);
	    
		//存放总数和页数
		int allNum = mapper.selectFilterCount(cdVo);
		pageBasePo.setAllNum(allNum);
		pageBasePo.setPageCount((int)Math.ceil((double)allNum/pageSize));

		//调用查询方法	
		List<CDVo> list=mapper.selectFilter(pageBasePo);

		
		//覆盖索引位置
		pageBasePo.setPageIndex(pageIndex);
		
		//存放查询结果集合
		pageBasePo.setList(list);
		return pageBasePo;
	}
	@Override
	public int getShowCDCount(){
		return mapper.selectShowCDVoCount();
	}
	@Override
	public List<CDPo> selectCdNameBySearch(String cdname) {
	 
		return mapper.selectCdPoBlockByCdName("%"+cdname+"%");
	}
	

	public PageBasePo<CDVo> filterNotShowCDPos(String search, Integer pageSize,
			Integer pageIndex) {
		// TODO Auto-generated method stub
		if(pageIndex==null){
			pageIndex= 1;
		}
		
		PageBasePo<CDVo> pageBasePo = new PageBasePo<CDVo>();
		
		//如果查询条件不为空，进行字符串处理便于模糊查询
		if(search!=null){
		search="%"+search+"%";
		}
         Map<String, Object> map = new HashMap<String, Object> ();
		
		//跳过的条数用于查询
		int index = (pageIndex-1)*pageSize;
		
	    map.put("cdname", search);
	    map.put("pageIndex",index);
	    map.put("pageSize", pageSize);
	    
	    
	   //调用查询方法	
	    List<CDVo> list=mapper.selectNotShowCDPosByName(map);
		//存放总数和页数
		int allNum = mapper.selectCountNotShowCDPosByName(map);
		pageBasePo.setAllNum(allNum);
		pageBasePo.setPageCount((int)Math.ceil((double)allNum/pageSize));
		//存放索引的大小 
		pageBasePo.setPageSize(pageSize);
		//存放索引位置
		pageBasePo.setPageIndex(pageIndex);
		//存放查询结果集合
		pageBasePo.setList(list);
		return pageBasePo;
	}

	public CDPo getCDById(int cdId) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(cdId);
	}
//专辑名和手名查询专辑
	public List<CDPo> selectCdNameBySearchAndSingerName(String cdname,
			String singername) {
		// TODO Auto-generated method stub
		if(cdname!=null){
		cdname ="%"+cdname+"%";
		}
		SingerPo singerPo = singerPoMapper.selectBySingerName(singername);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cdname", cdname);
		map.put("singerid", singerPo.getSingerid());
	   return  mapper.selectByCDNameAndSingerId(map);
	}	

}
