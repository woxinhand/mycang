package myshop.product;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import myshop.category.Category;
import myshop.category.CategoryService;
import uutils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	PageBean<Product> pagebean;
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public PageBean<Product> getPagebean() {
		return pagebean;
	}

	public void setPagebean(PageBean<Product> pagebean) {
		this.pagebean = pagebean;
	}

	private Integer cid;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private Integer page;
	
	private ProductService productService;
    private CategoryService categoryService;
	public CategoryService getCategoryService() {
	return categoryService;
}

public void setCategoryService(CategoryService categoryService) {
	this.categoryService = categoryService;
}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String findByCid(){
		List<Category> categorylist = categoryService.findALL();
		ActionContext.getContext().getValueStack().set("categorylist", categorylist);
	    pagebean=productService.findByPage(cid,page);
		
		return "findByCidSuccess";
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public Product getModel() {
		// TODO 自动生成的方法存根
		return product;
	}
    public String findByPid(){
    	Product product = new Product();
    	List<Category> categorylist = categoryService.findALL();
		ActionContext.getContext().getValueStack().set("categorylist", categorylist);
    	product=productService.findByPid(product.getPid());
    	return "findByPidSuccess";
    }
    
    public String findByCsid() {
		// 查询所有一级分类:
		List<Category> categoryList = categoryService.findALL();
		// 获得值栈:
		ActionContext.getContext().getValueStack()
				.set("categoryList", categoryList);

		pagebean = productService.findByCsid(csid, page);
		return "findByCsidSuccess";
	}
}
