<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Innovate Responsive HTML Website Template</title>
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="./css/backend.css" />
		<link rel="stylesheet" type="text/css" href="./css/docs.css" />
		
		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/bootstrap.min.js"></script>
		<script src="./js/ckeditor/ckeditor.js"></script>
		<script src="./js/ckeditor/adapters/jquery.js"></script>
		<script src="./js/backend.js"></script>
		
		<script>
		var selectIndex = -1;
		CKEDITOR.disableAutoInline = true;
		
		$( document ).ready( function() {
			$( '#editor' ).ckeditor(
			{
					toolbar:[
					    ['Bold','Italic','Underline','Strike','-','EqnEditor'],
					    ['TextColor','BGColor'],
					],
					height:"80px"
				}
				); 
			
		} );
		
		
		//var question = {
			//	"type":"single_selection",
			//	"category":"option1",
			//	"question":"NaOH溶液滴定HCl的化学计量点pH值",
			//	"question_value":"NaOH溶液滴定HCl的化学计量点pH值为( 　)。",
			//	"options": [
		    //  {"text": "6.80", "value":"65", "right_answer": true},
		    //  {"text": "7.00", "value":"66", "right_answer": false},
		    //  {"text": "7.20", "value":"67", "right_answer": false},
		    //  {"text": "7.40", "value":"68", "right_answer": false}
		        
		 //   ]
		//};
		var question = eval(${jsonText1});
		
		</script>
	</head>
	<body>

		<div class="navbar-fixed-top" role="banner" style="background-color: #004A8F; width: 100%;">
			<div class="container">
				<div class="navbar-header">
					<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a href="#" class="navbar-brand" style="color:#fff">&nbsp;</a>
				</div>

			</div>
		</div>
		<div style="margin-top: 50px">
			<div class="row" style="width: 100%;">
				<#include "../commons/marginmenu.ftl">
				<div class="col-md-8">
					<div class="panel panel-info">
					  <div class="panel-heading">
					    <h3 class="panel-title">Exam</h3>
					  </div>
					  <div class="panel-body">
					    <!--<form class="form-horizontal" role="form" method="post" action="newitem1.action">-->
					    <form class="form-horizontal" method="post" id="examform" action="newitem1.action">
					    	<textarea id="editor"> </textarea>
					    	 <div class="form-group">
						    <div class="bt_group">
						      <div class="btn-group pull-right">
								  <button type="button" class="btn btn-primary" id="changeTitlebt" onclick="read_editor_title()">Modify exam</button>
								  <button type="button" class="btn btn-primary" id="changeOptionbt" onclick="update_data()">Modify options</button>
								  <button type="button" class="btn btn-primary" id="addOptionbt" onclick="read_editor()">Add option</button>
								</div>
							</div>						    
						  </div>
						  <div class="form-group">
						  	<label  class="col-sm-12">类型</label>
						    	<div class="col-sm-12">
								
								<label class="radio-inline">
								<#if item?exists && item.category==2>
									<input type="radio" id="inlineCheckbox1" class="exam_type" value="2" name="category" checked> 单选题
								<#else>  
									<input type="radio" id="inlineCheckbox1" class="exam_type" value="2" name="category"> 单选题
								</#if>
								</label>
								
								<label class="radio-inline">
								<#if item?exists && item.category==3>
									<input type="radio" id="inlineCheckbox2" class="exam_type" value="3" name="category" checked> 复选题
								<#else>  
									<input type="radio" id="inlineCheckbox2" class="exam_type" value="3" name="category"> 复选题
								</#if>
								</label>
								
								<label class="radio-inline">
								<#if item?exists && item.category==1>
									<input type="radio" id="inlineCheckbox3" class="exam_type" value="1" name="category" checked> 是非题
								<#else>  
								 	<input type="radio" id="inlineCheckbox3" class="exam_type" value="1" name="category"> 是非题
								</#if>
							
								</div>
							</label>
						  </div>
						  <div class="form-group">
						  	<div><label class="col-sm-12">考试</label></div>
						  	<#if Session.exams?exists>						    	
						    <div class="col-sm-8">
								<select name="examid" class="form-control">
								<#list Session.exams as exam>
									<#if item?exists && item.examid=exam.id>
								  		<option class="e_c" value="${exam.id}" selected>${exam.name}</option>
								  	<#else>
								  		<option class="e_c" value="${exam.id}">${exam.name}</option>
								  	</#if>								 
								</#list>
								</select>
							</div>
							</#if>
							<!--
						  	<label  class="col-sm-12">考试</label>
						    <div class="col-sm-12">
						    <#if Session.exams?exists>
						    	<#list Session.exams as exam>
								<label class="radio-inline">
								  <input type="radio" id="inlineCheckbox_${exam.id}" value="${exam.id}" name="examid">${exam.name}
								</label>								
							    </#list>
							</#if>
							</div>
							-->
						  </div>
						  <div class="form-group exam_title_input">
						  	
						    <label  class="col-sm-12 ">题目</label>
						    <div class="col-sm-12">
						      <div class="panel panel-default">
								  <div class="panel-body">
								   请输入题目。。。
								  </div>
								</div>
						    </div>
						  </div>
						  <div class="form-group all_options">
						  	
						    <label  class="col-sm-12">选项</label>


						  </div>


						  
						  <div class="form-group">
						    <div class="col-sm-offset-2 col-sm-10">
						    	<!--
						    	<button type="button" class="btn btn-primary pull-right">
									  <span class="glyphicon glyphicon-floppy-disk"></span> 保存
								</button>
								-->
								<input type="submit" value="保存" />
						    </div>
						  </div>
						</form>
					  </div>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">Modal title</h4>
					</div>
					<div class="modal-body">
						...
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							Close
						</button>
						<button type="button" class="btn btn-primary">
							Save changes
						</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
	</body>

</html>
