package se.school.calculator;

import javafx.application.Application;
import javafx.stage.Stage;
import se.school.calculator.handlers.StageHandler;

public class Launcher extends Application {

	public static StageHandler stageHandler = new StageHandler();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		stageHandler.setupMainStage(stage);

		stageHandler.mainStage.setScene(stageHandler.calculatorScene);
	}
}
