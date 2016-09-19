package myshop.categorysecond;

import java.util.HashSet;
import java.util.Set;

import myshop.category.Category;
import myshop.product.Product;

public class CategorySecond {
	private String csname;
	private Integer csid;
	private Integer cid;
	private Category category;
	private Set<Product> products=new HashSet<Product>();
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
