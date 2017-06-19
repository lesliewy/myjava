package concept;
//import wy.Test1; 


//public class TestException
//{
//	public static void main(String [] args)
//	{
//		try
//		{
//			int reslut = new Test().devide( 3, 0 );
//			System.out.println("the result is" + reslut );
//		} 
//		catch(Exception e)
//		{
//			System.out.println(e.getMessage());
//		}
//		System.out.println("program is running here ,that is normal !");
//	}
//}
/*
 * 1, 没有try catch时,出现异常时程序直接退出了.
 * 2, devide 方法后面最好添加throws Exception，这样调用该方法没有try catch语句的话,编译就会报错.
 * 3, main()也可以throws Exception,向上级抛出异常,这样即使存在devide这样包含throws Exception的方法，编译也不会报错.
 */
class Test
{
	public int devide(int x, int y) throws Exception
	{
		int result = x/y;
		return x/y;
	}
}
public class TestException
{
	public static void main(String [] args) throws Exception
	{
		try
		{
			int result = new Test().devide( 3, 0 );
			//int result = new Test().devide( 3, -1 );
			//int result = new Test().devide( 3, 1 );
			System.out.println("the result is " + result );
		}
//		catch(DevideByMinusException e)
//		{
//			System.out.println("program is running into"+
//			 "DevideByMinusException");
//			System.out.println(e.getMessage());
//			System.out.println("the devisor is " +
//			 e. getDevisor());
//		}
		catch(ArithmeticException e)
		{
			System.out.println("program is running into"+ 
			  "ArithmeticException");
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("program is running into"+
			  "other unknowned Exception");
			System.out.println(e.getMessage());
		}
		System.out.println("program is running here ,that is normal !");
	}
}