package cn.pojo.vo;

import cn.pojo.SongListPo;
import cn.pojo.UserWithSongListPo;

/**
 * UserWithSongList类数据传递的拓展，拓展添加了SongList
 * @author liuqiao
 */
public class UserCollectionSongListVo {
	private int uSongListId;
	private SongListPo songListPo;
	private UserWithSongListPo userWithSongListPo;
	
	public int getuSongListId() {
		return uSongListId;
	}
	public void setuSongListId(int uSongListId) {
		this.uSongListId = uSongListId;
	}
	public SongListPo getSongListPo() {
		return songListPo;
	}
	public void setSongListPo(SongListPo songListPo) {
		this.songListPo = songListPo;
	}
	public UserWithSongListPo getUserWithSongListPo() {
		return userWithSongListPo;
	}
	public void setUserWithSongListPo(UserWithSongListPo userWithSongListPo) {
		this.userWithSongListPo = userWithSongListPo;
	}
	
}
