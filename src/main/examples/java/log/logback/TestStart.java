package examples.java.log.logback;

/**
 *  slf4j + logback 方式;
 *  import slf4j-api-1.7.5.jar   logback-classic-1.0.13.jar  logback-core-1.0.13.jar
 *  不能有  log4j-over-slf4j-1.7.5.jar
 *  否则报错: 不知道用哪个;
 *  
 *  classpath 中要有 logback.xml
 * @author Administrator
 *
 */
public class TestStart {
	public static void main(String[] args){
		TestA a = new TestA();
		TestB b = new TestB();
		TestC c = new TestC();
		TestD d = new TestD();
	}
}
