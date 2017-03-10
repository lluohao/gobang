package com.llhao.gobang.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.llhao.gobang.entity.User;
import com.llhao.gobang.net.AutoInitialization;
import com.llhao.gobang.net.SpringServlet;
import com.llhao.gobang.service.IUserService;

/**
 * @author 罗浩
 * 
 **/
@SuppressWarnings("serial")
@Controller
public class UserServlet extends SpringServlet {
	private String name;
	private String password;
	private String email;
	private IUserService userService;
	@AutoInitialization(init = false, out = true)
	private String message;
	@AutoInitialization(init = false, out = true)
	private String url;

	@Override
	public void init() throws ServletException {
		super.init();
		this.setContentType("text/html;charset=utf-8");
		userService = (IUserService) ctx.getBean("userService");
	}

	public void register(HttpServletRequest req, HttpServletResponse res) {
		User user = null;
		try {
			user = userService.register(name, password, email);
		} catch (Exception e) {
			this.message = e.getMessage();
			this.forward(req, res, "/register.jsp");
		}
		if (user != null) {
			session.setAttribute("user", user);
			this.message = ("注册成功！请牢记用户名和密码，正在跳转到登录页面");
			this.url = "login.jsp";
			this.forward(req, res, "/jump.jsp");
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse res) {
		User user = null;
		try {
			user = userService.login(name, password);
		} catch (Exception e) {
			this.message = e.getMessage();
			this.forward(req, res, "login.jsp");
		}
		if (user != null) {
			this.message = "登录成功！正在跳转到主页>>>>";
			this.url = "index.jsp";
			this.forward(req, res, "/jump.jsp");
		} else {
			this.message = "用户名或者密码错误！";
			this.forward(req, res, "login.jsp");
		}
	}

}
