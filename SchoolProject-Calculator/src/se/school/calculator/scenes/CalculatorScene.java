package se.school.calculator.scenes;

import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import se.school.calculator.Calculator;

public class CalculatorScene {

	// RootElement
	BorderPane root;

	long value = 0;

	public Scene buildScene() {

		root = new BorderPane();
		root.setTop(display());
		root.setCenter(buttons());

		return new Scene(root, 375, 500);
	}

	private VBox display() {

		VBox vbox = new VBox();
		TextField tfNumber = new TextField();
		tfNumber.setId("display");
		tfNumber.setText("");
		tfNumber.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		// textDisplay.setMinHeight(Double.MAX_VALUE);
		tfNumber.setFont(new Font(20));
		tfNumber.setDisable(true);
		tfNumber.setStyle("-fx-background-color: lightblue; -fx-text-fill: blue; -fx-padding: 20;");
		vbox.getChildren().add(tfNumber);
		return vbox;

	}

	private GridPane buttons() {
		TextField display = (TextField) this.root.lookup("#display");

		Calculator calculator = new Calculator();

		/*
		 * =================================================== Create grid
		 * =====================================================
		 */
		GridPane grid = new GridPane();

		// Rows
		RowConstraints rowCon = new RowConstraints();
		rowCon.setPercentHeight(100);
		rowCon.setVgrow(Priority.ALWAYS);
		rowCon.setFillHeight(true);
		grid.getRowConstraints().addAll(rowCon, rowCon, rowCon, rowCon, rowCon);

		// Columns
		ColumnConstraints colCon = new ColumnConstraints();
		colCon.setPercentWidth(100);
		colCon.setHgrow(Priority.ALWAYS);
		colCon.setFillWidth(true);
		grid.getColumnConstraints().addAll(colCon, colCon, colCon, colCon);

		/*
		 * =================================================== Create buttons
		 * =====================================================
		 */

		// buttons 0-9 digit
		Button[] digit = new Button[10];

		for (Integer i = 0; i < 10; i++) {
			digit[i] = new Button(i.toString());

			// append digit to display on click event
			// digit[i].setOnMouseClicked(event -> {
			// display.setText(calculator.toEquation(((Button)
			// event.getSource()).getText()));
			// });
		}

		// button +-
		Button btnPlusMinus = new Button("+-");		
		
		
		// button Empty
		Button btnEmpty = new Button("");
		btnEmpty.setDisable(true);


		// button CE
		Button btnCE = new Button("CE");
		btnEmpty.setDisable(true); //TODO temp disabled

		// button C
		Button btnC = new Button("C");
		btnC.setOnMouseClicked(event -> {
		 	display.setText(calculator.clear());
		});	


		// button /
		Button btnDivision = new Button("/");

		// button *
		Button btnMultiplication = new Button("*");

		// button -
		Button btnSubstraction = new Button("-");

		// button +
		Button btnAddition = new Button("+");

		// button .
		Button btnDecimal = new Button(".");

		// button =
		Button btnEnter = new Button("=");
		btnEnter.setDefaultButton(true);

		/*
		 * =================================================== Add buttons to grid
		 * =====================================================
		 */
		grid.addRow(0, btnCE, btnC, btnEmpty, btnDivision);
		grid.addRow(1, digit[7], digit[8], digit[9], btnMultiplication);
		grid.addRow(2, digit[4], digit[5], digit[6], btnSubstraction);
		grid.addRow(3, digit[1], digit[2], digit[3], btnAddition);
		grid.addRow(4, btnPlusMinus, digit[0], btnDecimal, btnEnter);

		/*
		 * =================================================== Set shared attributes
		 * =====================================================
		 */

		for (Node node : grid.getChildrenUnmodifiable()) {
			if (node instanceof Button) {
				((Button) node).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				((Button) node).setStyle("-fx-font: 22 arial;");

			}
		}

		/*
		 * ==== action event for all buttons
		 * ====
		 * */
		for (Node node : grid.getChildrenUnmodifiable()) {
			if (node instanceof Button) {
				((Button) node).setOnAction(event -> {
					System.out.println(((Button) event.getSource()).getText());
					if (!((Button) event.getSource()).getText().equals("="))
						display.setText(calculator.toEquation(((Button) event.getSource()).getText()));
					else {
						display.setText(calculator.result());
					}
				});
			}
		}
		return grid;
	}
}
