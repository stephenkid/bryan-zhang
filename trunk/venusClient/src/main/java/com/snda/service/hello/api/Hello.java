package com.snda.service.hello.api;

import java.io.Serializable;
import java.util.Map;

public class Hello implements Serializable{

    /**
    *
    */
    private static final long serialVersionUID = 5422334283701487440L;

    private String name;
    
    private String greeting;
    
    private Map<String,String> map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Map<String,String> getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
