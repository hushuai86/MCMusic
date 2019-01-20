package cn.pojo.vo;

import java.util.List;

import cn.pojo.CDPo;
import cn.pojo.SingerPo;
import cn.pojo.SongPo;
/**
 * CDPo类数据传递的拓展，拓展外键，和被引用外键（添加字段list<SongPo>,singerPo）
 * @author liuqiao
 *
 */
public class CDVo {
	private boolean isCollection; //是否被收藏
	private CDPo CDPo;
	private SingerPo singerPo;
	private List<SongPo> list;
	public boolean isCollection() {
		return isCollection;
	}
	public void setCollection(boolean isCollection) {
		this.isCollection = isCollection;
	}
	public CDPo getCDPo() {
		return CDPo;
	}
	public void setCDPo(CDPo cDPo) {
		CDPo = cDPo;
	}
	public SingerPo getSingerPo() {
		return singerPo;
	}
	public void setSingerPo(SingerPo singerPo) {
		this.singerPo = singerPo;
	}
	public List<SongPo> getList() {
		return list;
	}
	public void setList(List<SongPo> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "CDBelongSingerVo [isCollection=" + isCollection + ", CDPo="
				+ CDPo + ", singerPo=" + singerPo + ", list=" + list + "]\n";
	}
	
	
	
}
