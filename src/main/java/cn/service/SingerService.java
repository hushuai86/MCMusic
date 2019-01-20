package cn.service;

import java.util.List;

import cn.exception.SingerIdNotExistException;
import cn.pojo.PageBasePo;
import cn.pojo.SingerPo;
import cn.pojo.vo.SingerVo;

/**
 * 歌手服务接口
 * @author liuqiao
 *
 */
public interface SingerService {
	
	/**
	 * 访问次数加一
	 * 添加事务
	 * @param singerId 歌手id
	 * @return 返回当前次数
	 * @throws SingerIdNotExistException id不存在异常
	 */
	public long addAccessCount(int singerId)throws SingerIdNotExistException;
	
	/**
	 * 收藏次数加一
	 * 添加事务
	 * @param singerId 歌手id
	 * @return 返回当前次数
	 * @throws SingerIdNotExistException id不存在异常
	 */
	public long addCollectionCount(int singerId)throws SingerIdNotExistException;
	
	/**
	 * 收藏次数减一
	 * 添加事务
	 * @param singerId 歌手id
	 * @return 返回当前次数
	 * @throws SingerIdNotExistException id不存在异常
	 */
	public long cutCollectionCount(int singerId)throws SingerIdNotExistException;
	
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
	public SingerPo getShowSingerPoById(int signerId)throws SingerIdNotExistException;
	
	
	/**
	 * 歌手分页条件筛选，状态为不阻塞状态
	 * 进入歌手模块将singerPo存储到session中作为查询实体，然后对singerPo进行动态sql查询用于页面检索与筛选
	 * @param singerPo 歌手查询关键字实体对象
	 * @param pageSize 分页大小
	 * @param pageIndex 分页索引
	 * 但是起作用的只有singerSex，accessCount，collectionCount，areaId，SingerName,firstLetter为歌手首字母
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
	public SingerVo getShowSingerVoById(int singerId)throws SingerIdNotExistException;
	
	/**
	 * 查询非中国国籍歌手
	 */
	public List<SingerPo> getSingerPoForForeign(int pageSize,int pageIndex);
	
}
