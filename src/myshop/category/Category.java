package myshop.category;

import java.util.HashSet;
import java.util.Set;

import myshop.categorysecond.CategorySecond;

public class Category {
private String cname;
private Integer cid;
private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}
public Set<CategorySecond> getCategorySeconds() {
	return categorySeconds;
}
public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
	this.categorySeconds = categorySeconds;
}
}
