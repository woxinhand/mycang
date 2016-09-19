package myshop.order;


import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import myshop.user.User;

public class OrderDao extends HibernateDaoSupport{

	// 持久层保存订单
	public Integer save(Order order) {
		Integer oid = (Integer) this.getHibernateTemplate().save(order);
		return oid;
	}

	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	// 按用户查询订单
	public List<Order> findByUid(User existUser) {
		List<Order> list = this.getHibernateTemplate().find("from Order o where o.user.uid=? order by ordertime desc",existUser.getUid());
		return list;
	}

}