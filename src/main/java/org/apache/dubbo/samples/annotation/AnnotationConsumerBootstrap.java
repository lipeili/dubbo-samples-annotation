/*
 *
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.apache.dubbo.samples.annotation;

import org.apache.dubbo.samples.annotation.action.TpCountAction;
import org.apache.dubbo.samples.annotation.config.ConsumerConfiguration;
import org.apache.dubbo.samples.annotation.filter.InvokeFilter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;

public class AnnotationConsumerBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        final TpCountAction tpCountAction = (TpCountAction) context.getBean("tpCountAction");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(3000);
        executor.initialize();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (InvokeFilter.costContainer) {
                    for (Map.Entry<String, List<Long>> entry : InvokeFilter.costContainer.entrySet()) {

                        Collections.sort(entry.getValue());

                        long size = entry.getValue().size();
                        System.out.print("method:" + entry.getKey() + "   ");
                        System.out.print("TP90:" + entry.getValue().get((int) (size * 0.9)) + " ms" + "   ");
                        System.out.println("TP99:" + entry.getValue().get((int) (size * 0.99))  + " ms");
                    }

                    System.out.println();
                    System.out.println();
                }



            }
        }, 1000, 5000);

        while (true) {
            executor.execute(() -> tpCountAction.doSayHello());

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


}
