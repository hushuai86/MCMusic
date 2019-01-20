package cn.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.exception.CollectionException;
import cn.exception.CollectionIdNotExistException;
import cn.exception.SongListIdNotExistException;
import cn.exception.UserIdNotExistException;
import cn.mapper.SongListPoMapper;
import cn.mapper.UserPoMapper;
import cn.mapper.UserWithSongListPoMapper;
import cn.pojo.SongListPo;
import cn.pojo.UserPo;
import cn.pojo.UserWithSongListPo;
import cn.pojo.vo.UserCollectionSongListVo;
import cn.service.UserCollectionSongListService;

@Service
public class UserCollectionSongListServiceImpl implements
		UserCollectionSongListService {
	@Autowired
	private SongListPoMapper songListPoMapper;
	@Autowired
	private UserWithSongListPoMapper userWithSongListPoMapper;
	@Autowired
	private UserPoMapper userPoMapper;
	@Override
	/**
	 * 通过session中的当前userid 和 传递过来的songListid 取消收藏，收藏量-1
	 * 操作对应的关联表
	 * @param songListId 歌单id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SongListIdNotExistException 歌单id不存在异常
	 */
	public boolean notCollectionSongList(int songListId, int userId)
			throws SongListIdNotExistException {
		SongListPo songListPo = songListPoMapper.selectByPrimaryKey(songListId);
		if (songListPo == null) {
			throw new SongListIdNotExistException("歌单id不存在");
		}
		Map map = new HashMap();
		map.put("songListId", songListId);
		map.put("userId", userId);
		System.out.println(songListId+","+userId);
		userWithSongListPoMapper.deleteBySongListIdAndUserId(map);
		songListPo.setCollectioncount(songListPo.getCollectioncount() - 1);
		songListPoMapper.updateByPrimaryKey(songListPo);
		return true;
	}

	@Override
	/**
	 * 通过session中的当前userid 和 传递过来的songListid 进行歌单收藏，收藏量+1
	 * 写入对应的关联表
	 * @param songListId 歌单id
	 * @param userId 用户id
	 * @return 返回一个bool值，成功true
	 * @throws SongListIdNotExistException 歌单id不存在异常
	 * @throws CollectionException 收藏失败异常
	 */
	public boolean CollectionSongList(int songListId, int userId)
			throws SongListIdNotExistException, CollectionException {
		SongListPo songListPo = songListPoMapper.selectByPrimaryKey(songListId);
		if (songListPo == null) {
			throw new SongListIdNotExistException("歌单id不存在");
		}
		if (isConllectionForSongListId(songListId, userId)) {
			throw new CollectionException("收藏失败，该歌单已被你收藏");
		} else {
			UserWithSongListPo userWithSongListPo1 = new UserWithSongListPo();
			userWithSongListPo1.setSonglistid(songListId);
			userWithSongListPo1.setUserid(userId);
			userWithSongListPo1.setCollectiondate(new Date());
			userWithSongListPoMapper.insert(userWithSongListPo1);
			songListPo.setCollectioncount(songListPo.getCollectioncount() + 1);
			songListPoMapper.updateByPrimaryKey(songListPo);
			return true;
		}
	}

	@Override
	/**
	 * 判断歌单是否被收藏
	 * @param songListId 歌单id
	 * @param userId 用户id
	 * @return 一个bool值，收藏为true
	 * @throws SongListIdNotExistException 歌单id不存在异常
	 */
	public boolean isConllectionForSongListId(int songListId, int userId)
			throws SongListIdNotExistException {
		Map map = new HashMap();
		map.put("songListId", songListId);
		map.put("userId", userId);
		UserWithSongListPo userWithSongListPo = userWithSongListPoMapper
				.selectBySongListIdAndUserId(map);
		if (userWithSongListPo == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	/**
	 * 通过用户id 得到UserWithSongList收藏的所有歌曲拓展类集合
	 * @param userId 用户id
	 * @return 返回一个UserWithSongList收藏的拓展类集合
	 * @throws UserIdNotExistException 用户id不存在异常
	 */
	public List<UserCollectionSongListVo> getUserCollectionById(int userId)
			throws UserIdNotExistException {
		
		UserPo userPo = userPoMapper.selectByPrimaryKey(userId);
		if(userPo == null){
			//如果id不存在 抛错
			throw new UserIdNotExistException("用户id不存在");
		}
		List<UserCollectionSongListVo> list = userWithSongListPoMapper.selectUserCollectionSongListVoByUserId(userId);
		
		return list;
	}

	@Override
	/**
	 * 通过主键id 得到对应UserWithSongList
	 * @param Key 主键
	 * @return 返回一个UserWithSongList收藏的po
	 */
	public UserWithSongListPo getUserCollectionByKey(int Key)
			throws CollectionIdNotExistException {
		UserWithSongListPo userWithSongListPo = userWithSongListPoMapper.selectByPrimaryKey(Key);
		return userWithSongListPo;
	}

}
