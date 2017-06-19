package api.io;

import java.io.RandomAccessFile;

public class AboutRandomAccessFile {
	public static void main(String[] args) throws Exception{
		/*
		 * 1,RandomAccessFile 支持众多文件访问方法
		 * 2,RandomAccessFile 支持随机访问方式.
		 * 3,RandomAccessFile 在随机(相对顺序而言)读写等长记录格式的文件(一条条的记录,如员工信息，学生成绩等)时有很大的优势.
		 * 4,RandomAccessFile 仅限于操作文件,不能访问其他io设备，如：网络，内存映像等．
		 * 5，
		 * 
		 */
		Employee e1 = new Employee("张",1000);
		Employee e2 = new Employee("lisi",2000);
		Employee e3 = new Employee("wangwu",30);
		System.out.println("e1.name:"+e1.name);
		System.out.println("e2.name:"+e2.name);
		System.out.println("e3.name:"+e3.name);
		System.out.println("e1.age:"+e1.age);
		System.out.println("e2.age:"+e2.age);
		System.out.println("e3.age:"+e3.age);
		// 写文件,byte 不支持中文.
		RandomAccessFile ra = new RandomAccessFile("2.txt", "rw");
//		ra.write(e1.name.getBytes);
		ra.writeChars(e1.name);
		ra.writeInt(e1.age);
//		ra.write(e2.name.getBytes());
		ra.writeChars(e2.name);
		ra.writeInt(e2.age);
//		ra.write(e3.name.getBytes());
		ra.writeChars(e3.name);
		ra.writeInt(e3.age);
		ra.close();
		// 分开   读文件  
//		byte[] buff = new byte[8];
		RandomAccessFile raf = new RandomAccessFile("2.txt","r");
		//读取第二个员工信息
		raf.skipBytes(12);
//		int len = raf.read(buff);
//		String strName= new String(buff,0,len);
		String strName=null;
		for (int i=0;i<Employee.LEN;i++){
			strName=strName+raf.readChar();
		}
		System.out.println("Name:"+strName.trim()+"  age:"+raf.readInt());
		
		// 读取第一个员工信息
		raf.seek(0);  // 文件开始处
//		len = raf.read(buff);
//		strName= new String(buff,0,len);
		for (int i=0;i<Employee.LEN;i++){
			strName=strName+raf.readChar();
		}
		System.out.println("Name:"+strName.trim()+"  age:"+raf.readInt());
		
		// 读取第三个员工信息.
		raf.skipBytes(12);
//		len = raf.read(buff);
//		strName= new String(buff,0,len);
		for (int i=0;i<Employee.LEN;i++){
			strName=strName+raf.readChar();
		}
		System.out.println("Name:"+strName.trim()+"  age:"+raf.readInt());
	}
}
class Employee{
	public String name=null;
	public int age=0;
	public static final int LEN=8;
    public Employee(String name,int age){
		if(name.length() > LEN){
			this.name = name.substring(0,8);
		}else{
			// 拼凑8个字符,\u0000 代表空字符
			while(name.length() < LEN){
				name+="\u0000";
			}
		}
		this.age=age;
		this.name=name;
	}
}
