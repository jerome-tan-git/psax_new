
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
			
				

				<h2 align="center">上海市浦东新区食品生产安全管理协会</h2>
				<h2 align="center">肉制品原料肉进货表汇总</h2>
				
				<p class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<!--<p class="STYLE1">填写请参见背面样张，填写完整后请于20130130	前寄往背面地址表或电子版邮箱至pdspsax@163.com(未来电可索要电子版)</p>-->
				
				<table width="1033" border="0" cellpadding="0" cellspacing="1" bgcolor="#666666">
				  <tr font-size=15px align="center">
				    <td colspan="2" bgcolor="#FFFFFF"><p><b>进货日期</b><br /></p></td>
				    <td colspan="2" bgcolor="#FFFFFF"><p><b>原料肉名称</b><br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p><b>原料肉品种</b><br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p><b>产地</b><br /></p></td>
				    <td colspan="3" bgcolor="#FFFFFF"><p><b>进货情况</b><br /></p></td>
				    <td colspan="1" bgcolor="#FFFFFF"><p><b>进货量</b><br /></p></td>			    
				    
				  </tr>
				 
				  
				
				
				 
				<#if docslist?exists>
				  	<#if fieldslist?exists>
				  		<#list docslist as doc>
				  			<#assign fieldvalues=doc.fvlist>
						  <tr>
						  	<td colspan="2" bgcolor="#FFFFFF">
							  <#list fieldvalues as f>
							  		<#if (f.fieldid==941)>
							  		<p>${f.value}<br /></p>
							  		</#if>
							  </#list>
							  </td>
							  <td colspan="2" bgcolor="#FFFFFF">
							  <#list fieldvalues as f>
							  		<#if (f.fieldid==942)>
							    	<p>${f.value}<br /></p>
							    	</#if>
							  </#list>
							  </td>
							  <td colspan="1" bgcolor="#FFFFFF">
							  <#list fieldvalues as f>
							  		<#if (f.fieldid==943)>
							    	<p>${f.value}<br /></p>
							    	</#if>
							  </#list>
							  </td>
							  <td colspan="1" bgcolor="#FFFFFF">
							  <#list fieldvalues as f>
							  		<#if (f.fieldid==951)>
							    	<p>${f.value}<br /></p>
							    	</#if>
							  </#list>
						  	 </td>
						  	
						  	<td colspan="3" bgcolor="#FFFFFF">
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
				    	
				    		<td colspan="1" bgcolor="#FFFFFF">
						  	  <#list fieldvalues as f>
						  		    <#if (f.fieldid==952)>
						    		<p>${f.value}<br /></p>
						    		</#if>						    	
						  	  </#list>
						  	  </td>
						  </tr>
				  		</#list>
				  	</#if>
				  </#if>
				 
				
				</table>
								
				<div style="float:right">					
 				<!-- <a href="./import?formid=15&mode=edit"><h3 align="right">新增</h3></a> -->
 				<a href="./import?formid=15&mode=edit"><input type="button" value="新增" class="clearinput"  style="margin-top: 30px;margin-right: 9px;padding: 5px 15px;" /></a>				
 				</div>
				<br />
				
				
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
