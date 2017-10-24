package examples.drools.assetrule.rule;

import java.util.Map;

/**
 * Created by leslie on 2017/10/18.
 */
public class BoolRule extends Rule<Boolean>{

    private String  ruleUuid;
    private Boolean isRecording;
    private Boolean isMessage;
    private Boolean isRepair;
    private Boolean isChinese;

    private Map<Integer, Map<String, Boolean>> values;

    public String getRuleUuid() {
        return ruleUuid;
    }

    public void setRuleUuid(String ruleUuid) {
        this.ruleUuid = ruleUuid;
    }

    public Boolean getIsRecording() {
        return isRecording;
    }

    public void setIsRecording(Boolean isRecording) {
        this.isRecording = isRecording;
    }

    public Boolean getIsMessage() {
        return isMessage;
    }

    public void setIsMessage(Boolean isMessage) {
        this.isMessage = isMessage;
    }

    public Boolean getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Boolean isRepair) {
        this.isRepair = isRepair;
    }

    public Boolean getChinese() {
        return isChinese;
    }

    public void setChinese(Boolean chinese) {
        isChinese = chinese;
    }

    public Map<Integer, Map<String, Boolean>> getValues() {
        return values;
    }

    public void setValues(Map<Integer, Map<String, Boolean>> values) {
        this.values = values;
    }
}
