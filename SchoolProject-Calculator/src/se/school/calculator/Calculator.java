package se.school.calculator;

import java.util.ArrayList;

/** 
 * The class Calculator handles the calculations.
 */
public class Calculator {

	private ArrayList<String> equation = new ArrayList<String>();

	/**
	 * Reads the ArrayList and builds a string
	 */
	public String equationToString() {
		StringBuilder sb = new StringBuilder();

		for (String element : equation) {
			sb.append(element);
		}
		return sb.toString();
	}

	/**
	 * Resets the ArrayList equation
	 */
	public String clear() {
		equation.clear();
		return "0"; // To show 0 in the display
	}

	/**
	 * Adds a string to the ArrayList
	 */
	public String toEquation(String o) {

		if (intCheck(o)) {
			equation.add(o.toString());
		} else if (equation.size() > 0) {
			if (intCheck(equation.get(equation.size() - 1)))
				equation.add(o);
			else
				equation.set(equation.size() - 1, o);
		}

		return equationToString();
	}

	/**
	 * Gets the equation and the calculated result
	 */
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
			}
			if (!first && intCheck(equation.get(i))) {
				temp2 = temp2 + equation.get(i);
			}
			if (!first && !intCheck(equation.get(i))) {
				temp = calculate(Integer.parseInt(temp), Integer.parseInt(temp2), arithmetic);
				temp2 = "";
				arithmetic = equation.get(i);
			}
			if (!first && i == equation.size() - 1) {
				temp = calculate(Integer.parseInt(temp), Integer.parseInt(temp2), arithmetic);
			}
		}

		equation = new ArrayList<String>();
		for (char c : temp.toCharArray()) {
			equation.add(String.valueOf(c));
		}

		return temp;
	}
	
	/**
	 * Checks whether the value is an integer
	 */
	private boolean intCheck(String o) {
		try {
			Integer.parseInt(o);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Calculates the result
	 */
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
