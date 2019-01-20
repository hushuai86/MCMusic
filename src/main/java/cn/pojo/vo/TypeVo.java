package cn.pojo.vo;

import java.util.List;

import cn.pojo.SongListPo;
import cn.pojo.TypePo;
/**
 * TypePo类数据传递的拓展，拓展外键，和被引用外键（添加字段songListPo）
 * @author liuqiao
 *
 */
public class TypeVo {
	private TypePo typePo;
	private List<SongListPo> songListList;
	public TypePo getTypePo() {
		return typePo;
	}
	public void setTypePo(TypePo typePo) {
		this.typePo = typePo;
	}
	public List<SongListPo> getSongListList() {
		return songListList;
	}
	public void setSongListList(List<SongListPo> songListList) {
		this.songListList = songListList;
	}
	@Override
	public String toString() {
		return "TypeIncludeSongAndListVo [typePo=" + typePo + ", songListList="
				+ songListList + "]\n";
	}
	
	
	
}
