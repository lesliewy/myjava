package examples.drools.assetrule.rule;

import java.math.BigDecimal;

/**
 * Created by leslie on 2017/10/20.
 */
public class Range {

    private BigDecimal min;
    private boolean containsMin;
    private BigDecimal max;
    private boolean containsMax;

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }
}
