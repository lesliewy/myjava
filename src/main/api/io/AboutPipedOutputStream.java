package api.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class AboutPipedOutputStream extends Thread{
	/*
	 * 
	 */
	private PipedOutputStream out = new PipedOutputStream();
	public PipedOutputStream getOutputStream(){
		return out;
	}
	public void run(){
		String strInfo = new String("Hello,receiver!");
		try{
			out.write(strInfo.getBytes());
			out.close();			
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public static void main(String[] args){
		AboutPipedInputStream receiver = new AboutPipedInputStream();
		AboutPipedOutputStream sender = new AboutPipedOutputStream();
		PipedOutputStream out = sender.getOutputStream();
		PipedInputStream in = receiver.getInputStream();
		try {
			out.connect(in);
			receiver.start();
			sender.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
