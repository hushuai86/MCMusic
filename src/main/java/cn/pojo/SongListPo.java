package cn.pojo;

public class SongListPo {
    private Integer songlistid;

    private Integer userid;

    private String songlistname;

    private Integer typeid;

    private Integer accesscount;

    private Integer collectioncount;

    private String tags;

    private Integer songliststateid;

    private String imgurl;

    private String introduce;

    public Integer getSonglistid() {
        return songlistid;
    }

    public void setSonglistid(Integer songlistid) {
        this.songlistid = songlistid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSonglistname() {
        return songlistname;
    }

    public void setSonglistname(String songlistname) {
        this.songlistname = songlistname == null ? null : songlistname.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Integer getSongliststateid() {
        return songliststateid;
    }

    public void setSongliststateid(Integer songliststateid) {
        this.songliststateid = songliststateid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}