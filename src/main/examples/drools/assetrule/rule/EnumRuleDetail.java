package examples.drools.assetrule.rule;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by leslie on 2017/10/18.
 */
public class EnumRuleDetail extends RuleDetail {

    private Set<String> value;

    public Set<String> getValue() {
        return value;
    }

    public void setValue(Set<String> value) {
        this.value = value;
    }
}
