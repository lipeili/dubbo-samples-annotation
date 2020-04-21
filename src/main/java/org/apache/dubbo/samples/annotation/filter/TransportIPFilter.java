package org.apache.dubbo.samples.annotation.filter;


import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.samples.annotation.Utils.IpAddressHolderUtil;

@Activate(group = {CommonConstants.CONSUMER})
public class TransportIPFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getContext().setAttachment("ip", IpAddressHolderUtil.getIp());
        return invoker.invoke(invocation);
    }
}
