package myshop.adminuser;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	// 妯″瀷椹卞姩鎺ユ敹鏁版嵁浣跨敤
	private AdminUser adminUser = new AdminUser();
	// 娉ㄥ叆Service
	private AdminUserService adminUserService ;
	public AdminUser getModel() {
		return adminUser;
	}
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}


	/**
	 * 鍚庡彴鐧婚檰鐨勬柟娉�:
	 */
	public String login(){
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser == null){
			// 鐧婚檰澶辫触
			this.addActionError("鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒!");
			return LOGIN;
		}else{
			// 鐧婚檰鎴愬姛
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}


}
