package examples.drools.assetrule.function;

import examples.drools.assetrule.data.CallStrategyDroolsInput;
import examples.drools.assetrule.data.CallStrategyDroolsOutput;
import examples.drools.assetrule.data.DroolsExecuteContext;
import examples.drools.assetrule.rule.BoolRule;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by leslie on 2017/10/18.
 */
public class BoolFunctionImpl {

    public void execute(BoolRule boolRule, DroolsExecuteContext context) {
        System.out.println("=== boolRule: " + ToStringBuilder.reflectionToString(boolRule));
        CallStrategyDroolsInput input = context.getCallStrategyDroolsInput();
        CallStrategyDroolsOutput output = context.getCallStrategyDroolsOutput();
        String str = output.getResult();
        if (input.isChinese()) {
            str += "is a Chinese, ";
            System.out.println("is a Chinese.");
        } else {
            System.out.println("not a Chinese");
        }
        System.out.println(" boolRule isChinese: " + boolRule.getChinese());
        str += "age: " + input.getAge();
        output.setResult(str);
    }

}
