package cn.pojo.vo;

import java.util.List;

import cn.pojo.AreaPo;
import cn.pojo.CDPo;
import cn.pojo.SingerPo;
/**
 * singerPo类数据传递的拓展，拓展外键，和被引用外键（添加字段list<CDPo>,areaPo）
 * @author liuqiao
 *
 */
public class SingerVo {
	private boolean isCollection; //是否被收藏
	private SingerPo singerPo;
	private AreaPo areaPo;
	private List<CDPo> CDList;
	public boolean isCollection() {
		return isCollection;
	}
	public void setCollection(boolean isCollection) {
		this.isCollection = isCollection;
	}
	public SingerPo getSingerPo() {
		return singerPo;
	}
	public void setSingerPo(SingerPo singerPo) {
		this.singerPo = singerPo;
	}
	public AreaPo getAreaPo() {
		return areaPo;
	}
	public void setAreaPo(AreaPo areaPo) {
		this.areaPo = areaPo;
	}
	public List<CDPo> getCDList() {
		return CDList;
	}
	public void setCDList(List<CDPo> cDList) {
		CDList = cDList;
	}
	@Override
	public String toString() {
		return "SingerVo [isCollection=" + isCollection + ", singerPo="
				+ singerPo + ", areaPo=" + areaPo + ", CDList=" + CDList + "]\n";
	}
	
	
	
	
}
