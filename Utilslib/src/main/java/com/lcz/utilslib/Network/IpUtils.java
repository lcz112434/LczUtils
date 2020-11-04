package com.lcz.utilslib.Network;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.lcz.utilslib.BaseApp;

/**
 * 获取ip地址
 *
 *
 */

public class IpUtils {
    /**
     * 当前ip地址
     *
     * @return String ip
     */
    private static String getIpAddress() {
        try {
            WifiManager wifiManager = (WifiManager) BaseApp.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            assert wifiManager != null;
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            return defaultIp((ipAddress & 0xFF) + "." +
                    ((ipAddress >> 8) & 0xFF) + "." +
                    ((ipAddress >> 16) & 0xFF) + "." +
                    (ipAddress >> 24 & 0xFF));
        } catch (Exception ignored) {

        }
        return defaultIp("");
    }

    private static String defaultIp(String ip) {
        if ("".equals(ip) || ip == null) {
            return "0.0.0.0";
        } else {
            return ip;
        }
    }
}
