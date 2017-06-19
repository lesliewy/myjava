package api.org.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Attribute;
import java.util.List;
import java.util.Iterator;
import org.dom4j.io.XMLWriter;
import java.io.*;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader; 
public class Dom4jParser{
    public void modifyDocument(File inputXml){
        try{
            // 使用 SAXReader 解析 XML 文档
            SAXReader saxReader = new SAXReader();
            System.out.println("before read.");
            System.out.println("inputXml:"+inputXml);
            Document document = saxReader.read(inputXml);
            System.out.println("before select.");
            // 支持xpath方式查找节点.
            // 返回的是attribute的list:   含有level属性的article节点的所有属性.
            List list = document.selectNodes("//article/@level" );
            // 返回的是node 的list: 含有level属性的所有article节点.
//            List list = document.selectNodes("//article[@level]" );
            System.out.println("list:"+list);
            Iterator iter=list.iterator();
            while(iter.hasNext()){
                Attribute attribute=(Attribute)iter.next();
                if(attribute.getValue().equals("Intermediate")){
                    attribute.setValue("Introductory");
                }
            }
            list = document.selectNodes("//article/@date" );
            iter=list.iterator();
            while(iter.hasNext()){
                Attribute attribute=(Attribute)iter.next();
                if(attribute.getValue().equals("December-2001"))
                    attribute.setValue("October-2002");
            }
            list = document.selectNodes("//article" );
            iter=list.iterator();
            while(iter.hasNext()){
                Element element=(Element)iter.next();
                Iterator iterator=element.elementIterator("title");
                while(iterator.hasNext()){
                    Element titleElement=(Element)iterator.next();
                    if(titleElement.getText().equals("Java configuration with XML Schema"))
                        titleElement.setText("Create flexible and extensible XML schema");
                }
            }
            list = document.selectNodes("//article/author" );
            iter=list.iterator();
            while(iter.hasNext()){
                Element element=(Element)iter.next();
                Iterator iterator=element.elementIterator("firstname");
                while(iterator.hasNext()){
                    Element firstNameElement=(Element)iterator.next();
                    if(firstNameElement.getText().equals("Marcello"))
                    firstNameElement.setText("Ayesha");
                }
            }
            list = document.selectNodes("//article/author" );
            iter=list.iterator();
            while(iter.hasNext()){
                Element element=(Element)iter.next();
                Iterator iterator=element.elementIterator("lastname");
                while(iterator.hasNext()){
                    Element lastNameElement=(Element)iterator.next();
                    if(lastNameElement.getText().equals("Vitaletti"))
                        lastNameElement.setText("Malik");
                }
            }
            XMLWriter output = new XMLWriter(
            new FileWriter( new File("l:/catalog-modified.xml") ));
            output.write( document );
            output.close();
        }
        catch(DocumentException e){
        	System.out.println("e1");
            System.out.println(e.getMessage());
        }
        catch(IOException e){
        	System.out.println("e2");
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] argv){
        Dom4jParser dom4jParser=new Dom4jParser();
        dom4jParser.modifyDocument(new File("L://catalog.xml"));
    }
}
