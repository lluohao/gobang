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
 * 自动注入和输出的HttpServlet子类，这个类会把请求中的数据自动注入到字段中，
 * 并且在请求处理结束之后会把请求中出现的字段添加到HttpServletRequest中。 如果某个字段不希望被此类自动注入或输出，需要加注
 * {@link AutoInitialization}注解，并设置其init或者out为false.
 * 如果某个字段没有在请求中出现，却希望被自动输出，也需要加注{@link AutoInitialization}并设置out为true. <br />
 * 这些注入会在service方法调用前执行
 * ，而输出会在service方法之后被执行。也可以手动调用initializaData或者outputDate方法执行
 * <p>
 * 注意：继承自此类后如果在service中处理请求将使得自动注入和输出失效，需手动调用
 * </p>
 * 
 * @author 罗浩
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
	 * 在此类的此方法前注入，此方法后输出
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
	 * 转向，如果请求中包含method属性，并且运行时对象中包含之个方法，调用此方法处理请求
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
	 * 转向，建议使用此方法进行转向
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
	 * 将数据输出到HttpServletRequest中
	 * 
	 * @param req
	 * @param resp
	 */
	public void outputDate(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 * 检查此次是否输出过了
		 */
		if (isOutput) {
			return;
		}
		isOutput = true;
		/**
		 * 先将请求中出现的字段输出
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
		 * 再将未出现在请求中的字段输出
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
	 * 输出数据
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
	 * 输出一行数据
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
