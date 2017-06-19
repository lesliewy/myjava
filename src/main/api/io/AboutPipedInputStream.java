package api.io;

import java.io.PipedInputStream;

public class AboutPipedInputStream extends Thread{
	/*PipedInputStream 和 PipedOutputStream 用于在应用程序中创建管道通信，线程间的通信.
	 * 1, extends Thread 表明需要重写 run();
	 * 
	 */
	private PipedInputStream in = new PipedInputStream();
	public PipedInputStream getInputStream(){
		return in;
	}
	public void run(){
		byte[] buff = new byte[1024];
		int len = 0;
		try{
			len = in.read(buff);
			System.out.println("the following message:"+ new String(buff,0,len));
			in.close();			
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
