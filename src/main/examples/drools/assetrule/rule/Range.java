package examples.drools.assetrule.rule;

import java.math.BigDecimal;

/**
 * Created by leslie on 2017/10/20.
 */
public class Range<T> {

    private T min;
    private boolean containsMin;
    private T max;
    private boolean containsMax;

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public boolean isContainsMin() {
        return containsMin;
    }

    public void setContainsMin(boolean containsMin) {
        this.containsMin = containsMin;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public boolean isContainsMax() {
        return containsMax;
    }

    public void setContainsMax(boolean containsMax) {
        this.containsMax = containsMax;
    }
}
