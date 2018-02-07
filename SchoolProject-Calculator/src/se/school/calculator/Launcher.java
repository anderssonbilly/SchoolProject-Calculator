package se.school.calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.school.calculator.scenes.*;

/**
 * The class Launcher runs the application.
 *
 */
public class Launcher extends Application {

	/**
	 * The main method
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Starts the application
	 */
	@Override
	public void start(Stage stage) throws Exception {

		// Create and set scene
		Scene calcScene = new CalculatorScene().getScene();
		stage.setScene(calcScene);

		// Set title and show
		stage.setTitle("EvilCorp Calculator");
		stage.show();
	}
}