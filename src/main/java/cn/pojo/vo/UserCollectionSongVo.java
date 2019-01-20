package cn.pojo.vo;

import cn.pojo.SongPo;
import cn.pojo.UserWithSongPo;

/**
 * UserWithSong类数据传递的拓展，拓展添加了Song
 * @author liuqiao
 */
public class UserCollectionSongVo {
	private int uSongId;
	private SongPo songPo;
	private UserWithSongPo userWithSongPo;
	private String singerName;
	
	
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public int getuSongId() {
		return uSongId;
	}
	public void setuSongId(int uSongId) {
		this.uSongId = uSongId;
	}
	public SongPo getSongPo() {
		return songPo;
	}
	public void setSongPo(SongPo songPo) {
		this.songPo = songPo;
	}
	public UserWithSongPo getUserWithSongPo() {
		return userWithSongPo;
	}
	public void setUserWithSongPo(UserWithSongPo userWithSongPo) {
		this.userWithSongPo = userWithSongPo;
	}
	
}
