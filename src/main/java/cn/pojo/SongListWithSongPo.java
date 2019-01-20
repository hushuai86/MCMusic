package cn.pojo;

import java.util.Date;

public class SongListWithSongPo {
    private Integer slsongid;

    private Integer songlistid;

    private Integer songid;

    private Date collectiondate;

    public Integer getSlsongid() {
        return slsongid;
    }

    public void setSlsongid(Integer slsongid) {
        this.slsongid = slsongid;
    }

    public Integer getSonglistid() {
        return songlistid;
    }

    public void setSonglistid(Integer songlistid) {
        this.songlistid = songlistid;
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