package cn.pojo.vo;

import java.util.List;

import cn.pojo.SongListPo;
import cn.pojo.SongPo;
import cn.pojo.TypePo;
import cn.pojo.UserPo;

/**
 * songListPo类数据传递的拓展，拓展外键，和被引用外键（添加字段user，song,type）
 * @author liuqiao
 *
 */
public class SongListVo {
	private int songListId;
	private SongListPo songListPo;
	private UserPo userPo;
	private List<SongPo> list;
	private TypePo typePo;
	
	
	public int getSongListId() {
		return songListId;
	}
	public void setSongListId(int songListId) {
		this.songListId = songListId;
	}
	public SongListPo getSongListPo() {
		return songListPo;
	}
	public void setSongListPo(SongListPo songListPo) {
		this.songListPo = songListPo;
	}
	public UserPo getUserPo() {
		return userPo;
	}
	public void setUserPo(UserPo userPo) {
		this.userPo = userPo;
	}
	public List<SongPo> getList() {
		return list;
	}
	public void setList(List<SongPo> list) {
		this.list = list;
	}
	public TypePo getTypePo() {
		return typePo;
	}
	public void setTypePo(TypePo typePo) {
		this.typePo = typePo;
	}
	@Override
	public String toString() {
		return "SongListVo [songListPo=" + songListPo + ", userPo=" + userPo
				+ ", list=" + list + ", typePo=" + typePo + "]\n";
	}
	
}
