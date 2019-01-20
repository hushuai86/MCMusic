package cn.pojo;

public class AreaPo {
    private Integer areaid;

    private String areaname;

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

	@Override
	public String toString() {
		return "AreaPo [areaid=" + areaid + ", areaname=" + areaname + "]\n";
	}
    
}