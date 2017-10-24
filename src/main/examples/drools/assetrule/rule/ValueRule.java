package examples.drools.assetrule.rule;

import java.util.Map;

/**
 * Created by leslie on 2017/10/18.
 */
public class ValueRule extends Rule<String> {

    private String              ruleUuid;

    private Map<Integer, Map<String, String>> values;

    public String getRuleUuid() {
        return ruleUuid;
    }

    public void setRuleUuid(String ruleUuid) {
        this.ruleUuid = ruleUuid;
    }

    public Map<Integer, Map<String, String>> getValues() {
        return values;
    }

    public void setValues(Map<Integer, Map<String, String>> values) {
        this.values = values;
    }
}
