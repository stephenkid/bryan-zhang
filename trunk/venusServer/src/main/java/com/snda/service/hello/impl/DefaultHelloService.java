package com.snda.service.hello.impl;

import java.util.HashMap;
import java.util.Map;

import com.snda.service.hello.api.Hello;
import com.snda.service.hello.api.HelloService;
import com.snda.service.notify.InvocationListener;

public class DefaultHelloService implements HelloService {
    private String greeting;

    public void init(){
        System.out.println("start hello Service!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public Hello getHello(String name) {
        Hello hello = new Hello();
        hello.setName(name);
        hello.setGreeting(greeting);
        Map<String, Object> map = new HashMap<String, Object>();
        hello.setMap(map);
        map.put("1", 1);
        return hello;
    }

    public void sayHello(String name, InvocationListener<Hello> invocationListener) {
        Hello hello = new Hello();
        hello.setName(name);
        hello.setGreeting(greeting);
        Map<String, Object> map = new HashMap<String, Object>();
        hello.setMap(map);
        map.put("1", 1);
        if (invocationListener != null) {
            invocationListener.callback(hello);
        }
    }

    public void sayHello(String name) {
        System.out.println("remote call hello..." + name);
    }
}

