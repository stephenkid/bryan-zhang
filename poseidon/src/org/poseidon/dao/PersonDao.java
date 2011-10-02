package org.poseidon.dao;

import org.apache.log4j.Logger;
import org.poseidon.dao.base.BaseDAO;
import org.springframework.stereotype.Component;

@Component("personDao")
public class PersonDao extends BaseDAO {
	private static final Logger log = Logger.getLogger(PersonDao.class);
}
