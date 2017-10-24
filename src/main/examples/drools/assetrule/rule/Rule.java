package examples.drools.assetrule.rule;

/**
 * Created by leslie on 2017/10/20.
 */
public class Rule<T> {

    private String fieldName;

    private T      fieldValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public T getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(T fieldValue) {
        this.fieldValue = fieldValue;
    }
}
