/**
 * Project Name:MyJava  
 * File Name:BigDecimal2.java  
 * Package Name:api.math.BigDecimal  
 * Date:Apr 26, 201311:57:30 PM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.math.BigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Test;

/**
 */
public class TestBigDecimal1 {
    /**
     * setScale(int): 可以设置小数点的位数，但是要保证设置完的值和之前的值相等, 否则抛出异常: java.lang.ArithmeticException: Rounding necessary
     */
    @Test
    public void test1(){
        BigDecimal bg = new BigDecimal("2.323");
        System.out.println(bg.setScale(2));
        System.out.println(bg.setScale(2, BigDecimal.ROUND_HALF_DOWN));
    }
    
    /**
     * 关于四舍五入:  Math.round
     */
    @Test
    public void test2(){
    	System.out.println("Math.round(12.5): " + Math.round(12.5));
    	System.out.println("Math.round(-12.4): " + Math.round(-12.4));
    	System.out.println("Math.round(-12.5): " + Math.round(-12.5));
    	System.out.println("Math.round(-12.6): " + Math.round(-12.6));
    }
    
    /**
     * 按照四舍五入对于银行来说:
     *  存款利息:  0.000,  0.001,  0.002,  0.003,  0.004   舍掉的是存款利息，相当于银行赚钱了
     *  贷款利息:  0.005,  0.006,  0.007,  0.008,  0.009   入的是贷款利息，相当于银行赔钱了, 分别是(0.005, 0.004, 0.003, 0.002, 0.001).
     *  0.000 + 0.001 + 0.002 + 0.003 + 0.004 - 0.005 - 0.004 - 0.003 - 0.002 - 0.001 = -0.005 银行亏钱了.
     * 所以发明了银行家算法:
     *   舍去位的数值小于5时，直接舍去     11.554 = 11.54
     *   舍去位的数值大于5时，进位后舍去   11.556 = 11.56
     *   舍去位的数值等于5时，如果5后面是非0值，进位后舍去    11.5551 = 11.56
     *                    如果5后面是0值，根据5前面数字的奇偶性来判断，奇数进位，偶数舍去    11.555 = 11.56   11.545 = 11.54
     */
    @Test
    public void test3(){
    	// 银行家算法
    	BigDecimal d1 = new BigDecimal("11.545");
    	BigDecimal d2 = new BigDecimal("11.555");
    	System.out.println(d1.setScale(2, RoundingMode.HALF_EVEN));
    	System.out.println(d2.setScale(2, RoundingMode.HALF_EVEN));
    	
    	// 四舍五入
    	System.out.println(d1.setScale(2, RoundingMode.HALF_UP));
    	System.out.println(d2.setScale(2, RoundingMode.HALF_UP));
    }
    
    /**
     * 实现保留位的方法
     */
    @Test
    public void test4(){
    	double f = 111.3456;
    	
    	// BigDecimal
    	BigDecimal d = new BigDecimal(f);
    	double f2 = d.setScale(2, RoundingMode.HALF_UP).doubleValue();
    	System.out.println("BigDecimal 保留位: " + f2);
    	
    	// DecimalFormat
    	DecimalFormat df = new DecimalFormat("#.00");
    	f2 = Double.valueOf(df.format(f));
    	System.out.println("DecimalFormat 保留位: " + f2);
    	
    	// String.format
    	String stringFormat = String.format("%.2f", f);
    	System.out.println("String.format 保留位: " + stringFormat);
    	
    }
}
