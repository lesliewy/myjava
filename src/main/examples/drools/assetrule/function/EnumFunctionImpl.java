package examples.drools.assetrule.function;

import examples.drools.assetrule.rule.EnumRuleDetail;
import examples.drools.assetrule.rule.Rule;
import org.apache.commons.lang.builder.ToStringBuilder;

import examples.drools.assetrule.data.DroolsExecuteContext;
import examples.drools.assetrule.data.RulesInnerOutput;
import examples.drools.assetrule.rule.EnumRule;

/**
 * Created by leslie on 2017/10/18.
 */
public class EnumFunctionImpl {

    public void execute(Rule<EnumRuleDetail> enumRule, DroolsExecuteContext context) {
        System.out.println("=== enumRule: " + ToStringBuilder.reflectionToString(enumRule));
        RulesInnerOutput innerOutput = context.getRulesInnerOutput();
        innerOutput.getHitRules().put(3, enumRule.getValues().get(3));
    }

}
