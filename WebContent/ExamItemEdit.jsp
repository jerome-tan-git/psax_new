<%@ page language="java" 
    pageEncoding="GB18030"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>����༭</title>
</head>
<body>
	<!-- <s:iterator value="users">  -->
	User:	<s:property value="username"/>
	<!-- </s:iterator> -->
	<form method="post" action="userlogout.action">
		 <input type="submit" value="�ǳ�">
	</form>
	
	<br><br>	
	ѡ�п��⣺	
	 <br>
	 <form method="post" action="userlogin.action">
		 ���⣺<input type="password" name="password" ><br> 
		 ���ͣ�<input type="text" name="username" ><br>
		��ѡ�
		 �ο��𰸣�<input type="password" name="password1" ><br>
		 <input type="submit" value="����">
	 </form>
	 <form method="post" action="userlogin.action">
		 <input type="submit" value="ɾ��">
	 </form>	
	<br>	
	
</body>
</html>