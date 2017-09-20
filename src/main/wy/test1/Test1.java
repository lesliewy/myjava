package wy.test1;

class Test1{
	public static void main(String [] args){
		System.out.println("aaaaaaaa");
		System.out.println((long)(Math.random()*300));
		
		System.out.println(3 << 3);  // 3 * 2^3
		System.out.println(25 >> 3); // 25 / (2^3) 取整数;
		System.out.println(-25 >> 3); // 
		System.out.println(-1 >>> 3); // 无符号右移
		System.out.println(Integer.toBinaryString(536870911));
	}
	protected void print1(){
		System.out.println("this is wy.test1.Test1().print()");
	}
}
