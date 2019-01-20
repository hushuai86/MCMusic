package cn.pojo;

import java.util.Date;

public class SingerPo {
    private Integer singerid;

    private String singername;

    private Integer accesscount;

    private Integer collectioncount;

    private Integer areaid;

    private Date birthday;

    private Boolean singersex;

    private String photourl;

    private Date debutdate;

    private Integer singerstateid;

    private String introduce;

    public Integer getSingerid() {
        return singerid;
    }

    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername == null ? null : singername.trim();
    }

    public Integer getAccesscount() {
        return accesscount;
    }

    public void setAccesscount(Integer accesscount) {
        this.accesscount = accesscount;
    }

    public Integer getCollectioncount() {
        return collectioncount;
    }

    public void setCollectioncount(Integer collectioncount) {
        this.collectioncount = collectioncount;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getSingersex() {
        return singersex;
    }

    public void setSingersex(Boolean singersex) {
        this.singersex = singersex;
    }

    public String getPhotourl() {
        return photourl;
    }


    public void setPhotourl(String photourl) {
        this.photourl = photourl == null ? null : photourl.trim();
    }

    public Date getDebutdate() {
        return debutdate;
    }

    public void setDebutdate(Date debutdate) {
        this.debutdate = debutdate;
    }

    public Integer getSingerstateid() {
        return singerstateid;
    }

    public void setSingerstateid(Integer singerstateid) {
        this.singerstateid = singerstateid;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

	@Override
	public String toString() {
		return "SingerPo [singerid=" + singerid + ", singername=" + singername
				+ ", accesscount=" + accesscount + ", collectioncount="
				+ collectioncount + ", areaid=" + areaid + ", birthday="
				+ birthday + ", singersex=" + singersex + ", photourl="
				+ photourl + ", debutdate=" + debutdate + ", singerstateid="
				+ singerstateid + ", introduce=" + introduce + "]\n";
	}
    
}