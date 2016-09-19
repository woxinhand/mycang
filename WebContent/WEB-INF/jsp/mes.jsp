<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="/struts-tags" prefix="s"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功，请选择登陆</title>
</head>
<body>
<font><s:actionmessage/></font>
<a herf="${pageContext.request.contextPath}/index.action">回到主页</a>
<a herf="${pageContext.request.contextPath}/user_registPage.action">注册</a>
<a herf="${pageContext.request.contextPath}/user_loginPage.action">登陆</a>

</body>
</html>