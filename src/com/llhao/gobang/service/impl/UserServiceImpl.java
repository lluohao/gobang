package com.llhao.gobang.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llhao.gobang.dao.impl.UserDAO;
import com.llhao.gobang.entity.User;
import com.llhao.gobang.md5.MD5Encoding;
import com.llhao.gobang.service.IUserService;
import com.llhao.gobang.util.RegexUtils;

/**
 * @author �޺�
 * 
 **/
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDAO userDao;

	@Override
	public User register(String name, String password, String email) {
		if (name == null || name.length() < 2 || name.length() > 15) {
			throw new RuntimeException("�û������ȱ�����2-15���ַ�֮��");
		} else if (password == null || password.length() < 6
				|| password.length() > 16) {
			throw new RuntimeException("���볤�ȱ�����6-16���ַ�֮��");
		} else if (!RegexUtils.isEmailAdress(email)) {
			throw new RuntimeException("������һ����ȷ���ʼ���ַ");
		}

		if (userDao.findByName(name).size() > 0) {
			throw new RuntimeException("�û����ѱ�ռ��");
		} else if (userDao.findByEmail(email).size() > 0) {
			throw new RuntimeException("�������Ѿ���ע��");
		}
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(MD5Encoding.encoding(password));
		user.setWin(0);
		user.setTie(0);
		user.setLose(0);
		user.setTime(new Date(System.currentTimeMillis()));
		try {
			int id = userDao.save(user);
			user = userDao.findById(id);
		} catch (RuntimeException e) {
		}
		return user;
	}

	@Override
	public User login(String name, String password) {
		List<User> users = (List<User>) userDao.findByName(name);
		if (users != null && users.size() > 0) {
			User user = users.get(0);
			if (user.getPassword().equals(MD5Encoding.encoding(password))) {
				return user;
			}
		} else {
			throw new RuntimeException("�û���������");
		}
		return null;
	}

	@Override
	public void findPassword(String name) {
		List<User> users = userDao.findByName(name);
		if (users.size() == 0) {
			throw new RuntimeException("�û���������");
		}
		String email = users.get(0).getEmail();
		if (email == null || email.length() == 0) {
			throw new RuntimeException("û���������䣬�޷��һ�����");
		}
	}

}
