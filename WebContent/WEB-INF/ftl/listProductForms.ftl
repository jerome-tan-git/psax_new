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
			
				<div class="col-md-6">
				<div class="page-header" style="margin-top: 20px;margin-left: 20px;border-bottom: 1px solid #D6D6D6;position: relative;">
				  <h3><small>企业食品生产管理文件 </small></h3>				 
				  
				</div>
				<form method="post" action="listChosenProductForms.action" class="form-horizontal" role="form">
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
					    <label for="inputPassword3" class="col-sm-3 control-label">报表</label>
					    <div class="col-sm-9">					       			
							<select name="formid" class="form-control">		
								<#if formid?exists && (formid==15)>					
									<option class="e_c" value="15" selected>肉制品原料肉进货表</option>
								<#else>
									<option class="e_c" value="15">肉制品原料肉进货表</option>
								</#if>							
								<#if formid?exists && (formid==16)>	
								  	<option class="e_c" value="16" selected>肉制品原料肉销售表</option>
								<#else>
								  	<option class="e_c" value="16" >肉制品原料肉销售表</option>
								</#if>			
							</select>
					    </div>
					  </div>
					  <div class="form-group">
						    <div class="col-sm-10" style="float:right;padding-right: 15px;">
						      <button type="submit" class="btn btn-default"  style="float:right">确认</button>
						    </div>
					 </div>
				</div>
				</form>
	
				
				<div class="col-md-16">
					<#if docslist?exists>
					<div class="page-header" style="margin-top: -15px;border-bottom: 1px solid #D6D6D6;position: relative;font-size: 12pt;">
					  <span>共有${docslist?size}个记录</span>
					</div>
				
					<#if (formid==15)>
					<div>
						<table width="800" border="1" cellpadding="0" cellspacing="1" bgcolor="#666666">
						  <tr font-size=15px align="center" >
						    <td width="10%" ><p><b>进货日期</b><br /></p></td>
						    <td width="15%" ><p><b>原料肉名称</b><br /></p></td>
						    <td width="10%" ><p><b>原料肉品种</b><br /></p></td>
						    <td width="5%" ><p><b>产地</b><br /></p></td>
						    <td width="50%" ><p><b>进货情况</b><br /></p></td>
						    <td width="10%" ><p><b>进货量(kg)</b><br /></p></td>	
						  </tr>
						 
						
						  	<#if fieldslist?exists>
						  		<#list docslist as doc>
						  			<#assign fieldvalues=doc.fvlist>
								  <tr>
								  	<td width="10%" >
									  <#list fieldvalues as f>
									  		<#if (f.fieldid==941)>
									  		<p>${f.value}<br /></p>
									  		</#if>
									  </#list>
									  </td>
									  <td width="15%">
									  <#list fieldvalues as f>
									  		<#if (f.fieldid==942)>
									    	<p>${f.value}<br /></p>
									    	</#if>
									  </#list>
									  </td>
									  <td width="10%" >
									  <#list fieldvalues as f>
									  		<#if (f.fieldid==943)>
									    	<p>${f.value}<br /></p>
									    	</#if>
									  </#list>
									  </td>
									  <td width="5%">
									  <#list fieldvalues as f>
									  		<#if (f.fieldid==951)>
									    	<p>${f.value}<br /></p>
									    	</#if>
									  </#list>
								  	 </td>
								  	
								  	<td width="50%">
								  	<#list fieldvalues as f>  		
								  		<#if (f.fieldid==944)>
								  			<#if (f.value?number==1)>	
										    <p>直接进口<br /></p>
										    </#if>
										    <#if (f.value?number==2)>
										    <p>贸易商进口<br /></p>
										    </#if>
										    <#if (f.value?number==3)>
										    <p>国内厂家进货<br /></p>
										    </#if>
										    <#if (f.value?number==4)>
										    <p>国内中间商进货<br /></p>
										    </#if>		  
										    
										    <#if (f.value?number<3)>
										    <p>进口肉类卫生证书编号：
										    <#list fieldvalues as fv>
										    	<#if (fv.fieldid==945)>
										    		${fv.value}
										    	</#if>
										 	</#list>  
										    </p>
										    </#if>
										    
										    <#if (f.value?number>2)>
										    <p>厂家营业执照注册号：
										    	<#list fieldvalues as fv>
										    		<#if (fv.fieldid==946)>
										    		${fv.value}
										    		</#if>
										 		</#list>  
										    </p>
										    <p>厂家定点屠宰证代号：
										    	<#list fieldvalues as fv>
										    		<#if (fv.fieldid==947)>
										    		${fv.value}
										    		</#if>
										 		</#list>  
										    </p>
										    <p>厂家动物防疫条件合格证代码编号：
										    	<#list fieldvalues as fv>
										    		<#if (fv.fieldid==948)>
										    		${fv.value}
										    		</#if>
										 		</#list>  
										    </p>
										    <p>厂家动物检疫合格证明（产品A或产品B）：
										    	<#list fieldvalues as fv>
										    		<#if (fv.fieldid==949)>
										    		${fv.value}
										    		</#if>
										 		</#list>  
										    </p>
										    </#if>
										    <#if (f.value?number==4)>								    
										    <p>流通许可证编号：
										    	<#list fieldvalues as fv>
										    		<#if (fv.fieldid==950)>
										    		${fv.value}
										    		</#if>
										 		</#list>   	
										    </p>
										    </#if>								  
								    	</#if>										    	
								  	</#list>						  	
								    </td>				
						    	
						    		<td width="10%">
								  	  <#list fieldvalues as f>
								  		    <#if (f.fieldid==952)>
								    		<p>${f.value}<br /></p>
								    		</#if>						    	
								  	  </#list>
								  	  </td>
								  </tr>
						  		</#list>
						  	</#if>				 
						</table>
					<#elseif (formid==16)>
					
						<table width="600" border="1" cellpadding="0" cellspacing="1" bgcolor="#666666">
							<tr font-size=15px font-weight=bold align="center">
							    <td width="20%"><p>出货日期<br /></p></td>
							    <td width="20%"><p>产品名称<br /></p></td>
							    <td width="10%"><p>类别<br /></p></td>
							    <td width="30%"><p>产品批号<br /></p></td>
							    <td width="10%"><p>销售去向<br /></p></td>
							    <td width="10%"><p>销售量<br /></p></td>
							</tr>
							  
							<#if fieldslist?exists>
						  		<#list docslist as doc>
						  			<#assign fieldvalues=doc.fvlist>
							<tr >
								<td width="20%">
									<#list fieldvalues as f>
										<#if (f.fieldid==961)>
									 		<p>${f.value}<br /></p>
									  	</#if>
									 </#list>
								</td>
								<td width="20%">
									<#list fieldvalues as f>
										<#if (f.fieldid==962)>
									 		<p>${f.value}<br /></p>
									  	</#if>
									 </#list>
								</td>
								<td width="10%">
									<#list fieldvalues as f>
										<#if (f.fieldid==963)>
									 		<p>${f.value}<br /></p>
									  	</#if>
									 </#list>
								</td>
								<td width="30%">
									<#list fieldvalues as f>
										<#if (f.fieldid==964)>
									 		<p>${f.value}<br /></p>
									  	</#if>
									 </#list>
								</td>
								<td width="10%">
									<#list fieldvalues as f>
										<#if (f.fieldid==965)>
									 		<p>${f.value}<br /></p>
									  	</#if>
									 </#list>
								</td>
								<td width="10%">
									<#list fieldvalues as f>
										<#if (f.fieldid==966)>
									 		<p>${f.value}<br /></p>
									  	</#if>
									 </#list>
								</td>
							</tr>
							
								</#list>
						  	</#if>
						
						</table>
					
					</#if>												
				</#if>					
					
					<div class="form-group">
						<form action="meatReport.action" method="post" > 	
							<#if (formid?exists)>
							<input type="hidden" value="${formid}" name="formid" />
							<#elseif (f.id?exists)>
							<input type="hidden" value="${f.id}" name="formid" />
							</#if>
							<#if (userid?exists)>
							<input type="hidden" value="${userid}" name="userid" />
							<#elseif (user?exists)>
							<input type="hidden" value="${user.id}" name="userid" />
							</#if>	
							<#if docslist?exists>							
								<#if (docslist?size>0)>
									<br />					
									<div class="col-sm-10" style="float:right;padding-right: 15px;">
									<!--<input type="submit" value="下载" name="snext" style="margin-top: 30px;margin-right: 9px;padding: 5px 15px;" />-->
									<!--<input type="submit" class="btn btn-default"  style="float:right" value="下载"/>-->
										<button type="submit" class="btn btn-default"  style="float:right">下载</button>
									</div>
								</#if>		
		 					</#if>
	 					</form> 					 					
	 				</div>
	 				 
				</div>									
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
