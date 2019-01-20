package cn.pojo.vo;

import cn.pojo.SingerPo;
import cn.pojo.UserWithSingerPo;
/**
 * UserWithSinger类数据传递的拓展，拓展添加了singer
 * @author liuqiao
 */
public class UserCollectionSingerVo {
	private int uSingerId;
	private UserWithSingerPo userWithSingerPo;
	private SingerPo singerPo;
	public int getuSingerId() {
		return uSingerId;
	}
	public void setuSingerId(int uSingerId) {
		this.uSingerId = uSingerId;
	}
	public UserWithSingerPo getUserWithSingerPo() {
		return userWithSingerPo;
	}
	public void setUserWithSingerPo(UserWithSingerPo userWithSingerPo) {
		this.userWithSingerPo = userWithSingerPo;
	}
	public SingerPo getSingerPo() {
		return singerPo;
	}
	public void setSingerPo(SingerPo singerPo) {
		this.singerPo = singerPo;
	}
	
}
