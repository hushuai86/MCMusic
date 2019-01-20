package cn.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.CollectionException;
import cn.exception.CollectionIdNotExistException;
import cn.exception.SongIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.mapper.SingerPoMapper;
import cn.mapper.SongPoMapper;
import cn.mapper.UserPoMapper;
import cn.mapper.UserWithCDPoMapper;
import cn.mapper.UserWithSongPoMapper;
import cn.pojo.SongPo;
import cn.pojo.UserPo;
import cn.pojo.UserWithSongPo;
import cn.pojo.vo.UserCollectionSongVo;
import cn.service.UserCollectionSongService;
@Service
public class UserCollectionSongServiceImpl implements UserCollectionSongService {
	@Autowired
	private SongPoMapper songPoMapper;
	@Autowired
	private UserPoMapper userPoMapper;
	@Autowired
	private UserWithSongPoMapper userWithSongPoMapper;
	@Autowired
	private SingerPoMapper singerPoMapper;
	@Override

	//判断歌曲是否被收藏（我喜欢的歌曲）
	public boolean isConllectionForSongId(int songId, int userId)
			throws SongIdNotExistException {
		//存在  将songId 和 userId作为查询条件 查询出符合条件的userWithSongPo对象
		Map map = new HashMap();
		map.put("songId", songId);
		map.put("userId", userId);
		UserWithSongPo userWithSongPo = userWithSongPoMapper
				.selectByUserIdAndSongId(map);
		//如果userWithSongPo有值  则证明已经被收藏 返回false  反之 true
		if (userWithSongPo == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	/**
	 * 通过session中的当前userid 和 传递过来的songid 进行歌曲收藏，收藏量+1
	 * 写入对应的关联表
	 * @param songId 歌曲id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SongIdNotExistException 歌曲id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionSong(int songId, int userId)
			throws SongIdNotExistException, CollectionException {
		SongPo songPo = songPoMapper.selectByPrimaryKey(songId);
		//判断歌手id是否存在
		if (songPo == null) {
			//不存在 抛错
			throw new SongIdNotExistException("歌曲id不存在");
		} else {
			//判断该歌是否被你收藏
			if (isConllectionForSongId(songId, userId)) {
				//已经收藏 抛错
				throw new CollectionException("收藏失败，该歌曲已经被你收藏");
			} else {
				UserWithSongPo userWithSongPo = new UserWithSongPo();
				userWithSongPo.setCollectiondate(new Date());
				userWithSongPo.setSongid(songId);
				userWithSongPo.setUserid(userId);
				//没有收藏  将该歌收藏 并将记录添加到数据库
				userWithSongPoMapper.insert(userWithSongPo);
				//该歌 被收藏次数加一
				songPo.setCollectioncount(songPo.getCollectioncount() + 1);
				songPoMapper.updateByPrimaryKey(songPo);
				return true;
			}
		}
	}

	@Override
	/**
	 * 通过session中的当前userid 和 传递过来的songid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param songId 歌单id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SongIdNotExistException 歌曲id不存在异常
	 */
	public boolean notCollectionSong(int songId, int userId)
			throws SongIdNotExistException {
		//判断歌手id是否存在
		SongPo songPo = songPoMapper.selectByPrimaryKey(songId);
		if (songPo == null) {
			//不存在 抛错
			throw new SongIdNotExistException("歌曲id不存在");
		} else {
			Map map = new HashMap();
			map.put("songId", songId);
			map.put("userId", userId);
			//将你的收藏中删除该歌记录
			userWithSongPoMapper.deleteByUserIdAndSongId(map);
			//该歌收藏数减一
			songPo.setCollectioncount(songPo.getCollectioncount() - 1);
			songPoMapper.updateByPrimaryKey(songPo);
			return true;
		}
	}

	@Override
	/**
	 * 通过用户id 得到UserWithSong收藏的所有歌曲拓展类集合
	 * @param userId 用户id
	 * @return 返回一个UserWithSong收藏的拓展类集合
	 * @throws UserIdNotExistException 用户id不存在异常
	 */							 
	public List<UserCollectionSongVo> getUserCollectionById(int userId)
			throws UserIdNotExistException {
		//根据id查询出对应的userPo对象
		UserPo userPo = userPoMapper.selectByPrimaryKey(userId);
		if(userPo == null){
			//如果id不存在 抛错
			throw new UserIdNotExistException("用户id不存在");
		}else{
			//查询出所有UserCollectionSongVo对象
			List<UserCollectionSongVo>  list = userWithSongPoMapper.selectUserCollectionSongVoByUserId(userId);
			
			/*for (int i = 0; i < list.size(); i++) {
				//由于selectUserCollectionSongVoByUserId没有给UserCollectionSongVo中的singerName属性赋值
				//在这里给singerName赋值  根据查询返回的songPo对象 找到对应的singerId在找到对应的singename
				list.get(i).setSingerName(singerPoMapper.selectByPrimaryKey(list.get(i).getSongPo().getSingerid()).getSingername());
			}*/
			return list;
		}
	}

	@Override
	/**
	 * 通过主键id 得到对应UserWithSong
	 * @param Key 主键
	 * @return 返回一个UserWithSong收藏的po
	 */
	public UserWithSongPo getUserCollectionByKey(int Key)
			throws CollectionIdNotExistException {
		//查询得到相应的Po对象
		UserWithSongPo userWithSongPo = userWithSongPoMapper.selectByPrimaryKey(Key);
		return userWithSongPo;
	}

}
