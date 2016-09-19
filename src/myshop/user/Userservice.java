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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	public User findByCode(String code) {
		// TODO �Զ����ɵķ������
		return userdao1.findByCode( code);
	}

	public  void update(User existuser) {
		// TODO �Զ����ɵķ������
		userdao1.update(existuser);
	}

	public User login(User user1) {
		
		// TODO �Զ����ɵķ������
		return userdao1.login(user1);
	}

	public User findByName(String username) {
		// TODO �Զ����ɵķ������
		return userdao1.findByName(username);
	}

	

	

}
