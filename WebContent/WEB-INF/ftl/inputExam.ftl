<#--  
<#assign s=JspTaglibs["struts-tags.tld"]>  
 -->  
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"] />
 <meta http-equiv="Content-type" content="text/html; charset=utf-8">
 
<html>  

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>����ϵͳ����</title>
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script>
	$(document).ready(function(){
	  $("#btn1").click(function(){
		  $(".abc").append("ѡ�<input type='textarea' name='refs'><br>");
	  });
	
	});
	</script>
	
	<style type="text/css">
	.abc {}
	</style>

</head>

<body>  
  
<%System.out.println(request.getParameter("examsave"));
System.out.println(request.getQueryString());
System.out.println(request.getRequestURL());


PrintWriter pw=response.getWriter();
if(request.getParameter("examsave").equals("1")){	
	pw.print("�½����Գɹ����������ӿ��⡣<br><br>");
}else{
	pw.print("�½�����<br>");
	pw.print("<form method='post' action='newexam.action'>");
	pw.print("�������ƣ�<input type='textarea' name='examname' cols=2  rows=4  wrap=soft><br>");
	pw.print("<input type='submit' value='�ύ'>");
	pw.print("</form>");
}
%>	

<%	if(request.getParameter("examsave").equals("2")){
	out.println("������ӳɹ�����������!<br><br>");
}
%>	
��ӿ���
	 <form method="post" action="newitem.action?profiling=true">
	���ԣ�  <select name="examid">
        <option value="3" selected>�м�ʳƷ���鹤</option> 
        <option value="2">ʳƷ��Ӽ�����</option> 
        <option value="1">���ɷ���</option> 
       
        </select><br>
	���⣺<input type="textarea" name="question" ><br> 
	 ���ͣ�<input type="radio" name="category" value=1> �ж�
		<input type="radio" name="category" value=2> ��ѡ 
		<input type="radio" name="category" value=3> ��ѡ<br> 
	<div class="abc">
	ѡ�<input type="textarea" name="refs" /><br>
	</div>
	�ο��𰸣�<input type="textarea" name="answers" ><br>
	<input type="button" id="btn1" value="׷��ѡ��" />	<input type="submit" value="�ύ">
	 
	</form>
	<br>

�鿴<br>	
	<form method="post" action="manageritemlist.action">
	���ԣ�  <select name="examid">
        <option value="3" selected>�м�ʳƷ���鹤</option> 
        <option value="2">ʳƷ��Ӽ�����</option> 
        <option value="1 ">���ɷ���</option> 
         <option value="0">������</option> 
        </select>   	
	 	<input type="submit" value="���п���">	 
	</form>
	 <br>
	 
ģ�⿼��<br>	
	<form method="post" action="intoexam.action">
	���ԣ�  <select name="examid">
        <option value="3" selected>�м�ʳƷ���鹤</option> 
        <option value="2">ʳƷ��Ӽ�����</option> 
        <option value="1 ">���ɷ���</option> 
         <option value="0">������</option> 
        </select>   	
	 	<input type="submit" value="��ʼ���ԣ�go">	 
	</form>
	 <br>

 �༭���� 
	 <form method="post" action="itemslist.action">	 
	 	���ͣ�<input type="text" name="username" ><br>	
	 	<input type="submit" value="����">
	 </form>
	 <form method="post" action="itemslist.action">
	���⣺<input type="password" name="password" ><br> 
	 ���ͣ�<input type="text" name="username" ><br>
	��ѡ�
	 �ο��𰸣�<input type="password" name="password1" ><br>
	 <input type="submit" value="ѡ��">
	 </form>
	
��������׼��֤<br>
	 <form method="post" action="newexam.action">
	 �������ƣ�<input type="text" name="username" ><br>	
	 �������֣�<input type="text" name="username" ><br>	
	 ����ʱ�䣺<input type="text" name="username" ><br>	
	 <input type="submit" value="����">
	</form>

</body>

  
</html>  