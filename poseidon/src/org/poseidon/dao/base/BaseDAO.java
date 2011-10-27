package org.poseidon.dao.base;
// default package

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

@SuppressWarnings({"unchecked","unused"})
public abstract class BaseDAO{
	private static final Logger log = Logger.getLogger(BaseDAO.class);
	
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	protected void initDao() {
		// do nothing
	}

	public <T> void save(T transientInstance) {
		log.debug("saving instance");
		try {
			this.hibernateTemplate.save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public <T> void delete(T persistentInstance) {
		log.debug("deleting instance");
		try {
			this.hibernateTemplate.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public <T> T findById(String entityName, Long id) {
		log.debug("getting " + entityName +" instance with id: " + id);
		try {
			Object instance = this.hibernateTemplate.get(entityName, id);
			return (T)instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public <T> List<T> findByExample(T instance) {
		log.debug("finding instance by example");
		try {
			List<T> results = this.hibernateTemplate.findByExample(instance);
			log.debug("find by example successful, result size: "+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public <T> List<T> findByProperty(String entityName, String propertyName, Object value) {
		log.debug("finding " + entityName + " instance with property: " + propertyName+ ", value: " + value);
		try {
			String queryString = "from " + entityName + " as model where model."+ propertyName + "= ?";
			return this.hibernateTemplate.find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public <T> List<T> findAll(String entityName) {
		log.debug("finding all " + entityName + " instances");
		try {
			String queryString = "from " + entityName;
			return this.hibernateTemplate.find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public <T> T merge(T detachedInstance) {
		log.debug("merging instance");
		try {
			T result = this.hibernateTemplate.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public <T> void attachDirty(T instance) {
		log.debug("attaching dirty instance");
		try {
			this.hibernateTemplate.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public <T> void attachClean(T instance) {
		log.debug("attaching clean instance");
		try {
			this.hibernateTemplate.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public <T> List<T> findByCriteria(DetachedCriteria dc){
		try {
			return this.hibernateTemplate.findByCriteria(dc);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public Map<String, ?> findByCriteria(final DetachedCriteria dc, final int page, final int rows){
	    try{
	        Map<String, ?> returnMap = this.hibernateTemplate.execute(new HibernateCallback<Map<String, ?>>() {
                public Map<String, ?> doInHibernate(Session session) throws HibernateException, SQLException {
                    Map<String, Object> returnMap = new HashMap<String, Object>();
                    Criteria criteria = dc.getExecutableCriteria(session);
                    int total = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
                    returnMap.put("total", total);
                    criteria.setProjection(null);
                    List<?> list = criteria.setFirstResult((page-1)*rows).setMaxResults(rows).list();
                    returnMap.put("rows", list);
                    System.out.println(returnMap);
                    return returnMap;
                }
            });
	        return returnMap;
	    } catch (RuntimeException re){
	        throw re;
	    }
	}
}