package org.sky.webcrawler.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(getLocalAddress());
	}
	
	
	/**
     * Get host IP address
     *
     * @return IP Address
     */
    public static InetAddress getLocalAddress() {
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    return addresses.nextElement();
                }
            }
        } catch (SocketException e) {
            System.out.println("Error when getting host ip address: <e.getMessage()>.");
        }
        return null;
    }

}
