package examples.drools.assetrule.rule;

/**
 * Created by leslie on 2017/10/18.
 */
public class DynamicRuleDetail extends RuleDetail {

    private String  unit;

    private Integer value;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
