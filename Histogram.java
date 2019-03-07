import java.io.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import java.util.Scanner;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Histogram extends Application{


	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage){
		
		// The main pane. 
		BorderPane pane = new BorderPane(); 
		// The pane for the TextField label, Textfield, and view button. 
		HBox fileEntry = new HBox(5);
		
		// Initializing the array of letters. 
		char alphabet[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		
		// Initializing the label, Textfield, and view button. 
		Label fileLabel = new Label("File: ");
		TextField t1 = new TextField();
		t1.setPrefColumnCount(30);
		Button btView = new Button("View");
		
		// Setting the functionality of the button. 
		
		btView.setOnAction(e -> {
			File file = new File(t1.getText());

			Scanner input;
			// Try catch, handling the case where the specified file is not found. 
			try {
				// Reading through the file, finding each word. Looping through each character of the word and 
				// incrementing the count for each letter in the letterCount array. 
				input = new Scanner(file);
				int letterCount[] = new int[26];
				while(input.hasNext()) {
					String word = input.next();
					for(int i = 0; i < word.length(); i++) {
						for(int j = 0; j < alphabet.length; j++) {
							if(word.charAt(i) == alphabet[j] || word.charAt(i) == Character.toLowerCase(alphabet[j])) {
								letterCount[j]++;
							}
						}
					}
				}
				
				// Creating a BarChart, with the letters on the x-axis and the number of times the letter appears in the file on the y-axis.
				CategoryAxis xAxis = new CategoryAxis();
				NumberAxis yAxis = new NumberAxis();

				BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
				bc.setTitle("Letters in File");
				xAxis.setLabel("Letters");
				yAxis.setLabel("Number of Letters");

				XYChart.Series s1 = new XYChart.Series<>();
				

				for(int i = 0; i < 26; i++) {
					s1.getData().add(new XYChart.Data(Character.toString(alphabet[i]),letterCount[i]));
				}
				bc.getData().addAll(s1);
				pane.setCenter(bc);
				input.close();
			} catch (FileNotFoundException e1) {
				System.out.println("File not found.");
				System.exit(0);

			}



		});

		fileEntry.getChildren().addAll(fileLabel, t1, btView);
		pane.setBottom(fileEntry);
		
		// Creating the scene. 
		Scene scene = new Scene(pane,800,800); 
		primaryStage.setTitle("Question 04");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);






	}

}
