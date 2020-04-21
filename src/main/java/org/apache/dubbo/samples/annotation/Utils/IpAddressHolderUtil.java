package org.apache.dubbo.samples.annotation.Utils;


public class IpAddressHolderUtil extends ThreadLocal{

    private IpAddressHolderUtil () {}

    private final static ThreadLocal<String> IP_ADDRESS = new ThreadLocal<>();

    public static synchronized void setIp(String ip) {
        IP_ADDRESS.set(ip);
    }

    public static synchronized String getIp() {
        return IP_ADDRESS.get();
    }

    public static synchronized void removeIp() {
        IP_ADDRESS.remove();
    }

}
