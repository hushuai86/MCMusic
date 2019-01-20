package cn.pojo;

import java.util.Date;

public class UserPo {
    private Integer userid;

    private String loginid;

    private String password;

    private String username;

    private String usersex;

    private String email;

    private String phone;

    private Integer usertype;

    private String sign;

    private String headsculptureurl;

    private Date registationdate;

    private Integer userstateid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid == null ? null : loginid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex == null ? null : usersex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getHeadsculptureurl() {
        return headsculptureurl;
    }

    public void setHeadsculptureurl(String headsculptureurl) {
        this.headsculptureurl = headsculptureurl == null ? null : headsculptureurl.trim();
    }

    public Date getRegistationdate() {
        return registationdate;
    }

    public void setRegistationdate(Date registationdate) {
        this.registationdate = registationdate;
    }

    public Integer getUserstateid() {
        return userstateid;
    }

    public void setUserstateid(Integer userstateid) {
        this.userstateid = userstateid;
    }

	@Override
	public String toString() {
		return "UserPo [userid=" + userid + ", loginid=" + loginid
				+ ", password=" + password + ", username=" + username
				+ ", usersex=" + usersex + ", email=" + email + ", phone="
				+ phone + ", usertype=" + usertype + ", sign=" + sign
				+ ", headsculptureurl=" + headsculptureurl
				+ ", registationdate=" + registationdate + ", userstateid="
				+ userstateid + "]\n";
	}
    
}