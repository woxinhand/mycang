package myshop.user;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.transaction.annotation.Transactional;

import uutils.mailutils;
import uutils.uuidutils;

@Transactional
public class Userservice {
	private Userdao userdao1;

	public void setUserdao1(Userdao userdao1) {
		this.userdao1 = userdao1;
	}

	public void regist(User user1) {
		user1.setState(0);
		String code = uuidutils.getuuid();
		user1.setCode(code);
		userdao1.save(user1);
		try {
			mailutils.sendMail(user1.getEmail(), code);
		} catch (AddressException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public User findByCode(String code) {
		// TODO 自动生成的方法存根
		return userdao1.findByCode( code);
	}

	public  void update(User existuser) {
		// TODO 自动生成的方法存根
		userdao1.update(existuser);
	}

	public User login(User user1) {
		
		// TODO 自动生成的方法存根
		return userdao1.login(user1);
	}

	public User findByName(String username) {
		// TODO 自动生成的方法存根
		return userdao1.findByName(username);
	}

	

	

}
