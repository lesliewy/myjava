package examples.drools.assetrule.data;

import java.util.concurrent.ConcurrentHashMap;

import examples.drools.assetrule.rule.RuleDetail;

/**
 * Created by leslie on 2017/10/18.
 */
public class RulesInnerOutput extends RulesOutput {

    // key: ruleSetName
    private ConcurrentHashMap<Integer, RuleDetail> hitRules;

    public ConcurrentHashMap<Integer, RuleDetail> getHitRules() {
        return hitRules;
    }

    public void setHitRules(ConcurrentHashMap<Integer, RuleDetail> hitRules) {
        this.hitRules = hitRules;
    }
}
