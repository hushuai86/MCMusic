package cn.pojo;

import java.util.Date;

public class SongPo {
    private Integer songid;

    private String songname;

    private Integer singerid;

    private Integer cdid;

    private String language;

    private Integer playcount;

    private Integer downloadcount;

    private Integer collectioncount;

    private Date publishdate;

    private String songurl;

    private String cyricurl;

    private Double songtime;

    private Integer typeid;

    private Integer songstateid;

    public Integer getSongid() {
        return songid;
    }

    public void setSongid(Integer songid) {
        this.songid = songid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname == null ? null : songname.trim();
    }

    public Integer getSingerid() {
        return singerid;
    }

    public void setSingerid(Integer singerid) {
        this.singerid = singerid;
    }

    public Integer getCdid() {
        return cdid;
    }

    public void setCdid(Integer cdid) {
        this.cdid = cdid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public Integer getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(Integer downloadcount) {
        this.downloadcount = downloadcount;
    }

    public Integer getCollectioncount() {
        return collectioncount;
    }

    public void setCollectioncount(Integer collectioncount) {
        this.collectioncount = collectioncount;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getSongurl() {
        return songurl;
    }

    public void setSongurl(String songurl) {
        this.songurl = songurl == null ? null : songurl.trim();
    }

    public String getCyricurl() {
        return cyricurl;
    }

    public void setCyricurl(String cyricurl) {
        this.cyricurl = cyricurl == null ? null : cyricurl.trim();
    }

    public Double getSongtime() {
        return songtime;
    }

    public void setSongtime(Double songtime) {
        this.songtime = songtime;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getSongstateid() {
        return songstateid;
    }

    public void setSongstateid(Integer songstateid) {
        this.songstateid = songstateid;
    }

	@Override
	public String toString() {
		return "SongPo [songid=" + songid + ", songname=" + songname
				+ ", singerid=" + singerid + ", cdid=" + cdid + ", language="
				+ language + ", playcount=" + playcount + ", downloadcount="
				+ downloadcount + ", collectioncount=" + collectioncount
				+ ", publishdate=" + publishdate + ", songurl=" + songurl
				+ ", cyricurl=" + cyricurl + ", songtime=" + songtime
				+ ", typeid=" + typeid + ", songstateid=" + songstateid + "]";
	}
    
}