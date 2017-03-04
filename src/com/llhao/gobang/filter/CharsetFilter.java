package com.llhao.gobang.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.llhao.gobang.jdbc.Config;

/**
 * ���������
 * 
 * @author �޺�
 * 
 **/
public class CharsetFilter implements Filter {
	private Config config;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		ensureConfig(request.getServletContext());
		chain.doFilter(new CharsetServletRequest((HttpServletRequest) request),
				response);
	}

	/**
	 * �ж��Ƿ���������ļ������û�У������
	 * 
	 * @param servletContext
	 *            web�������������Ķ���
	 */
	private void ensureConfig(ServletContext servletContext) {
		if (servletContext.getAttribute("config") == null) {
			synchronized (this) {
				if (servletContext.getAttribute("config") == null) {
					Class<? extends CharsetFilter> cls = this.getClass();
					InputStream is = cls
							.getResourceAsStream("/conf/server.properties");
					try {
						config = new Config(is);
					} catch (IOException e) {
						e.printStackTrace();
					}
					servletContext.setAttribute("config", config);
				}
			}
		}
	}

	/**
	 * HttpServletRequest��װ���������ݽ��б���ת��
	 * 
	 * @author �޺�
	 */
	private class CharsetServletRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request;
		private ServletContext context;

		public CharsetServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.context = request.getServletContext();
		}

		@Override
		public String getParameter(String name) {
			Config config = (Config) context.getAttribute("config");
			String value = request.getParameter(name);
			if (config == null) {
				return value;
			}
			String base = config.get("base");
			String charset = config.get("charset");
			try {
				if (value != null && base != null && charset != null) {
					return new String(value.getBytes(base), charset);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return request.getParameter(name);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
