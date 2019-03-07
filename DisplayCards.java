import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DisplayCards extends Application{
	
	public void start(Stage primaryStage) {
		
		// HBox to display the cards in.
		HBox cards = new HBox(5);
		
		// Seeding randomness. 
		Random random = new Random();
		
		// Computing 3 random numbers for the three cards displayed. 
	    int rand1 = random.nextInt(53)+1;
	    int rand2 = random.nextInt(53)+1;
	    int rand3 = random.nextInt(53)+1;
	   
	    // All of the cards are in format: "num.png", so a random number between 1 and 54 with the ".png" extension will be a card.  
		Image card1 = new Image("file:///C:\\\\Users\\\\joshu\\\\Downloads\\\\Assignment\\\\Cards/" + Integer.toString(rand1) + ".png");
		Image card2 = new Image("file:///C:\\\\Users\\\\joshu\\\\Downloads\\\\Assignment\\\\Cards/" + Integer.toString(rand2) + ".png");
		Image card3 = new Image("file:///C:\\\\Users\\\\joshu\\\\Downloads\\\\Assignment\\\\Cards/" + Integer.toString(rand3) + ".png");
		
		// Converting the selected cards to viewable images. 
		ImageView c1 = new ImageView(card1);
		ImageView c2 = new ImageView(card2);
		ImageView c3 = new ImageView(card3);
		
		cards.getChildren().addAll(c1,c2,c3);
		
		// Creating the scene. 
		Scene scene = new Scene(cards);
		primaryStage.setTitle("Question 1");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}
