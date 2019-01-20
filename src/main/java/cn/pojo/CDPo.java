package cn.pojo;

import java.util.Date;

public class CDPo {
    private Integer cdid;

    private String cdname;

    private String coverurl;

    private Integer songcount;

    private Date publishdate;

    private Integer singerid;

    private String introduce;

    private Integer collectioncount;

    private Integer cdstateid;

    public Integer getCdid() {
        return cdid;
    }

    public void setCdid(Integer cdid) {
        this.cdid = cdid;
    }

    public String getCdname() {
        return cdname;
    }

    public void setCdname(String cdname) {
        this.cdname = cdname == null ? null : cdname.trim();
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl == null ? null : coverurl.trim();
    }

    public Integer getSongcount() {
        return songcount;
    }

    public void setSongcount(Integer songcount) {
        this.songcount = songcount;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Integer getSingerid() {
        return singerid;
    }

    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getCollectioncount() {
        return collectioncount;
    }

    public void setCollectioncount(Integer collectioncount) {
        this.collectioncount = collectioncount;
    }

    public Integer getCdstateid() {
        return cdstateid;
    }

    public void setCdstateid(Integer cdstateid) {
        this.cdstateid = cdstateid;
    }
}