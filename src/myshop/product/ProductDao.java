package myshop.product;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uutils.PageHibernateCallback;



public class ProductDao extends HibernateDaoSupport{

	public List<Product> findHot() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		//List<Product> list1 = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>("from Product where is_hot=?", new Object[]{1}, 0, 10));
		return list;
	}

	public Integer findCount(Integer cid) {
		List<Long> list= this.getHibernateTemplate().find("select count(*) from Product p , CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?",cid);
		
		return list.get(0).intValue();
	}

	public List<Product> findBypage(Integer cid, int begin, int limit) {
		String sql = "select * from Product p CategorySecond cs where p.categorySecond = cs and cs.category.cid = ? ";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>("sql", new Object[]{cid}, begin, limit));
		return list;
	}

	public Product findByPid(Integer pid) {
	
		return this.getHibernateTemplate().get(Product.class,pid );
	}
	public Integer findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p join p.categorySecond cs where cs.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		return list.get(0).intValue();
	}
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		return list;
	}

}
