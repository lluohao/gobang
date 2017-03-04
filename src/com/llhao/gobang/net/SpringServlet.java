package com.llhao.gobang.net;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author ÂÞºÆ
 * 
 **/
@SuppressWarnings("serial")
public class SpringServlet extends CompleteInitializationHttpServlet {
	protected WebApplicationContext ctx;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ctx = WebApplicationContextUtils
				.getWebApplicationContext(config.getServletContext());
		super.init(config);
	}
}
