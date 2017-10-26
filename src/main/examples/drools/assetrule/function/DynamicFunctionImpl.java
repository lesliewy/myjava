package examples.drools.assetrule.function;

import org.apache.commons.lang.builder.ToStringBuilder;

import examples.drools.assetrule.data.DroolsExecuteContext;
import examples.drools.assetrule.data.RulesInnerOutput;
import examples.drools.assetrule.rule.DynamicRuleDetail;
import examples.drools.assetrule.rule.Rule;

/**
 * Created by leslie on 2017/10/20.
 */
public class DynamicFunctionImpl {

    public void execute(Rule<DynamicRuleDetail> dynamicRule, DroolsExecuteContext context) {
        System.out.println("=== dynamicRule: " + ToStringBuilder.reflectionToString(dynamicRule));
        // 根据ruleName, 获取input 的map中的参数.
        RulesInnerOutput innerOutput = context.getRulesInnerOutput();
        innerOutput.getHitRules().put(4, dynamicRule.getValues().get(4));
    }

}
