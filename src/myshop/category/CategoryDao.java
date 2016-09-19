package myshop.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport {

	public List<Category> findALL() {
		// TODO 自动生成的方法存根
		return this.getHibernateTemplate().find("from Category");
	}

}
