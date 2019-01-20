package cn.pojo.vo;

import cn.pojo.CDPo;
import cn.pojo.SingerPo;
import cn.pojo.SongPo;
import cn.pojo.TypePo;

/**
 * songPo类数据传递的拓展，拓展外键，和被引用外键（添加字段singPo,cdPo,typePo）
 * @author liuqiao
 *
 */
public class SongVo {
	private Integer songid;
	private boolean isCollection; //是否被收藏
	private SongPo songPo;
	private SingerPo singPo;
	private CDPo cdPo;
	private TypePo typePo;
	
	public Integer getSongid() {
		return songid;
	}
	public void setSongid(Integer songid) {
		this.songid = songid;
	}
	public boolean isCollection() {
		return isCollection;
	}
	public void setCollection(boolean isCollection) {
		this.isCollection = isCollection;
	}
	public SongPo getSongPo() {
		return songPo;
	}
	public void setSongPo(SongPo songPo) {
		this.songPo = songPo;
	}
	public SingerPo getSingPo() {
		return singPo;
	}
	public void setSingPo(SingerPo singPo) {
		this.singPo = singPo;
	}
	public CDPo getCdPo() {
		return cdPo;
	}
	public void setCdPo(CDPo cdPo) {
		this.cdPo = cdPo;
	}
	public TypePo getTypePo() {
		return typePo;
	}
	public void setTypePo(TypePo typePo) {
		this.typePo = typePo;
	}
	@Override
	public String toString() {
		return "SongVo [isCollection=" + isCollection + ", songPo=" + songPo
				+ ", singPo=" + singPo + ", cdPo=" + cdPo + ", typePo="
				+ typePo + "]\n";
	}
	
}
