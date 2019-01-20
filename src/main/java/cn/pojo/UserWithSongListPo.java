package cn.pojo;

import java.util.Date;

public class UserWithSongListPo {
    private Integer usonglistid;

    private Integer userid;

    private Integer songlistid;

    private Date collectiondate;

    public Integer getUsonglistid() {
        return usonglistid;
    }

    public void setUsonglistid(Integer usonglistid) {
        this.usonglistid = usonglistid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSonglistid() {
        return songlistid;
    }

    public void setSonglistid(Integer songlistid) {
        this.songlistid = songlistid;
    }

    public Date getCollectiondate() {
        return collectiondate;
    }

    public void setCollectiondate(Date collectiondate) {
        this.collectiondate = collectiondate;
    }
}