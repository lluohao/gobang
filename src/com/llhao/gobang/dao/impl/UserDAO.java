package com.llhao.gobang.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.llhao.gobang.dao.IUserDAO;
import com.llhao.gobang.entity.User;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.llhao.gobang.entity.User
 * @author MyEclipse Persistence Tools
 */
public class UserDAO extends HibernateDaoSupport implements IUserDAO {
	private static final Log log = LogFactory.getLog(UserDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String IMAGE = "image";
	public static final String WIN = "win";
	public static final String TIE = "tie";
	public static final String LOSE = "lose";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#save(com.llhao.gobang.entity.User)
	 */
	public int save(User transientInstance) {
		log.debug("saving User instance");
		try {
			int id = (Integer) getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
			return id;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#delete(com.llhao.gobang.entity.User)
	 */
	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findById(java.lang.Integer)
	 */
	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"com.llhao.gobang.entity.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findByExample(com.llhao.gobang.entity.User)
	 */
	public List findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List<User> findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findByName(java.lang.Object)
	 */
	public List<User> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findByPassword(java.lang.Object)
	 */
	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findByEmail(java.lang.Object)
	 */
	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findByImage(java.lang.Object)
	 */
	public List findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findByWin(java.lang.Object)
	 */
	public List findByWin(Object win) {
		return findByProperty(WIN, win);
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findByTie(java.lang.Object)
	 */
	public List findByTie(Object tie) {
		return findByProperty(TIE, tie);
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findByLose(java.lang.Object)
	 */
	public List findByLose(Object lose) {
		return findByProperty(LOSE, lose);
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#findAll()
	 */
	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#merge(com.llhao.gobang.entity.User)
	 */
	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#attachDirty(com.llhao.gobang.entity.User)
	 */
	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IUserDAO#attachClean(com.llhao.gobang.entity.User)
	 */
	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IUserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IUserDAO) ctx.getBean("UserDAO");
	}
}