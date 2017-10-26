package examples.drools.assetrule.data;

/**
 * Created by leslie on 2017/10/18.
 */
public class RulesOutput {

    private boolean accepted;

    private String  ruleSetName;

    private String  ruleName;

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getRuleSetName() {
        return ruleSetName;
    }

    public void setRuleSetName(String ruleSetName) {
        this.ruleSetName = ruleSetName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
