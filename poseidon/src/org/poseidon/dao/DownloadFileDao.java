package org.poseidon.dao;

import org.apache.log4j.Logger;
import org.poseidon.dao.base.BaseDAO;
import org.springframework.stereotype.Component;

@Component("downloadFileDao")
public class DownloadFileDao extends BaseDAO {
    private static final Logger log = Logger.getLogger(DownloadFileDao.class);
}
