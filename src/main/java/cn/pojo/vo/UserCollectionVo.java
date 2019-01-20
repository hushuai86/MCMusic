package cn.pojo.vo;

import java.util.List;

import cn.pojo.CDPo;
import cn.pojo.SingerPo;
import cn.pojo.SongListPo;
import cn.pojo.SongPo;
import cn.pojo.UserPo;
/**
 * UserPo类数据传递的拓展，拓展添加了singer,song,songlist
 * @author liuqiao
 *
 */
public class UserCollectionVo {
	private UserPo userPo;
	private List<SingerPo> singerList;
	private List<CDPo> CDList;
	private List<SongListPo> songListList;
	private List<SongPo> songList;
	public UserPo getUserPo() {
		return userPo;
	}
	public void setUserPo(UserPo userPo) {
		this.userPo = userPo;
	}
	public List<SingerPo> getSingerList() {
		return singerList;
	}
	public void setSingerList(List<SingerPo> singerList) {
		this.singerList = singerList;
	}
	public List<CDPo> getCDList() {
		return CDList;
	}
	public void setCDList(List<CDPo> cDList) {
		CDList = cDList;
	}
	public List<SongListPo> getSongListList() {
		return songListList;
	}
	public void setSongListList(List<SongListPo> songListList) {
		this.songListList = songListList;
	}
	public List<SongPo> getSongList() {
		return songList;
	}
	public void setSongList(List<SongPo> songList) {
		this.songList = songList;
	}
	@Override
	public String toString() {
		return "UserCollectionVo [userPo=" + userPo + ", singerList="
				+ singerList + ", CDList=" + CDList + ", songListList="
				+ songListList + ", songList=" + songList + "]\n";
	}
	
	
}
