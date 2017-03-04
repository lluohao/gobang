package com.llhao.gobang.net;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �Զ�ע��������HttpServlet���࣬������������е������Զ�ע�뵽�ֶ��У�
 * ���������������֮���������г��ֵ��ֶ���ӵ�HttpServletRequest�С� ���ĳ���ֶβ�ϣ���������Զ�ע����������Ҫ��ע
 * {@link AutoInitialization}ע�⣬��������init����outΪfalse.
 * ���ĳ���ֶ�û���������г��֣�ȴϣ�����Զ������Ҳ��Ҫ��ע{@link AutoInitialization}������outΪtrue. <br />
 * ��Щע�����service��������ǰִ��
 * �����������service����֮��ִ�С�Ҳ�����ֶ�����initializaData����outputDate����ִ��
 * <p>
 * ע�⣺�̳��Դ���������service�д�������ʹ���Զ�ע������ʧЧ�����ֶ�����
 * </p>
 * 
 * @author �޺�
 * @version 1.0
 **/
@SuppressWarnings("serial")
public class InitializationHttpServlet extends HttpServlet {
	private Class<? extends InitializationHttpServlet> cls;
	@AutoInitialization(init = false, out = false)
	private boolean isOutput = false;
	@AutoInitialization(init = true, out = false)
	private String method;

	public InitializationHttpServlet() {
		cls = this.getClass();
	}

	/**
	 * �ڴ���Ĵ˷���ǰע�룬�˷��������
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		isOutput = false;
		initializaData(req, resp);
		if (method != null) {
			boolean invoke = transport(req, resp);
			if (!invoke) {
				super.service(req, resp);
			}
		} else {
			super.service(req, resp);
		}
		method = null;
		outputDate(req, resp);
	}

	/**
	 * ת����������а���method���ԣ���������ʱ�����а���֮�����������ô˷�����������
	 * 
	 * @param req
	 * @param resp
	 * @return
	 */
	private boolean transport(HttpServletRequest req, HttpServletResponse resp) {
		boolean invoke = false;
		try {
			Method me = cls.getMethod(method, HttpServletRequest.class,
					HttpServletResponse.class);
			me.setAccessible(true);
			invoke = true;
			me.invoke(this, req, resp);
		} catch (Exception e) {
		}
		return invoke;
	}

	/**
	 * ת�򣬽���ʹ�ô˷�������ת��
	 * 
	 * @param req
	 * @param res
	 * @param path
	 */
	public void forward(HttpServletRequest req, HttpServletResponse res,
			String path) {
		outputDate(req, res);
		try {
			req.getRequestDispatcher(path).forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �����������HttpServletRequest��
	 * 
	 * @param req
	 * @param resp
	 */
	public void outputDate(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * ���˴��Ƿ��������
		 */
		if (isOutput) {
			return;
		}
		isOutput = true;
		/**
		 * �Ƚ������г��ֵ��ֶ����
		 */
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String key = names.nextElement();
			try {
				Field field = cls.getDeclaredField(key);
				if (field != null) {
					Annotation[] annos = field.getAnnotations();
					boolean in = true;
					for (Annotation annotation : annos) {
						if (annotation.annotationType() == AutoInitialization.class) {
							AutoInitialization autoIn = (AutoInitialization) annotation;
							in = autoIn.out();
						}
					}
					if (in) {
						field.setAccessible(true);
						Object obj = field.get(this);
						req.setAttribute(key, obj);
					}
				}
			} catch (Exception e) {
			}
		}
		/**
		 * �ٽ�δ�����������е��ֶ����
		 */
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annos = field.getAnnotations();
			boolean in = false;
			for (Annotation annotation : annos) {
				if (annotation.annotationType() == AutoInitialization.class) {
					AutoInitialization autoIn = (AutoInitialization) annotation;
					in = autoIn.out();
				}
			}
			if (in) {
				Object obj = null;
				try {
					field.setAccessible(true);
					obj = field.get(this);
					req.setAttribute(field.getName(), obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
				req.setAttribute(field.getName(), obj);
			}
		}
	}

	/**
	 * �������
	 * 
	 * @param req
	 * @param res
	 */
	public void initializaData(HttpServletRequest req, HttpServletResponse res) {
		Enumeration<String> names = req.getParameterNames();
		while (names.hasMoreElements()) {
			String key = names.nextElement();
			String value = req.getParameter(key);
			initOne(key, value);
		}
	}

	/**
	 * ���һ������
	 * 
	 * @param key
	 * @param value
	 */
	private void initOne(String key, String value) {
		if("method".equals(key)){
			this.method = value;
			return;
		}
		Field field = null;
		try {
			field = cls.getDeclaredField(key);
		} catch (Exception e1) {
		}
		if (field == null) {
			try {
				field = cls.getField(key);
			} catch (Exception e) {
			}
		}
		if (field != null) {
			Annotation[] annos = field.getAnnotations();
			boolean in = true;
			for (Annotation annotation : annos) {
				if (annotation.annotationType() == AutoInitialization.class) {
					AutoInitialization autoIn = (AutoInitialization) annotation;
					in = autoIn.init();
				}
			}
			if (in) {
				try {
					field.setAccessible(true);
					Object tValue = null;
					Class<?> type = field.getType();
					if (type == Integer.class || type == int.class) {
						tValue = Integer.parseInt(value);
					} else if (type == String.class) {
						tValue = value;
					} else if (type == Double.class || type == double.class) {
						tValue = Double.parseDouble(value);
					} else if (type == Float.class || type == float.class) {
						tValue = Float.parseFloat(value);
					} else if (type == Boolean.class || type == boolean.class) {
						tValue = Boolean.parseBoolean(value);
					} else if (type == Character.class || type == char.class) {
						if (value != null && value.length() > 0) {
							tValue = value.charAt(0);
						}
					} else if (type == Long.class || type == long.class) {
						tValue = Long.parseLong(value);
					} else if (type == Short.class || type == short.class) {
						tValue = Short.parseShort(value);
					} else if (type == Byte.class || type == byte.class) {
						tValue = Byte.parseByte(value);
					}
					field.set(this, tValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
