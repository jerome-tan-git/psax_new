			<div style="margin-top: 7px;float: right;margin-right: 15px;">	
 				<#if Session?exists>
	 				<#if Session.user_?exists>
	 					<a href="./userlogout.action" style="color: #333;" >登出</a>
	 				<#else>
	 					<a href="./gologin.action" style="color: #333;" >登录</a>
	 				</#if> 				
 				</#if>
 				<a style="color: #333;" href="./page.action?categoryid=1">用户中心</a>
 				<br>
 				<a style="color: #333; " href="./page.action?categoryid=11">申请入会</a>
 			</div>
 			
 			
						
			<div class="logo"> 		
 				<div class="logo_container">
		    		<a href="./page.action?categoryid=0"><img src="./images/logo_new.png" alt="img" /></a>
		    	</div>
		    	<div class="logo_search">
		    		<div class="search_box">
						<form action="#" method="get">
						    <div style="width:210px" class="clearfix">
							    <div style="width:150px; float: left">	
							    	<input id="error_search" type="text" name="s">
							    </div>
					    		<input  type="submit" value="" style="float: right" />
						    </div>
					    </form>					   
					</div>
				</div>
		    </div>