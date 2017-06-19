package dp.structuralpattern.decorator.sketch;

/**
 * 主要就是：1、有原数据、可以被包装的。
 * 2、包装类，能包装其他事物的，就是在原事物上添加点东西
 * 
 *  JDK中:
 *  BufferedInputStream(InputStream in)   BufferedOutputStream(OutputStream out)  
 *  DataInputStream(InputStream in) DataOutputStream(OutputStream out) 
 *  
 *  BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("text.txt"))));
 *    InputStreamReader 起到桥梁作用，连接Inputstream 和 Reader, 可以认为时适配器模式.
 *    
 * @author leslie  
 * @version   
 * @since version 1.0
 */
public class Decorator implements Component {
	private Component component;
	/**
	 * 构造函数
	 */
    public Decorator(Component component){
    	this.component = component;
    }
    public Decorator(){
    	// your own code here
    }
    /**
     * 商业方法，委派给component
     */
    public void sampleOperation() {
    	this.component.sampleOperation();
	}

}
