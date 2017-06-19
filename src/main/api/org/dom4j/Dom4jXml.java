package api.org.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
public class Dom4jXml {
	public void CreateXML(){
		// XML 声明 <?xml version="1.0" encoding="UTF-8"?> 自动添加到 XML 文档中。
		Document document = DocumentHelper.createDocument();
		Element catalogElement = document.addElement("catalog");
		catalogElement.addComment("An XML catalog");
        catalogElement.addProcessingInstruction("target","text");
	    Element journalElement =  catalogElement.addElement("journal");
	    journalElement.addAttribute("title", "XML Zone");
	    journalElement.addAttribute("publisher", "IBM developerWorks");
	    Element articleElement=journalElement.addElement("article");
        articleElement.addAttribute("level", "Intermediate");
	    articleElement.addAttribute("date", "December-2001");
	    Element  titleElement=articleElement.addElement("title");
	    titleElement.setText("Java configuration with XML Schema");
	    Element authorElement=articleElement.addElement("author");
	    Element  firstNameElement=authorElement.addElement("firstname");
        firstNameElement.setText("Marcello");
        Element lastNameElement=authorElement.addElement("lastname");
        lastNameElement.setText("Vitaletti");
        document.addDocType("catalog",
	                           null,"file://c:/Dtds/catalog.dtd");
        String a = document.asXML();
        System.out.println(a);
//	    try{
//	    XMLWriter output = new XMLWriter(
//	            new FileWriter( new File("C:/catalog.xml") ));
//	        output.write( document );
//	        output.close();
//	        }
//	     catch(IOException e){System.out.println(e.getMessage());}
	}
	public static void main(String[] argv){
		Dom4jXml dom4j=new Dom4jXml();
	    dom4j.CreateXML();
	}}