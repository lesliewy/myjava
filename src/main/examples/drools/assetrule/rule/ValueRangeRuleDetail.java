package examples.drools.assetrule.rule;

/**
 * Created by leslie on 2017/10/20.
 */
public class ValueRangeRuleDetail<T> extends RuleDetail {

    private Range<T> range;

    public Range<T> getRange() {
        return range;
    }

    public void setRange(Range<T> range) {
        this.range = range;
    }
}
