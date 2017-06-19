package api.lang.reflection;

import java.lang.reflect.Method;

import org.junit.Test;

/**.
 * 
         * ClassName: DumpMethods <br/>  
         * Function:
         * Reason: 
         * date: Jan 24, 2013 3:27:24 PM <br/>  
         *  
         * @author Leslie  
         * @version   
         * @since version 1.0
 */
public class DumpMethods {

    /*
     * 1,Java中，无论生成某个类的多少个对象，这些对象都会对应于同一个Class对象.
     * 
     */
    @Test
    public void test1(){
        // 获得class对象. Class<?> 泛型 即 Extend Object
        Class<?> classType;
        try {
            classType = Class.forName("java.lang.reflect.Method");
            Method[] methods = classType.getDeclaredMethods();
            
            for (Method method:methods){
                System.out.println(method);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  
        }
    }
}
