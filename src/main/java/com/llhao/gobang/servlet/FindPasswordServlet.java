package com.llhao.gobang.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import com.llhao.gobang.entity.VerificationCode;
import com.llhao.gobang.net.AutoInitialization;
import com.llhao.gobang.net.SpringServlet;
import com.llhao.gobang.service.IUserService;

/**
 * @author �޺�
 * 
 **/
@SuppressWarnings("serial")
@Controller
public class FindPasswordServlet extends SpringServlet {
	private String name;
	private String key;
	private String code;
	private IUserService userService;
	@AutoInitialization(out = true)
	private String message;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userService = ctx.getBean(IUserService.class);
	}

	public void find(HttpServletRequest req, HttpServletResponse res) {
		if (key != null && code != null) {
			try {
				VerificationCode vcode = (VerificationCode) session
						.getAttribute(key);
				if (code != null && vcode.check(code)) {
					userService.findPassword(name);
					message = "�ʼ��Ѿ����ͣ���鿴����";
				} else {
					message = "��֤�벻��ȷ";
				}
			} catch (Exception e) {
				message = e.getMessage();
			}
		} else {
			message = "��������֤��";
		}
		this.forward(req, res, "/find.jsp");
	}
}
