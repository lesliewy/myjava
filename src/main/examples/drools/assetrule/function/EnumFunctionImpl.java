package examples.drools.assetrule.function;

import examples.drools.assetrule.rule.EnumRule;
import org.apache.commons.lang.builder.ToStringBuilder;

import examples.drools.assetrule.data.DroolsExecuteContext;

/**
 * Created by leslie on 2017/10/18.
 */
public class EnumFunctionImpl {

    public void execute(EnumRule enumRule, DroolsExecuteContext context) {
        System.out.println("=== enumRule: " + ToStringBuilder.reflectionToString(enumRule));
        String result = context.getCallStrategyDroolsOutput().getResult();
        result += "; this is enumRule result";
        context.getCallStrategyDroolsOutput().setResult(result);
    }

}
