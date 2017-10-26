package examples.drools.assetrule.function;

import java.util.Map;

import examples.drools.assetrule.data.DroolsExecuteContext;
import examples.drools.assetrule.data.RulesInnerOutput;
import examples.drools.assetrule.rule.Range;
import examples.drools.assetrule.rule.Rule;
import examples.drools.assetrule.rule.ValueRangeRuleDetail;

/**
 * Created by leslie on 2017/10/20.
 */
public class ValueRangeFunctionImpl {

    public void execute(Rule<ValueRangeRuleDetail> valueRangeRule, DroolsExecuteContext context) {
        System.out.println("=== valueRangeRule: ");
        if (valueRangeRule.getValues() != null) {
            for (Map.Entry<Integer, ValueRangeRuleDetail> entry : valueRangeRule.getValues().entrySet()) {
                ValueRangeRuleDetail valueRangeRuleDetail = entry.getValue();
                Range range = valueRangeRuleDetail.getRange();
                System.out.println("seq: " + entry.getKey() + ", range: " + range.getMin() + " - " + range.getMax());
            }
        }
        RulesInnerOutput innerOutput = context.getRulesInnerOutput();
        innerOutput.getHitRules().put(1, valueRangeRule.getValues().get(1));
    }

}
