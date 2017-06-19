package examples.json.test;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonBeanProcessor;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test1 {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 1,输出一个JSONArray的话，类似下面的样子，JSONObject格式不需要最外面的[];
     * 前面的key都有"",如果value是boolean、int,则没有"";   如果是String,则有"";  如果是map,则再用一对{},里面格式同外面的； 如果是List或者Array,则用一对[],里面格式同外面；
     * [{"a_boolean":false,"a_str":"This is String.","b_int":30,"c_map":{"m1":"m1value","m2":28},"col":0,"list":["it's a list",32],"row":0,"value":""},{"a_boolean":true,"a_str":"This is String 2.","b_int":32,"c_map":{"n1":"n1value","n2":false},"col":0,"list":[],"row":0,"value":""}]
     * 
     * 2, 可以使用 
     * JSONArray.fromObject( object ):  List 或者 Array.
     * JSONObject.fromObject( object ): 其他object
     * 
     * String -> 普通的Bean:
     *   1,JSONObject.fromObject(String):  先将String转为JSONObject
     *   2,JSONObject.toBean( jsonObject ): 再将JSONObject转为Bean. 返回Object
     *   可以使用PropertyUtils.getProperty( bean, "name" )来取这种Bean中的内容.
     * 
     * String -> 自定义Bean
     *   1, JSONObject.fromObject(String):  先将String转为JSONObject
    	 2, JsonBean2 bean = (JsonBean2) JSONObject.toBean( jsonObject, JsonBean2.class ): 再将JSONObject转为自定义的Bean.
     * 
     * 
     */
    @Test
    public void testList() {
        boolean[] boolArray = new boolean[] { true, false, true };
        JSONArray jsonArray1 = JSONArray.fromObject(boolArray);
        System.out.println(jsonArray1); // prints [true,false,true]
        List<Object> list = new ArrayList<Object>();
        list.add("first");
        list.add("second");
        JSONArray jsonArray2 = JSONArray.fromObject(list);
        System.out.println(jsonArray2); // prints ["first","second"]
        JSONArray jsonArray3 = JSONArray.fromObject("['json','is','easy']");
        System.out.println(jsonArray3); // prints ["json","is","easy"]
    }

    @Test
    public void testMap() {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("name", "json");
        map.put("bool", Boolean.TRUE);
        map.put("int", new Integer(1));
        map.put("arr", new String[] { "a", "b" });
        map.put("func", "function(i){ return this.arr[i]; }");
        JSONObject json = JSONObject.fromObject(map);
        System.out.println(json); //{"func":function(i){ return this.arr[i]; },"arr":["a","b"],"int":1,"name":"json","bool":true}    
    }

    @Test
    public void testBean() {
        JsonBean jsonBean = new JsonBean();
        jsonBean.setName("leslie1");
        jsonBean.setSex("boy");
        jsonBean.setDead(false);
        JSONObject jsonObject = JSONObject.fromObject(jsonBean);
        System.out.println(jsonObject); //{"func1":function(i){ return this.options[i]; },"pojoId":1,"name":"json","options":["a","f"],"func2":function(i){ return this.options[i]; }}  
    }

    @Test
    public void testBeans() {
        List<Object> list = new ArrayList<Object>();
        JsonBean2 jb1 = new JsonBean2();
        HashMap<Object, Object> map1 = new HashMap<Object, Object>();
        List<Object> list1 = new ArrayList<Object>();
        list1.add("it's a list");
        list1.add(32);
        map1.put("m1", "m1value");
        map1.put("m2", 28);
        jb1.setA_boolean(false);
        jb1.setA_str("This is String.");
        jb1.setC_map(map1);
        jb1.setB_int(30);
        jb1.setList(list1);
        JsonBean2 jb2 = new JsonBean2();
        HashMap<Object, Object> map2 = new HashMap<Object, Object>();
        map2.put("n1", "n1value");
        map2.put("n2", false);
        jb2.setA_boolean(true);
        jb2.setA_str("This is String 2.");
        jb2.setC_map(map2);
        jb2.setB_int(32);
        list.add(jb1);
        list.add(jb2);
        JSONArray ja = JSONArray.fromObject(list);
        System.out.println(ja.toString()); //[{"value":"xx","row":1,"col":1},{"value":"","row":2,"col":2}]    
    }

    @SuppressWarnings( { "deprecation", "unchecked" })
    @Test
    public void testString2Bean1() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String json = "{name=\"json\",bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}";
        //		 JSONObject jsonObject = JSONObject.fromString(json);
        JSONObject jsonObject = JSONObject.fromObject(json);
        Object bean = JSONObject.toBean(jsonObject);
        assertEquals(jsonObject.get("name"), PropertyUtils.getProperty(bean, "name"));
        assertEquals(jsonObject.get("bool"), PropertyUtils.getProperty(bean, "bool"));
        assertEquals(jsonObject.get("int"), PropertyUtils.getProperty(bean, "int"));
        assertEquals(jsonObject.get("double"), PropertyUtils.getProperty(bean, "double"));
        assertEquals(jsonObject.get("func"), PropertyUtils.getProperty(bean, "func"));
        List<Object> expected = JSONArray.toList(jsonObject.getJSONArray("array"));
        assertEquals(expected, (List<Object>) PropertyUtils.getProperty(bean, "array"));
    }

    @Test
    public void testString2Bean2() {
        String json = "{\"value\":\"xx\",\"row\":1,\"col\":1,\"timestamp\":\"2013-02-01 22:00:00\"}";
        //		JSONObject jsonObject = JSONObject.fromString(json);
        JSONObject jsonObject = JSONObject.fromObject(json);
        JsonBean2 bean = (JsonBean2) JSONObject.toBean(jsonObject, JsonBean2.class);

        JsonConfig cfg = new JsonConfig();
        cfg.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
            private final String format = "yyyy-MM-dd HH:mm:ss";

            public Object processObjectValue(String key, Object value, JsonConfig arg2) {
                if (value == null)
                    return "";
                if (value instanceof Date) {
                    String str = new SimpleDateFormat(format).format((Date) value);
                    return str;
                }
                if (value instanceof Timestamp) {
                    String str = new SimpleDateFormat(format).format((Timestamp) value);
                    return str;
                }

                return value.toString();
            }

            public Object processArrayValue(Object value, JsonConfig arg1) {
                return null;
            }
        });

        assertEquals(jsonObject.get("col"), new Integer(bean.getCol()));
        assertEquals(jsonObject.get("row"), new Integer(bean.getRow()));
        assertEquals(jsonObject.get("value"), bean.getValue());
        System.out.println("col:" + bean.getCol());
        System.out.println("t:" + bean.getTimestamp());
    }

    @Test
    public void testString2Bean3() {
//        String json = "{\"value\":\"xx\",\"row\":1,\"col\":1,\"timestamp\":\"2013-02-01 22:00:00\"}";
        String json = "{\"value\":\"xx\",\"row\":1,\"col\":1,\"date\":\"05/12/2001 19:20:23\"}";
        JsonBean2 bean2 = new JsonBean2();
        bean2.setCol(2);
        bean2.setValue("abc");
//        bean2.setTimestamp(Timestamp.valueOf("2012-02-10 22:01:10"));
        bean2.setDate(new Date());

        JsonConfig cfg = new JsonConfig();
        cfg.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonValueProcessor() {
            private final String format = "yyyy-MM-dd HH:mm:ss";

            public Object processObjectValue(String key, Object value, JsonConfig arg2) {
                if (value == null)
                    return "";
                if (value instanceof Date) {
                    String str = new SimpleDateFormat(format).format((Date) value);
                    return str;
                }
                if (value instanceof Timestamp) {
                    //                    String str = new SimpleDateFormat(format).format((Timestamp) value);
                    String str = ((Timestamp) value).toString();
                    return str;
                }

                return value.toString();
            }

            public Object processArrayValue(Object value, JsonConfig arg1) {
                return null;
            }
        });
        //        Collection<JsonBean> list = JSONArray.toCollection(JSONArray.fromObject(jsonStr,cfg ), JsonBean.class);     
        //                  
        //                JSONArray json = JSONArray.fromObject(votes,cfg);   
        JSONObject jsonObject = JSONObject.fromObject(bean2, cfg);
        System.out.println("String: " + jsonObject.toString());

//        cfg.registerJsonBeanProcessor(java.sql.Timestamp.class, new JsonBeanProcessor() {
//
//            @Override
//            public JSONObject processBean(Object bean, JsonConfig arg1) {
////                if (bean instanceof java.sql.Date) {
////
////                    java.sql.Date d = (java.sql.Date) bean;
////
////                    long time = d.getTime();
////
////                    String pattern = getDatePattern();
////
////                    String date = DateFormatUtils.format(time, pattern);
////
////                    return makeJSONObject(date, time, pattern);
////
////                }
//
//                if (bean instanceof JsonBean2) {
//
////                    Date d = (Date) bean;
////
////                    long time = d.getTime();
////
////                    String pattern = getDatePattern();
////
////                    String date = DateFormatUtils.format(time, pattern);
////
////                    return makeJSONObject(date, time, pattern);
//                    Timestamp t = (Timestamp)bean;
//                    JSONObject object = new JSONObject();
//                    object.element("timestamp", ((JsonBean2) bean).getTimestamp());
//                    return object;
//
//                }
//
//                return new JSONObject(true);
//
//            }
////            private static JSONObject makeJSONObject(String date, long time,String pattern) {
////
////            JSONObject jsonObject = new JSONObject();
////            jsonObject.
////
////            jsonObject.element("date", date);
////
////            jsonObject.element("time", time);
////
////            jsonObject.element("pattern", pattern);
////
////            return jsonObject;
////
////    } 
//
//
//        });
        MorpherRegistry morpherRegistry = JSONUtils.getMorpherRegistry();
        Morpher dynaMorpher = new BeanMorpher( JsonBean2.class,  morpherRegistry); 
        morpherRegistry.registerMorpher( dynaMorpher );  
//        morpherRegistry.morph(java.sql.Timestamp.class, "timestamp");
        
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"MM/dd/yyyy HH:mm:ss"}) );
        JsonBean2 bean = (JsonBean2) JSONObject.toBean(jsonObject, JsonBean2.class);
//        System.out.println("ts: " + bean.getTimestamp());
        //              JSONObject jsonObject = JSONObject.fromString(json);
        //        JSONObject jsonObject = JSONObject.fromObject(json);
        //        JsonBean2 bean = (JsonBean2) JSONObject.toBean(jsonObject, JsonBean2.class);
        //
        //        
        //
        //        assertEquals(jsonObject.get("col"), new Integer(bean.getCol()));
        //        assertEquals(jsonObject.get("row"), new Integer(bean.getRow()));
        //        assertEquals(jsonObject.get("value"), bean.getValue());
        //        System.out.println("col:" + bean.getCol());
        //        System.out.println("t:" + bean.getTimestamp());
    }

    @Test
    public void testJSON2XML1() {
        JSONObject json = new JSONObject(true);
        // 需要xom-1.1.jar
        XMLSerializer xmlSerializer = new XMLSerializer();
        String xml = xmlSerializer.write(json);
        System.out.println("xml:" + xml);
    }

    @Test
    public void testJSON2XML2() {
        JSONObject json = JSONObject.fromObject("{\"name\":\"json\",\"bool\":true,\"int\":1}");
        XMLSerializer xmlSerializer = new XMLSerializer();
        String xml = xmlSerializer.write(json);
        System.out.println("xml:" + xml);
    }

    @Test
    public void testXML2JSON1() {
        StringBuffer xmlsb = new StringBuffer();
        xmlsb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlsb.append("\n");
        xmlsb
                .append("<o1><bool type=\"boolean\">true</bool><int type=\"number\">1</int><name type=\"string\">json</name></o1>");
        System.out.println("xml:" + xmlsb);
        String xml = xmlsb.toString();
        XMLSerializer xmlSerializer = new XMLSerializer();
        //		JSONArray json = (JSONArray) (xmlSerializer.read( xml ));
        JSONArray json = JSONArray.fromObject(xmlSerializer.read(xml));
        System.out.println(json);
    }
}
