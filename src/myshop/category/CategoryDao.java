package myshop.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport {

	public List<Category> findALL() {
		// TODO �Զ����ɵķ������
		return this.getHibernateTemplate().find("from Category");
	}

}
