package jvm.example1;

import java.util.ArrayList;

/*
 * javap -v -p -s examples/generic/TestGeneric1.class: 基本输出全部了, p 是输出private, s是Signature
 * 
 * 下面都是使用java 1.6的进行测试的.
 * javap -c Test1
 * Compiled from "Test1.java"
public class jvm.example1.Test1 extends java.lang.Object{                 //显然就算没写，默认都继承了Object.
public jvm.example1.Test1();
  Code:
   0:   aload_0                  // 加载local variable array中的第0个reference 到 operand stack中.  这个第0个是this,当前类实例. 每次要对某对象中的变量操作时，都先load该对象.
   1:   invokespecial   #8; //Method java/lang/Object."<init>":()V       // 调用 实例初始化方法, 觉得这2句是每个类必须的.
   4:   return

}
 */
public class Test1 {

}

/*
 * Compiled from "Test1.java"
class jvm.example1.Test2 extends java.lang.Object{
java.lang.String a;    // 比上面多了一个这个,觉得这是local variable array

jvm.example1.Test2();
  Code:
   0:   aload_0
   1:   invokespecial   #10; //Method java/lang/Object."<init>":()V
   4:   return

}
 */
class Test2 {
    String a;
}

/*
 * Compiled from "Test1.java"
class jvm.example1.Test3 extends java.lang.Object{
java.lang.String a;

jvm.example1.Test3();
  Code:
   0:   aload_0
   1:   invokespecial   #10; //Method java/lang/Object."<init>":()V
   4:   aload_0                                     // 从local variable array取index是0的变量a
   5:   ldc     #12; //String 123                   // 从当前类的 runtime constant pool 中取出常量index 是12的常量123
   7:   putfield        #14; //Field a:Ljava/lang/String; // 
   10:  return

}
 */
class Test3 {
    String a = "123";
}

/*
 * Compiled from "Test1.java"
class jvm.example1.Test4 extends java.lang.Object{
java.lang.String a;          // local variable array 中有2个.

java.lang.String b;

jvm.example1.Test4();
  Code:
   0:   aload_0
   1:   invokespecial   #11; //Method java/lang/Object."<init>":()V
   4:   aload_0
   5:   ldc     #13; //String 123456         // 显然，如果编译时能确定的“+”左右的值，jvm会将其连起来. 这样更高效.
   7:   putfield        #15; //Field a:Ljava/lang/String;  // a 赋值为123456, a 和  123456 都要出栈.  将a放在local variable array的后面.
   10:  aload_0
   11:  new     #17; //class java/lang/StringBuilder   // 会new 一个StringBuilder, index 是17, 字符串是常量，对字符串的操作使用StringBuilder
   14:  dup                                            // 再duplicate 一个StringBuilder
   15:  aload_0
   16:  getfield        #15; //Field a:Ljava/lang/String;   // 将a放入栈顶.
   19:  invokestatic    #19; //Method java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;   // 执行 index 19 的static 方法.
   22:  invokespecial   #25; //Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V  // new 的StringBuilder此时真正初始化完成.
   25:  aload_0
   26:  getfield        #15; //Field a:Ljava/lang/String;
   29:  invokevirtual   #28; //Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
   32:  invokevirtual   #32; //Method java/lang/StringBuilder.toString:()Ljava/lang/String;
   35:  putfield        #36; //Field b:Ljava/lang/String;
   38:  return

}
 */
class Test4 {
    String a = "123" + "456";
    String b = a + a;
}

/*
 * Compiled from "Test1.java"
class jvm.example1.Test5 extends java.lang.Object{
java.lang.String a;

java.lang.String b;

java.lang.String c;

jvm.example1.Test5();
  Code:
   0:   aload_0
   1:   invokespecial   #12; //Method java/lang/Object."<init>":()V
   4:   aload_0
   5:   ldc     #14; //String 123
   7:   putfield        #16; //Field a:Ljava/lang/String;
   10:  aload_0
   11:  ldc     #18; //String 456
   13:  putfield        #20; //Field b:Ljava/lang/String;
   16:  aload_0
   17:  new     #22; //class java/lang/StringBuilder
   20:  dup
   21:  aload_0
   22:  getfield        #16; //Field a:Ljava/lang/String;
   25:  invokestatic    #24; //Method java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
   28:  invokespecial   #30; //Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
   31:  aload_0
   32:  getfield        #20; //Field b:Ljava/lang/String;
   35:  invokevirtual   #33; //Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
   38:  invokevirtual   #37; //Method java/lang/StringBuilder.toString:()Ljava/lang/String;
   41:  putfield        #41; //Field c:Ljava/lang/String;
   44:  return

}
 */
class Test5 {
    String a = "123";
    String b = "456";
    String c = a + b;
}

/*
 * Compiled from "Test1.java"
class jvm.example1.Test6 extends java.lang.Object{
int a;

int b;

int c;

jvm.example1.Test6();
  Code:
   0:   aload_0
   1:   invokespecial   #12; //Method java/lang/Object."<init>":()V
   4:   aload_0
   5:   iconst_1                            // [-1, 5] 直接使用 iconst_[i], 不需要操作数.
   6:   putfield        #14; //Field a:I
   9:   aload_0
   10:  iconst_2                            
   11:  putfield        #16; //Field b:I
   14:  aload_0
   15:  aload_0
   16:  getfield        #14; //Field a:I     // getfield 与 putfield 不管是int 还是 String
   19:  aload_0
   20:  getfield        #16; //Field b:I
   23:  iadd                                 // iadd 用来计算 int 加法.  原来的2个操作数出栈, result 入栈.
   24:  putfield        #18; //Field c:I
   27:  return

}
 */
class Test6 {
    int a = 1;
    int b = 2;
    int c = a + b;
}

/*
 * Compiled from "Test1.java"
class jvm.example1.Test10 extends java.lang.Object{
jvm.example1.Test10();
  Code:
   0:   aload_0
   1:   invokespecial   #8; //Method java/lang/Object."<init>":()V
   4:   return

  void m1();
    flags: 
    Code:
      stack=0, locals=1, args_size=1                  // 每个方法第一个参数都是this, 除了 main方法。
         0: return        
      LineNumberTable:
        line 191: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0       1     0  this   Ljvm/example1/Test10;

  void m2();
    flags: 
    Code:
      stack=0, locals=1, args_size=1
         0: return        
      LineNumberTable:
        line 195: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0       1     0  this   Ljvm/example1/Test10;


}
 */
class Test10 {
    void m1() {

    }

    void m2() {

    }
}

/*
 * Compiled from "Test1.java"
class jvm.example1.Test11 extends java.lang.Object{
int a;

java.lang.String s;

jvm.example1.Test12();
  Code:
   0:   aload_0
   1:   invokespecial   #12; //Method java/lang/Object."<init>":()V
   4:   aload_0
   5:   bipush  30                // push 一个 byte. 与前面的，
                                                            我发现如果是int 在下面的范围:
                          -32768 =< value <= -129: sipush value 后面是2个byte.
                              -128 =< value <= -2: 使用bipush value;
                                               -1: icont_m1; 
                                  0 =< value <= 5: iconst_0 - iconst_5;
                                6 =< value <= 127: 使用bipush value;
                            128 =< value <= 32767: sipush value    是2个byte.
                            32768 =< value       : ldc ,从runtime constant pool中取值,和String一样.
                       float 在下面的范围:
                                      value <= -1: ldc
                                  0 =< value <= 5: fconst_0 - fconst_5
                                      6 =< value : ldc
                       long 和 double 使用 ldc2_w , bipush 后面的1个byte, sipush 后面的2个byte都是value, 而ldc2_w后面是2个byte的index(高位在前),是wide index类型.
                                                                      可能是考虑效率问题.
   7:   putfield        #14; //Field a:I
   10:  aload_0
   11:  ldc     #16; //String a
   13:  putfield        #17; //Field s:Ljava/lang/String;
   16:  return

void m1();
  Code:
   0:   bipush  22
   2:   istore_1
   3:   ldc     #24; //String abc    // 从这可以看出来即使是method中的字符串常量也是存在constant pool 中.
   5:   astore_2
   6:   return

int m2();
  Code:
   0:   iconst_0       // 将false作0处理
   1:   istore_1
   2:   iconst_1       // 将true做1处理
   3:   istore_2
   4:   bipush  102    // 将byte做int处理, 显然都用bipush就可以了.
   6:   istore_3
   7:   bipush  101    // 将char做int处理.
   9:   istore  4      
   11:  bipush  33
   13:  istore  5      // 上面的都做int 处理,所以用istore.
   15:  ldc     #31; //float 44.0f
   17:  fstore  6      // float 用fstore
   19:  ldc2_w  #32; //long 55l
   22:  lstore  7      // long 用lstore, 它占用了2个字长,index 是7和8.
   24:  ldc2_w  #34; //double 66.0d
   27:  dstore  9      // double 用dstore, 同样的占用了2个字长.
   29:  ldc     #36; //String def
   31:  astore  11     // reference 用astore
   33:  iload   5      // 从当前的frame的local variable array中取index是5的 int 类型的值.
   35:  ireturn        // 返回一个int类型, 包括上面的boolean,byte,char,short,int
          // 从上面的2个method可以看出, 每个method的变量都是存储自己的栈帧(frame)中.
 LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      36     0  this   Ljvm/example1/Test12;
               2      34     1     g   Z
               4      32     2     h   Z
               7      29     3     f   B
              11      25     4     e   C
              15      21     5     a   I
              19      17     6     b   F
              24      12     7     c   J
              29       7     9     d   D
              33       3    11 local_2   Ljava/lang/String;

}
 */
class Test12 {
    float a = 30;
    String s = "a";

    void m1() {
        int local_int = 22;
        String local_string = "abc";
    }

    int m2() {
        boolean g = false;
        boolean h = true;
        byte f = 'f';
        char e = 'e';
        int a = 33;
        float b = 44;
        long c = 55;
        double d = 66;
        String local_2 = "def";
        return a;
    }
}

/*
 * {
  java.util.ArrayList<java.lang.String> list;
    Signature: Ljava/util/ArrayList;
    flags: 
    Signature: #8                           // Ljava/util/ArrayList<Ljava/lang/String;>;

  jvm.example1.Test13();
    Signature: ()V
    flags: 
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0       
         1: invokespecial #12                 // Method java/lang/Object."<init>":()V
         4: aload_0       
         5: new           #14                 // class java/util/ArrayList
         8: dup           
         9: invokespecial #16                 // Method java/util/ArrayList."<init>":()V
        12: putfield      #17                 // Field list:Ljava/util/ArrayList;
        15: return        
      LineNumberTable:
        line 319: 0
        line 320: 4
        line 319: 15
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      16     0  this   Ljvm/example1/Test13;

  public void addOne(java.lang.String);
    Signature: (Ljava/lang/String;)V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=2
         0: aload_0       
         1: getfield      #17                 // Field list:Ljava/util/ArrayList;
         4: aload_1       
         5: invokevirtual #25                 // Method java/util/ArrayList.add:(Ljava/lang/Object;)Z
         8: pop           
         9: return        
      LineNumberTable:
        line 322: 0
        line 323: 9
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      10     0  this   Ljvm/example1/Test13;
               0      10     1   str   Ljava/lang/String;
}
 */
class Test13{
	ArrayList<String> list = new ArrayList<String>();
	public void addOne(String str){
		list.add(str);
	}
}

/*
 {
  public int a;
    Signature: I
    flags: ACC_PUBLIC

  private int index;
    Signature: I
    flags: ACC_PRIVATE

  private static int count;
    Signature: I
    flags: ACC_PRIVATE, ACC_STATIC

  static {};                                       // static 型的变量放在static{}中.
    Signature: ()V
    flags: ACC_STATIC
    Code:
      stack=1, locals=0, args_size=0
         0: iconst_1      
         1: putstatic     #12                 // Field count:I
         4: return        
      LocalVariableTable:
        Start  Length  Slot  Name   Signature

  jvm.example1.Test14();
    Signature: ()V
    flags: 
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0       
         1: invokespecial #17                 // Method java/lang/Object."<init>":()V
         4: aload_0       
         5: iconst_1      
         6: putfield      #19                 // Field a:I
         9: aload_0       
        10: iconst_2      
        11: putfield      #21                 // Field index:I
        14: return        
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0      15     0  this   Ljvm/example1/Test14;

  public int getA();
    Signature: ()I
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0       
         1: getfield      #19                 // Field a:I
         4: ireturn       
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0       5     0  this   Ljvm/example1/Test14;

  private int getIndex();
    Signature: ()I
    flags: ACC_PRIVATE
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0       
         1: getfield      #21                 // Field index:I
         4: ireturn       
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
               0       5     0  this   Ljvm/example1/Test14;

  public static int getCount();
    Signature: ()I
    flags: ACC_PUBLIC, ACC_STATIC             // static 标志.
    Code:
      stack=1, locals=0, args_size=0
         0: getstatic     #12                 // Field count:I
         3: ireturn       
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
}

 */
class Test14{
	public int a = 1;
	private int index = 2;
	private static int count = 1;
	
	public int getA(){
		return a;
	}
	
	private int getIndex(){
		return index;
	}
	
	public static int getCount(){
		return count;
	}
	
}
