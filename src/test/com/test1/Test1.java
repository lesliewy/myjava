package com.test1;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test1 {

	@Test
	public void test1() {
		String host = "127.1.1.2a";
		try {
			InetAddress.getByName(host);
			System.out.println("success...");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
