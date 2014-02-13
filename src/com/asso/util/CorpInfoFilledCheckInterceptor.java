package com.asso.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.asso.manager.DocManager;
import com.asso.manager.FormManager;
import com.asso.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CorpInfoFilledCheckInterceptor extends AbstractInterceptor  {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DocManager dm;
	
	
	public DocManager getDm() {
		return dm;
	}
	@Resource(name="docManager")
	public void setDm(DocManager dm) {
		this.dm = dm;
	}
	

	/** 拦截请求并进行登录有效性验证 */
    public String intercept(ActionInvocation ai) throws Exception {
        
    	//取得请求的URL
        String url = ServletActionContext.getRequest().getRequestURL().toString();
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setHeader("Pragma","No-cache");          
        response.setHeader("Cache-Control","no-cache");   
        response.setHeader("Cache-Control", "no-store");   
        response.setDateHeader("Expires",0);
        User user = null;
        
//        //对登录与注销请求直接放行,不予拦截
//        if (url.indexOf("userlogin.action")!=-1 || url.indexOf("userlogout.action")!=-1){
//            return ai.invoke();
//        }
//        else{
//            //验证Session是否过期
//            if(!ServletActionContext.getRequest().isRequestedSessionIdValid()){
//                //session过期,转向session过期提示页,最终跳转至登录页面
//                return "gologin";
//            }
//            else{
//                user = (User)ServletActionContext.getRequest().getSession().getAttribute("user_");
//                //验证是否已经登录
//                if (user==null){
//                    //尚未登录,跳转至登录页面
//                    return "gologin";
//                }else{                    
//                    return ai.invoke();
//                                
//                }                
//            }            
//        }
        
        
        
      //对登录与注销请求直接放行,不予拦截
        if (url.indexOf("userlogin.action")!=-1 ){
        	  //验证Session是否过期
            if(!ServletActionContext.getRequest().isRequestedSessionIdValid()){
                //session过期,转向session过期提示页,最终跳转至登录页面
                return "gologin";
            }
            else{
                user = (User)ServletActionContext.getRequest().getSession().getAttribute("user_");
                if(dm.checkCorpInfoFilled(user.getId()))
                	return "haveProblem";
                //验证是否已经登录
                if (user==null){
                    //尚未登录,跳转至登录页面
                    return "gologin";
                }                               
            }  
        }
        return ai.invoke();
                    
        
    }
	
}
