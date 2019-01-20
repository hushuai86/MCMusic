package cn.pojo;

import java.util.Date;

public class UserWithSongPo {
    private Integer usongid;

    private Integer userid;

    private Integer songid;

    private Date collectiondate;

    public Integer getUsongid() {
        return usongid;
    }

    public void setUsongid(Integer usongid) {
        this.usongid = usongid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSongid() {
        return songid;
    }

    public void setSongid(Integer songid) {
        this.songid = songid;
    }

    public Date getCollectiondate() {
        return collectiondate;
    }

    public void setCollectiondate(Date collectiondate) {
        this.collectiondate = collectiondate;
    }
}