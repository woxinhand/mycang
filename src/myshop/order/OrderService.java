package myshop.order;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import myshop.user.User;

@Transactional
public class OrderService {
	// ע��DAO
	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}



	// ҵ��㱣�涩��
	public Integer save(Order order) {
		return orderDao.save(order);
	}



	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}



	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}



	public List<Order> findByUid(User existUser) {
		return orderDao.findByUid(existUser);
	}

}
