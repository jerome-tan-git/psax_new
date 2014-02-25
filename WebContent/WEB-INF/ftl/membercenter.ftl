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

		</style>
		<script type="text/javascript">
			$(document).ready(function() {

				// bancy bo=x
				// jQuery("a#example6").fancybox({
					// 'titlePosition' : 'outside',
					// 'overlayColor' : '#000',
					// 'overlayOpacity' : 0.9
				// });
				//slider
				// var sudoSlider = jQuery("#testimonail").sudoSlider({
				// continuous:true,
				// numeric:false
				// });
				$('.banner').unslider();

				//menu_item
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
			<div class="sub_header  exam_bg">

				<div class="sub_header_title">
					<h2>用户中心</h2>
					<div class="sub_header_description">
						<span><a href="./page.action?categoryid=0">首页 &raquo;</a></span>
						<span class="page">用户中心</span>
					</div>

				</div>
				<div class="exam_type">
					
				</div>
				
			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 30px">
				
				
				<#if Session?exists>
					<#if Session.user_?exists>
						<#assign user=Session.user_>						
						<#if hasCorpForm(user.id)==1>
							<a href="./hello?mode=edit">
						<#else>
							<a href="./hello?formid=9">
						</#if>
					<#else>
						<a href="./hello?formid=9">
					</#if>
				</#if>								
				<div class="member_block qyxx">
					<div class="member_block_icon qyxx">&nbsp;</div>
					<div class="member_block_icon_bg qyxx_bg">&nbsp;</div>
					<#if mcclist?exists>
					<#assign hasid=0>
						<#list mcclist as mcc>
							<#if (mcc.id==1)>
								<div class="member_block_title">${mcc.name}</div>
								<#assign hasid=1>
							</#if>						
						</#list>
						<#if (hasid==0)>
							<div class="member_block_title">企业信息</div>
						</#if>
					<#else>
						<div class="member_block_title">企业信息</div>
					</#if>
					
				</div>
				</a>
				<a href="./usercenter.action">				
				<div class="member_block jbxx">
					<div class="member_block_icon jbxx">&nbsp;</div>
					<div class="member_block_icon_bg jbxx_bg">&nbsp;</div>
					<#if mcclist?exists>
					<#assign hasid=0>
						<#list mcclist as mcc>
							<#if (mcc.id==2)>
								<div class="member_block_title">${mcc.name}</div>
								<#assign hasid=1>
							</#if>						
						</#list>
						<#if (hasid==0)>
							<div class="member_block_title">基本信息</div>
						</#if>
					<#else>
						<div class="member_block_title">基本信息</div>
					</#if>
					
				</div>
				</a>
				<a href="./process.action">	
				<div class="member_block blue">
					<div class="member_block_icon gkbs">&nbsp;</div>
					<div class="member_block_icon_bg gkbs_bg">&nbsp;</div>
					<#if mcclist?exists>
					<#assign hasid=0>
						<#list mcclist as mcc>
							<#if (mcc.id==3)>
								<div class="member_block_title">${mcc.name}</div>
								<#assign hasid=1>
							</#if>						
						</#list>
						<#if (hasid==0)>
							<div class="member_block_title">公开办事</div>
						</#if>
					<#else>
						<div class="member_block_title">公开办事</div>
					</#if>
					
				</div>
				</a>	
				<!--<a href="./upload_list.html">-->
				<!--<a href="./uploadfilesmanager.action">-->				
				<!--<a href="./uploadfoldersmanager.action">-->
				<a href="./uploadfoldersmanager.action">
				<div class="member_block qygl">
					<div class="member_block_icon qygl">&nbsp;</div>
					<div class="member_block_icon_bg  qygl_bg">&nbsp;</div>
					<#if mcclist?exists>
					<#assign hasid=0>
						<#list mcclist as mcc>
							<#if (mcc.id==4)>
								<div class="member_block_title">${mcc.name}</div>
								<#assign hasid=1>
							</#if>						
						</#list>
						<#if (hasid==0)>
							<div class="member_block_title">食品生产管理</div>
						</#if>
					<#else>
						<div class="member_block_title">食品生产管理</div>
					</#if>
					
				</div>
				</a>				
				<!--<a href="./download.html">-->
				<a href="./listtodownloads.action">				
				<div class="member_block xz">
					<div class="member_block_icon xz">&nbsp;</div>
					<div class="member_block_icon_bg xz_bg">&nbsp;</div>
					<#if mcclist?exists>
					<#assign hasid=0>
						<#list mcclist as mcc>
							<#if (mcc.id==5)>
								<div class="member_block_title">${mcc.name}</div>
								<#assign hasid=1>
							</#if>						
						</#list>
						<#if (hasid==0)>
							<div class="member_block_title">相关下载</div>
						</#if>
					<#else>
						<div class="member_block_title">相关下载</div>
					</#if>
					
				</div>
				</a>
				<a href="./page.action?categoryid=15">				
				<div class="member_block ks">
					<div class="member_block_icon ks">&nbsp;</div>
					<div class="member_block_icon_bg ks_bg">&nbsp;</div>
					<#if mcclist?exists>
					<#assign hasid=0>
						<#list mcclist as mcc>
							<#if (mcc.id==6)>
								<div class="member_block_title">${mcc.name}</div>
								<#assign hasid=1>
							</#if>						
						</#list>
						<#if (hasid==0)>
							<div class="member_block_title">考试中心</div>
						</#if>
					<#else>
						<div class="member_block_title">考试中心</div>
					</#if>
					
				</div>
				</a>
				<div class="member_block jgdt">
					<div class="member_block_icon jgdt">&nbsp;</div>
					<div class="member_block_icon_bg jgdt_bg">&nbsp;</div>
					<#if mcclist?exists>
					<#assign hasid=0>
						<#list mcclist as mcc>
							<#if (mcc.id==7)>
								<div class="member_block_title">${mcc.name}</div>
								<#assign hasid=1>
							</#if>						
						</#list>
						<#if (hasid==0)>
							<div class="member_block_title">监管动态</div>
						</#if>
					<#else>
						<div class="member_block_title">监管动态</div>
					</#if>
					
				</div>
				
				<!--<a href="./notice.html">-->
				<a href="./listnotice.action">
				<div class="member_block tz">
					<div class="member_block_icon tz">&nbsp;</div>
					<div class="member_block_icon_bg tz_bg">&nbsp;</div>
					<#if Session?exists>
						<#if Session.user_?exists>
						<#assign user=Session.user_>						
						<div class="unread">${stats(user.id)}&nbsp;<img src="./images/iconmonstr-email-4-icon-16.png" /></div>
						<#else>
						<div class="unread">&nbsp;</div>
						</#if>
					</#if>
					<#if mcclist?exists>
					<#assign hasid=0>
						<#list mcclist as mcc>
							<#if (mcc.id==8)>
								<div class="member_block_title">${mcc.name}</div>
								<#assign hasid=1>
							</#if>						
						</#list>
						<#if (hasid==0)>
							<div class="member_block_title">通知</div>
						</#if>
					<#else>
						<div class="member_block_title">通知</div>
					</#if>
					
				</div>
				</a>
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
