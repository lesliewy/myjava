package dp.structuralpattern.facade.example;

public class Client {
	private static SecurityFacade security;
	public static void main(String[] args){
		security.activate();
	}
}
