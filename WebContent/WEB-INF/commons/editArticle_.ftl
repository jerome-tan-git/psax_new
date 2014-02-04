<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Innovate Responsive HTML Website Template</title>
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="./css/backend.css" />
		<link rel="stylesheet" type="text/css" href="./css/docs.css" />
		<link rel="stylesheet" type="text/css" href="./css/datepicker.css" />
		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/bootstrap.min.js"></script>
		<script src="./js/ckeditor/ckeditor.js"></script>
		<script src="./js/ckeditor/adapters/jquery.js"></script>
		<script src="./js/backend.js"></script>
		<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
		<script>
			var selectIndex = -1;
			CKEDITOR.disableAutoInline = true;
			CKEDITOR.editorConfig = function(config) {
				// Define changes to default configuration here. For example:
				// config.language = 'fr';
				// config.uiColor = '#AADC6E';

				config.height = 500;
			};
			$(document).ready(function() {
				$('#editor').ckeditor();
				// Use CKEDITOR.replace() if element is <textarea>.
				//$( '#editor1' ).ckeditor(); // Use CKEDITOR.replace() if element is <textarea>.
				$('.date').datepicker()
				  .on('changeDate', function(ev){
					$('.date').datepicker('hide');
				  });

			});
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
				<div class="col-md-3">
					<div class="bs-sidebar hidden-print affix" style="margin-top: 20px;">
						<ul class="nav bs-sidenav">

							<li>
								<a href="./AddExam.html">Exam</a>

							</li>
							<li class="active">
								<a href="#transitions">Article</a>
							</li>
							<li>
								<a href="#modals">workflow</a>

							</li>
							<li>
								<a href="#dropdowns">Dropdown</a>

							</li>
							<li>
								<a href="#scrollspy">Scrollspy</a>

							</li>
							<li>
								<a href="#tabs">Tab</a>

							</li>
							<li>
								<a href="#tooltips">Tooltip</a>

							</li>
							<li>
								<a href="#popovers">Popover</a>

							</li>
							<li>
								<a href="#alerts">Alert</a>
								<ul class="nav">
									<li>
										<a href="#alerts-examples">Examples</a>
									</li>
									<li>
										<a href="#alerts-usage">Usage</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>

				</div>
				<div class="col-md-8">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">Article</h3>
						</div>
						<div class="panel-body">
							<form class="form-horizontal" role="form" action="addArt.action" method="post" enctype="multipart/form-data">
								<div class="col-sm-12">
									    <label for="exampleInputEmail1">����</label>
									    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="����" name="title">
								 </div>
								 <div class="col-sm-12">&nbsp;</div>
								 <div class="col-sm-12">
									    <label for="exampleInputEmail1">ժҪ</label>
									    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="ժҪ" name="absinfo">
								 </div>
								 <div class="col-sm-12">&nbsp;</div>
								 
								<div class="col-sm-6">
									    <label for="exampleInputEmail1">���</label>
									    <#if categories?exists>
									    <select class="form-control" name="categoryid">
									    <#list categories as category>
										  <option value="${category.id}">${category.category}</option>										  
										</#list>										
										</select>
										</#if>
										
								 </div>
								<div class="col-sm-6">
									    <label for="exampleInputEmail1">ͼƬ</label>
									   <input type="file" name="pic"/>
								 </div>
								 <div class="col-sm-12">&nbsp;</div>
								 <div class="col-sm-12">
									    <label for="exampleInputEmail1">����</label>
									   <input type="file" name="addition"/>
								 </div>
								 <div class="col-sm-12">&nbsp;</div>
								<label  class="col-sm-12">����</label>
								<div class="col-sm-12">
									
									<textarea id="editor" name="article"> </textarea>
								</div>
								<div class="col-sm-12">&nbsp;</div>
								<div class="col-sm-12">
								<!--
									<button type="button" class="btn btn-primary pull-right">
									  <span class="glyphicon glyphicon-floppy-disk"></span> ����
									</button>
									-->
									<input type="submit" value="����" />
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
