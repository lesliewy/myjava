package api.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

public class AboutObjectInputStream {
	/*
	 * 1,ObjectInputStream 和 ObjectOutputStream 这两个包装类,用于从底层输入流中读取对象类型的数据和将对象类型的数据写入到底层输入流.
	 * 2,ObjectInputStream 和 ObjectOutputStream 所读写的对象必须实现Serializable接口.
	 *   对象中的transient 和 static 类型的成员变量不会被读取和写入.
	 * 
	 */
	
	@Test
	public void test1() throws IOException, ClassNotFoundException{

            Student stu1 = new Student(19,"zhangsan",25,"physics");
            Student stu2 = new Student(20,"lisi",26,"chemitry");
            FileOutputStream fos = new FileOutputStream("6.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(stu1);
            oos.writeObject(stu2);
            oos.close();           // 同时会关闭底层的流.
            
            
            FileInputStream fis = new FileInputStream("6.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student newStudent1 = (Student)ois.readObject();
            Student newStudent2 = (Student)ois.readObject();
            ois.close();
            
            System.out.println("id:"+newStudent1.id);
            System.out.println("name:"+newStudent1.name);
            System.out.println("age:"+newStudent1.age);
            System.out.println("department:"+newStudent1.department);
            System.out.println("id:"+newStudent2.id);
            System.out.println("name:"+newStudent2.name);
            System.out.println("age:"+newStudent2.age);
            System.out.println("department:"+newStudent2.department);
    
	}
}
// 可序列化的类.
class Student implements Serializable{
	int id;
	String name;
	int age;
	String department;
	public Student(int id, String name,int age,String department){
		this.id = id;
		this.name=name;
		this.age=age;
		this.department=department;
	}
}
