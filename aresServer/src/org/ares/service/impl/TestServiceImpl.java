package org.ares.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.ares.dto.TestDto;
import org.ares.dto.TestDto2;
import org.ares.service.TestService;

public class TestServiceImpl implements TestService {

    public TestDto test1() {
        TestDto dto = new TestDto();
        dto.setName("张三");
        dto.setAge(23L);
        Map<String, Object> dateMap = new HashMap<String, Object>();
        dateMap.put("1", new Date());
        dto.setDateMap(dateMap);
        
        TestDto2 dto2 = new TestDto2();
        dto2.setT2("222");
        dto.setDto2(dto2);
        return dto;
    }

}
