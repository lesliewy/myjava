package api.org.jdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class JdomParser {
	public void ParseXml1(File file){
		try { 
			SAXBuilder builder = new SAXBuilder(); 
			Document doc = builder.build(file); 
			Element foo = doc.getRootElement(); 
			List allChildren = foo.getChildren();
			for(int i=0;i<allChildren.size();i++) { 
			    System.out.print("车牌号码:" + ((Element)allChildren.get(i)).getChild("NO").getText()); 
			    System.out.println(" 车主地址:" + ((Element)allChildren.get(i)).getChild("ADDR").getText()); 
			}
		}catch (Exception e) {
	             e.printStackTrace();
	      }
 }
	public void ParseXml2(File file){
		try {
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(file);
			Element rootNode = document.getRootElement();    // 获取ROOT节点.
			System.out.println("ROOT:"+rootNode.getName());  
			
			Element infoNode = rootNode.getChild("INFO");
			Element versionNode = infoNode.getChild("VERSION");
			String version = versionNode.getText();
			System.out.println("Version:"+version);
			
			List list = rootNode.getChildren("ROW");
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				Element rowNode = (Element)iterator.next();
				List rowList = rowNode.getChildren("PARAM");
                Iterator rowIterator = rowList.iterator();
                while(rowIterator.hasNext()){
                	Element paramNode = (Element)rowIterator.next();
                	if(paramNode.getAttributeValue("name").equals("账单类型名称")){
                		List paramList = paramNode.getChildren("PARAM-VALUE");
                		Iterator iter = paramList.iterator();
                		while(iter.hasNext()){
                			Element paramvalueNode = (Element)iter.next();
                			if(paramvalueNode.getAttributeValue("name").equals("etf-name")){
                				String billTypeNmEtf = paramvalueNode.getText();
                				System.out.println("账单类型名称 ETF-NAME:"+billTypeNmEtf);
                			}
                		}
                	}else if (paramNode.getAttributeValue("name").equals("账单条数")){
                		List paramList = paramNode.getChildren("PARAM-VALUE");
                		Iterator iter = paramList.iterator();
                		while(iter.hasNext()){
                			Element paramvalueNode = (Element)iter.next();
                			if(paramvalueNode.getAttributeValue("name").equals("etf-name")){
                				String billNumEtf = paramvalueNode.getText();
                				System.out.println("账单条数ETF-NAME:"+billNumEtf);
                			}else if(paramvalueNode.getAttributeValue("name").equals("display")){
                				String display = paramvalueNode.getText();
                				System.out.println("display:"+display);
                			}
                		}
                	}else if (paramNode.getAttributeValue("name").equals("LOGO")){
                		List paramList = paramNode.getChildren("PARAM-VALUE");
                		Iterator iter = paramList.iterator();
                		while(iter.hasNext()){
                			Element paramvalueNode = (Element)iter.next();
                			if(paramvalueNode.getAttributeValue("name").equals("logo-url")){
                				String logoUrl = paramvalueNode.getText();
                				System.out.println("LOGO URL:"+logoUrl);
                			}
                		}
                	}
                }
			}
//			for(int i = 0; i < list.size(); i++){
//				Element
//			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createXML() throws FileNotFoundException, IOException{
		Element root = new Element("GREETING");
		Document doc = new Document(root);
		root.setText("Hello JDOM!");
		XMLOutputter outp = new XMLOutputter();
		outp.output(doc, new FileOutputStream(new File("text4.xml")));   // 输出.
		System.out.println(doc);
	}
    public static void main(String arge[]) throws FileNotFoundException, IOException {
		JdomParser jdomParser = new JdomParser();
//		jdomParser.ParseXml2(new File("l:/my/java/MyJava/src/main/api/org/jdom/test3.xml"));
    	
    	jdomParser.createXML();
    }
}
