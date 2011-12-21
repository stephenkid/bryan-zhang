package org.ares.dto;

import java.io.Serializable;
import java.util.Map;

public class TestDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String name;
    private Map<String, Object> map;
    private Long age;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Map<String, Object> getMap() {
        return map;
    }
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
    public Long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }
}
