package org.ares.dto;

import java.io.Serializable;
import java.util.Map;

public class TestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Long age;
    private Map<String, Object> dateMap;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }
    public Map<String, Object> getDateMap() {
        return dateMap;
    }
    public void setDateMap(Map<String, Object> dateMap) {
        this.dateMap = dateMap;
    }
}
