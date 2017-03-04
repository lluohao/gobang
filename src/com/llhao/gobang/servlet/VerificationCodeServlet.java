package com.llhao.gobang.servlet;

import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import net.sf.json.JSONObject;

import com.llhao.gobang.entity.VerificationCode;
import com.llhao.gobang.image.GaussImageNoise;
import com.llhao.gobang.image.VerificationImage;
import com.llhao.gobang.net.SpringServlet;
import com.llhao.gobang.service.IUserService;
import com.llhao.gobang.service.IVerificationCodeService;

/**
 * @author 罗浩
 * 
 **/
@SuppressWarnings("serial")
@Controller
public class VerificationCodeServlet extends SpringServlet {
	private int width = 200;
	private int height = 125;
	private int fontSize = 70;
	private IVerificationCodeService verificationCodeService;
	private String key;
	private String code;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		verificationCodeService = (IVerificationCodeService) ctx
				.getBean("verificationCodeService");
		ctx.getBean(IUserService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		image(req, resp);
	}

	/**
	 * 请求一张验证码图片
	 * 
	 * @param req
	 * @param resp
	 */
	public void image(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("image/jpeg");
		VerificationCode code = verificationCodeService
				.requestVerificationCode(key);
		try {
			OutputStream os = resp.getOutputStream();
			VerificationImage image = new VerificationImage(width, height,
					code.getValue());
			image.setFont(new Font("宋体", 0, fontSize));
			image.addNoise(new GaussImageNoise());
			image.create();
			ImageIO.write(image, "jpg", os);
			os.close();
			session.setAttribute(code.getKey(), code);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void verifi(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html");
		VerificationCode c = (VerificationCode) session.getAttribute(key);
		System.out.println(c.getValue() + ":" + code);
		JSONObject jsonObj = new JSONObject();
		try {
			if (c.check(code)) {
				jsonObj.put("code", 1);
				jsonObj.put("message", "验证成功");
			} else {
				jsonObj.put("code", 0);
				jsonObj.put("message", "验证失败");
			}
		} catch (Exception e) {
			jsonObj.put("code", -1);
			jsonObj.put("message", "验证码已经失效");
		}
		try {
			res.getWriter().print(jsonObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
