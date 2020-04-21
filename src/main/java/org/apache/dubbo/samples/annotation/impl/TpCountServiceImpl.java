package org.apache.dubbo.samples.annotation.impl;


import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.samples.annotation.api.TpCountService;

@Service
public class TpCountServiceImpl implements TpCountService {

    @Override
    public String sayHello1(String name) {
        sleep();

        System.out.println("provider received invoke of sayHello1: " + RpcContext.getContext().getAttachment("ip"));
        return name;
    }

    @Override
    public String sayHello2(String name) {
        sleep();

        System.out.println("provider received invoke of sayHello2: " + RpcContext.getContext().getAttachment("ip"));
        return name;
    }

    @Override
    public String sayHello3(String name) {
        sleep();

        System.out.println("provider received invoke of sayHello3: " + RpcContext.getContext().getAttachment("ip"));
        return name;
    }

    void sleep () {
        try {
            Thread.sleep((long) (Math.random()*100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
