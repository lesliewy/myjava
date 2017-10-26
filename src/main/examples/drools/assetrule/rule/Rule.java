package examples.drools.assetrule.rule;

import java.util.Map;

/**
 * Created by leslie on 2017/10/20.
 */
public class Rule<T> {

    private Map<Integer, T> values;

    public Map<Integer, T> getValues() {
        return values;
    }

    public void setValues(Map<Integer, T> values) {
        this.values = values;
    }
}
