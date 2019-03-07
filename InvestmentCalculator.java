import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InvestmentCalculator extends Application {

	public void start(Stage primaryStage) {
		// VBox for TextField labels. 
		VBox row1 = new VBox(15);
		// VBox for TextFields and Calculate button. 
		VBox row2 = new VBox(5); 
		// HBox to combine the two VBoxs.
		HBox column = new HBox(5); 
		
		// The labels. 
		Label IA = new Label("Investment Amount");
		Label years = new Label("Years");
		Label AIR = new Label("Annual Interest Rate");
		Label FV = new Label("Future Value");

		row1.getChildren().addAll(IA,years,AIR,FV);

		// The TextFields.
		TextField investment = new TextField();
		investment.setPrefColumnCount(5);

		TextField numYears = new TextField();
		numYears.setPrefColumnCount(5);

		TextField interestRate = new TextField();
		interestRate.setPrefColumnCount(5);

		TextField futureValue = new TextField(); 
		futureValue.setPrefColumnCount(5);
		futureValue.setDisable(true);
		
		// The "Calculate" button.
		Button btCalculate = new Button("Calculate");
		
		
		// Activated when "Calculuate" is pressed. 
		btCalculate.setOnAction(e -> {
			// Computes the future value given the parameters entered into the field. 
				float monthlyInterestRate = Float.parseFloat(interestRate.getText())/1200;
				double fv = (Float.parseFloat(investment.getText()) * Math.pow(1 + monthlyInterestRate,12*Integer.parseInt(numYears.getText())));
				futureValue.setText(Double.toString(fv));
		
	});
		
		row2.getChildren().addAll(investment,numYears,interestRate,futureValue,btCalculate);

		column.getChildren().addAll(row1,row2);
		
		// Setting the scene.
		Scene scene = new Scene(column);
		primaryStage.setTitle("Question 2");
		primaryStage.setScene(scene);
		primaryStage.show();


	}

	public static void main(String args[]) {
		launch(args);
	}
}
