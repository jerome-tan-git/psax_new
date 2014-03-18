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
    		ol.e {list-style-type:decimal;}
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
	    <h2>申请入会</h2>
	    <div class="sub_header_description">
	     	<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
	        <span class="page">申请入会</span>
		</div>
	</div>
	

	</div>
		<!-- Flex Slider End -->
		
		<!-- Teaser Start -->
		<div class="section" id="content" class="tag_line">

			<div style="width:60%; margin:auto;">
			   <h2>欢迎阁下的加入！</h2>
			   <br>
			   <h3>上海市浦东新区食品生产安全管理协会入会步骤：</h3>
			   <br>
			   <div style="font-size: 12pt; line-height:200%; width:90%; margin:30px 80px;">
					<ol class="e">
				   		<li>请下载右侧“单位入会登记表”&nbsp;&nbsp;
				   		<a href="./files/joinusForm.doc">
				   		<img src="./images/icons/iconmonstr-note-19-icon-16.png" />
				   		</a></li>
				   		<li>填妥“单位入会登记表”</li>
				   		<li>加盖公章</li>
				   		<li>寄送至：上海市浦东新区康桥镇秀浦路2388弄B901室 <p>或传真至：021-61183723</p></li>
			   		</ul>
			   </div>
			</div>
			<div class="one_third_last">
			    <h3></h3>			    
			</div>			
			<div class="v_space clear">&nbsp;</div>
			
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
