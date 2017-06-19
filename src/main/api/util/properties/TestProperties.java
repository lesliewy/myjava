package api.util.Properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class TestProperties {

	public static void main(String[] args) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.setProperty("name1", "wy1");
        prop.setProperty("name2", "wy2");
        prop.setProperty("name2", "wy22");    // 同名key，会覆盖之前的value.
        prop.setProperty("age", "24");
        System.out.println("name1:"+prop.getProperty("name1"));
        System.out.println("age:"+prop.getProperty("age"));
        if (prop.containsKey("name1")){
        	System.out.println("name1:"+prop.getProperty("name1"));
        }
        if (prop.containsValue("wy1")){
        	System.out.println("value wy1,key is:");
        }
        System.out.println("prop size:"+prop.size());
        System.out.println("===========================================");
        /*
         * 构造时带一个默认的property, 如果没取到值，从默认中取.
         * 优先级是上面的这个高.
         */
        Properties prop1 = new Properties(prop);
        System.out.println("new Properties(prop) name1:"+prop1.getProperty("name1"));
        System.out.println("new Properties(prop) default name1:"+prop1.getProperty("name1","defalut name"));
        System.out.println("===========================================");
        /*
         * 遍历properties: propertyNames() stringPropertyNames()  list()
         */
        System.out.println("propertyNames():");
        Enumeration<?> enum_prop = prop.propertyNames();
        while(enum_prop.hasMoreElements()){
        	String prop_key=(String)enum_prop.nextElement();
        	String prop_value=(String)prop.getProperty(prop_key);
        	System.out.println("in prop:"+"key:"+prop_key+"  value:"+prop_value);
        }
        System.out.println("stringPropertyName():");
        Set<String> set_prop_string = prop.stringPropertyNames();
        Iterator<String> iter = set_prop_string.iterator();
        while(iter.hasNext()){
        	String prop_key=(String)iter.next();
        	String prop_value=(String)prop.getProperty(prop_key);
        	System.out.println("in prop:"+"key:"+prop_key+"  value:"+prop_value);
        }
        System.out.println("list():");
        prop.list(System.out);
        System.out.println("===========================================");
        /*
         * load   loadFromXML   参数都是InputStream
         * store  storeToXML
         */
//        prop.load(new FileInputStream(new File("src/api/util/properties/test1.properties")));
//        prop.load(new FileInputStream("src/api/util/properties/test1.properties"));
        System.out.println("AppClassLoader url: " + System.getProperty("java.class.path"));
        prop.load(new BufferedInputStream(new FileInputStream("bin/api/util/Properties/test1.properties")));
//        prop.load(new BufferedInputStream(new FileInputStream("test1.properties")));
        enum_prop = prop.propertyNames();
        while(enum_prop.hasMoreElements()){
        	System.out.println("in prop load:"+enum_prop.nextElement());
        }
        System.out.println("============================================");
        prop.store(new FileOutputStream(new File("bin/api/util/properties/test1_store.properties")), "this is leslie comment here.");  // comment出现在首行.
        prop.storeToXML(new FileOutputStream(new File("bin/api/util/properties/test1_store.xml")), "this is xml file comment.");
//        PrintStream out = new PrintStream();
//        prop.list(out);
        System.out.println("============================================");
        /*
         * 注意要load 的xml文件的写法.  <entry key="name5">wy5</entry>
         */
        prop.loadFromXML(new FileInputStream(new File("bin/api/util/properties/test1_load.xml")));
        enum_prop = prop.propertyNames();
        while (enum_prop.hasMoreElements()){
        	System.out.println("in prop load xml:"+enum_prop.nextElement());
        }
	}

}
