package se.school.calculator.handlers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import se.school.calculator.scenes.CalculatorScene;

public class StageHandler {
	public Stage mainStage;
	public Scene calculatorScene;

	public void setupMainStage(Stage stage) {
		mainStage = stage;
		calculatorScene = setupCalculatorScene();

		stage.setScene(calculatorScene);
		stage.show();
	}

	private Scene setupCalculatorScene() {
		Scene calculatorScene = new CalculatorScene().buildScene();
		return calculatorScene;
	}
}
