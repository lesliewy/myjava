package api.org.xml.sax;

import java.io.File;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*; 

import api.org.xml.sax.*;

import javax.xml.parsers.*; 

public class SaxParseXml extends DefaultHandler { 
    java.util.Stack tags = new java.util.Stack();   // 用户记录上一个节点名称.因为SAX会把所有的空白计算在内,当作TextNode. 此值用来判断上一节点值是什么.
    public SaxParseXml() { 
        super(); 
    }
    public void parseXml1(File file){
    	
    }

    public static void main(String args[]) { 
    	try {
            SAXParserFactory sf = SAXParserFactory.newInstance();
            SAXParser sp = sf.newSAXParser();
            SaxParseXml reader = new SaxParseXml();
            sp.parse(new InputSource("l:/my/java/MyJava/src/main/api/org/w3c/dom/test.xml"), reader);
		}catch (Exception e) {
            e.printStackTrace();
		}
    }
    /*
     * 开始解析<?xml ?>
     */
    public void startDocument(){
    	System.out.println("now begin...");
    }

    /*
     * 开始一个element时执行.
     */
    public void startElement(String uri,String localName,String qName,Attributes attrs) {
    	System.out.println("StartElement qName:"+qName+"   localName:"+localName);
    	if(attrs.getLength()!=0){
    		System.out.println(qName+"attributes 0:"+attrs.getValue(0));
    	}
    	tags.push(qName);
    }
    
    /*
     * 和DOM一样,SAX也不会忽略空白. 
     * <a> <b>b</b> </a>    :<a>结束时执行characters,此时String(ch,start,length)是空格,ch始终是整个xml
     * <b>结束时执行characters,此时String(ch,start,length)是b;
     * </b>结束时执行characters,此时String(ch,start,length)是空格.
     */
    public void characters(char ch[], int start, int length) throws SAXException {
    	System.out.println("characters:"+String.valueOf(ch,start,length));
    	String tag = (String) tags.peek();
    	if (tag.equals("NO")) {
            System.out.print("车牌号码：" + new String(ch, start, length)); 
    	}
    	if (tag.equals("ADDR")) {
            System.out.println(" 地址:" + new String(ch, start, length)); 
    	}
    }
}
