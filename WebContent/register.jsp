<%@ page language="java" import="java.util.*" 
	
    pageEncoding="GB18030"%>
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"
	+request.getServerPort()+path+"/";
%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>�û�ע��</title>
</head>
<body>
	 <form method="post" action="user.action">
	 �û�����<input type="text" name="username" ><br>
	 ���룺<input type="password" name="password" ><br>
	 ȷ�����룺<input type="password" name="password1" ><br>
	 <input type="submit" value="�ύ">
	</form>
</body>
</html>