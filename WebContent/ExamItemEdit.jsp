<%@ page language="java" 
    pageEncoding="GB18030"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>考题编辑</title>
</head>
<body>
	<!-- <s:iterator value="users">  -->
	User:	<s:property value="username"/>
	<!-- </s:iterator> -->
	<form method="post" action="userlogout.action">
		 <input type="submit" value="登出">
	</form>
	
	<br><br>	
	选中考题：	
	 <br>
	 <form method="post" action="userlogin.action">
		 问题：<input type="password" name="password" ><br> 
		 类型：<input type="text" name="username" ><br>
		答案选项：
		 参考答案：<input type="password" name="password1" ><br>
		 <input type="submit" value="更新">
	 </form>
	 <form method="post" action="userlogin.action">
		 <input type="submit" value="删除">
	 </form>	
	<br>	
	
</body>
</html>