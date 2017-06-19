package api.org.w3c.dom;

import java.io.BufferedWriter;
import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.*;

public class DomParseXml{
    /*
     * 
     */
    @Test
    public void test1(){
        long lasting =System.currentTimeMillis();
        try{
            File f=new File("C:/IBM/my/java/MyJava/src/main/api/org/w3c/dom/test.xml");
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            System.out.println("1.============================");
            /*
             * 利用getElementsByTagName,可以直接取任意层的节点.返回NodeList
             */
            NodeList nl = doc.getElementsByTagName("VALUE");
            for (int i=0;i<nl.getLength();i++){
                System.out.print("车牌号码:" + doc.getElementsByTagName("NO").item(i).getTextContent()); 
                System.out.println(" 车主地址:" + doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue());
                
                // 获取属性.
                Element e1 = (Element) nl.item(i);
                String addr=e1.getAttribute("INF");    // case sensitive, 如果写成 inf,就取不到.
                System.out.println("inf "+i+":"+addr);
            }
            /*
             * 利用getDocumentElement().getChildNodes()
             * 注意getChildNodes()的值:
             *   节点与节点之间都是DOM的解析范围.<a> <b>b</b> </a>: <a>与<b>之间的空格,</b>和</a>之间的空格都是child node. 
             *   comment是DOM的解析范围.
             */
           System.out.println("2. ========================");
           Element e3 = doc.getDocumentElement();  // 一般是根节点(ROOT),不包括孙子节点.
           NodeList nl3 = e3.getChildNodes();
           for (int i3 = 0; i3 < nl3.getLength(); i3++){
        	   Node n3 = nl3.item(i3);
        	   String name3 = n3.getNodeName();
        	   if(n3.getNodeType() == Node.ELEMENT_NODE){
        	       System.out.println("name:"+name3);
             	   System.out.println(n3.getAttributes().getNamedItem("INF"));
        	   }
        	   System.out.println("child :"+i3+":"+nl3.item(i3));   // [#text:
           }
           /*
            * 
            */
           System.out.println("3===============");
           System.out.println("SUBINF:"+doc.getElementsByTagName("SUBVALUE").item(0).getAttributes().getNamedItem("SUBINF"));  // 取属性.
           System.out.println("SUBVALUE TextContent:"+doc.getElementsByTagName("SUBVALUE").item(0).getTextContent()); // 取内容.
           System.out.println("SUBVALUE:"+doc.getElementsByTagName("SUBVALUE").item(0).getFirstChild().getNodeValue());
        }catch(Exception e){
             e.printStackTrace();
        }
    }
    
    @Test
    public void test2(){
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
            Element e1 = doc.createElement("name1");
            Element e2 = doc.createElement("name2");
            Text t1 =doc.createTextNode("leslie1");
            e1.appendChild(t1);
            e1.appendChild(e2);
            System.out.println("t1:" + t1);
            System.out.println("e1:" + e1);
            NodeList nl = e1.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++){
                Node n3 = nl.item(i);
                String name3 = n3.getNodeName();
                if(n3.getNodeType() == Node.ELEMENT_NODE){
                    System.out.println("name:"+name3);
                System.out.println(n3.getAttributes().getNamedItem("INF"));
                }
                System.out.println("child :"+i+":"+nl.item(i));   // [#text:
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            StringWriter s = new StringWriter();
            StreamResult result1 = new StreamResult(new BufferedWriter(s));
            transformer.transform(new DOMSource(doc), result1);
            System.out.println(s.getBuffer().toString().trim());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();  
        } catch (TransformerException e) {
            e.printStackTrace();  
        }
    }
}
