package examples.java.log.jcl;

import org.apache.commons.logging.*;

/**
 * import commons-logging-1.1.3.jar
 * 
 * JCL 提供日志接口，可以支持不同的日志实现框架;  
 *  
 * 有必要详细说明一下调用LogFactory.getLog()时发生的事情。调用该函数会启动一个发现过程，即找出必需的底层日志记录功能的实现，具体的发现过程在下面列出：
	⑴ Commons的Logging首先在CLASSPATH中查找commons-logging.properties文件。这个属性文件至少定义 org.apache.commons.logging.Log属性，它的值应该是上述任意Log接口实现的完整限定名称。如果找到 org.apache.commons.logging.Log属相，则使用该属相对应的日志组件。结束发现过程。
	⑵ 如果上面的步骤失败（文件不存在或属相不存在），Commons的Logging接着检查系统属性 org.apache.commons.logging.Log。如果找到org.apache.commons.logging.Log系统属性，则使用该系统属性对应的日志组件。结束发现过程。
	⑶ 如果找不到org.apache.commons.logging.Log系统属性，Logging接着在CLASSPATH中寻找log4j的类。如果找到了，Logging就假定应用要使用的是log4j。不过这时log4j本身的属性仍要通过log4j.properties文件正确配置。结束发现过程。
	⑷ 如果上述查找均不能找到适当的Logging API，但应用程序正运行在JRE 1.4或更高版本上，则默认使用JRE 1.4的日志记录功能。结束发现过程。
	⑸ 最后，如果上述操作都失败（JRE 版本也低于1.4），则应用将使用内建的SimpleLog。SimpleLog把所有日志信息直接输出到System.err。结束发现过程。
	本例中，JCL在第（1）步和第（2）步均查找失败，在第（3）步是找到了LOG4J的JAR包，于是JCL就使用LOG4J作为日志处理方案。
	如果后来的开发人员不想使用LOG4J，可直接添加相应的JAR包或类，然后在commons-logging.properties文件中指定即可，而不用去修改程序代码。
 * 
 * @author Administrator
 * 
 */

public class LogJCLTest {

	private static final Log logger = LogFactory.getLog(LogJCLTest.class);

	public static void main(String[] args) {

		// Logger logger=Logger.getLogger(LogTest.class);

		logger.debug("debuging");

		logger.info("info...");

		logger.error("error...");

	}

}
