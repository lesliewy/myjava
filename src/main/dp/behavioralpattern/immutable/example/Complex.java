package dp.behavioralpattern.immutable.example;

import java.io.Serializable;

public final class Complex extends Number implements Serializable, Cloneable,
		Comparable {
	/**
	 * 虚数单位
	 */
	static final public Complex i = new Complex(0.0,1.0);
	/**
	 * 复数的实部
	 */
	private double re;
	/**
	 * 复数的虚部
	 */
	private double im;
	/**
	 * 构造函数,根据传入的复数，再构造一个数学值相等的复数.
	 */
	public Complex(Complex z){
		re=z.re;
		im=z.im;
	}
	/**
	 * 构造函数，根据传入的实部和虚部，再构造一个复数对象
	 */
	public Complex(double re,double im){
		this.re=re;
		this.im=im;
	}
	/**
	 * 构造函数，根据一个实部构造复数对象
	 */
	public Complex(double re){
		this.re=re;
		this.im=0.0;
	}
	/**
	 * 默认构造函数，构造一个为0的复数
	 */
	public Complex(){
		this.re=0.0;
		this.im=0.0;
	}
	/**
	 * 把本复数与作为参数传入的复数比较
	 */
    public boolean equals(Complex z){
    	return(re==z.re && im==z.im);
    }
    /**
	 * 把本对象与作为参数传入的对象比较
	 */
    public boolean equals(Object obj){
    	if(obj == null){
    		return false;
    	}else if(obj instanceof Complex){
    		return equals((Complex)obj);
    	}else{
    		return false;
    	}
    }
    public int hashCode(){
    	long re_bits=Double.doubleToLongBits(re);
    	long im_bits=Double.doubleToLongBits(im);
    	return (int)((re_bits^im_bits)^((re_bits^im_bits)>>32));
    }
    /**
     * 返回本复数的实部
     */
    public double real(){
    	return re;
    }
    /**
     * 返回本复数的虚部
     */
    public double imag(){
    	return im;
    }
    /**
     * 静态方法，返回作为参数传入的复数的实部
     */
    public static double real(Complex z){
    	return z.re;
    }
    /**
     * 静态方法，返回作为参数传入的复数的虚部
     */
    public static double imag(Complex z){
    	return z.im;
    }
    /**
     * 静态方法,返回作为参数传入的复数的相反数
     */
    public static Complex negate(Complex z){
    	return new Complex(-z.re,-z.im);
    }
    /**
     * 静态方法，返回作为参数传入的复数的复共轭
     */
    
	
	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int doCompare(Object paramObject1, Object paramObject2) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
