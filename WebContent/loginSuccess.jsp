<%@ page language="java" 
    pageEncoding="GB18030"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>注册</title>
</head>
<body>
恭喜您，登陆成功！
	<!-- <s:iterator value="users">  -->
		<s:property value="username"/>
	<!-- </s:iterator> -->
	
	<br>
	<a href="checksession.action">浏览页面</a>
	<br><br>
	<a href="inputExam.jsp?examsave=0">管理员页面</a>
	
	
	 <form method="post" action="managerexamcontext.action">
		 <input type="submit" value="管理员页面">
	 </form>
	 <form method="post" action="userlogin.action">
		 <input type="submit" value="删除">
	 </form>
	

	<br>
	<a href="beginexam.action">开始考试</a>
	<br>
	<form method="post" action="userlogout.action">
		 <input type="submit" value="登出">
	</form>
</body>
</html>