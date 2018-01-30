package se.school.calculator;

import java.util.ArrayList;

public class Calculator {

	private ArrayList<String> equation = new ArrayList<String>();

	public String clear() {	
		equation.clear();
		return "";
	}
	
	public String toEquation(String o) {
		StringBuilder sb = new StringBuilder();
		if (intCheck(o)) {
			equation.add(o.toString());
		} else if (equation.size() > 0) {
			if (intCheck(equation.get(equation.size() - 1)))
				equation.add(o);
			else
				equation.set(equation.size() - 1, o);
		}

		for (String i:equation) {
			sb.append(i);
		}
		return sb.toString();
	}

	public String result() {
		String temp = "";
		String arithmetic = "";
		String temp2 = "";
		boolean first = true;
		for (int i = 0; i < equation.size(); i++) {
			if (first && intCheck(equation.get(i))) 
				temp = temp + equation.get(i);
			if (first && !intCheck(equation.get(i))) {
				arithmetic = equation.get(i);
				first = false;
				i++;
				System.out.println("nr1: " + temp);
			}

			if (!first && intCheck(equation.get(i))) {
				temp2 = temp2 + equation.get(i);
			}
			if (!first && !intCheck(equation.get(i))) {
				System.out.println("nr2: " + temp2);
				System.out.println("calculate: " + temp + arithmetic + temp2);
				temp = calculate(Integer.parseInt(temp), Integer.parseInt(temp2), arithmetic);
				temp2 = "";
				arithmetic = equation.get(i);
			}
			if (!first && i == equation.size() - 1) {
				System.out.println("last: " + temp + arithmetic + temp2);
				temp = calculate(Integer.parseInt(temp), Integer.parseInt(temp2), arithmetic);
			}
		}
		System.out.println("result: " + temp);
		
		equation = new ArrayList<String>();
		for(char c : temp.toCharArray()) {
			equation.add(String.valueOf(c));
		}
		
		return temp;
	}

	private boolean intCheck(String o) {
		try {
			Integer.parseInt(o);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	private String calculate(Integer left, Integer right, String arithmetic) {
		switch (arithmetic) {
		case "+":
			return String.valueOf(Math.addExact(left, right));
		case "-":
			return String.valueOf(Math.subtractExact(left, right));
		case "*":
			return String.valueOf(Math.multiplyExact(left, right));
		case "/":
			return String.valueOf(Math.floorDiv(left, right));
		default:
			return "";
		}
	}
}
