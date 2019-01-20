package cn.pojo;

import java.util.Date;

public class UserWithCDPo {
    private Integer ucdid;

    private Integer userid;

    private Integer cdid;

    private Date collectiondate;

    public Integer getUcdid() {
        return ucdid;
    }

    public void setUcdid(Integer ucdid) {
        this.ucdid = ucdid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCdid() {
        return cdid;
    }

    public void setCdid(Integer cdid) {
        this.cdid = cdid;
    }

    public Date getCollectiondate() {
        return collectiondate;
    }

    public void setCollectiondate(Date collectiondate) {
        this.collectiondate = collectiondate;
    }
}