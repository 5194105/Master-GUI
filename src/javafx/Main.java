package javafx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
		primaryStage.setTitle("My First JavaFX App");
		Label label = new Label("Hello World, JavaFX !");
	    Scene scene = new Scene(label, 400, 200);
	    primaryStage.setScene(scene);
		primaryStage.show();
	}
	 public static void main(String[] args) {
	        Application.launch(args);
	    }
}
