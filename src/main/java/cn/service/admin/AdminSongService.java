package cn.service.admin;

import java.util.List;
import cn.exception.AdminException;
import cn.exception.SongIdNotExistException;
import cn.pojo.PageBasePo;
import cn.pojo.SongPo;
import cn.pojo.vo.SongVo;


/**
 * 后台歌曲服务接口
 * @author  xiaoyefeng
 *
 */

public interface AdminSongService {
	/**
	 * 添加歌曲
	 * @param SongPo  歌曲对象
	 * @throws AdminException
	 *  
	 */
	public boolean addSong(SongPo songPo) throws AdminException;
	/**
	 * 修改歌曲信息
	 * @param songPo  歌曲对象
	 * @throws AdminException
	 *  
	 */
	public boolean updateSong(SongPo songPo) throws AdminException;
	/**
	 * 删除歌曲
	 * SongStateId 修改为1
	 * @param songId 歌曲Id
	 * @throws AdminException
	 * 
	 */
	public boolean deleteSong(Integer songId) throws AdminException;
	
	/**
	 * 批量删除歌曲
	 * SongStateId 修改为1
	 * @param List<Integer> 歌曲Id集合
	 * @throws AdminException
	 */
	public int deleteSongs(List<Integer> list) throws AdminException;
	
	/**
	 * 获取所有歌曲实体对象，状态码不为阻塞（不显示）状态
	 * @return 返回一个list容器对象，填充歌曲实体对象
	 */
	public List<SongPo> getShowSongPo();
 
	
	/**
	 * 根据歌曲id获取歌曲实体对象，状态码不为阻塞（不显示）状态
	 * @param songId 歌曲id
	 * @return 返回一个歌曲实体对象
	 * @throws SongIdNotExistException 歌曲id不存在异常
	 */
	public SongPo getShowSongPoById(int songId)throws AdminException;
	
	 

	/**
	 * 获取所有SongPo实体拓展对象（添加字段singPo,cdPo,typePo），状态码不为阻塞（不显示）状态
	 * @return 一个list容器，填充拓展实体拓展对象
	 */
	public List<SongVo> getShowSongVo();

	/**
	 * 通过歌曲id获取SongPo实体拓展对象（添加字段singPo,cdPo,typePo），状态码不为阻塞（不显示）状态
	 * @param SongId 歌曲id
	 * @return 返回一个拓展实体对象
	 * @throws SongIdNotExistException 歌曲为空异常
	 */
	public SongVo getShowSongVoById(int SongId)throws AdminException;
	/**
	 * 模糊查询  分页
	 * @param songVo
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public PageBasePo<SongVo> filter(SongVo songVo, int pageSize, int pageIndex);
	
	/**
	 * 获取歌歌曲总数，状态码不为阻塞（不显示）状态
	 * @return 歌曲总数
	 */
	int getShowSongCount();
}
