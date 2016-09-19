package myshop.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;


public class Useraction extends ActionSupport implements ModelDriven<User> {
	
private User user1 = new User();
private Userservice userservice1;
private String checkcode;
public void setUser1(User user1) {
	this.user1 = user1;
}
public void setCheckcode(String checkcode) {
	this.checkcode = checkcode;
}				
public User getModel(){
	return user1;
}
public String registPage(){
	return "registPageSuccess";
}
@InputConfig(resultName="registInput")
public String regist(){
	String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkCode");
	if(checkcode==null || !checkcode.equalsIgnoreCase(checkcode1)){
		this.addActionError("验证码有误，小四眼");
		return "registInput";
	};
	userservice1.regist(user1);
	this.addActionMessage("注册成功，请激活认证");
	return "registSuccess";
}
public String active(){
	User existuser=userservice1.findByCode(user1.getCode());
	if(existuser!=null){
		existuser.setState(1);
		userservice1.update(existuser);
		this.addActionMessage("激活成功");
		return "registSuccess";
	}
	this.addActionMessage("激活失败，你大爷的从新注册吧");
	return "registPageSuccess";
}


public String loginPage(){
	return "loginPageSuccess";
}
@InputConfig(resultName="loginInput")
public String login(){
	User existuser = userservice1.login(user1);
	if(existuser == null){
		this.addActionError("错了 ，傻逼");
		return "loginIuput";
	}else{
		
		ServletActionContext.getRequest().getSession().setAttribute("existuser", existuser);
	    return "loginSuccess";
	}
	
}
public String checkusernaem() throws IOException{
	User existuser=userservice1.findByName(user1.getUsername());
	HttpServletResponse response=ServletActionContext.getResponse();
	response.setContentType("text/html; charset=utf-8");
	if(existuser==null){
		response.getWriter().print("<font color='green'>用户名可以用傻逼</font>");
	}else{
		response.getWriter().print("<font color='red'>用户名bu可以用傻逼</font>");	
	}
	return NONE;
}
public void setUserservice1(Userservice userservice1) {
	this.userservice1 = userservice1;
}
}
