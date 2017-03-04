package com.llhao.gobang.net;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.llhao.gobang.jdbc.Config;

/**
 * @author ÂÞºÆ
 * 
 **/
@SuppressWarnings("serial")
public class CompleteInitializationHttpServlet extends
		InitializationHttpServlet {
	protected String contentType = null;
	protected PrintWriter out;
	protected HttpServletRequest request;
	protected HttpServletResponse respones;
	protected HttpSession session;
	protected ServletContext context;
	protected ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
		context = config.getServletContext();
		super.init(config);
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.request = req;
		this.respones = resp;
		this.session = req.getSession();
		if (contentType != null) {
			this.respones.setContentType(contentType);
			this.out = this.respones.getWriter();
		}
		super.service(req, resp);
	}
}
