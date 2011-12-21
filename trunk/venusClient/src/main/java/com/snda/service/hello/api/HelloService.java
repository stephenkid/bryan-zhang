package com.snda.service.hello.api;

import com.snda.service.annotations.Endpoint;
import com.snda.service.annotations.Param;
import com.snda.service.annotations.Service;
import com.snda.service.notify.InvocationListener;

@Service(name="HelloService",version=1)
public interface HelloService {

    /**
     *
     **无返回结果的服务调用，支持回调方式，该服务在通讯层面上为异步调用 
     *@param name 
     *@param invocationListener 客户端的回调接口
     */
    
    @Endpoint(name="sayHelloWithCallbak")    
    public abstract void sayHello(@Param(name="name") String name, @Param(name="callback") InvocationListener<Hello> invocationListener); 
    
    /**
     * 无返回结果的服务调用，支持同步或者异步调用      
     * @param name      
     */
    @Endpoint(name="sayHello",async=true) 
    public abstract void sayHello(@Param(name="name") String name);
    
    /**
     * 有返回结果的服务调用，该接口只能支持同步调用
    * @param name
    * @return
     */
    @Endpoint(name="getHello")
    public abstract Hello getHello(@Param(name="name") String name); 
}
