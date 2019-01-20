package cn.pojo;

import java.util.List;

public class PageBasePo<T> {
	private T indexEntity;
	private int pageSize;//每页大小
	private int pageIndex;//每页位置
	private int AllNum;//总数量
	private int pageCount;//页数量
	private List<T> list;
	public T getIndexEntity() {
		return indexEntity;
	}
	public void setIndexEntity(T indexEntity) {
		this.indexEntity = indexEntity;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getAllNum() {
		return AllNum;
	}
	public void setAllNum(int allNum) {
		AllNum = allNum;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageBasePo [indexEntity=" + indexEntity + ", pageSize="
				+ pageSize + ", pageIndex=" + pageIndex + ", AllNum=" + AllNum
				+ ", pageCount=" + pageCount + ", list=" + list + "]\n";
	}
	
	
	

}
