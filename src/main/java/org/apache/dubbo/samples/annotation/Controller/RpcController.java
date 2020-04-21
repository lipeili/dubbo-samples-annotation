package org.apache.dubbo.samples.annotation.Controller;


import org.apache.dubbo.samples.annotation.Utils.IpAddressHolderUtil;
import org.apache.dubbo.samples.annotation.Utils.IpAddressUtil;
import org.apache.dubbo.samples.annotation.action.TpCountAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RpcController {

    @Autowired
    private TpCountAction tpCountAction;

    /**
     * http://localhost:8080/ip/record
     * @param request
     * @return
     */
    @RequestMapping("/ip/record")
    public String record (HttpServletRequest request) {
        IpAddressHolderUtil.setIp(IpAddressUtil.getIPAddress(request));
        tpCountAction.ipHello();
        return "OK";
    }

}
