package com.myNutz.dao;

import javax.sql.DataSource;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.myNutz.pojo.Person;

public class Test {

	public static void main(String[] args) {
		Ioc ioc = new NutIoc(new JsonLoader("dsJson.js"));
		DataSource ds = ioc.get(DataSource.class, "dataSource");
		Dao dao = new NutDao(ds);
		
		Person p = new Person();
		p.setName("李四");
		p.setAge(45);
		p.setEmail("123@163.com");
		p.setIsValid(true);
		dao.insert(p);
	}

}
