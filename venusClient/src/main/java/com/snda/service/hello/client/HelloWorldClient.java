package com.snda.service.hello.client;

import com.snda.service.client.ServiceFactory;
import com.snda.service.client.VenusServiceFactory;
import com.snda.service.hello.api.Hello;
import com.snda.service.hello.api.HelloService;

public class HelloWorldClient {
    private ServiceFactory serviceFactory;     

    public HelloWorldClient() {
        // 创建ServiceFactory
        VenusServiceFactory serviceFactory = new VenusServiceFactory();
        // 设置service配置文件
        serviceFactory.setConfigFiles(new String[] { "classpath:VenusClient.xml" });
        try { // 初始化
            serviceFactory.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.serviceFactory = serviceFactory;
    }    

    public void invokService() {
        // 获得HelloService
        HelloService helloService = serviceFactory.getService(HelloService.class);
        Hello hello = helloService.getHello("Jack");
        System.out.println("return hello=" + hello.getName());
    }  
   
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            HelloWorldClient client = new HelloWorldClient();
            client.invokService();
        } 
        System.exit(0);
    }
}
