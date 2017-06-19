package concept;
class Student1 // (extends Object). 默认都是继承自Object.
{
	String name;
    int age;
    // 重载equals方法.
    public boolean equals(Object obj)
   {
    Student1 st=null;
	if(obj instanceof Student1)
		st = (Student1)obj;
	else
		return false;
  	if(st.name==this.name && st.age==this.age)
  	   return true;
		else
  	   return false;
  }
  
	public static void main(String[] args)
	{  
		Student1 p=new Student1();
		Student1 q=new Student1();
		p.name="xyz";
		p.age=13;
		q.name="xyz";
		q.age=13;
		if(p.equals(q))
		  System.out.println("p 与 q 相等");
		else
		  System.out.println("p 与 q 不等");
	}
}
