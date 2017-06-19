/**
 * Project Name:MyJava  
 * File Name:PipedInputStream1.java  
 * Package Name:api.lang.thread.producer  
 * Date:Feb 16, 2017 3:28:21 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package api.lang.thread.producer;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * ClassName: PipedInputStream1 <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * date: Feb 16, 2017 3:28:21 PM <br/>  
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class PipedInputStream1 {
	final PipedInputStream pis = new PipedInputStream();
	final PipedOutputStream pos = new PipedOutputStream();
	{
		try {
			pis.connect(pos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Producer implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					int b = (int) (Math.random() * 255);
					System.out.println("Producer: a byte, the value is " + b);
					pos.write(b);
					pos.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					pos.close();
					pis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}

	class Consumer implements Runnable {

		@Override
		public void run() {
			try {
				while (true) {
					int b = pis.read();
					System.out.println("Consumer: a byte, the value is "
							+ String.valueOf(b));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					pos.close();
					pis.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		PipedInputStream1 hosee = new PipedInputStream1();
		new Thread(hosee.new Producer()).start();
		new Thread(hosee.new Consumer()).start();
	}

}
