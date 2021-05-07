import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Mp3_player extends Application{
	
	public void start(Stage stage) throws FileNotFoundException{
		Gui gui = new Gui(stage);
	}

	public static void main(String args[]){
		launch(args);
	}
   
}