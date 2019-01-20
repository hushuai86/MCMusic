package cn.pojo.vo;

import cn.pojo.CDPo;
import cn.pojo.UserWithCDPo;

/**
 * UserWithCD类数据传递的拓展，拓展添加了cd
 * @author liuqiao
 */
public class UserCollectionCDVo {
	private int uCDId;
	private UserWithCDPo userWithCDPo;
	private CDPo cdPo;
	private String singerName;
	
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	
	public int getuCDId() {
		return uCDId;
	}
	public void setuCDId(int uCDId) {
		this.uCDId = uCDId;
	}
	public UserWithCDPo getUserWithCDPo() {
		return userWithCDPo;
	}
	public void setUserWithCDPo(UserWithCDPo userWithCDPo) {
		this.userWithCDPo = userWithCDPo;
	}
	public CDPo getCdPo() {
		return cdPo;
	}
	public void setCdPo(CDPo cdPo) {
		this.cdPo = cdPo;
	}
	
}
