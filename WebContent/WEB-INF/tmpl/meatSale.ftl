
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<title>上海市浦东新区食品生产安全管理协会</title>
		<link rel="stylesheet" type="text/css" href="./css/reset.css" />
		<link rel="stylesheet" type="text/css" href="./css/layout.css" />
		<link rel="stylesheet" type="text/css" href="./css/flexslider.css" />
		<link rel="stylesheet" type="text/css" href="./css/sudo.css" />
		<link rel="stylesheet" type="text/css" href="./css/jqueryslidemenu.css" />
		<link rel="stylesheet" type="text/css" href="./style.css" />
		<link rel="stylesheet" type="text/css" href="./css/blog.css" />

		<link rel="stylesheet" type="text/css" href="./js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="./css/portfolio.css" />
		<link rel="stylesheet" type="text/css" href="./css/quicksand.css" />
		<link rel="stylesheet" type="text/css" href="./css/skin.css" />
		<link rel="stylesheet" type="text/css" href="./css/960_24_col.css" />


		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/jquery-migrate.js"></script>

		<script type="text/javascript" src="./js/jqueryslidemenu.js"></script>
		<script type="text/javascript" src="./js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="./js/custom.js"></script>
		<script type="text/javascript" src="./js/unslider.js"></script>
		<script type="text/javascript" src="./js/fancybox/jquery.fancybox-1.3.4.js"></script>

		<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>

		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="js/form.js"></script>

		<style type="text/css">
			html, body, p {
				margin: 0;
				padding: 0;
			}

			body {
				/*overflow: hidden;*/
			}
			.warp {
				filter: alpha(opacity=20);
			}

			.img24 {
				background: none;
				filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='./images/test.png"');
				width: 100px;
				height: 100px;
			}
				input.editor
				{
					border: 0px solid #ccc;
					height: 27px;
					border-bottom: 1px solid #ccc;
					margin-left:5px;
					margin-bottom:5px;
				}

		</style>
		<script type="text/javascript">
			$(document).ready(function() {
				$('.banner').unslider();

				$(".menu_item").hover(function() {
					$(this).addClass("selected");
					var myList = $(this).find(".menu_list");
					myList.show();

				}, function() {

					$(this).removeClass("selected");
					//alert(".menu_list .sub_"+$(this).attr("id"));
					var myList = $(this).find(".menu_list");
					myList.hide();

				});

				$(".small_menu_title").bind("click", function() {
					var newHeight = $(".small_menu_list").find(".clearfix").outerHeight();
					if ($(".small_menu_list").outerHeight() == 0) {
						$(".small_menu_list").animate({
							height : newHeight + "px"
						}, 1000, "easeOutExpo");
					} else {
						$(".small_menu_list").animate({
							height : 0 + "px"
						}, 1000, "easeOutExpo");
					}
				});

				$(".small_menu_item").hover(function() {
					$(this).addClass("small_menu_hover");
				}, function() {
					$(this).removeClass("small_menu_hover");
				});

				$(".list_item").hover(function() {

					$(this).find(".sub_menu").css("display", "block");
					$(this).animate({
						backgroundColor : "#FDFFD0"
					}, 500);
				}, function() {
					$(this).animate({
						backgroundColor : "#fff"
					}, 500);
					$(this).find(".sub_menu").hide();
				});

				$(".exam_options").each(checkOptions);
				$(".exam_options").change(function() {
					$(".exam_options").each(checkOptions);
				});
				$(".exam_submit").hover(function() {
					$(this).animate({
						backgroundColor : "#F5FAFF"
					}, 500);
				}, function() {
					$(this).animate({
						backgroundColor : "#fff"
					}, 500);
				});
				
				$(".click_down").click(function(){
					var toHeight = $(".exam_no_container").outerHeight();
					var beginHeight = $(".exam_no_outer").outerHeight();
					if(beginHeight == 0)
					{
						   $(".exam_no_outer").animate({
								height : toHeight + "px"
							}, 1000, "easeOutExpo",
							function()
							{
								$(".click_down").attr("src","images/up.png");
							});
					}
					else
					{
							$(".exam_no_outer").animate({
								height : 0 + "px"
							}, 1000, "easeOutExpo",
							function()
							{
								$(".click_down").attr("src","images/down.png");
							});
					}
				});
			});
			function checkOptions() {
				var op = $(this).find("input");
				var label = $(this).find("." + (op.attr("id")));

				if (op.attr("checked")) {
					label.addClass("exam_select");
				} else {
					if (label.hasClass("exam_select")) {
						label.removeClass("exam_select");
					}
				}
			}
		</script>
		
		<script>
			var form_data= eval(${jsonText3});			
		</script>
		<style>
			table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#fff;}
			th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px;}
			th{font-weight:bold;background:#ccc;}
			input.long
			{
				width:300px;
			}
			input.middle
			{
				width:150px;
			}
			input.short
			{
				width:100px;
			}
			input.exshort
			{
				width:50px;
			}
			
		</style>
	</head>
	<body>
		<div id="page_wrap">
			<!-- Header Start -->
			<div class="header">
		 		<#include "../commons/logo.ftl">
				<#include "../commons/menubar.ftl">
				<#include "../commons/smallmenu.ftl">
			</div>
			<!-- Header End -->

			<div class="clear"></div>

			<!-- Flex Slider Start -->
			<div class="sub_header  exam_bg">

				<div class="sub_header_title">
					<h2>企业信息</h2>
					<div class="sub_header_description">
						<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
						<span><a href="./page.action?categoryid=1">用户中心 &raquo;</a></span>
						<span class="page">企业信息</span>
					</div>

				</div>
				<div class="exam_type">
					
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px" bordercolor="#666666">
			
				<form action="saveDoc.action" method="post" >

				<h2 align="center">上海市浦东新区食品生产安全管理协会</h2>
				<h2 align="center">肉制品原料肉进货表汇总</h2>
				
				<p class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<!--<p class="STYLE1">填写请参见背面样张，填写完整后请于20130130	前寄往背面地址表或电子版邮箱至pdspsax@163.com(未来电可索要电子版)</p>-->
				
				<table width="1033" border="0" cellpadding="0" cellspacing="1" bgcolor="#666666">
				  <tr>
				    <td colspan="3" bgcolor="#FFFFFF"><p>进货日期<br /></p></td>
				    <td colspan="5" bgcolor="#FFFFFF">
				    	<div class="display" form_data="mi_importDate" ></div>			    
					    <input class="editor long" form_data="mi_importDate" name="mi_importDate" type="text"></input>
				    </td>
				    
				  </tr>
				  
				  <tr>
				  	<td colspan="3" bgcolor="#FFFFFF">原料肉名称</td>
				    <td colspan="5" bgcolor="#FFFFFF">
					    <div class="display" form_data="mi_meatName" ></div>			    
						<input class="editor short" form_data="mi_meatName" name="mi_meatName" type="text"></input>
				    </td>
				  </tr>
				  
				  <tr>				  
				    <td colspan="3" bgcolor="#FFFFFF" width="50px" >原料肉品种</td>
				    <td colspan="5" bgcolor="#FFFFFF">			
				    	<div class="display" form_data="mi_meatTypeName" ></div>
						<input form_data="mi_meatTypeName" name="mi_meatTypeName" type="text"></input>
				    </td>
				  </tr>
				  
				  <tr>
				    <td colspan="3" bgcolor="#FFFFFF"><p>产地</p></td>
				    <td colspan="5" bgcolor="#FFFFFF">
					    <div class="display" form_data="mi_producerLocate" ></div>			    
						<input class="editor long" form_data="mi_producerLocate" name="mi_producerLocate" type="text"></input>
				    </td>				    
				  </tr>
				  
				  <tr>
				  	<td colspan="3" bgcolor="#FFFFFF">进货情况</td>
				    <td colspan="5" bgcolor="#FFFFFF">
					    <div class="display" form_data="mi_srcTypeName" ></div>
					    <p>	
					    <select>
					    	<option form_data="mi_srcTypeName" name="mi_srcTypeName">直接进口</option>
					    	<option form_data="mi_srcTypeName" name="mi_srcTypeName">贸易商进口</option>
					    	<option form_data="mi_srcTypeName" name="mi_srcTypeName">国内厂家进货</option>
					    	<option form_data="mi_srcTypeName" name="mi_srcTypeName">国内中间商进货</option>
					    </select>
					    </p>
					    <p>进口肉类卫生证书编号：</p>
					    <p>厂家营业执照注册号：</p>
					    <p>厂家定点屠宰证代号：</p>
					    <p>厂家动物防疫条件合格证代码编号：</p>
					    <p>厂家动物检疫合格证明（产品A或产品B）：</p>
					    <p>流通许可证编号：</p>										    
				    </td>
				  </tr>
				  
				  <tr>
				    <td colspan="3" bgcolor="#FFFFFF">进货数量kg</td>
				    <td colspan="5" bgcolor="#FFFFFF">
				        <div class="display" form_data="mi_corpContactAddress" ></div>			    
						<input class="editor long" form_data="mi_importCount" name="mi_importCount" type="text"></input>
				    </td>
				    
				  </tr>
				
				</table>
				<div style="width: 300px;float: left;margin-top:5px;">填表日期：
					<div class="display" form_data="mi_formMadeDate" ></div>
					<input class="editor" form_data="mi_formMadeDate" name="mi_formMadeDate" type="text" ></input>
				</div>
				<div style="width: 300px;float: left;margin-top:5px;">
				填表人：
					<div class="display" form_data="mi_formMaker" ></div>
					<input class="editor" form_data="mi_formMaker" name="mi_formMaker" type="text"></input>
				</div>
					<div style="float:right">
					<input type="hidden" value="${docid}" name="docid" />
					
 					<input type="submit" value="新增" style="margin-top: 30px;margin-right: 9px;padding: 5px 15px;" /> 					
 					</div>
				</form>
				
				
			</div>
			<!-- Teaser End -->

			<!-- Content Start -->

			<!-- Content End -->

			<!-- Bottom Section Start -->
			<#include "../commons/footertest.ftl">
			<!-- Bottom Section End -->

		</div>
	</body>

</html>
