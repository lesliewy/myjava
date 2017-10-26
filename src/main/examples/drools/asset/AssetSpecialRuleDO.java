package examples.drools.asset;

import java.util.List;

/**
 * Created by leslie on 2017/10/24.
 */
public class AssetSpecialRuleDO<T> {

    private List<T> values;

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
