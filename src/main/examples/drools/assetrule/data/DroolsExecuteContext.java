package examples.drools.assetrule.data;

/**
 * Created by leslie on 2017/10/18.
 */
public class DroolsExecuteContext {

    private RulesInput       rulesInput;
    private RulesOutput      rulesOutput;
    private RulesInnerOutput rulesInnerOutput;

    public RulesInput getRulesInput() {
        return rulesInput;
    }

    public void setRulesInput(RulesInput rulesInput) {
        this.rulesInput = rulesInput;
    }

    public RulesOutput getRulesOutput() {
        return rulesOutput;
    }

    public void setRulesOutput(RulesOutput rulesOutput) {
        this.rulesOutput = rulesOutput;
    }

    public RulesInnerOutput getRulesInnerOutput() {
        return rulesInnerOutput;
    }

    public void setRulesInnerOutput(RulesInnerOutput rulesInnerOutput) {
        this.rulesInnerOutput = rulesInnerOutput;
    }
}
