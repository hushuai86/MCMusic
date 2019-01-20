package cn.service;

import java.util.List;

import cn.exception.CDIdNotExistException;
import cn.exception.SingerIdNotExistException;
import cn.exception.SongDownStateException;
import cn.exception.SongIdNotExistException;
import cn.pojo.PageBasePo;
import cn.pojo.SongPo;
import cn.pojo.vo.SongVo;

/**
 * 歌曲服务接口
 * @author liuqiao
 *
 */
public interface SongService {
	/**
	 * 播放次数加一
	 * 添加事务
	 * @param songId 歌曲id
	 * @return 返回当前次数
	 * @throws SongIdNotExistException id不存在异常
	 */
	public long addPlayCount(int songId)throws SongIdNotExistException;
	
	/**
	 * 下载次数加一
	 * 添加事务
	 * @param songId 歌曲id
	 * @return 返回当前次数
	 * @throws SongIdNotExistException id不存在异常
	 */
	public long addDownloadCount(int songId)throws SongIdNotExistException;
	
	/**
	 * 收藏次数加一
	 * 添加事务
	 * @param songId 歌曲id
	 * @return 返回当前次数
	 * @throws SongIdNotExistException id不存在异常
	 */
	public long addCollectionCount(int songId)throws SongIdNotExistException;
	
	/**
	 * 收藏次数减一
	 * 添加事务
	 * @param songId 歌曲id
	 * @return 返回当前次数
	 * @throws SongIdNotExistException id不存在异常
	 */
	public long cutCollectionCount(int songId)throws SongIdNotExistException;
	
	
	
	/**
	 * 获取所有歌曲实体对象，状态码不为阻塞（不显示）状态
	 * @return 返回一个list容器，填充歌曲实体对象
	 */
	public List<SongPo> getShowSongPo();
	
	/**
	 * 通过歌曲id得到一个实体对象，状态码不为阻塞（不显示）状态
	 * @param songId 歌曲id
	 * @return 返回一个song实体对象
	 * @throws SongIdNotExistException 歌曲id为空异常
	 */
	public SongPo getShowSongPoById(int songId)throws SongIdNotExistException;
	
	/**
	 * 通过当前用户权限和歌曲状态，判断是否可以下载
	 * @param songId 歌曲id
	 * @return
	 * @throws SongDownStateException 歌曲状态异常（不可下载）
	 */
	public boolean isSongDown(int songId)throws SongDownStateException;
	
	
	
	/**
	 * 获取所有songPo实体拓展对象（添加字段singPo,cdPo,typePo），状态码不为阻塞（不显示）状态
	 * @return 一个list容器，填充拓展实体对象
	 */
	public List<SongVo> getShowSongVo();
	
	/**
	 * 通过歌曲id获取一个songPo实体拓展对象（添加字段singPo,cdPo,typePo），状态码不为阻塞（不显示）状态
	 * @param songId 歌曲id
	 * @return 返回一个拓展实体对象
	 * @throws SongIdNotExistException 歌曲id为空异常
	 */
	public SongVo getShowSongVoById(int songId)throws SongIdNotExistException;
	
	
	
	
	
	/**
	 * 根据歌手id得到其所有歌曲数量
	 * @param SingerId 歌手id
	 * @return 歌曲数量
	 * @throws SingerIdNotExistException 歌手id异常
	 */
	public int getSongCountBySingerId(int SingerId)throws SingerIdNotExistException; 
	
	/**
	 * 得到热门歌曲（热度排序）默认得到20首歌曲
	 * @param SingerId 歌手id
	 * @return 歌曲集合
	 * @throws SingerIdNotExistException 歌手id异常
	 */
	public List<SongVo> getHonSongBySingerId(int SingerId)throws SingerIdNotExistException;  
	
	/**
	 * 分页检索，关键字获取对应名称的songPo实体拓展对象（添加字段singPo,cdPo,typePo），状态码不为阻塞（不显示）状态
	 * @param pageSize 分页大小
	 * @param pageIndex 分页索引
	 * @return 一个分页对象
	 * 只作用于songName
	 */ 
	public PageBasePo<SongVo> filter(SongVo songVo,int pageSize,int pageIndex);
	

	/**
	 * 通过typeId来获取歌曲集合，然后打乱顺序当电台播放
	 * @param typeId 电台类型id
	 * @param pageSize
	 * @param pageIndex
	 * @return 符合电台类型的随机list歌曲集合
	 */
	public List<SongVo> getRadioStation(int typeId,int pageSize,int pageIndex);   
	
	/**
	 * 歌曲日推，随机获取歌曲集合
	 * @return 返回随机获取的歌曲集合
	 */
	public List<SongVo> getSongRecommend();
	
	/**
	 * 根据singerId 得到songPo对象
	 * @param SingerId
	 * @return 返回符合条件的songPO对象
	 * 分页
	 */
	public List<SongPo> getSongPoBySingerId(int singerId,int pageSize,int pageIndex) throws SingerIdNotExistException ;
	
	public int count();
	
	/**
	 * 根据cd得到所有歌曲
	 * @param cdId
	 * @return
	 * @throws CDIdNotExistException
	 */
	public List<SongVo> getSongByCDId(int cdId)throws CDIdNotExistException; 
	
}
