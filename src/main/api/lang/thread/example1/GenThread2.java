package api.lang.thread.example1;

public class GenThread2 implements Runnable {
	public void run(){
		//1 
		System.out.println("My Name is "+Thread.currentThread().getName());
		//2 
//		int sleeptime = 3*1000;
//		try{
//			Thread.sleep(sleeptime);
//			System.out.println("thread "+Thread.currentThread().getName()+" sleep time:"+sleeptime);
//		}catch (InterruptedException ie){
//			ie.printStackTrace();
//		}
	}
}
   // 3 同步
   class BlankSaving{
	   private static int money=10000;
	   public void add(int i){
		   money += i;
		   System.out.println("Husband 存入  "+i);
	   }
	   public void get(int i){
		   money -= i;
		   if (money < 0){
			   System.out.println("余额不足!");
		   }
		   System.out.println("Wife 取出" + i);
	   }
	   public int showMoney(){
		   return money;
	   }
   }
   class Operator implements Runnable{
	   String name;
	   BlankSaving bs;
	   public Operator(String name,BlankSaving bs){
		   this.name = name;
		   this.bs = bs;
	   }
	   // 必须有static,下面的run 不可以加static,有语法错误.
//	   public static void oper(String name,BlankSaving bs)
	   synchronized public static void oper(String name,BlankSaving bs)
	   {
		   if (name.equals("husband")){
			   try{
				   for (int i=0;i<10;i++){
					   Thread.sleep((long) (Math.random()*300));
					   bs.add(1000);
				   }
			   }catch (InterruptedException ie){
				   ie.printStackTrace();
			   }
		   }else{
			   try{
				   for (int i=0;i<10;i++){
					   Thread.sleep((long) (Math.random()*300));    // Math.random()返回0.0 - 1.0 之间的double,所以后面必须加(),否则总是0.
					   bs.get(1000);
				   }
			   }catch (InterruptedException ie){
				   ie.printStackTrace();
			   }
		   }
	   }
	    public void run() {
		   oper(name,bs);
	   }
	   
}