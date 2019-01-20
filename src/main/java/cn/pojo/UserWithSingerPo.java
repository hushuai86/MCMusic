package cn.pojo;

import java.util.Date;

public class UserWithSingerPo {
    private Integer usingerid;

    private Integer userid;

    private Integer singerid;

    private Date collectiondate;

    public Integer getUsingerid() {
        return usingerid;
    }

    public void setUsingerid(Integer usingerid) {
        this.usingerid = usingerid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSingerid() {
        return singerid;
    }

    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    public Date getCollectiondate() {
        return collectiondate;
    }

    public void setCollectiondate(Date collectiondate) {
        this.collectiondate = collectiondate;
    }

	@Override
	public String toString() {
		return "UserWithSingerPo [usingerid=" + usingerid + ", userid="
				+ userid + ", singerid=" + singerid + ", collectiondate="
				+ collectiondate + "]\n";
	}
    
}