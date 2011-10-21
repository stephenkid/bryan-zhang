package org.poseidon.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.nutz.Nutz;
import org.nutz.filepool.FilePool;
import org.nutz.filepool.NutFilePool;
import org.poseidon.component.dataExport.XlsExportor;
import org.poseidon.dao.DownloadFileDao;
import org.poseidon.dao.PersonDao;
import org.poseidon.pojo.DownloadFile;
import org.poseidon.pojo.Person;
import org.poseidon.service.DownloadService;
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
		List<Person> pList = personDao.findByExample(cond);
		return pList;
	}
	
	public void generateBigDataFile(){
	    new Thread(){
            @Override
            public void run() {
                DownloadFile df = new DownloadFile();
                df.setFileStatus(DownloadFile.FILE_STATUS_PENDING);
                df.setStartTime(new Date());
                downloadFileDao.save(df);
                
                String sql = "select * from t_person t";
                String path = "d:/bigData.csv";
                XlsExportor.generateFileFromSql(sql, null, path);
                
                df.setFinishTime(new Date());
                df.setFileStatus(DownloadFile.FILE_STATUS_SUCCESS);
                downloadFileDao.merge(df);
            }
	    }.start();
	}
}
