package com.asso.action;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import util.SpringFactory;

import com.asso.manager.DocManager;
import com.asso.manager.FormManager;

public class CheckFirstLoginServlet implements javax.servlet.Filter {

	
	private FilterConfig config;
	private DocManager dm;	
	
	public CheckFirstLoginServlet(){		
		dm = (DocManager) SpringFactory.getObject("docManager");
	}
	public DocManager getDm() {
		return dm;
	}
	public void setDm(DocManager dm) {
		this.dm = dm;
	}

	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rpo = (HttpServletResponse) response;
		javax.servlet.http.HttpSession session = req.getSession();
		
		try {
		req.setCharacterEncoding("utf-8");
		} catch (Exception e1) {
		e1.printStackTrace();
		}
		
		String userId = (String) session.getAttribute("user_");
		String request_uri = req.getRequestURI().toUpperCase();// �õ��û������URI
		String ctxPath = req.getContextPath();// �õ�webӦ�ó����������·��
		String uri = request_uri.substring(ctxPath.length()); // ȥ��������·�����õ�ʣ�ಿ�ֵ�·��
		
		System.out.println("~~~~~~$$$$$$$~~~~~~########CheckFirstLoginServlet~~~~~~~~~~~~~~");
		System.out.println("~~~~~~$$$$$$$~~~~~~userid="+userId);
		System.out.println("~~~~~~$$$$$$$~~~~~~request_uri="+request_uri);
		System.out.println("~~~~~~$$$$$$$~~~~~~ctxPath="+ctxPath);
		System.out.println("~~~~~~$$$$$$$~~~~~~uri="+uri);
		
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException  {
		// �Ӳ����������л�ȡ��¼ҳ�����ҳ��URI
		config = filterconfig;		
	}

}
