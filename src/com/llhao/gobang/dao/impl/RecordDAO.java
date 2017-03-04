package com.llhao.gobang.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.llhao.gobang.dao.IRecordDAO;
import com.llhao.gobang.entity.Record;

/**
 * A data access object (DAO) providing persistence and search support for
 * Record entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.llhao.gobang.entity.Record
 * @author MyEclipse Persistence Tools
 */
public class RecordDAO extends HibernateDaoSupport implements IRecordDAO {
	private static final Log log = LogFactory.getLog(RecordDAO.class);
	// property constants
	public static final String FILE = "file";
	public static final String WIN = "win";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#save(com.llhao.gobang.entity.Record)
	 */
	public void save(Record transientInstance) {
		log.debug("saving Record instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#delete(com.llhao.gobang.entity.Record)
	 */
	public void delete(Record persistentInstance) {
		log.debug("deleting Record instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#findById(java.lang.Integer)
	 */
	public Record findById(java.lang.Integer id) {
		log.debug("getting Record instance with id: " + id);
		try {
			Record instance = (Record) getHibernateTemplate().get(
					"com.llhao.gobang.entity.Record", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#findByExample(com.llhao.gobang.entity.Record)
	 */
	public List findByExample(Record instance) {
		log.debug("finding Record instance by example");
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
	 * @see com.llhao.gobang.dao.IRecordDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Record instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Record as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#findByFile(java.lang.Object)
	 */
	public List findByFile(Object file) {
		return findByProperty(FILE, file);
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#findByWin(java.lang.Object)
	 */
	public List findByWin(Object win) {
		return findByProperty(WIN, win);
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#findAll()
	 */
	public List findAll() {
		log.debug("finding all Record instances");
		try {
			String queryString = "from Record";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#merge(com.llhao.gobang.entity.Record)
	 */
	public Record merge(Record detachedInstance) {
		log.debug("merging Record instance");
		try {
			Record result = (Record) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#attachDirty(com.llhao.gobang.entity.Record)
	 */
	public void attachDirty(Record instance) {
		log.debug("attaching dirty Record instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.llhao.gobang.dao.IRecordDAO#attachClean(com.llhao.gobang.entity.Record)
	 */
	public void attachClean(Record instance) {
		log.debug("attaching clean Record instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IRecordDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IRecordDAO) ctx.getBean("RecordDAO");
	}
}