package examples.java.log.log4j;

import org.apache.log4j.Logger;

public class LogTest {

	/**
	 * import log4j-1.2.17.jar
	 * 必须在classpath中存在 log4j.properties, 否则无法输出日志;
	 * 
	 * 
	 */
	public static void main(String[] args) {

		// 获取Logger实例，参数为本类

		Logger logger = Logger.getLogger(LogTest.class);

		logger.debug("debuging");// 输出一段DEBUG信息

		logger.info("info..."); // 输出一段INFO信息

		logger.error("error..."); // 输出一段ERROR错误信息

	}

}
