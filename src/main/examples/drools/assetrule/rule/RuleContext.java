package examples.drools.assetrule.rule;

import java.util.List;

/**
 * Created by leslie on 2017/10/18.
 */
public class RuleContext {

    private List<DrlContext> drlContexts;

    public List<DrlContext> getDrlContexts() {
        return drlContexts;
    }

    public void setDrlContexts(List<DrlContext> drlContexts) {
        this.drlContexts = drlContexts;
    }
}
