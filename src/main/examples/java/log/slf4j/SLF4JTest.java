package examples.java.log.slf4j;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

/**
 * slf4j + log4j 方式;
 * import  slf4j-api-1.7.5.jar
 * 如果使用log4j的实现，还需要import slf4j-log4j12-1.7.5.jar
 * 将jcl方式修改成 slf4j方式：
 *     删除commons-logging-1.1.jar，添加SLF4J提供的jcl-over-slf4j-1.6.0.jar，slf4j-api-1.6.0.jar,slf4j-log4j12-1.6.0.jar 三个包
 *     jcl-over-slf4j-1.6.0.jar包是用来替换JCL的原生包的，它提供JCL一模一样的API，但是底层却是桥接到SLF4J去了。
 * 
 * 最好不要使用jcl-over-slf4j.jar， 因为可能导致 commons-logging.jar     和  slf4j-api.jar + logback 无法使用;
 * @author Administrator
 *
 */
public class SLF4JTest {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(SLF4JTest.class);

		logger.info("info ....");

		logger.debug("debug ...");

		logger.error("error ...");

	}

}
