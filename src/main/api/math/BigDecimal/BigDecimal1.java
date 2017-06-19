package api.math.BigDecimal;

import java.math.BigDecimal;

public class BigDecimal1 {
	public static void main(String[] argv){
		/*
		 * 参看 api文档中  BigDecimal(double val) 部分.
		 */
//		BigDecimal bigDec = new BigDecimal(2.945);        //近似 2.945，double型，不推荐用这种方式.
		BigDecimal bigDec = new BigDecimal("2.945");      // 精确的就是2.945，推荐用String constructor.
		BigDecimal bigDec1 = bigDec.setScale(2, BigDecimal.ROUND_HALF_UP);  // 四舍五入.
		System.out.println("bigDec:"+bigDec);
		System.out.println("bigDec1:"+bigDec1);
	}
}
