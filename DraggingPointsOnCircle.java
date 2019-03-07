import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import java.util.Random;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class DraggingPointsOnCircle extends Application{


	public void start(Stage primaryStage) {
		
		// The main pane
		Pane pane = new Pane(); 
		
		// The circle that the points will appear on. 
		Circle circle = new Circle(250);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);
		circle.setCenterX(250);
		circle.setCenterY(250);

		// Seeding randomness
		Random random = new Random();
		
		// This part of the code determines where the points will appear on the circle. 
		// This is done by finding a random angle to put the points on. 
		// Then, we convert those angles to (x,y) co-ordinates using the equations: x = cos(angle)*r, y = sin(angle)*r
		double angle1 = (Math.random()*Math.PI*2);
		double angle2 = (Math.random()*Math.PI*2);
		double angle3 = (Math.random()*Math.PI*2);

		double r = 250;
		double x1 =  250 + (Math.cos(angle1)*r);
		double y1 =  250 + (Math.sin(angle1)*r);
		double x2 =  250 + (Math.cos(angle2)*r);
		double y2 =  250 + (Math.sin(angle2)*r);
		double x3 =  250 + (Math.cos(angle3)*r);
		double y3 =  250 + (Math.sin(angle3)*r);


		// Initializing the points. 
		Circle p1 = new Circle(10);
		p1.setCenterX(200);
		p1.setFill(Color.RED);
		p1.setStroke(Color.BLACK);
		p1.setCenterX(x1);
		p1.setCenterY(y1);

		Circle p2 = new Circle(10);
		p2.setFill(Color.RED);
		p2.setStroke(Color.BLACK);
		p2.setCenterX(x2);
		p2.setCenterY(y2);

		Circle p3 = new Circle(10);
		p3.setFill(Color.RED);
		p3.setStroke(Color.BLACK);
		p3.setCenterX(x3);
		p3.setCenterY(y3);

		// Initializing the lines which connect the points. 
		Line l1 = new Line(p1.getCenterX(),p1.getCenterY(),p2.getCenterX(),p2.getCenterY());
		Line l2 = new Line(p2.getCenterX(),p2.getCenterY(),p3.getCenterX(),p3.getCenterY()); 
		Line l3 = new Line(p3.getCenterX(),p3.getCenterY(),p1.getCenterX(),p1.getCenterY());
		
		// Computing the length of the sides between each point (a,b,c) 
		double a = new Point2D(p3.getCenterX(), p3.getCenterY()).
				distance(p2.getCenterX(), p2.getCenterY());
		double b = new Point2D(p3.getCenterX(), p3.getCenterY()).
				distance(p1.getCenterX(), p1.getCenterY());
		double c = new Point2D(p2.getCenterX(), p2.getCenterY()).
				distance(p1.getCenterX(), p1.getCenterY());     

		// Computing the angles of the points (A,B,C)
		double A = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
		double B = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
		double C = Math.acos((c * c - b * b - a * a) / (-2 * a * b));
		
		// These texts display the angle of each point next to the point. 
		Text t1 = new Text();
		t1.setX(p1.getCenterX()+10);
		t1.setY(p1.getCenterY());
		t1.setText(String.format("%.2f", Math.toDegrees(A)));

		Text t2 = new Text();
		t2.setX(p2.getCenterX()+10);
		t2.setY(p2.getCenterY());
		t2.setText(String.format("%.2f", Math.toDegrees(B)));

		Text t3 = new Text(); 
		t3.setX(p3.getCenterX()+10);
		t3.setY(p3.getCenterY());
		t3.setText(String.format("%.2f", Math.toDegrees(C)));
		

		// Handling the dragging of the points.
		
		/* Note: 
		 * If you wanted to keep the points close to the perimeter of the circle, you could continuously check if your mouse's 
		 * co-ordinates (x,y) are around equal to the radius squared (r^2). The reason I was unable to do this is because I was unable
		 * to find a work around for the origin not being at the center of the circle. This means that the values of x and y don't fall below
		 * 0 at points they would otherwise, which is essential to the equation x^2 + y^2 = r^2.
		 */
		p1.setOnMouseDragged(e -> {
			if(p1.contains(e.getX(), e.getY())) {

				p1.setCenterX(e.getX());
				p1.setCenterY(e.getY());
				l1.setStartX(p1.getCenterX());
				l1.setStartY(p1.getCenterY());
				l3.setEndX(p1.getCenterX());
				l3.setEndY(p1.getCenterY());
				t1.setX(p1.getCenterX()+10);
				t1.setY(p1.getCenterY());
				
				// Recalculating side lengths
				double a1 = new Point2D(p3.getCenterX(), p3.getCenterY()).
						distance(p2.getCenterX(), p2.getCenterY());
				double b1 = new Point2D(p3.getCenterX(), p3.getCenterY()).
						distance(p1.getCenterX(), p1.getCenterY());
				double c1 = new Point2D(p2.getCenterX(), p2.getCenterY()).
						distance(p1.getCenterX(), p1.getCenterY());
				
				// Recalculating point angles
				double A1 = Math.acos((a1 * a1 - b1 * b1 - c1 * c1) / (-2 * b1 * c1));
				double B1 = Math.acos((b1 * b1 - a1 * a1 - c1 * c1) / (-2 * a1 * c1));
				double C1 = Math.acos((c1 * c1 - b1 * b1 - a1 * a1) / (-2 * a1 * b1));

				t1.setText(String.format("%.2f", Math.toDegrees(A1)));
				t2.setText(String.format("%.2f", Math.toDegrees(B1)));
				t3.setText(String.format("%.2f", Math.toDegrees(C1)));

			}
		});

		p2.setOnMouseDragged(e -> {
			if(p2.contains(e.getX(), e.getY())) {
				p2.setCenterX(e.getX());
				p2.setCenterY(e.getY());
				l2.setStartX(p2.getCenterX());
				l2.setStartY(p2.getCenterY());
				l1.setEndX(p2.getCenterX());
				l1.setEndY(p2.getCenterY());
				t2.setX(p2.getCenterX()+10);
				t2.setY(p2.getCenterY());
				
				// Recalculating side lengths
				double a2 = new Point2D(p3.getCenterX(), p3.getCenterY()).
						distance(p2.getCenterX(), p2.getCenterY());
				double b2 = new Point2D(p3.getCenterX(), p3.getCenterY()).
						distance(p1.getCenterX(), p1.getCenterY());
				double c2 = new Point2D(p2.getCenterX(), p2.getCenterY()).
						distance(p1.getCenterX(), p1.getCenterY());

				// Recalculating point angles
				double A2 = Math.acos((a2 * a2 - b2 * b2 - c2 * c2) / (-2 * b2 * c2));
				double B2 = Math.acos((b2 * b2 - a2 * a2 - c2 * c2) / (-2 * a2 * c2));
				double C2 = Math.acos((c2 * c2 - b2 * b2 - a2 * a2) / (-2 * a2 * b2));

				t1.setText(String.format("%.2f", Math.toDegrees(A2)));
				t2.setText(String.format("%.2f", Math.toDegrees(B2)));
				t3.setText(String.format("%.2f", Math.toDegrees(C2)));

			}
		});

		p3.setOnMouseDragged(e -> {
			if(p3.contains(e.getX(), e.getY())) {
				p3.setCenterX(e.getX());
				p3.setCenterY(e.getY());
				l3.setStartX(p3.getCenterX());
				l3.setStartY(p3.getCenterY());
				l2.setEndX(p3.getCenterX());
				l2.setEndY(p3.getCenterY());
				t3.setX(p3.getCenterX()+10);
				t3.setY(p3.getCenterY());
				
				// Recalculating side lengths
				double a3 = new Point2D(p3.getCenterX(), p3.getCenterY()).
						distance(p2.getCenterX(), p2.getCenterY());
				double b3 = new Point2D(p3.getCenterX(), p3.getCenterY()).
						distance(p1.getCenterX(), p1.getCenterY());
				double c3 = new Point2D(p2.getCenterX(), p2.getCenterY()).
						distance(p1.getCenterX(), p1.getCenterY());
				
				// Recalculating point angles
				double A3 = Math.acos((a3 * a3 - b3 * b3 - c3 * c3) / (-2 * b3 * c3));
				double B3 = Math.acos((b3 * b3 - a3 * a3 - c3 * c3) / (-2 * a3 * c3));
				double C3 = Math.acos((c3 * c3 - b3 * b3 - a3 * a3) / (-2 * a3 * b3));

				t1.setText(String.format("%.2f", Math.toDegrees(A3)));
				t2.setText(String.format("%.2f", Math.toDegrees(B3)));
				t3.setText(String.format("%.2f", Math.toDegrees(C3)));
			}
		});


		// Adding all objects to pane.
		pane.getChildren().addAll(circle,p1,p2,p3,l1,l2,l3,t1,t2,t3);

		// Setting the scene
		Scene scene = new Scene(pane,800,800);
		primaryStage.setTitle("Question 3");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String args[]) {
		launch(args);
	}
}
