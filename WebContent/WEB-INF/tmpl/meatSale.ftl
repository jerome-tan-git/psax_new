
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
				<link rel="stylesheet" type="text/css" href="./css/jquery-ui-1.10.3.custom.css" />
		<link rel="stylesheet" type="text/css" href="./css/select2.css" />
		

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
		<script src="./js/select2.js"></script>
		<script src="./js/select2_locale_zh-CN.js"></script>
		
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
				$("input.clearinput").bind("click",function()
				{
					$(".needClear").each(
						function()
						{
							$(this).val("");
						}
					);
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
					<h2>食品生产管理</h2>
					<div class="sub_header_description">
						<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
						<span><a href="./page.action?categoryid=1">用户中心 &raquo;</a></span>
						<span class="page"><a href="./uploadfoldersmanager.action">食品生产管理</a></span>
					</div>

				</div>
				<div class="exam_type">
					
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px" bordercolor="#666666">
			
				<form action="saveMeatSaleDoc.action" method="post" >

				<h2 align="center">上海市浦东新区食品生产安全管理协会</h2>
				<h2 align="center">肉制品销售（出货）情况表</h2>
				
				<p class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<!--<p class="STYLE1">填写请参见背面样张，填写完整后请于20130130	前寄往背面地址表或电子版邮箱至pdspsax@163.com(未来电可索要电子版)</p>-->
				
				<table width="1033" border="0" cellpadding="0" cellspacing="1" bgcolor="#666666">
				  <tr font-size=15px font-weight=bolder align="center">
				    <td colspan="1" bgcolor="#FFFFFF"><p>出货日期<br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p>产品名称<br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p>类别<br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p>产品批号<br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p>销售去向<br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p>销售量<br /></p></td>
				  </tr>
				  
				  <tbody id='data_2_container'>
					<tr form_data="_loop" target_data = "data_2">
						<td><div colspan="1" bgcolor="#FFFFFF" class="display" form_data="ms_date">&nbsp;</div>
						<input class="editor" form_data="ms_date" disabled style="background-color: transparent;border-bottom: 0px" />
						</td>
						<td><div colspan="1" bgcolor="#FFFFFF" class="display" form_data="ms_productName">&nbsp;</div>
						<input class="editor" form_data="ms_productName" disabled style="background-color: transparent;border-bottom: 0px" />
						</td>
						<td><div colspan="1" bgcolor="#FFFFFF" class="display" form_data="ms_category">&nbsp;</div>
						<input class="editor" form_data="ms_category" disabled style="background-color: transparent;border-bottom: 0px" />
						</td>
						<td><div colspan="1" bgcolor="#FFFFFF" class="display" form_data="ms_productBatchId">&nbsp;</div>
						<input class="editor" form_data="ms_productBatchId" disabled style="background-color: transparent;border-bottom: 0px" />
						</td>
						<td><div colspan="1" bgcolor="#FFFFFF" class="display" form_data="ms_buyer">&nbsp;</div>
						<input class="editor" form_data="ms_buyer" disabled style="background-color: transparent;border-bottom: 0px" />
						</td>
						<td><div colspan="1" bgcolor="#FFFFFF" class="display" form_data="ms_saleAmount">&nbsp;</div>
						<input class="editor" form_data="ms_saleAmount" disabled style="background-color: transparent;border-bottom: 0px" />
						</td>
					</tr>
				  </tbody>
				  
				  <tr>
				  	<td colspan="1" bgcolor="#FFFFFF"><p><input class="editor short needClear" form_data="ms_date" name="ms_date" type="text" fieldtype="date" readonly></input><br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p><input class="editor short needClear" form_data="ms_productName" name="ms_productName" type="text"></input><br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p><input class="editor short needClear" form_data="ms_category" name="ms_category" type="text"></input><br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p><input class="editor short needClear" form_data="ms_productBatchId" name="ms_productBatchId" type="text"></input><br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p><input class="editor short needClear" form_data="ms_buyer" name="ms_buyer" type="text"></input><br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p><input class="editor short needClear" form_data="ms_saleAmount" name="ms_saleAmount" type="text"></input><br /></p></td>
				  </tr>
				  
				
				</table>
								
					<div style="float:right">
					<#if (docid?exists)>
					<input type="hidden" value="${docid}" name="docid" />
					<#elseif (doc.id?exists)>
					<input type="hidden" value="${doc.id}" name="docid" />
					</#if>
					
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
					 					
 					<input type="submit" value="保存" name="snext" style="margin-top: 30px;margin-right: 9px;padding: 5px 15px;" />
 					<input type="button" value="取消" class="clearinput" name="snext" style="margin-top: 30px;margin-right: 9px;padding: 5px 15px;" /> 					 					
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
