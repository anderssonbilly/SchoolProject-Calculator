package se.school.calculator.scenes;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import se.school.calculator.Calculator;

public class CalculatorScene {

	// THE SCENE
	private Scene scene;
	public Scene getScene() {
		return scene;
	}

	// Calculator
	private Calculator calculator = new Calculator();

	// Constructor
	public CalculatorScene() {
				
		// Create the layout
		BorderPane layout = new BorderPane();
		
		// Create the display and attach it to the top 
		layout.setTop(display());
		
		// Create Numpad and attach it to the center
		layout.setCenter(buttons());

		// create scene and add keyeventhandler
		scene = new Scene(layout, 375, 500);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				
				if (event.getCode() == KeyCode.ENTER)
					handleEvent("=");
				else
					handleEvent(event.getText());
				}
		});
	}

	// Create the display
	private VBox display() {

		VBox vbox = new VBox();
		
		// Label for displaying equation
		Label lblEquation = new Label("");
		
		
		lblEquation.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		lblEquation.setId("equation");
		lblEquation.setAlignment(Pos.CENTER_RIGHT);
		lblEquation.setFont(new Font(15));
		lblEquation.setDisable(true);
		lblEquation.setStyle("-fx-background-color: lightblue; -fx-text-fill: blue; -fx-padding: 10;");
		
		// Textfield for displaying numbers
		TextField tfNumber = new TextField();
		tfNumber.setId("display");
		tfNumber.setText("");
		tfNumber.setAlignment(Pos.CENTER_RIGHT);
		tfNumber.setFont(new Font(25));
		tfNumber.setDisable(true);
		tfNumber.setStyle("-fx-background-color: lightblue; -fx-text-fill: NAVY; -fx-padding: 10;");
		
		// Add label and textfield to vbox
		vbox.getChildren().add(lblEquation);
		vbox.getChildren().add(tfNumber);
		return vbox;

	}

	
	// create the numpad
	private GridPane buttons() {

		/*
		 =================================================== 
		 Create grid
		 =====================================================
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
		 =====================================================
		 Create buttons
		 =====================================================
		 */

		// buttons 0-9 digit
		Button[] digit = new Button[10];
		for (Integer i = 0; i < 10; i++) {
			digit[i] = new Button(i.toString());
			digit[i].setOnMouseClicked(event -> {handleEvent(((Button) event.getSource()).getText());});
		}

		// button +- (disabled...)
		Button btnPlusMinus = new Button("+-");
		btnPlusMinus.setDisable(true);

		// button Empty (disabled...)
		Button btnEmpty = new Button("");
		btnEmpty.setDisable(true);

		// button CE
		Button btnCE = new Button("CE");
		
		// button C
		Button btnC = new Button("C");
		btnC.setOnMouseClicked(event -> {handleEvent("c");});	
		
		// button /
		Button btnDivision = new Button("/");
		btnDivision.setOnMouseClicked(event -> {handleEvent("/");});		

		// button *
		Button btnMultiplication = new Button("*");
		btnMultiplication.setOnMouseClicked(event -> {handleEvent("*");});

		// button -
		Button btnSubstraction = new Button("-");
		btnSubstraction.setOnMouseClicked(event -> {handleEvent("-");});
		
		// button +
		Button btnAddition = new Button("+");
		btnAddition.setOnMouseClicked(event -> {handleEvent("+");});
		
		// button . (disabled...)
		Button btnDecimal = new Button(".");
		btnDecimal.setDisable(true);

		// button =
		Button btnEnter = new Button("=");
		btnEnter.setDefaultButton(true);
		btnEnter.setOnMouseClicked(event -> {handleEvent("=");});

		/*
		===================================================== 
	 	Add buttons to grid
		=====================================================
		 */
		grid.addRow(0, btnCE, btnC, btnEmpty, btnDivision);
		grid.addRow(1, digit[7], digit[8], digit[9], btnMultiplication);
		grid.addRow(2, digit[4], digit[5], digit[6], btnSubstraction);
		grid.addRow(3, digit[1], digit[2], digit[3], btnAddition);
		grid.addRow(4, btnPlusMinus, digit[0], btnDecimal, btnEnter);

		/*
		 =====================================================
		 Set shared attributes
		 =====================================================
		 */
		for (Node node : grid.getChildrenUnmodifiable()) {
			if (node instanceof Button) {
				((Button) node).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				((Button) node).setStyle("-fx-font: 22 arial; -fx-margin: 5px");
			}
		}
		return grid;
	}
	
	private void handleEvent(String code) {
		
		TextField display = (TextField) scene.lookup("#display");
		Label equation = (Label) scene.lookup("#equation");
		
		switch (code) {
		case "0": // fallthrough
		case "1":			
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case "-":
		case "+":
		case "/":
		case "*":
		 	display.setText(calculator.toEquation(code));
		 	equation.setText(calculator.equationToString());
			break;
		case "=": // fallthrough	
		case "":
			equation.setText(calculator.equationToString());
			display.setText(calculator.result());
			break;
		case "c": // fallthrough	
		case "C":
		 	display.setText(calculator.clear());
		 	equation.setText(calculator.clear());
			break;
		default:
			break;
		}
	}
}
