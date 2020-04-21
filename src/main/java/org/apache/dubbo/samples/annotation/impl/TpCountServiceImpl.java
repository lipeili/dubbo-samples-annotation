package org.apache.dubbo.samples.annotation.impl;


import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.samples.annotation.api.TpCountService;

@Service
public class TpCountServiceImpl implements TpCountService {

    @Override
    public String sayHello1(String name) {
        sleep();
        System.out.println("provider received invoke of sayHello1: " + name);
        return name;
    }

    @Override
    public String sayHello2(String name) {
        sleep();

        System.out.println("provider received invoke of sayHello1: " + name);
        return name;
    }

    @Override
    public String sayHello3(String name) {
        sleep();

        System.out.println("provider received invoke of sayHello1: " + name);
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
