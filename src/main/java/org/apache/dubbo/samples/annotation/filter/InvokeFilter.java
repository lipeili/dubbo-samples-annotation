package org.apache.dubbo.samples.annotation.filter;


import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Activate(group = {CommonConstants.CONSUMER})
public class InvokeFilter implements Filter {

    public static Map<String,List<Long>> costContainer = new HashMap<>();

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long startMills = System.currentTimeMillis();
        try {
            return invoker.invoke(invocation);

        } finally {
            long cost = System.currentTimeMillis() - startMills;
            synchronized (costContainer) {
                List<Long> containList = costContainer.get(invocation.getMethodName());
                if (containList == null) {
                    costContainer.put(invocation.getMethodName(), new LinkedList() {{add(cost);}});
                } else {
                    containList.add(cost);
                    costContainer.put(invocation.getMethodName(), containList);
                }
            }
        }


    }
}
