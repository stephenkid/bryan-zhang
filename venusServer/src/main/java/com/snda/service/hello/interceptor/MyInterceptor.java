package com.snda.service.hello.interceptor;

import com.snda.service.framework.EndpointInvocation;
import com.snda.service.framework.interceptor.AbstractInterceptor;

public class MyInterceptor  extends AbstractInterceptor{

    private String name;
    private String first;
    public Object intercept(EndpointInvocation invocation) {
        return invocation.invoke();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFirst() {
        return first;
    }
    public void setFirst(String first) {
        this.first = first;
    }

}
