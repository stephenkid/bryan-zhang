package org.neptune.learning.hessianInvoke;

import org.ares.dto.TestDto;
import org.ares.service.TestService;

import com.caucho.hessian.client.HessianProxyFactory;


public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        HessianProxyFactory proxyFactory = new HessianProxyFactory();
        TestService service = (TestService)proxyFactory.create(TestService.class, "http://127.0.0.1:8080/aresServer/remote/testService");
        
        TestDto dto = service.test1();
        
        System.out.println(dto.getName());
        System.out.println(dto.getAge());
        System.out.println(dto.getDateMap());
    }

}
