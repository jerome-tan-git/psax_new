<%@ page language="java" import="java.util.*, java.io.*" 
	
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %> 
<%

%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>考试系统管理</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("#btn1").click(function(){
	  var c = $(".aaa").size();
	  if($("#btn3").attr('checked')!=undefined)
		  {
		  $("#test3").append("选项：<input type='textarea' name='refs' class='aaa'/>  正确？<input type='radio' name='refistrues' value='"+c+"'><br>");
		  }
	  else if($("#btn4").attr('checked')!=undefined)
	  {
	  $("#test3").append("选项：<input type='textarea' name='refs' class='aaa'/>  正确？<input type='checkbox' name='refistrues' value='"+c+"'><br>");
	  }
	  //$(".abc").append("选项：<input type='textarea' name='refs'/>  正确？<input type='radio' name='istrue' value='1'><br>");
	  
	  
  });
  $("#btn2").click(function(){
	    $("#test2").html("选项：<input type='radio' name='refs' value='1'>是</input><br/>选项：<input type='radio' name='refs' value='0'/>否</input><br>");
	    $("#test4").html("<div id='test4'></div>");  
  });
  $("#btn3").click(function(){
	  var c = $(".aaa").size();
	    $("#test2").html("<div id='test3'>选项：<input type='textarea' name='refs' class='aaa' />  正确？<input type='radio' name='refistrues' value='"+c+"'/><br></div>");
	    //$("#test4").html("<div id='test4'><input type='button' id='btn1' value='追加选项' /><br/></div>");
	    
  });
  $("#btn4").click(function(){
	  	var c = $(".aaa").size();
	    $("#test2").html("<div id='test3'>选项：<input type='textarea' name='refs' class='aaa' />  正确？<input type='checkbox' name='refistrues' value='"+c+"'/><br></div>");
	    //$("#test4").html("<div id='test4'><input type='button' id='btn1' value='追加选项' /><br/></div>");
  });
});


</script>
<!-- 
<style type="text/css">
.abc {}
</style>
 -->
</head>
<body>


<%System.out.println(request.getParameter("examsave"));
System.out.println(request.getQueryString());
System.out.println(request.getRequestURL());
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"
//	+request.getServerPort()+path+"/";

PrintWriter pw=response.getWriter();
if(request.getParameter("examsave").equals("1")){	
	pw.print("新建考试成功，请继续添加考题。<br><br>");
	
}else{
	pw.print("新建考试<br>");
	pw.print("<form method='post' action='newexam.action'>");
	pw.print("考试名称：<input type='textarea' name='examname' cols=2  rows=4  wrap=soft><br>");
	pw.print("<input type='submit' value='提交'>");
	pw.print("</form>");
}
%>	

<%	if(request.getParameter("examsave").equals("2")){
	out.println("考题添加成功，请继续添加!<br><br>");
}
%>	
添加考题
	<!--  <form method="post" action="newitem.action?profiling=true"> -->
	 <form method="post" action="newitem.action">
	考试：  <select name="examid">
       
        <s:iterator value="#session.exams" id="e">
         	<option value='<s:property value="id"/>' <s:if test="%{id==3}">selected</s:if>><s:property value="name"/></option> 			
		</s:iterator>
       
        </select><br>
	问题：<input type="textarea" name="question" ><br> 
	 类型：<input type="radio" name="category" value=1 id="btn2"> 判断
		<input type="radio" name="category" value=2 id="btn3"> 单选 
		<input type="radio" name="category" value=3 id="btn4" > 多选<br> 
		<div id="test2"></div>
	<!-- 
	<div class="abc">
	选项：<input type="textarea" name="refs" /><br>
	</div>
	参考答案：<input type="textarea" name="answers" ><br>
	<input type="button" id="btn1" value="追加选项" /><br/>
	 -->
	<div id="test4">
		<input type="button" id="btn1" value="追加选项" /><br/>
		<!-- <input type="button" id="btn5" value="追加选项" /><br/> -->
	</div>
	<input type="submit" value="提交" /><br/>
	 
	</form>
	<br>

查看<br>	
	<form method="post" action="manageritemlist.action">
	考试：<select name="examid">
        <s:iterator value="#session.exams" id="e">
         	<option value='<s:property value="id"/>' <s:if test="%{id==3}">selected</s:if>><s:property value="name"/></option> 			
		</s:iterator>       
        </select>   	
	 	<input type="submit" value="所有考题">	 
	</form>
	 <br>
	 
模拟考试<br>	
	<!-- <form method="post" action="intoexam.action"> -->
	<form method="post" action="beginexam.action">
	
	考试： <select name="examid">
        <s:iterator value="#session.exams" id="e">
         	<option value='<s:property value="id"/>' <s:if test="%{id==3}">selected</s:if>><s:property value="name"/></option> 			
		</s:iterator>       
        </select>   	
	 	<input type="submit" value="开始考试，go">	 
	</form>
	 <br>

 编辑考题 
	 <form method="post" action="itemslist.action">	 
	 	题型：<input type="text" name="username" ><br>	
	 	<input type="submit" value="搜索">
	 </form>
	 <form method="post" action="itemslist.action">
	问题：<input type="password" name="password" ><br> 
	 类型：<input type="text" name="username" ><br>
	答案选项：
	 参考答案：<input type="password" name="password1" ><br>
	 <input type="submit" value="选中">
	 </form>
	

	<s:iterator value="#session.exams" id="e">
		EXAM---<s:property value="name"/><br/>
		<s:if test="%{id==3}">选中<br/></s:if>
	</s:iterator>
	 

</body>
</html>