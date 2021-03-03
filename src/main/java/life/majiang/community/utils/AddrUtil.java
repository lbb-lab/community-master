package life.majiang.community.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author zlt
 * @date 2019/9/8
 */
@Slf4j
public class AddrUtil {
    private final static String UNKNOWN_STR = "unknown";
    /**
     * 获取客户端IP地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (isEmptyIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            if (isEmptyIP(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (isEmptyIP(ip)) {
                    ip = request.getHeader("HTTP_CLIENT_IP");
                    if (isEmptyIP(ip)) {
                        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                        if (isEmptyIP(ip)) {
                            ip = request.getRemoteAddr();
                            if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
                                // 根据网卡取本机配置的IP
                                ip = getLocalAddr();
                            }
                        }
                    }
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = ips[index];
                if (!isEmptyIP(ip)) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    private static boolean isEmptyIP(String ip) {
        if (StrUtil.isEmpty(ip) || UNKNOWN_STR.equalsIgnoreCase(ip)) {
            return true;
        }
        return false;
    }

    public static boolean isIp(String ip) {
        if (StrUtil.isEmpty(ip)) {
            return false;
        }
        return ip.matches("^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$");
    }

    /**
     * 获取本机的IP地址
     */
    public static String getLocalAddr() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("InetAddress.getLocalHost()-error", e);
        }
        return "";
    }

    /**il
     * 获取目前本机网上上对应可能的服务器IP(本机\测试\线上环境)
     * @return
     */
    public static String getServerIp() {
        List<String> possibleIps = getPossibleLocalIps();

        if(possibleIps.size() == 0) {
            return getLocalAddr();
        }

        for(String ip : possibleIps) {
            if(ip != null && ip.startsWith("172.")) {
                return ip;
            }
        }

        for(String ip : possibleIps) {
            if(ip != null && ip.startsWith("10.10.")) {
                return ip;
            }
        }

        for(String ip : possibleIps) {
            if(ip != null && ip.startsWith("192.168.")) {
                return ip;
            }
        }

        return possibleIps.get(0);
    }

    /**
     * 获取所有本机网卡上的IP
     * @return
     */
    private static List<String> getPossibleLocalIps() {
        List<String> ips = new ArrayList<>();
        try {
            //get all network interface
            Enumeration<NetworkInterface> allNetworkInterfaces =
                    NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface = null;

            //check if there are more than one network interface
            while (allNetworkInterfaces.hasMoreElements()) {
                //get next network interface
                networkInterface = allNetworkInterfaces.nextElement();
                //output interface's name
                log.debug("network interface: " + networkInterface.getDisplayName());

                //get all ip address that bound to this network interface
                Enumeration<InetAddress> allInetAddress =
                        networkInterface.getInetAddresses();

                InetAddress ipAddress = null;

                //check if there are more than one ip addresses
                //band to one network interface
                while (allInetAddress.hasMoreElements()) {
                    //get next ip address
                    ipAddress = allInetAddress.nextElement();
                    if (ipAddress instanceof Inet4Address) {
                        log.debug("ip address: " + ipAddress.getHostAddress());
                        ips.add(ipAddress.getHostAddress());
                    }
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }

        return ips;
    }
}
