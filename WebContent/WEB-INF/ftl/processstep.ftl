<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
		<title>jQa</title>
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

				$(".click_down").click(function() {
					var toHeight = $(".exam_no_container").outerHeight();
					var beginHeight = $(".exam_no_outer").outerHeight();
					if (beginHeight == 0) {
						$(".exam_no_outer").animate({
							height : toHeight + "px"
						}, 1000, "easeOutExpo", function() {
							$(".click_down").attr("src", "images/up.png");
						});
					} else {
						$(".exam_no_outer").animate({
							height : 0 + "px"
						}, 1000, "easeOutExpo", function() {
							$(".click_down").attr("src", "images/down.png");
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
			$(document).ready(function() {
				var tmpH = $(".step_left").outerHeight();
				
				if($(".step_content").outerHeight()>$(".step_left").outerHeight())
				{
					tmpH = $(".step_content").outerHeight();
				}
				
				$(".step_div_bg").css("height",tmpH+"px");
				}
			);
		</script>
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
			<div class="sub_header">

				<div class="sub_header_title">
					<h2>公开办事</h2>
					<div class="sub_header_description">
						<span><a href="./index.html">首页 &raquo;</a></span>
						<span class="page">公开办事&nbsp;&raquo;</span>
						<span class="page">企业标准备案</span>
					</div>

				</div>
				<div class="exam_type">

				</div>

			</div>

			<!-- Flex Slider End -->

			<!-- Teaser Start -->
			<div class="section" id="content" class="tag_line" style="padding-top: 0px;padding-bottom: 0px">

				<div class="demo">
					<div class="step_left clearfix">
					<#if processstep?exists>					
						<div class="step_line_blank">&nbsp;</div>
						<#if (processstep.id==1)>
						<div  class="step_menu">
							<div class="now_step">1.第一步</div>
						<#else>
						<div  class="step_menu_done">
							<div class="step_text">1.第一步</div>
						</#if>						
						</div>
						<div class="step_line">&nbsp;</div>
						
						<#if (processstep.id==2)>
						<div  class="step_menu">
							<div class="now_step">2.第二步</div>
						<#else>
							<#if (processstep.id>2)></#if>						
							<div  class="step_menu_done">
								<div class="step_text">2.第二步</div>
							<#else>
							<div  class="step_menu_todo">
								<div class="step_text">2.第二步</div>
							</#if>	
						</div>
						<div class="step_line">&nbsp;</div>
						
						<#if (processstep.id==3)>
						<div  class="step_menu_done">
							<div class="step_text">3.第三步</div>
						</div>
						</#if>	
						<div class="step_line">&nbsp;</div>
						<div  class="step_menu">
							<div class="now_step">4.第四步</div>
						</div>
						<div class="step_line">&nbsp;</div>
						<div  class="step_menu_todo">
							<div class="step_text">5.第五步</div>
						</div>
						<div class="step_line">&nbsp;</div>
						<div  class="step_menu_todo">
							<div class="step_text">6.第六步</div>
						</div>
						<div class="step_line_blank">&nbsp;</div>
					</#if>
					</div>
					<div class="step_div_bg">&nbsp;</div>
					<div class="step_content clearfix">
						<div class="step_title">
							资料准备
						</div>
						<div>
							准备企业标准备案登记表、企业标准正文、企业标准编制说明、产品工艺流程、产品配方、产品型式检验报告复印件（一年内的）、法人委托书、受委托人身份证复印件、工商营业执照复印件、生产许可证（已取得相关产品的生产许可证）复印件等资料，具体视申报情况而定。
						</div>
						<a href="./progress_1.html"><div class="next_submit" style="background-color: rgb(245, 250, 255);margin-top: 50px;">
						下一步
						</div></a>
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
