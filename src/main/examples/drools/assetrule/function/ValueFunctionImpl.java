package examples.drools.assetrule.function;

import examples.drools.assetrule.rule.ValueRule;
import org.apache.commons.lang.builder.ToStringBuilder;

import examples.drools.assetrule.data.DroolsExecuteContext;

/**
 * Created by leslie on 2017/10/18.
 */
public class ValueFunctionImpl {

    public void execute(ValueRule valueRule, DroolsExecuteContext context) {
        System.out.println("=== valueRule: " + ToStringBuilder.reflectionToString(valueRule));
        String result = context.getCallStrategyDroolsOutput().getResult();
        result += "; this is valueRule result";
        context.getCallStrategyDroolsOutput().setResult(result);
    }

}
