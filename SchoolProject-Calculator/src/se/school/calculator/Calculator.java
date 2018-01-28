package se.school.calculator;

public class Calculator {

	public static long add(long num1, long num2) {
		return num1 + num2;
	}
	
	public static long subtract(long num1, long num2) {
		return num1 - num2;
	}	
	
	public static long divide(long num1, long num2) {
		if (num2 == 0)
			return 0;
		
		return num1 / num2;
	}
	
	public static long multiply(long num1, long num2) {
		return num1 * num2;
	}
}
