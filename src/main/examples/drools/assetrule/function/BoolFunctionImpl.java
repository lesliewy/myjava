package examples.drools.assetrule.function;

import examples.drools.assetrule.rule.BoolRuleDetail;
import examples.drools.assetrule.rule.Rule;
import org.apache.commons.lang.builder.ToStringBuilder;

import examples.drools.assetrule.data.DroolsExecuteContext;
import examples.drools.assetrule.data.RulesInnerOutput;
import examples.drools.assetrule.rule.BoolRule;
import org.mockito.internal.matchers.InstanceOf;

/**
 * Created by leslie on 2017/10/18.
 */
public class BoolFunctionImpl {

    public void execute(Rule<BoolRuleDetail> boolRule, DroolsExecuteContext context) {
        System.out.println("=== boolRule: " + ToStringBuilder.reflectionToString(boolRule));
        // 按顺序执行.
        RulesInnerOutput innerOutput = context.getRulesInnerOutput();
        innerOutput.getHitRules().put(0, boolRule.getValues().get(0));
    }

}
