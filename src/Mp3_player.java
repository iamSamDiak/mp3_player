import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import static javafx.application.Application.launch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

import javafx.event.EventHandler;
 
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Circle; 

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
         
public class Mp3_player extends Application {
	
	protected static final boolean True = false;
	protected static final boolean False = false;
	private static boolean pause = false;
	private static boolean stop = true;
	
	Track mediaPlayer = new Track("file:/C:/Users/yasamy/Documents/football/nakamura_1/music/backwhen-pelican.mp3");
	Track mediaPlayer_1 = new Track("file:/C:/Users/yasamy/Documents/football/nakamura_1/music/Dreamcatcher.mp3");
	Track mediaPlayer_2 = new Track("file:/C:/Users/yasamy/Documents/football/nakamura_1/music/Hypnosis.mp3");
	Track mediaPlayer_3 = new Track("file:/C:/Users/yasamy/Documents/football/nakamura_1/music/Texture_Bath.mp3");
	Tracklist tracklist = new Tracklist();
	
	@Override 
   	public void start(Stage stage) throws FileNotFoundException {
		
		this.tracklist.addTrack(mediaPlayer);
		this.tracklist.addTrack(mediaPlayer_1);
		this.tracklist.addTrack(mediaPlayer_2);
		this.tracklist.addTrack(mediaPlayer_3);
		this.tracklist.listAllTracks();
		System.out.println(this.tracklist.getTracklist().get(2));
		
		Class<?> clazz = Mp3_player.class;
		 
		InputStream inputPause= clazz.getResourceAsStream("/icons/pause.png");
		InputStream inputPlay = clazz.getResourceAsStream("/icons/play.png");
		InputStream inputStop = clazz.getResourceAsStream("/icons/stop.png");
		InputStream inputRewind = clazz.getResourceAsStream("/icons/stop.png");
		
		Text title = new Text();        
	    //Setting the text to be added.
		//String titleText = (String) mediaPlayer.getMediaTitle();
		String titleText = "";
	    title.setText(titleText);
	    title.setFont(Font.font("verdana", FontPosture.REGULAR, 20));
	    //title.setX(50);
	    double title_ = titleText.length();
	    title.maxWidth(600);
	    title.setStyle("-");
	    title.setY(50);
		
		Image playIcon = new Image(inputPlay);  
		Image pauseIcon = new Image(inputPause);  
		Image stopIcon = new Image(inputStop);  
		
		ImageView playButton = new ImageView(playIcon);
		ImageView stopButton = new ImageView(stopIcon);
        playButton.setCache(true);
        stopButton.setCache(true);
		
		playButton.setX(250 - 25); 
	    playButton.setY(150 - 25); 
	      
	      //setting the fit height and width of the image view 
	    playButton.setFitHeight(50); 
	    playButton.setFitWidth(50); 
	      
	      //Setting the preserve ratio of the image view 
	     playButton.setPreserveRatio(true);  
	     
	     stopButton.setX(350 - 25); 
	     stopButton.setY(150 - 25); 
	     
	     //setting the fit height and width of the image view 
	     stopButton.setFitHeight(50); 
	     stopButton.setFitWidth(50); 
	     
	     //Setting the preserve ratio of the image view 
	     stopButton.setPreserveRatio(true);
	     stopButton.setOpacity(0.5);
	     
	      Slider slider = new Slider(0, 500, 0);
	      slider.setMin(0);
	      slider.setMax(mediaPlayer.getMediaDuration());
	      
	      
	      //Functions !!!!!!!!!!!!!!!
	     
	      mediaPlayer.getStatus();
	      
	     TimerTask task = new TimerTask() {

			public void run() {
	        	 double time = mediaPlayer.getMediaPlayer().getCurrentTime().toSeconds();
	        	 System.out.println(time);
            	 slider.setValue(time);
	         }
	     };
	     
	     Timer timer = new Timer("Timer");
	     
	     long delay = 100L;
	     

	      slider.valueProperty().addListener(
	              (ChangeListener<? super Number>) new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0,
						Number arg1, Number arg2) {
					//System.out.println(arg2);
					//mediaPlayer.setStartTime(Duration.millis((double) arg2));
				}
	         });
	      
	      slider.setOnMouseReleased(event -> {
	            System.out.println(slider.getValue());
	            //mediaPlayer.stop();
	            //mediaPlayer.setStartTime(Duration.seconds(slider.getValue()));
	            //mediaPlayer.play();
	            slider.setValue(slider.getValue());
	        });
	     
		
      //Play or Pause
      EventHandler<MouseEvent> playAudioEvent = new EventHandler<MouseEvent>() {
         @Override 
         public void handle(MouseEvent e) {
        	 //System.out.println(mediaPlayer.getCurrentTime());
        	 if (pause == false) {
            	 playButton.setImage(pauseIcon);
            	 pause = true;
            	 tracklist.getTracklist().get(2).onPlay();
            	 //mediaPlayer.play();
            	 //timer.schedule(task, 0, delay);
        	 } else {
        		 playButton.setImage(playIcon);
            	 pause = false;
            	 tracklist.getTracklist().get(2).onPause();
            	 //mediaPlayer.pause();
            	 //timer.purge();
        	 }
        	 stopButton.setOpacity(1);
        }
      };
      
      //Creating the mouse event handler 
      EventHandler<MouseEvent> stopAudioEvent = new EventHandler<MouseEvent>() {
    	  @Override 
    	  public void handle(MouseEvent e) {
    		  playButton.setImage(playIcon);
         	 pause = false;
         	// mediaPlayer.pause();
         	tracklist.getTracklist().get(2).onStop();
    		  stopButton.setOpacity(0.5);
         	 timer.purge();
    	  }
      };
      
      //Registering the event filter 
      playButton.addEventFilter(MouseEvent.MOUSE_CLICKED, playAudioEvent);   
      stopButton.addEventFilter(MouseEvent.MOUSE_CLICKED, stopAudioEvent);   
       
      //Creating a Group object  
      Group root = new Group(playButton, stopButton, title, slider);
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300); 
       
      //Setting the fill color to the scene 
      scene.setFill(Color.LAVENDER);  
      
      //Setting title to the Stage 
      stage.setTitle("Event Filters Example");       
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }

	public static void main(String args[]){ 
	      launch(args); 
	   } 
   
}