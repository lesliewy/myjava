package examples.drools.assetrule.function;

import examples.drools.assetrule.rule.Rule;
import examples.drools.assetrule.rule.ValueRuleDetail;
import org.apache.commons.lang.builder.ToStringBuilder;

import examples.drools.assetrule.data.DroolsExecuteContext;
import examples.drools.assetrule.data.RulesInnerOutput;
import examples.drools.assetrule.rule.ValueRule;

/**
 * Created by leslie on 2017/10/18.
 */
public class ValueFunctionImpl {

    public void execute(Rule<ValueRuleDetail> valueRule, DroolsExecuteContext context) {
        System.out.println("=== valueRule: " + ToStringBuilder.reflectionToString(valueRule));
        RulesInnerOutput innerOutput = context.getRulesInnerOutput();
        innerOutput.getHitRules().put(2, valueRule.getValues().get(2));
    }

}
