package api.org.junit4;

public class Calculator {
	double result = 0;
	public double add(double a1, double a2){
		result = a1 + a2;
		return result;
	}
	
	public double getResult(){
		return result;
	}
}
