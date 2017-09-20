package wy.enum1;

import org.junit.Test;

/**
 * Created by leslie on 2017/8/14.
 */
public class Test1 {

    @Test
    public void test1(){
        String enumName = "INTERESTRADAR";
        ModuleEnum a = Enum.valueOf(ModuleEnum.class, enumName);
        System.out.println(a.getEventType());
    }
}

enum ModuleEnum {
    APPLY("进件","Loan"),

    ANTIFRAUD("反欺诈","Loan"),

    PREFILTER("预筛","PreFilter"),

    INTERESTRADAR("兴趣雷达", "InterestRadar"),

    AUTHENTICATION("认证","Authentication"),

    CREDIT("授信","Credit"),

    POSTLOAN("贷后", "Lending"),

    WITHDRAWALS("提现", "Withdraw");

    private String displayName;

    private String eventType;

    ModuleEnum(String displayName,String eventType){
        this.displayName = displayName;
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public String getDisplayName() {
        return displayName;
    }
}
