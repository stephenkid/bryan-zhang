package org.poseidon.main;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.poseidon.mapper.PersonMapper;
import org.poseidon.pojo.Person;

public class MainTest {
    public static void main(String args[]) throws Exception {
        //读取数据源配置
        Reader reader = Resources.getResourceAsReader("dataResource.xml");
        //build SqlSessionFactory实例
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        //获取SqlSession
        SqlSession session = sqlMapper.openSession();
        //获取Mapper
        PersonMapper personMapper = session.getMapper(PersonMapper.class);
        //执行sql
        Person person = personMapper.getPerson(201201L);
        if (person != null) {
            System.out.println(person);
        }
    }
}
