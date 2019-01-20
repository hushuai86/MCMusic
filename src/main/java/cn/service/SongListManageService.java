package cn.service;

import cn.exception.SongListAddPutException;
import cn.exception.SongListIdNotExistException;
import cn.pojo.SongListPo;

/**
 * 歌单管理服务接口
 * @author liuqiao
 *
 */
public interface SongListManageService {
	/**
	 * 创建歌单
	 * 创建歌单默认作者收藏歌单
	 * @param songListPo 实体容器，用填充创建歌单的数据
	 * @return 一个bool值，成功true
	 * @throws SongListIdNotExistException 歌单字段验证异常
	 */
	public boolean createSongList(SongListPo songListPo)throws SongListIdNotExistException;
	
	/**
	 * 放弃歌单
	 * 判断该歌单收藏人数，如果只有一个人，即作者，这进行表记录删除。如果超过一个人，则进行收藏记录删除
	 * @param songListId 歌单id
	 * @return
	 */
	public boolean giveUpSongList(int songListId);
	
	
	/**
	 * 判断歌曲是否已经添加到该歌单
	 * @param songId 歌曲id
	 * @param songListId  歌单id
	 * @return 一个bool值，已经添加为true
	 */
	public boolean isAddSongForSongList(int songId,int songListId);
	
	
	/**
	 * 添加歌曲
	 * 通过两个id在歌单歌曲表中添加歌曲
	 * @param songId 歌曲id
	 * @param songListId 歌单id
	 * @return 一个bool值，成功为true
	 * @throws SongListAddPutException 歌曲操作异常（例如：歌曲已经添加）
	 */
	public boolean addSongForSongList(int songId,int songListId)throws SongListAddPutException;
	
	/**
	 * 删除歌曲
	 * 通过两个id在歌单歌曲表中移除歌曲
	 * @param songId 歌曲id
	 * @param songListId 歌单id
	 * @return 一个bool值，成功为true
	 * @throws SongListAddPutException 歌曲操作异常
	 */
	public boolean putSongForSongList(int songId,int songListId)throws SongListAddPutException;
}
