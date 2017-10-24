package examples.drools.assetrule.data;

/**
 * Created by leslie on 2017/10/18.
 */
public class CallStrategyDroolsInput {

    boolean isChinese;
    int     age;
    String  sex;

    public boolean isChinese() {
        return isChinese;
    }

    public void setChinese(boolean chinese) {
        isChinese = chinese;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
