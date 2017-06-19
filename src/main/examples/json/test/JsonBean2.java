package examples.json.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JsonBean2 {
    String a_str;
    int b_int;
    HashMap c_map = new HashMap();
    boolean a_boolean;
    String value;
    int col;
    int row;
    List list = new ArrayList();
    Timestamp timestamp;
    Date date ;

    public String getA_str() {
        return a_str;
    }

    public void setA_str(String aStr) {
        a_str = aStr;
    }

    public int getB_int() {
        return b_int;
    }

    public void setB_int(int bInt) {
        b_int = bInt;
    }

    public HashMap getC_map() {
        return c_map;
    }

    public void setC_map(HashMap cMap) {
        c_map = cMap;
    }

    public boolean isA_boolean() {
        return a_boolean;
    }

    public void setA_boolean(boolean aBoolean) {
        a_boolean = aBoolean;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
