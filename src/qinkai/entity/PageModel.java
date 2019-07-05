package qinkai.entity;

import java.util.List;
public class PageModel {
	private int currentPage; //当前页
	private int totalPage;//总页数
	private int pageSize;//每页的记录数
	private int totalSize; //总的记录数
	private List<Message> list; //当前页的学生集合
	
	public PageModel() {
	}
	public PageModel(int currentPage, int pageSize, int totalSize, List<Message> list) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
		this.list = list;
		this.totalPage = totalSize % pageSize==0 ? totalSize/pageSize : totalSize/pageSize +1;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public List<Message> getList() {
		return list;
	}
	public void setList(List<Message> list) {
		this.list = list;
	}
}

