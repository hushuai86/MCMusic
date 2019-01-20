package cn.service.admin;

import java.util.List;
import cn.exception.AdminException;
import cn.exception.SingerIdNotExistException;
import cn.exception.StateBlockException;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.vo.SingerVo;

/**
 * 后台歌手服务接口
 * @author hushuai
 *
 */

public interface AdminSingerService {
	
	/**
	 * 添加歌手
	 * @param singerPo  歌手对象
	 * @throws AdminException
	 * 
	 */
	public boolean addSinger(SingerPo singerPo) throws AdminException;
    
	/**
	 * 修改歌手信息
	 * @param singerPo  歌手对象
	 * @throws AdminException
	 * 
	 */
	public boolean updateSinger(SingerPo singerPo) throws AdminException;
	/**
	 * 删除歌手
	 * SingerStateId 修改为1
	 * @param singerId 歌手Id
	 * @throws AdminException
	 *  
	 */
	public boolean deleteSinger(Integer singerId) throws AdminException, StateBlockException;
	
	/**
	 * 批量删除歌手
	 * SingerStateId 修改为1
	 * @param List<Integer> 歌手Id集合
	 * @throws AdminException
	 */
	public int deleteSingers(List<Integer> list) throws AdminException, StateBlockException;
	
	/**
	 * 获取所有歌手实体对象，状态码不为阻塞（不显示）状态
	 * @return 返回一个list容器对象，填充歌手实体对象
	 */
	public List<SingerPo> getShowSingerPo();
	
	/**
	 * 分页获取所有歌手实体对象，状态码不为阻塞（不显示）状态
	 * @param pageSize 分页大小
	 * @param pageIndex 分页索引
	 * @return 分页对象
	 */
	public PageBasePo<SingerPo> getShowSingerPo(int pageSize,int pageIndex);
	
	/**
	 * 根据歌手id获取歌手实体对象，状态码不为阻塞（不显示）状态
	 * @param signerId 歌手id
	 * @return 返回一个歌手实体对象
	 * @throws SingerIdNotExistException 歌手id不存在异常
	 */
	public SingerPo getShowSingerPoById(int signerId)throws AdminException;
	
	
	/**
	 * 歌手分页条件筛选，状态为不阻塞状态
	 * 进入歌手模块将singerPo存储到session中作为查询实体，然后对singerPo进行动态sql查询用于页面检索与筛选
	 * @param singerPo 歌手查询关键字实体对象
	 * @param pageSize 分页大小
	 * @param pageIndex 分页索引
	 * 但是起作用的只有singerSex，accessCount，collectionCount，areaId，SingerName
	 * 其中accessCount，collectionCount用于排序
	 * @return 一个符合条件的分页对象
	 */
	public PageBasePo<SingerPo> filter(SingerPo singerPo,int pageSize,int pageIndex,String firstLetter);

	/**
	 * 获取所有singerPo实体拓展对象（添加字段list<CDPo>,areaPo），状态码不为阻塞（不显示）状态
	 * @return 一个list容器，填充拓展实体拓展对象
	 */
	public List<SingerVo> getShowSingerVo();

	/**
	 * 通过歌手id获取singerPo实体拓展对象（添加字段list<CDPo>,areaPo），状态码不为阻塞（不显示）状态
	 * @param singerId 歌手id
	 * @return 返回一个拓展实体对象
	 * @throws SingerIdNotExistException 歌手为空异常
	 */
	public SingerVo getShowSingerVoById(int singerId)throws AdminException;
     
	/**
	 * 获取歌手总数，状态码不为阻塞（不显示）状态
	 * @return 歌手总数
	 */
	int getShowSingerCount();
    
	/**
	 * 通过歌手名获取歌手，状态码不为阻塞（不显示）状态
	 * @return 歌手对象
	 */
	public SingerPo getShowSingerPoByName(String singerName)throws AdminException;
   
	/**
	 * 通过歌手名模糊查询歌手，状态码不为阻塞（不显示）状态
	 * @return 歌手对象
	 */
	public List<SingerPo> getSingerPosBySingerName(String singername);

	public PageBasePo<SingerPo> filter1(SingerPo singerPo, int pageSize,int pageIndex, String firstLetter);

	public SingerPo getShowSingerPoById1(int signerId)throws AdminException;

}
