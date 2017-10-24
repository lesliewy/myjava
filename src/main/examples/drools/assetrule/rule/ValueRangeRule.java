package examples.drools.assetrule.rule;

import java.util.Map;

/**
 * Created by leslie on 2017/10/20.
 */
public class ValueRangeRule extends Rule<Range> {

    private Map<Integer, Map<String, Range>> valueRange;

    public Map<Integer, Map<String, Range>> getValueRange() {
        return valueRange;
    }

    public void setValueRange(Map<Integer, Map<String, Range>> valueRange) {
        this.valueRange = valueRange;
    }
}
