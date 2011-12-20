package org.poseidon.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.nutz.filepool.FilePool;
import org.nutz.filepool.NutFilePool;
import org.poseidon.component.dataExport.XlsExportor;
import org.poseidon.dao.DownloadFileDao;
import org.poseidon.dao.PersonDao;
import org.poseidon.dto.DownloadFileDto;
import org.poseidon.pojo.DownloadFile;
import org.poseidon.pojo.Person;
import org.poseidon.service.DownloadService;
import org.poseidon.util.PropConstants;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("downloadService")
public class DownloadServiceImpl implements DownloadService {
	private static final Logger log = Logger.getLogger(DownloadServiceImpl.class);
	
	@Resource(name = "personDao")
	private PersonDao personDao;
	
	@Resource(name = "downloadFileDao")
    private DownloadFileDao downloadFileDao;
	
	@Resource(name = "XlsExportor")
    private XlsExportor XlsExportor;
	
	private ExecutorService threadPool;
	
	private List<Future> futureList = new ArrayList<Future>();
	
	public void createTestData(){
		Random random = new Random();
		Person person = null;
		for(int i = 0; i < 20001; i++){
			person = new Person();
			person.setAge(random.nextInt(100));
			person.setAddress("上海市南京东路" + i + "号");
			person.setCompany("上海市邮乐贸易有限公司");
			person.setCreateTime(new Date());
			person.setEmail("ez_winter31@hotmail.com");
			person.setMobile("13764095731");
			person.setName("钱六" + i);
			person.setTitle("驾驶员");
			this.personDao.save(person);
			log.info("saved" + i);
		}
	}
	
	public List<Person> findPerson(Person cond){
		List<Person> pList = this.personDao.findByExample(cond);
		return pList;
	}
	
	public void generateBigDataFile(){
	    if (this.threadPool == null){
	        this.threadPool = Executors.newFixedThreadPool(4);
	    }
        this.threadPool.submit(new Thread(){
            @Override
            public void run() {
                DownloadFile df = null;
                try{
                    FilePool filePool = new NutFilePool(PropConstants.getProperties("filePoolPath"));
                    
                    df = new DownloadFile();
                    df.setFileStatus(DownloadFile.FILE_STATUS_PENDING);
                    df.setStartTime(new Date());
                    File f = filePool.createFile(".csv");
                    Long fileId = filePool.getFileId(f);
                    df.setFileId(fileId);
                    downloadFileDao.save(df);
                    
                    String sql = "select * from t_person t";
                    XlsExportor.generateFileFromSql(sql, null, f);
                    
                    df.setFinishTime(new Date());
                    df.setFileStatus(DownloadFile.FILE_STATUS_SUCCESS);
                    downloadFileDao.merge(df);
                }catch(Throwable t){
                    t.printStackTrace();
                    df.setFileStatus(DownloadFile.FILE_STATUS_FAIL);
                    downloadFileDao.merge(df);
                }
            }
        });
	}
	
	public Map<String, ?> findDownloadFileList(DownloadFileDto dto, int page, int rows){
	    DetachedCriteria dc = DetachedCriteria.forClass(DownloadFile.class);
	    dc.addOrder(Order.desc("id"));
	    Map<String, ?> returnMap = this.downloadFileDao.findByCriteria(dc, page, rows);
	    return returnMap;
	}
}
