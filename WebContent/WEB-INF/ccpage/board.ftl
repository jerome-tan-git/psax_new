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
		<script type="text/javascript" src="./js/jquery.min.js"></script>
		<script type="text/javascript" src="./js/jquery-migrate.js"></script>
		
		<script type="text/javascript" src="./js/jqueryslidemenu.js"></script>
		<script type="text/javascript" src="./js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="./js/custom.js"></script>
		<script type="text/javascript" src="./js/unslider.js"></script>
		<script type="text/javascript" src="./js/fancybox/jquery.fancybox-1.3.4.js"></script>

		<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.min.js"></script>

		<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
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
			   
    		.iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
    		.iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
    		
    		div.name_list1{
    			font-size: 12pt;
				line-height:1.5;
				width:20%;
				padding:5px;
				border:1px;
				margin:0 10px;
				float:right;
				text-align:right;
    		}
    		div.name_list2{
    			font-size: 12pt;
				line-height:1.5;
				width:70%;
				padding:5px;
				border:1px;
				margin:0 10px;
				float:right;
				text-align:left;
    		}
		</style>
		<script type="text/javascript">
			function enterSite() {
				$("#page_wrap").show();
				var dis = $("#cover").outerHeight(true);
				$("#cover").animate({
					top : "-2000px"
				}, 2000, 'easeInQuart', function() {
					$("#cover").remove();
					$(document.body).css("overflow", "auto");
					$("html").css("overflow", "auto");
				});
				var curl =window.location.href;
				if (curl.indexOf("#enter")== -1)
				{
					window.location.href +="#enter";
				} 
				
			}


			$(document).ready(function() {
				
				// bancy bo=x
				jQuery("a#example6").fancybox({
					'titlePosition'		: 'outside',
					'overlayColor'		: '#000',
					'overlayOpacity'	: 0.9
				});
				//slider
				// var sudoSlider = jQuery("#testimonail").sudoSlider({
		           // continuous:true,
		           // numeric:false
		        // });
				  $('.banner').unslider();
				
				//menu_item 
				$(".menu_item").hover(function(){
					$(this).addClass("selected");
					var myList =$(this).find(".menu_list"); 
					myList.show();
					
						
				},function(){
					
					$(this).removeClass("selected");
					//alert(".menu_list .sub_"+$(this).attr("id"));
					var myList =$(this).find(".menu_list"); 
					myList.hide();
					
				});
				
				$(".small_menu_title").bind("click",function()
				{
					var newHeight= $(".small_menu_list").find(".clearfix").outerHeight();
					if ($(".small_menu_list").outerHeight()==0)
					{
						$(".small_menu_list").animate({
							height:newHeight+"px"
						},1000,"easeOutExpo"
						);
					}
					else
					{
						$(".small_menu_list").animate({
							height:0+"px"
						},1000,"easeOutExpo"
						);
					}
				});
				
				$(".small_menu_item").hover(function()
				{
					$(this).addClass("small_menu_hover");
				},
				function()
				{
					$(this).removeClass("small_menu_hover");
				});
				
				$(".list_item").hover(function()
				{
					
					$(this).find(".sub_menu").css("display","block");
					$( this ).animate({
          				backgroundColor: "#FDFFD0"}, 500 );
				},
				function()
				{
						$( this ).animate({
          				backgroundColor: "#fff"}, 500 );
					$(this).find(".sub_menu").hide();
				});
				

			});

		</script>
	</head>
	<body>
		<div id="page_wrap">
	<!-- Header Start -->
		<div class="header">
		 	<#include "../commons/logo.ftl">
		  	<#include "../commons/menubar.ftl">
			<#include "../commons/smallmenu.ftl"> 
		    <!-- <nav>
		    <div id="myslidemenu" class="jqueryslidemenu">
		        <ul>
		            <li><a class="active" href="./index.html">首页</a></li>
		            <li><a href="./about.html">关于我们</a></li>
		            <li><a href="./index.html">舆情动态</a></li>
		            
		            <li><a href="./portfolio.html">公开办事</a>
		                <ul>
		                    <li><a href="./portfolio_3col.html">肉制品报告</a></li>
		                    <li><a href="./portfolio_3col.html">标准备案</a></li>
		                    <li><a href="./portfolio_2col.html">添加剂报告</a></li>
		                    <li><a href="./portfolio_1col.html">委托加工备案</a></li>
		                </ul>
		            </li>
		            <li><a href="./blog.html">政策法规</a></li>
		            <li><a href="./contact.html">咨询服务</a></li>
		      </ul>
		    </div>
		    </nav> -->
		</div>
		<!-- Header End -->
		
		<div class="clear"></div>
		
		<!-- Flex Slider Start -->
	<div class="sub_header">
	
	<div class="sub_header_title">
	    <h2>组织架构</h2>
	    <div class="sub_header_description">
	        <span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
	        <span class="page"><a href="./page.action?categoryid=7">组织架构 &raquo;</a></span>
	        <span class="page">理事会</span>
		</div>
	</div>
	

	</div>
		<!-- Flex Slider End -->
		
		<!-- Teaser Start -->
		<div class="section" id="content" class="tag_line">
			<!--
			<div class="v_space clear">&nbsp;</div>
			-->
			<div class="two_third" style="width:100%;" >
			    
			     <h3>理事会名单</h3>
			    
			   <!-- <div class="description" style="text-align:center;">-->
			   <div>
			    	<div class="name_list2">长江原水厂</div>
			    	<div class="name_list1">名誉会长</div>
			    	<div class="name_list2">上海市浦东新区计量质量检测所</div>
			    	<div class="name_list1">副会长</div>
			    	<div class="name_list2">多美滋婴幼儿食品有限公司</div>
			    	<div class="name_list1">副会长</div>
			    	<div class="name_list2">海天坊食品制造有限公司</div>
			    	<div class="name_list1">副会长</div>
			    	<div class="name_list2">上海金丝猴食品股份有限公司</div>
			    	<div class="name_list1">副会长</div>
			    	<div class="name_list2">上海嘉里食品工业有限公司</div>
			    	<div class="name_list1">副会长</div>
			    	<div class="name_list2">上海山人食品有限公司</div>
			    	<div class="name_list1">副会长</div>
			    	<div class="name_list2">上海申美饮料食品有限公司</div>
			    	<div class="name_list1">副会长</div>			    	
			    	<div class="name_list2">上海闽龙实业有限公司分公司</div>
			    	<div class="name_list1">副会长</div>
			    	<div class="name_list2">上海界龙实业集团股份有限公司御天包装印务分公司</div>
			    	<div class="name_list1">副会长</div>
			    	<div class="name_list2">上海辉文生物技术有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">三得利（上海）食品有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海清美绿色食品有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海长阳面包厂有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海东航美心食品有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海水管家健康饮品有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">通用磨坊贸易(上海)有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海川崎食品有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海庆丰酿造调味品有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海新成食品有限公司华洲路分公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海佳丰食品有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海杰特食品有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海中航包装材料有限公司</div>
			    	<div class="name_list1">理事</div>
			    	<div class="name_list2">上海克莉丝汀食品有限公司</div>
			    	<div class="name_list1">理事</div>
			    	
				</div>
			</div>
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
