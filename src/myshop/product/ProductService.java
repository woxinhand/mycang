package myshop.product;

import java.util.List;

import uutils.PageBean;



public class ProductService {
	private ProductDao productdao;

	public void setProductdao(ProductDao productdao) {
		this.productdao = productdao;
	}

	public List<Product> findHot() {
		// TODO 自动生成的方法存根
		return productdao.findHot();
	}

	public PageBean<Product> findByPage(Integer cid, Integer page) {
		int limit = 12;
		int totalPage = 0;
		PageBean<Product> pagebean = new PageBean<Product>();
		pagebean.setPage(page);
		pagebean.setLimit(limit);
		Integer totalCount=productdao.findCount(cid);
		pagebean.setTotalCount(totalCount);
		if(totalCount % limit ==0){
			totalPage=totalCount/limit;
		}else{
			totalPage=totalCount/limit+1;
		}
		pagebean.setTotalPage(totalPage);
		int begin = (page-1)*limit;
		List<Product> list = productdao.findBypage(cid,begin ,limit);
		pagebean.setList(list);
  		return pagebean;
	}
	public PageBean<Product> findByCsid(Integer csid,Integer page) {
		int limit = 8; // 每页显示记录数.
		int totalPage = 0; // 总页数
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		// 总记录数
		Integer totalCount = productdao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 计算总页数:
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 数据的集合:
		int begin = (page - 1) * limit;
		List<Product> list = productdao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public Product findByPid(Integer pid) {
		// TODO 自动生成的方法存根
		return productdao.findByPid(pid);
	}

}
