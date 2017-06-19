package api.lang.reflection;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 
         * ClassName: InvokeTester <br/>  
         * Function: 
         * Reason: 
         * date: Jan 24, 2013 3:30:40 PM <br/>  
         *  
         * @author Leslie  
         * @version   
         * @since version 1.0
 */
public class InvokeTester {
    /*
     * 1, 
     *   a,Class<?> classType = Class.forName(args[0]); 
     *   b,Class<?> classType = InvokeTester.class;
     *     Object invokerTester = classType.newInstance();
     * 
     */
    private int a = 3;
    
    public static String b = "3";
    
    public String c = "c";
    
    public List<String> myList;
    public InvokeTester(){
        
    }
    
    private InvokeTester(int a){
        
    }
    
    public InvokeTester(int a, String b){
        this.a = a;
        this.b = b;
    }
    
    public int add(int param1, int param2){
        return param1 + param2;
    }
    public String echo(String message){
        return "hello:" + message;
    }
    
    public static void main(String[] args)throws Exception {
        /*
         * 根据方法名称来执行方法;
         */
//      InvokeTester test = new InvokeTester();
//      System.out.println(test.add(1, 2));
//      System.out.println(test.echo("tom"));
        Class<?> classType = InvokeTester.class;
        Object invokeTester = classType.newInstance();
        Method addMethod = classType.getMethod("add", new Class[]{int.class, int.class});
        Object result = addMethod.invoke(invokeTester, new Object[]{1, 2});

        System.out.println((Integer) result);
        System.out.println("=======================");

        Method  echoMethod = classType.getMethod("echo", new Class[]{String.class});
        Object result2 = echoMethod.invoke(invokeTester, new Object[]{"tom"});
        System.out.println((String) result2);
    }
    
    @Override
    public String toString(){
        System.out.println("a: " + a + "; b:" + b);
        return "";
        
    }
}
