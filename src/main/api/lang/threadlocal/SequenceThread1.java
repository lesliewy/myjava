
      
/*
 * FileName: SequenceThread1.java
 * Author:   leslie
 * Date:     Jul 10, 2016 3:53:05 PM
 * Description: //模块目的、功能描述      
 */
   
package api.lang.threadlocal;


/**
 *
 * @author leslie
 */

public class SequenceThread1 extends Thread{

	private Sequence sequence;
	
	public SequenceThread1(Sequence sequence){
		this.sequence = sequence;
	}
	
	@Override
	public void run(){
		for (int i = 0; i < 30; i++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " -> " + sequence.getNumber());
		}
	}
}

   