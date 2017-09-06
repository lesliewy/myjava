package wy.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fraudmetrix.creditcloud.bodyguard.api.intf.tigris.IntentScore;
import cn.fraudmetrix.creditcloud.bodyguard.object.request.BatchImportInfo;
import cn.fraudmetrix.creditcloud.object.response.QuickJSONResult;

/**
 * Created by maojianting on 16/1/5.
 */
public class DubboTest {

    private static IntentScore intentScore;

    public static void main(String[] args) {
        System.out.println("sun.boot.class.path:" + System.getProperty("sun.boot.class.path"));
        System.out.println("java.ext.dirs:" + System.getProperty("java.ext.dirs"));
        System.out.println("java.class.path:" + System.getProperty("java.class.path"));

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                                                                                                           "application-context-consumer.xml");
        classPathXmlApplicationContext.start();


/*
        PostLoan postLoan = (PostLoan) classPathXmlApplicationContext.getBean("postLoan");

        PostLoanMonitorSearchInfo postLoanMonitorSearchInfo = new PostLoanMonitorSearchInfo();
        postLoanMonitorSearchInfo.setOperator("fm");
        postLoanMonitorSearchInfo.setPartnerCode("creditcloud");
        postLoanMonitorSearchInfo.setPartnerKey("123456789");


        APIResult apiResult = postLoan.monitorList(postLoanMonitorSearchInfo);

        System.out.println("=====================================");
        System.out.println(apiResult);
        System.out.println("=====================================");
*/
        intentScore = (IntentScore)classPathXmlApplicationContext.getBean("tigrisConsumer");

//        testQueryIntentScores();
        testBatchImport();
    }
/*

    private static void testQueryIntentScores(){
        IntentScoreSearchInfo query = new IntentScoreSearchInfo();
        query.setOffset(0);
        query.setSize(10);
        query.setBeginTime("2017-06-10 17:00:00");
        query.setEndTime("2017-06-21 17:00:00");
        query.setPartnerCode("zhaoyao");
        query.setAppName("aaa");
        APIResult result = intentScore.queryIntentScores(query);
        System.out.println("result: " + result);
    }
*/

    private static void testBatchImport(){
        BatchImportInfo queryInfo = new BatchImportInfo();
        queryInfo.setPartnerCode("zhaoyao");
        queryInfo.setAppName("aaa");
        QuickJSONResult result = intentScore.batchImport(queryInfo);
        System.out.println("result: " + result);
    }

}
