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
		if (equation.size() > 0) {
			String prev = equation.get(equation.size() - 1);
			if (intCheck(o) && intCheck(prev)) {
				equation.set(equation.size() - 1, prev + o.toString());
			} else {
				if (intCheck(prev)) {
					equation.add(o.toString());
				} else {
					if (!intCheck(o)) {
						equation.set(equation.size() - 1, o);
					} else {
						equation.add(o);
					}
				}
			}
		} else {
			equation.add(o.toString());
		}
		return equationToString();
	}

	/**
	 * Gets the equation and the calculated result
	 */
	public String result() {
		calculate(true);
		calculate(false);

		return equationToString();
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
	 * Calculates the result with operator priority implemented.
	 */
	private String calculate(boolean priority) {
		if (equation.size() > 2) {
			ArrayList<String> newEquation = new ArrayList<String>();
			for (int i = 0; i < equation.size(); i++) {
				newEquation.add(equation.get(i));
				if (!intCheck(equation.get(i)) && equation.size() - 1 != i) {

					String left = equation.get(i - 1);
					String arithmetic = equation.get(i);
					String right = equation.get(i + 1);

					if (priority) {
						if (arithmetic.toString().equals("*") || arithmetic.toString().equals("/")) {
							newEquation.remove(newEquation.size()-1);
							newEquation.remove(newEquation.size()-1);
							newEquation.add(getCalculationResult(Integer.parseInt(left), Integer.parseInt(right), arithmetic));
							equation.set(i+1, newEquation.get(newEquation.size() - 1));
							i++;
						}
					} else {
						newEquation.remove(newEquation.size()-1);
						newEquation.remove(newEquation.size()-1);
						newEquation.add(getCalculationResult(Integer.parseInt(left), Integer.parseInt(right), arithmetic));
						equation.set(i+1, newEquation.get(newEquation.size() - 1));
						i++;
					}
					newEquation.trimToSize();
				}

			}
			equation = newEquation;
		}
		return equationToString();

	}

	/**
	 * Performs calculations and gets the calculated result
	 */
	private String getCalculationResult(Integer left, Integer right, String arithmetic) {
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
