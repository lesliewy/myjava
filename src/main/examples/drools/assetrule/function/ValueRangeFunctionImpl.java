package examples.drools.assetrule.function;

import examples.drools.assetrule.data.DroolsExecuteContext;
import examples.drools.assetrule.rule.Range;
import examples.drools.assetrule.rule.ValueRangeRule;

import java.util.Map;

/**
 * Created by leslie on 2017/10/20.
 */
public class ValueRangeFunctionImpl {

    public void execute(ValueRangeRule valueRangeRule, DroolsExecuteContext context) {
        System.out.println("=== valueRangeRule: ");
        if (valueRangeRule.getValueRange() != null) {
            for (Map.Entry<Integer, Map<String, Range>> entry : valueRangeRule.getValueRange().entrySet()) {
                Map<String, Range> rangeMap = entry.getValue();
                for(Map.Entry<String, Range> range : rangeMap.entrySet()){

                    System.out.println("seq: " + entry.getKey() + ", range: " + ((Range) range.getValue()).getMin() + " - "
                                       + ((Range) range.getValue()).getMax());
                }
            }
        }
        String result = context.getCallStrategyDroolsOutput().getResult();
        result += "; this is valueRangeRule result";
        context.getCallStrategyDroolsOutput().setResult(result);
    }

}
