package javatest;

import org.apache.commons.net.util.SubnetUtils;

/**
 * @author JC
 * @create 2023/12/25
 */
public class CIDRRangeChecker {

    public static void main(String[] args) {
        String ipAddress = "192.168.1.10";
        String cidrNotation = "192.168.1.10/31";

        boolean isInRange = isIPAddressInRange(ipAddress, cidrNotation);
        if (isInRange) {
            System.out.println(ipAddress + " is in the specified range.");
        } else {
            System.out.println(ipAddress + " is NOT in the specified range.");
        }
    }

    public static boolean isIPAddressInRange(String ipAddress, String cidrNotation) {
        SubnetUtils subnetUtils = new SubnetUtils(cidrNotation);
        SubnetUtils.SubnetInfo subnetInfo = subnetUtils.getInfo();

        return subnetInfo.isInRange(ipAddress);
    }
}