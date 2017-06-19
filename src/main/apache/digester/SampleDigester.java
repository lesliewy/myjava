package apache.digester;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.commons.digester3.CallMethodRule;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.ObjectCreateRule;
import org.apache.commons.digester3.Rule;
import org.apache.commons.digester3.SetNextRule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

/*
 * 一般需要: commons-digester3-3.2-with-deps.jar  这一个就包含了所有.
 */
public class SampleDigester {  
	  
    /** log */  
    protected static final Log log = LogFactory.getLog(SampleDigester.class);  
  
    /** dataSources */  
    private Vector<DataSource> dataSources;  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        SampleDigester sd = new SampleDigester();  
        sd.digester2();  
        log.info(sd.toString());  
    }  
  /*
   * addCallMethod addCallParam方式.
   */
    private void digester() {  
        Digester digester = new Digester();  
  
        setDataSources(new Vector<DataSource>());  
        // 把当前对象压入到digester栈中。  
        digester.push(this);  
        /* 设定解析此xml文件的规则 */  
        // 将XML文件解析所对应的方法.this.addDataSource();参数个数为5个.  
        digester.addCallMethod("datasources/datasource", "addDataSource", 5);  
        // 对应方法addDataSource参数１,final String name;  
        digester.addCallParam("datasources/datasource/name", 0);  
        digester.addCallParam("datasources/datasource/driver", 1);  
        digester.addCallParam("datasources/datasource/url", 2);  
        digester.addCallParam("datasources/datasource/username", 3);  
        digester.addCallParam("datasources/datasource/password", 4);  
          
        try {  
            digester.parse(new File("l:/my/java/MyJava/src/main/apache/digester/datasources.xml"));  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void addDataSource(final String name, final String driver,  
            final String url, final String username, final String password) {  
        DataSource dataSource = new DataSource();  
        dataSource.setName(name);  
        dataSource.setDriver(driver);  
        dataSource.setUrl(url);  
        dataSource.setUsername(username);  
        dataSource.setPassword(password);  
        getDataSources().add(dataSource);  
    }  
  
    /** 
     * @return the dataSources 
     */  
    public Vector<DataSource> getDataSources() {  
        return dataSources;  
    }  
  
    /** 
     * @param dataSources 
     *            the dataSources to set 
     */  
    public void setDataSources(Vector<DataSource> dataSources) {  
        this.dataSources = dataSources;  
    }  
  
    public String toString() {  
        String newline = System.getProperty("line.separator");  
        StringBuffer buff = new StringBuffer();  
        if (getDataSources() != null) {  
            for (DataSource ds : getDataSources()) {  
                buff.append(newline).append(ds);  
            }  
            return buff.toString();  
        }  
        return "";  
    }  
    
    /*
     * addObjectCreate方式
     */
    private void digester2() {  
        Digester digester = new Digester();  
      
        setDataSources(new Vector<DataSource>());  
        // 把当前对象压入到digester栈中。  
        digester.push(this);  
          
        // 指明匹配模式和要创建的类  
        digester.addObjectCreate("datasources/datasource", DataSource.class);  
        // 设置对象属性,与xml文件对应,不设置则是默认.  
        digester.addBeanPropertySetter("datasources/datasource/name", "name");  
        digester.addBeanPropertySetter("datasources/datasource/driver", "driver");  
        digester.addBeanPropertySetter("datasources/datasource/url");  
        digester.addBeanPropertySetter("datasources/datasource/username");  
        digester.addBeanPropertySetter("datasources/datasource/password");  
          
        // 当移动到下一个标签中时的动作  
        digester.addSetNext("datasources/datasource", "addDataSource");  
          
        try {  
            digester.parse(new File("l:/my/java/MyJava/src/main/apache/digester/datasources.xml"));  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public void addDataSource(final DataSource ds) {  
        getDataSources().add(ds);  
    }  
    
    /*
     * addRule 方式
     */
    private void digester3() {  
        Digester digester = new Digester();  
      
        setDataSources(new Vector<DataSource>());  
        // 把当前对象压入到digester栈中。  
        digester.push(this);  
      
        Rule objectCreate = new ObjectCreateRule(DataSource.class);  
        digester.addRule("datasources/datasource", objectCreate);  
      
        digester.addRule("datasources/datasource", new SetNextRule(  
                "addDataSource"));  
      
        digester.addRule("datasources/datasource/name", new CallMethodRule(  
                "setName", 0, new Class[] { String.class }));  
        digester.addRule("datasources/datasource/driver", new CallMethodRule(  
                "setDriver", 0, new Class[] { String.class }));  
        digester.addRule("datasources/datasource/url", new CallMethodRule(  
                "setUrl", 0, new Class[] { String.class }));  
        digester.addRule("datasources/datasource/username", new CallMethodRule(  
                "setUsername", 0, new Class[] { String.class }));  
        digester.addRule("datasources/datasource/password", new CallMethodRule(  
                "setPassword", 0, new Class[] { String.class }));  
        try {  
            digester.parse(new File("l:/my/java/MyJava/src/main/apache/digester/datasources.xml"));  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        }  
    }  
}  
