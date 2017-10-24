package examples.drools.assetrule.rule;

/**
 * Created by leslie on 2017/10/18.
 */
public class DrlContext {

    private static final String SPLIT = "/";

    private String              ruleType;
    private String              ruleMethod;
    private String              ruleCondition;
    private String              executeString;
    private String              condition;
    private String              varDefine;
    private String              salience;
    private String              alias;
    private String              desc;

    public DrlContext(String ruleType, String ruleMethod){
        this.ruleType = ruleType;
        this.ruleMethod = ruleMethod;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleMethod() {
        return ruleMethod;
    }

    public void setRuleMethod(String ruleMethod) {
        this.ruleMethod = ruleMethod;
    }

    public String getRuleCondition() {
        return ruleCondition;
    }

    public void setRuleCondition(String ruleCondition) {
        this.ruleCondition = ruleCondition;
    }

    public String getExecuteString() {
        return executeString;
    }

    public void setExecuteString(String executeString) {
        this.executeString = executeString;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getVarDefine() {
        return varDefine;
    }

    public void setVarDefine(String varDefine) {
        this.varDefine = varDefine;
    }

    public String getSalience() {
        return salience;
    }

    public void setSalience(String salience) {
        this.salience = salience;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
