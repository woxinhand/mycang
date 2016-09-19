package myshop.user;



import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class Userdao extends HibernateDaoSupport{

	public void save(User user1) {
		this.getHibernateTemplate().save(user1);
		
	}

	public  User findByCode(String code) {
		List<User> list=this.getHibernateTemplate().find("from user where code=?", code);
		return list.get(0);
	}

	

	public void update(User existuser) {
		// TODO 自动生成的方法存根
		this.getHibernateTemplate().update(existuser);
	}

	public User login(User user1) {
		// TODO 自动生成的方法存根
		List<User> list = this.getHibernateTemplate().find("find user where username= ? and password = ? and state = ?", user1.getUsername(),user1.getPassword(),user1.getState());
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}

	public User findByName(String username) {
		List<User> list=this.getHibernateTemplate().find("from user where username=?", username);
		return list.get(0);
	}

	

}
