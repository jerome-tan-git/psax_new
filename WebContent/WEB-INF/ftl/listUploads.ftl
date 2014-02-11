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
		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script>
		var selectIndex = -1;
		CKEDITOR.disableAutoInline = true;
		CKEDITOR.editorConfig = function( config ) {
			// Define changes to default configuration here. For example:
			// config.language = 'fr';
			// config.uiColor = '#AADC6E';
			config.toolbar =
			[
			    ['Bold','Italic','Underline','Strike','-','EqnEditor'],
			    ['TextColor','BGColor'],
			 
			];

		    config.height = 80;
		};
		$( document ).ready( function() {
			$( '#editor' ).ckeditor(); // Use CKEDITOR.replace() if element is <textarea>.
			//$( '#editor1' ).ckeditor(); // Use CKEDITOR.replace() if element is <textarea>.
			
		} );
		</script>
	</head>
	<body>
	
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			        <h4 class="modal-title" id="myModalLabel">新建文件夹</h4>
			      </div>
			      <form name="folderform" method="post" action="addfolder.action">
				      <div class="modal-body clearfix">
				      	<div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label" style="padding-top: 10px;">文件夹名</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control"placeholder="文件夹名" id="folderNameInput" name="uploadfolder"/>
						    </div>
					  	</div>	
				        <div>&nbsp;</div>				      
			      	  </div>
				      
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-default" data-dismiss="modal">关闭</button>
				        <button type="submit" class="btn btn-primary">保存</button>
				      </div>
			      </form>
			      
			       
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->

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
				
				<div class="col-md-4">
					

				<div class="page-header" style="margin-top: 20px;margin-left: 20px;border-bottom: 1px solid #D6D6D6;position: relative; height: 35px;">
				<div style="width:200px;float: left;font-size: 12pt;">
				  <span>企业下载文件夹管理</span>
				  </div>
				  <!--
				  <div style="float: right;position: absolute;right: 20px;top: 10px;font-size: 13pt;color: #333;">
				  	<a href="./managerArt.action"><span class="glyphicon glyphicon-plus"></span></a> 新建
				  </div>
				  -->
				  <div style="padding-right: 28px;margin-bottom: 23px;">
						<span class="glyphicon glyphicon-plus pull-right hand deleteExam" onclick="showUserDialog('','','')">新建文件夹</span>
				  </div>
				</div>
					<div class="col-md-12">
					<#if upfflist?exists>
						<#list upfflist as upff>
						<#if upff?exists>
						<div class="panel panel-default exam_container">
						  <div class="panel-heading clearfix">
						  		<div class="exam_title_list pull-left ">${upff.createtime}&nbsp;&nbsp;&nbsp;${upff.foldername}</div>
						  		<a href="deletefolder.action?folderid=${upff.id}"><span class="glyphicon glyphicon-remove pull-right hand deleteExam" ></span></a>
						  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>						  		
						  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>							  	
							</div>
						</div>
						</#if>
						</#list>
																	
					</#if>
														
						<ul class="pager">
						  
						</ul>
					</div>
					


				
				
				</div><!-- div col = 8-->
				
				<div class="col-md-4">
				<div class="page-header" style="margin-top: 20px;margin-left: 20px;border-bottom: 1px solid #D6D6D6;position: relative;">
				  <h3><small>企业用户文件 </small></h3>				 
				  
				</div>
				<form method="post" action="listUploads.action" class="form-horizontal" role="form">
					<div class="col-md-12">				
					<div class="form-group">
					    <label for="inputEmail3" class="col-sm-3 control-label">用户</label>
					    <div class="col-sm-9">
					      <#if allusers?exists>				    	
					    
							<select name="userid" class="form-control">
							<#list allusers as s_user>
								<#if s_user?exists && (s_user.id>0)>
									<#if user?exists && (user.id>0) && (user.id==s_user.id)>
										<option class="e_c" value="${s_user.id}" selected>${s_user.username}</option>
									<#else>
										<option class="e_c" value="${s_user.id}">${s_user.username}</option>
									</#if>							  		
							  	</#if>
							</#list>
							</select>
						
						</#if>	
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputPassword3" class="col-sm-3 control-label">文件夹</label>
					    <div class="col-sm-9">
					      <#if upfflist?exists>											    	
						   			
							<select name="uploadfolderId" class="form-control">
							<#list upfflist as upff>	
								<#if upff?exists>
									<#if folder?exists && (folder.id==upff.id)>
									<option class="e_c" value="${upff.id}" selected>${upff.foldername}</option>
									<#else>
								  	<option class="e_c" value="${upff.id}">${upff.foldername}</option>
								  	</#if>
								</#if>
							</#list>
							</select>							
							
							</#if>	
					    </div>
					    

					  </div>
					  <div class="form-group">
						    <div class="col-sm-10" style="float:right;padding-right: 15px;">
						      <button type="submit" class="btn btn-default"  style="float:right">确认</button>
						    </div>
					 </div>
				</div>
				</form>
				<div class="col-md-12">
					<#if upflist?exists>
					<div class="page-header" style="margin-top: -15px;border-bottom: 1px solid #D6D6D6;position: relative;font-size: 12pt;">
					  <span>共有${upflist?size}个文件</span>				 
					  
					</div>
						<#list upflist as upf>
						<#if upf?exists>
						<div class="panel panel-default exam_container">
						  <div class="panel-heading clearfix">
						  		<div class="exam_title_list pull-left ">${upf.uploadtime}&nbsp;&nbsp;&nbsp;${upf.fname}</div>						  		
						  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>						  		
						  		<span class="pull-right">&nbsp;&nbsp;&nbsp;</span>							  	
							</div>
						</div>
						</#if>
						</#list>																	
					</#if>														
					<ul class="pager">						  
					</ul>
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
