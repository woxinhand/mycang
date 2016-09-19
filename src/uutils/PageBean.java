package uutils;

import java.util.List;

import myshop.product.Product;

public class PageBean<T> {
private Integer page;
public Integer getPage() {
	return page;
}
public void setPage(Integer page) {
	this.page = page;
}
public Integer getLimit() {
	return limit;
}
public void setLimit(Integer limit) {
	this.limit = limit;
}
public Integer getTotalCount() {
	return totalCount;
}
public void setTotalCount(Integer totalCount) {
	this.totalCount = totalCount;
}
public Integer getTotalPage() {
	return totalPage;
}
public void setTotalPage(Integer totalPage) {
	this.totalPage = totalPage;
}
public List<T> getList() {
	return list;
}
public <T> void setList() {
	this.list = list;
}
private Integer limit;
private Integer totalCount;
private Integer totalPage;
private List<T> list;
public void setList(List<T> list) {
	this.list = list;
}

}
