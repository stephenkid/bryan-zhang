package org.poseidon.service.impl;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.poseidon.dao.PersonDao;
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
	
	
	public void createTestData() throws Exception{
		Random random = new Random();
		Person person = null;
		for(int i = 0; i < 2000; i++){
			person = new Person();
			person.setAge(random.nextInt(100));
			person.setAddress("上海市民星路" + i + "号");
			person.setCompany("上海市邮乐贸易有限公司");
			person.setCreateTime(new Date());
			person.setEmail("ez_winter31@hotmail.com");
			person.setMobile("13764095731");
			person.setName("李四" + i);
			person.setTitle("工程师");
			this.personDao.save(person);
			log.info("saved" + i);
		}
	}
}
