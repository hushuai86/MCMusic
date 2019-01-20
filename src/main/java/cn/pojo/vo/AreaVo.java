package cn.pojo.vo;

import java.util.List;

import cn.pojo.AreaPo;
import cn.pojo.SingerPo;
/**
 * AreaPo类数据传递的拓展，拓展外键，和被引用外键（添加字段list<singerPo>）
 * @author liuqiao
 *
 */
public class AreaVo {
	private AreaPo areaPo;
	private List<SingerPo> list;
	
	public AreaPo getAreaPo() {
		return areaPo;
	}

	public void setAreaPo(AreaPo areaPo) {
		this.areaPo = areaPo;
	}

	public List<SingerPo> getList() {
		return list;
	}

	public void setList(List<SingerPo> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "AreaVo [areaPo=" + areaPo + ", list=" + list + "]\n";
	}
	
}
