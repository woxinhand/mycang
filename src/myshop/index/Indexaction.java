package myshop.index;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import myshop.category.Category;
import myshop.category.CategoryService;
import myshop.product.Product;
import myshop.product.ProductService;

public class Indexaction extends ActionSupport{
	private ProductService productService;
	private List<Product> hotlist;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public String execute(){
		List<Category> categorylist=categoryService.findALL();
		ActionContext.getContext().getSession().put("categorylist", categorylist);
		setHotlist(productService.findHot());
		return "indexSuccess";
		
	}
	public List<Product> getHotlist() {
		return hotlist;
	}
	public void setHotlist(List<Product> hotlist) {
		this.hotlist = hotlist;
	}

}
