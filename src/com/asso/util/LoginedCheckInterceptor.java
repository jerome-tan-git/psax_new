package com.asso.util;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.asso.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginedCheckInterceptor extends AbstractInterceptor  {

	 /** �������󲢽��е�¼��Ч����֤ */
    public String intercept(ActionInvocation ai) throws Exception {
        
    	//ȡ�������URL
        String url = ServletActionContext.getRequest().getRequestURL().toString();
        HttpServletResponse response=ServletActionContext.getResponse();
        response.setHeader("Pragma","No-cache");          
        response.setHeader("Cache-Control","no-cache");   
        response.setHeader("Cache-Control", "no-store");   
        response.setDateHeader("Expires",0);
        User user = null;
        
        //�Ե�¼��ע������ֱ�ӷ���,��������
        if (url.indexOf("userlogin.action")!=-1 || url.indexOf("userlogout.action")!=-1){
            return ai.invoke();
        }
        else{
            //��֤Session�Ƿ����
            if(!ServletActionContext.getRequest().isRequestedSessionIdValid()){
                //session����,ת��session������ʾҳ,������ת����¼ҳ��
                return "gologin";
            }
            else{
                user = (User)ServletActionContext.getRequest().getSession().getAttribute("user_");
                //��֤�Ƿ��Ѿ���¼
                if (user==null){
                    //��δ��¼,��ת����¼ҳ��
                    return "gologin";
                }else{                    
                    return ai.invoke();
                                
                }                
            }            
        }
    }
	
}
