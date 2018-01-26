package se.school.calculator.scenes;

import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CalculatorScene {

	public Scene buildScene() {

		BorderPane border = new BorderPane();

		HBox hbox = addHBox();
		GridPane grid = addGridPane();

		border.setTop(hbox);
		border.setCenter(grid);

		Scene scene = new Scene(border, 375, 500);

		return scene;
	}

	private HBox addHBox() {
		HBox hbox = new HBox();

		Text title = new Text();
		title.setFont(new Font(30));
		title.setText("Evil Corp Calculator");
		hbox.getChildren().addAll(title);

		return hbox;
	}

	private GridPane addGridPane() {
		GridPane grid = new GridPane();
		
		RowConstraints rowCon = new RowConstraints();
		rowCon.setPercentHeight(100);
		rowCon.setVgrow(Priority.ALWAYS);
		rowCon.setFillHeight(true);
		grid.getRowConstraints().addAll(rowCon, rowCon, rowCon, rowCon, rowCon);
		
		ColumnConstraints colCon = new ColumnConstraints();
		colCon.setPercentWidth(100);
		colCon.setHgrow(Priority.ALWAYS);
		colCon.setFillWidth(true);
		grid.getColumnConstraints().addAll(colCon, colCon, colCon, colCon);

		// Row 0
		TextField textFieldEqvation = new TextField();
		textFieldEqvation.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		textFieldEqvation.setMaxHeight(100);
		textFieldEqvation.setFont(new Font(25));
		textFieldEqvation.setDisable(true);

		// Row 1
		Button btn9 = new Button("9");
		Button btn8 = new Button("8");
		Button btn7 = new Button("7");
		Button btnDivision = new Button("/");

		// Row 2
		Button btn6 = new Button("6");
		Button btn5 = new Button("5");
		Button btn4 = new Button("4");
		Button btnMultiplication = new Button("*");

		// Row 3
		Button btn3 = new Button("3");
		Button btn2 = new Button("2");
		Button btn1 = new Button("1");
		Button btnSubstraction = new Button("-");

		// Row 4
		Button btnDecimal = new Button(".");
		Button btn0 = new Button("0");
		Button btnEnter = new Button("=");
		btnEnter.setDefaultButton(true);
		Button btnAddition = new Button("+");

		grid.add(textFieldEqvation, 0, 0, 4, 1);
		grid.addRow(1, btn7, btn8, btn9, btnDivision);
		grid.addRow(2, btn4, btn5, btn6, btnMultiplication);
		grid.addRow(3, btn1, btn2, btn3, btnSubstraction);
		grid.addRow(4, btnDecimal, btn0, btnEnter, btnAddition);

		for (Node node : grid.getChildrenUnmodifiable()) {
			if (node instanceof Button) {
				((Button) node).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				
				((Button) node).setOnAction(event->{
					System.out.println(((Button) event.getSource()).getText());
					
					textFieldEqvation.appendText(((Button) event.getSource()).getText());
					
					//Switch case i en separat handler för varje knapp
				});
			}
		}

		return grid;
	}
}
