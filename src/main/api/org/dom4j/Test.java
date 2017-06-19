package api.org.dom4j;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/*
 * dom4j一般需要2个jar: dom4j.jar  jaxen-1.1-beta-4.jar
 */
public class Test {
	public void parseXML1(File file){
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			List list = document.selectNodes("/ROOT/INFO/VERSION");
			Iterator iterator=list.iterator();
			while(iterator.hasNext()){
				Element element = (Element) iterator.next();
				String version = element.getText();
				System.out.println("version:"+version);
			}
			
		    list = document.selectNodes("//ROW/PARAM");
		    System.out.println("leslie1:"+list);
		    iterator = list.iterator();   // 按顺序的 <PARAM>  </PARAM>   
		    while(iterator.hasNext()){
		    	Element element =(Element)iterator.next();
		    	Iterator iter=element.elementIterator();   // 按顺序的 <PARAM-NAME> <ETF-NAME>
		    	while(iter.hasNext()){
		    		Element ParaElement1 = (Element)iter.next();
		    		System.out.println("leslie2:"+"Name"+ParaElement1.getName()+"   Text:"+ParaElement1.getText());
		    		if(ParaElement1.getText().equals("账单类型名称")){
		    			Element ParaElement2 = (Element)iter.next();
//		    			System.out.println("leslie3:"+"Name:"+ParaElement2.getName()+"   Text:"+ParaElement2.getText());
		    			if(ParaElement2.getName().equals("ETF-NAME")){
		    				System.out.println("leslie4");
		    				String billTypeEtfnm=ParaElement2.getText();
		    				System.out.println("BILL_TYPE ETF_NAME:"+billTypeEtfnm);
		    			}
		    		}else if (ParaElement1.getText().equals("账单笔数")){
		    			Element ParaElement2 = (Element)iter.next();
//		    			System.out.println("leslie3:"+"Name:"+ParaElement2.getName()+"   Text:"+ParaElement2.getText());
//		    			System.out.println(ParaElement2.getName().equals("ETF-NAME"));
		    			if(ParaElement2.getName().equals("ETF-NAME")){
		    				String billnumEtfnm=ParaElement2.getText();
		    				System.out.println("BILL_NUM ETF_NAME:"+billnumEtfnm);
		    				ParaElement2 = (Element) iter.next();
		    				if (ParaElement2.getName().equals("DISPLAY")){
		    				    String display = ParaElement2.getText();
		    				    System.out.println("display:"+display);
		    	            }
		    			}
		    		}
		    	    else if (ParaElement1.getText().equals("LOGO")){
		    			Element ParaElement2 = (Element)iter.next();
		    			System.out.println("leslie3:"+"Name:"+ParaElement2.getName()+"   Text:"+ParaElement2.getText());
		    			if(ParaElement2.getName().equals("PARAM-URL")){
		    				String paramUrl = ParaElement2.getText();
		    				System.out.println("PARAM URL:"+paramUrl);
		    			}
		    		}
		    	}
		    }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	public void parseXML2(File file){
		try{
			SAXReader saxReader=new SAXReader();
			Document document = saxReader.read(file);
			Element versionNode = (Element)document.selectSingleNode("/ROOT/INFO/VERSION");
			String version = versionNode.getText();
			System.out.println("version:"+version);
			
			List list = document.selectNodes("//PARAM[@name='账单类型名称']");
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				Element element1 = (Element)iterator.next();
				Iterator iter = element1.elementIterator();
				while(iter.hasNext()){
					Element element2 = (Element)iter.next();
					if(element2.getName().equals("ETF-NAME")){
						String billTypeEtfNm = element2.getText();
						System.out.println("账单类型 ETF-NAME:"+billTypeEtfNm);
					}
				}
			}
			
		    list = document.selectNodes("//PARAM[@name='账单条数']");
		    iterator = list.iterator();
		    while(iterator.hasNext()){
		    	Element element1 = (Element)iterator.next();
		    	Iterator iter = element1.elementIterator();
		    	while(iter.hasNext()){
		    		Element element2 = (Element)iter.next();
		    		if(element2.getName().equals("ETF-NAME")){
		    			String billnumEtfnm = element2.getText();
		    			System.out.println("账单条数 ETF-NAME:"+billnumEtfnm);
		    		}else if(element2.getName().equals("DISPLAY")){
		    			String display = element2.getText();
		    			System.out.println("DISPLAY:"+display);
		    		}
		    		
		    	}
		    }
		    
		    list = document.selectNodes("//PARAM[@name='LOGO']");
		    iterator = list.iterator();
		    while(iterator.hasNext()){
		    	Element element1 = (Element)iterator.next();
		    	Iterator iter = element1.elementIterator();
		    	while(iter.hasNext()){
		    		Element element2 = (Element)iter.next();
		    		if(element2.getName().equals("PARAM-URL")){
		    			String paramUrl = element2.getText();
		    			System.out.println("Logo url:"+paramUrl);
		    		}
		    	}
		    }
		}catch(DocumentException e){
			e.printStackTrace();
		}
	}
	public HashMap parseXML3(File file){
		HashMap<String,String> map = new HashMap<String,String>();
		try{
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			// 获取version信息
			Element versionNode = (Element)document.selectSingleNode("/ROOT/INFO/VERSION");
			String version = versionNode.getText();
			map.put("version", version);
			
			Element billTypeNmNode = (Element)document.selectSingleNode("//PARAM[@name='账单类型名称']/PARAM-VALUE[@name='etf-name']");
			String billTypeNmEtf = billTypeNmNode.getText();
			map.put("billTypeNmEtf", billTypeNmEtf);
			
			Element billNumNode = (Element)document.selectSingleNode("//PARAM[@name='账单条数']/PARAM-VALUE[@name='etf-name']");
			String billNumEtf = billNumNode.getText();
			map.put("billNumEtf", billNumEtf);
			Element billNumDisplayNode = (Element)document.selectSingleNode("//PARAM[@name='账单条数']/PARAM-VALUE[@name='display']");
			String billNumDisplayEtf = billNumDisplayNode.getText();
			map.put("billNumDisplayEtf", billNumDisplayEtf);
			
			Element logoUrlNode = (Element)document.selectSingleNode("//PARAM[@name='LOGO']/PARAM-VALUE[@name='logo-url']");
			String logoUrl = logoUrlNode.getText();
			map.put("logoUrl", logoUrl);
		}catch (DocumentException e){
			e.printStackTrace();
		}
		return map;
	}
	public HashMap<String, String> parseXML10(File file){
		HashMap<String,String> hashMap = new HashMap<String,String>();
		try{
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			Element versionNode = (Element)document.selectSingleNode("/ROOT/INFO/VERSION");
			String version = versionNode.getText();
			hashMap.put("version", version);
			
			Element billDateNode = (Element)document.selectSingleNode("//ROW[@name='账单日']/PARAM/PARAM-VALUE[@name='etf-name']");
			String billDateEtf = billDateNode.getText();
			hashMap.put("billDateEtf", billDateEtf);
			
			Element billExpDateNode = (Element)document.selectSingleNode("//ROW[@name='到期还款日']/PARAM/PARAM-VALUE[@name='etf-name']"	);
			String billExpDateEtf = billExpDateNode.getText();
			hashMap.put("billExpDateEtf", billExpDateEtf);
			
			Element lastBillAmtNode = (Element)document.selectSingleNode("//ROW[@name='上期账单金额']/PARAM/PARAM-VALUE[@name='etf-name']");
			String lastBillAmtEtf = lastBillAmtNode.getText();
			hashMap.put("lastBillAmtEtf", lastBillAmtEtf);
			
			Element lastPayAmtNode = (Element)document.selectSingleNode("//ROW[@name='上期还款金额']/PARAM/PARAM-VALUE[@name='etf-name']");
			String lastPayAmtEtf = lastPayAmtNode.getText();
			hashMap.put("lastPayAmtEtf", lastPayAmtEtf);
			
			Element billAmtNode = (Element)document.selectSingleNode("//ROW[@name='本期账单金额']/PARAM/PARAM-VALUE[@name='etf-name']");
			String billAmtEtf = billAmtNode.getText();
			hashMap.put("billAmtEtf", billAmtEtf);

			Element adjustAmtNode = (Element)document.selectSingleNode("//ROW[@name='本期调整金额']/PARAM/PARAM-VALUE[@name='etf-name']");
			String adjustAmtEtf = adjustAmtNode.getText();
			hashMap.put("adjustAmtEtf", adjustAmtEtf);

			Element cycInterestNode = (Element)document.selectSingleNode("//ROW[@name='循环利息']/PARAM/PARAM-VALUE[@name='etf-name']");
			String cycInterestEtf = cycInterestNode.getText();
			hashMap.put("cycInterestEtf", cycInterestEtf);

            // 本期应还金额取 本期账单金额
			
			Element minPayAmtNode = (Element)document.selectSingleNode("//ROW[@name='最低还款金额']/PARAM/PARAM-VALUE[@name='etf-name']");
			String minPayAmtEtf = minPayAmtNode.getText();
			hashMap.put("minPayAmtEtf", minPayAmtEtf);

		}catch (DocumentException e){
			e.printStackTrace();
		}
		return hashMap;
	}
	public HashMap parseXML15(File file){
		HashMap<String,String> hashMap = new HashMap<String,String>();
		try{
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			Element versionNode = (Element)document.selectSingleNode("/ROOT/INFO/VERSION");
			String version = versionNode.getText();
			hashMap.put("version", version);
			
			Element checkDateNode = (Element)document.selectSingleNode("//ROW[@name='抄表日']/PARAM/PARAM-VALUE[@name='etf-name']");
			String checkDateEtf = checkDateNode.getText();
			hashMap.put("checkDateEtf", checkDateEtf);
			
			Element billExpDateNode = (Element)document.selectSingleNode("//ROW[@name='到期缴费日']/PARAM/PARAM-VALUE[@name='etf-name']"	);
			String billExpDateEtf = billExpDateNode.getText();
			hashMap.put("billExpDateEtf", billExpDateEtf);
			
			Element checkValueNode = (Element)document.selectSingleNode("//ROW[@name='本期抄表数']/PARAM/PARAM-VALUE[@name='etf-name']");
			String checkValueEtf = checkValueNode.getText();
			hashMap.put("checkValueEtf", checkValueEtf);
			
			Element useValueNode = (Element)document.selectSingleNode("//ROW[@name='本期用电量']/PARAM/PARAM-VALUE[@name='etf-name']");
			String useValueEtf = useValueNode.getText();
			hashMap.put("useValueEtf", useValueEtf);
			
			Element billAmtNode = (Element)document.selectSingleNode("//ROW[@name='本期缴费金额']/PARAM/PARAM-VALUE[@name='etf-name']");
			String billAmtEtf = billAmtNode.getText();
			hashMap.put("billAmtEtf", billAmtEtf);
		}catch(DocumentException e){
			e.printStackTrace();
		}
		return hashMap;
	}
	public HashMap<String,String> parseXML20(File file){
		HashMap<String,String> hashMap = new HashMap<String,String>();
		try{
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			Element versionNode = (Element)document.selectSingleNode("/ROOT/INFO/VERSION");
			String version = versionNode.getText();
			hashMap.put("version", version);
			
			Element billMonthNode = (Element)document.selectSingleNode("//ROW[@name='账单月份']/PARAM/PARAM-VALUE[@name='etf-name']");
			String billMonthEtf = billMonthNode.getText();
			hashMap.put("billMonthEtf", billMonthEtf);
			
			Element billExpDateNode = (Element)document.selectSingleNode("//ROW[@name='到期缴费日']/PARAM/PARAM-VALUE[@name='etf-name']"	);
			String billExpDateEtf = billExpDateNode.getText();
			hashMap.put("billExpDateEtf", billExpDateEtf);
			
			Element monthFeeNode = (Element)document.selectSingleNode("//ROW[@name='基本月租费']/PARAM/PARAM-VALUE[@name='etf-name']");
			String monthFeeEtf = monthFeeNode.getText();
			hashMap.put("monthFeeEtf", monthFeeEtf);
			
			Element valAddFeeNode = (Element)document.selectSingleNode("//ROW[@name='增值业务费']/PARAM/PARAM-VALUE[@name='etf-name']");
			String valAddFeeEtf = valAddFeeNode.getText();
			hashMap.put("valAddFeeEtf", valAddFeeEtf);
			
			Element collectFeeNode = (Element)document.selectSingleNode("//ROW[@name='移动代收费']/PARAM/PARAM-VALUE[@name='etf-name']");
			String collectFeeEtf = collectFeeNode.getText();
			hashMap.put("collectFeeEtf", collectFeeEtf);

			Element airFeeNode = (Element)document.selectSingleNode("//ROW[@name='通话费']/PARAM/PARAM-VALUE[@name='etf-name']");
			String airFeeEtf = airFeeNode.getText();
			hashMap.put("airFeeEtf", airFeeEtf);

			Element messageFeeNode = (Element)document.selectSingleNode("//ROW[@name='点对点短信费']/PARAM/PARAM-VALUE[@name='etf-name']");
			String messageFeeEtf = messageFeeNode.getText();
			hashMap.put("messageFeeEtf", messageFeeEtf);

			Element favourFeeNode = (Element)document.selectSingleNode("//ROW[@name='优惠金额合计']/PARAM/PARAM-VALUE[@name='etf-name']");
			String favourFeeEtf = favourFeeNode.getText();
			hashMap.put("favourFeeEtf", favourFeeEtf);
			
			Element billAmtNode = (Element)document.selectSingleNode("//ROW[@name='账单总金额']/PARAM/PARAM-VALUE[@name='etf-name']");
			String billAmtEtf = billAmtNode.getText();
			hashMap.put("billAmtEtf", billAmtEtf);
			
		}catch(DocumentException e){
			e.printStackTrace();
		}
		return hashMap;
	}
	public static void main(String[] argv){
		Test test1 = new Test();
//		test1.parseXML1(new File("l:/my/MyJava/src/examples/java/parsexml/dom4j/test1.xml"));
//		test1.parseXML2(new File("l:/my/MyJava/src/examples/java/parsexml/dom4j/test2.xml"));

//		HashMap map=test1.parseXML3(new File("l:/my/MyJava/src/examples/java/parsexml/dom4j/test3.xml"));
//	    System.out.println("版本:"+map.get("version"));
//	    System.out.println("账单名称:"+map.get("billTypeNmEtf"));
//	    System.out.println("账单数:"+map.get("billNumEtf"));
//	    System.out.println("账单数display:"+map.get("billNumDisplayEtf"));
//	    System.out.println("LOGO URL:"+map.get("logoUrl"));
		
//		HashMap hashmap=test1.parseXML10(new File("l:/my/MyJava/src/examples/java/parsexml/dom4j/test10.xml"));
//		System.out.println("版本:"+hashmap.get("version"));
//		System.out.println("账单日:"+hashmap.get("billDateEtf"));
//		System.out.println("到期还款日:"+hashmap.get("billExpDateEtf"));
//		System.out.println("上期账单金额:"+hashmap.get("lastBillAmtEtf"));
//		System.out.println("上期还款金额:"+hashmap.get("lastPayAmtEtf"));
//		System.out.println("本期账单金额:"+hashmap.get("billAmtEtf"));
//		System.out.println("本期调整金额:"+hashmap.get("adjustAmtEtf"));
//		System.out.println("循环利息:"+hashmap.get("cycInterestEtf"));
//		System.out.println("本期应还金额:"+hashmap.get("billAmtEtf"));
//		System.out.println("最低还款金额:"+hashmap.get("minPayAmtEtf"));
		
//		HashMap hashmap=test1.parseXML15(new File("l:/my/MyJava/src/examples/java/parsexml/dom4j/test15.xml"));
//		System.out.println("版本:"+hashmap.get("version"));
//		System.out.println("抄表日:"+hashmap.get("checkDateEtf"));
//		System.out.println("到期缴费日:"+hashmap.get("billExpDateEtf"));
//		System.out.println("本期抄表数:"+hashmap.get("checkValueEtf"));
//		System.out.println("本期用电量:"+hashmap.get("useValueEtf"));
//		System.out.println("本期缴费金额:"+hashmap.get("billAmtEtf"));
		
		HashMap hashmap=test1.parseXML20(new File("l:/my/java/MyJava/src/main/api/org/dom4j/test20.xml"));
		System.out.println("版本:"+hashmap.get("version"));
		System.out.println("账单月份:"+hashmap.get("billMonthEtf"));
		System.out.println("到期缴费日:"+hashmap.get("billExpDateEtf"));
		System.out.println("基本月租费:"+hashmap.get("monthFeeEtf"));
		System.out.println("增值业务费:"+hashmap.get("valAddFeeEtf"));
		System.out.println("移动代收费:"+hashmap.get("collectFeeEtf"));
		System.out.println("通话费:"+hashmap.get("airFeeEtf"));
		System.out.println("点对点短信费:"+hashmap.get("messageFeeEtf"));
		System.out.println("优惠金额合计:"+hashmap.get("favourFeeEtf"));
		System.out.println("账单总金额:"+hashmap.get("billAmtEtf"));
	}
}
