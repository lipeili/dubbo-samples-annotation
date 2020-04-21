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

package org.apache.dubbo.samples.annotation.action;

import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.samples.annotation.AnnotationConstants;
import org.apache.dubbo.samples.annotation.api.GreetingService;
import org.apache.dubbo.samples.annotation.api.HelloService;
import org.apache.dubbo.samples.annotation.api.TpCountService;
import org.springframework.stereotype.Component;

@Component("tpCountAction")
public class TpCountAction {

    @Reference(interfaceClass = TpCountService.class,
            filter = "invokeFilter"
            /*
    , version = AnnotationConstants.VERSION ,
            methods = {
                    @Method(
                            name = "sayHello",
                            oninvoke = "notify.oninvoke",
                            onreturn = "notify.onreturn",
                            onthrow = "notify.onthrow")
            }
             */
    )
    private TpCountService tpCountService;

//    @Reference(interfaceClass = GreetingService.class,
//            version = AnnotationConstants.VERSION,
//            timeout = 1000,
//            methods = {@Method(name = "greeting", timeout = 3000, retries = 1)})
//    private GreetingService greetingService;

    public void doSayHello() {
//        System.out.println("hello : " + tpCountService.sayHello1("sayHello1"));
//        System.out.println("hello : " + tpCountService.sayHello1("sayHello2"));
//        System.out.println("hello : " + tpCountService.sayHello1("sayHello3"));

        tpCountService.sayHello1("sayHello1");
        tpCountService.sayHello2("sayHello2");
        tpCountService.sayHello3("sayHello3");
    }

}
