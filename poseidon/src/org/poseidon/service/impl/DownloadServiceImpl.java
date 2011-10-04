package org.poseidon.service.impl;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.poseidon.dao.PersonDao;
import org.poseidon.pojo.Person;
import org.poseidon.service.DownloadService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
@Component("downloadService")
public class DownloadServiceImpl implements DownloadService {
	@Resource(name = "personDao")
	private PersonDao personDao;
	
	
	public void createTestData() {
		Random random = new Random();
		Person person = null;
		for(int i = 0; i < 2; i++){
			person = new Person();
			person.setAge(random.nextInt(100));
			person.setAddress("上海市民星路" + i + "号");
			person.setCompany("上海市邮乐贸易有限公司");
			person.setCreateTime(new Date());
			person.setEmail("ez_winter31@hotmail.com");
			person.setMobile("13764095731");
			person.setName("张三" + i);
			person.setTitle("工程师");
			this.personDao.save(person);
			if (i==1){
				String a = null;
				System.out.println(a.equals("2"));
			}
		}
	}
}
