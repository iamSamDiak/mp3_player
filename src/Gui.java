import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gui{
	private static ArrayList<MediaPlayer> track;
	private static Tracklist Tracklist = new Tracklist();
	private static int TrackList_length;
	private static Slider slider = new Slider();
	private static boolean SliderDragged = false;
	private static ImageView pause_imageview;
	private static ImageView play_imageview;
	private static ImageView stop_imageview;
	//TrackNum is public to be use in any classes !
	private static int TrackNum = 0;
	
	public Gui(Stage stage) throws FileNotFoundException {
		start(stage);
	}

	public void start(Stage stage) throws FileNotFoundException {
		
		Tracklist.addTrack(new Track("file:/C:/Users/yasamy/eclipse-workspace/mp3_player/src/Tracks/backwhen-pelican.mp3"));
		Tracklist.addTrack(new Track("file:/C:/Users/yasamy/eclipse-workspace/mp3_player/src/Tracks/Dreamcatcher.mp3"));
		Tracklist.addTrack(new Track("file:/C:/Users/yasamy/eclipse-workspace/mp3_player/src/Tracks/Fruits.mp3"));
		
		TrackList_length = Tracklist.getTracklist().size();

		track = Tracklist.getTrackMedias();
		
		//Creating a Group object  
		Group root = new Group(
				TrackTitles(), 
				this.playImageView(), 
				this.pauseImageView(), 
				this.stopImageView(), 
				this.rewindImageView(), 
				this.fastForwardView(),
				this.minutesSlider()
		);
         
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
	
	public GridPane TrackTitles() {
		//listTracks
		GridPane Track_titles = new GridPane();
		Track_titles.setMinSize(0, 0);
		Track_titles.setPadding(new Insets(10, 10, 10, 10));
		Track_titles.setAlignment(Pos.CENTER);
		for (int i = 0; i < Tracklist.getTracklist().size(); i++) {
			Text text = new Text(Tracklist.getTrackTitles().get(i));
			Track_titles.add(text, 0, i);
		}
		//
		return Track_titles;
	}
	
	public ImageView playImageView() {
		//new Button play
		Button play = new Button("/icons/play.png");
		play_imageview = play.getViewImage();
		play_imageview.setCache(true);
		play_imageview.setX(250 - 25); 
		play_imageview.setY(150 - 25); 
		play_imageview.setFitHeight(50); 
		play_imageview.setFitWidth(50);
		play_imageview.addEventFilter(MouseEvent.MOUSE_CLICKED, play.play(track));
		play_imageview.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

		     @Override
		     public void handle(MouseEvent event) {
		    	 givenUsingTimer_whenSchedulingTaskOnce_thenCorrect();
		     }
		});
		//
		return play_imageview;
	}
	
	public ImageView pauseImageView() {
		//new Button pause
		Button pause = new Button("/icons/pause.png");
		pause_imageview = pause.getViewImage();
		pause_imageview.setCache(true);
		pause_imageview.setX(250 - 25); 
		pause_imageview.setY(150 - 25); 
		pause_imageview.setFitHeight(50); 
		pause_imageview.setFitWidth(50);
		pause_imageview.setVisible(false);
		pause_imageview.addEventFilter(MouseEvent.MOUSE_CLICKED, pause.pause(track, TrackNum));
		//
		return pause_imageview;
	}
	
	public ImageView stopImageView() {
		//new Button stop
		Button stop = new Button("/icons/stop.png");
		stop_imageview = stop.getViewImage();
        stop_imageview.setCache(true);
        stop_imageview.setX(350 - 25); 
        stop_imageview.setY(150 - 25); 
        stop_imageview.setFitHeight(50); 
        stop_imageview.setFitWidth(50); 
        stop_imageview.setPreserveRatio(true);
        stop_imageview.setOpacity(0.5);
		stop_imageview.addEventFilter(MouseEvent.MOUSE_CLICKED, stop.stop(track));
		//
		return stop_imageview;
	}
	
	public ImageView rewindImageView() {
		//new Button rewind
		Button rewind = new Button("/icons/rewind.png");
		ImageView rewind_imageview = rewind.getViewImage();
		rewind_imageview.setCache(true);
		rewind_imageview.setX(150 - 25); 
		rewind_imageview.setY(150 - 25);
		rewind_imageview.setFitHeight(50); 
		rewind_imageview.setFitWidth(50); 
		rewind_imageview.setPreserveRatio(true);
		rewind_imageview.addEventFilter(MouseEvent.MOUSE_CLICKED, rewind.rewind(track, TrackNum));
		//
		return rewind_imageview;
	}
	
	public ImageView fastForwardView() {
		//new Button fast_forward
		Button fast_forward = new Button("/icons/fast_forward.png");
		ImageView fast_forward_imageview = fast_forward.getViewImage();
		fast_forward_imageview.setCache(true);
		fast_forward_imageview.setX(450 - 25); 
		fast_forward_imageview.setY(150 - 25); 
		fast_forward_imageview.setFitHeight(50); 
		fast_forward_imageview.setFitWidth(50); 
		fast_forward_imageview.setPreserveRatio(true);
		fast_forward_imageview.addEventFilter(MouseEvent.MOUSE_CLICKED, fast_forward.fast_forward(track));
		//
		return fast_forward_imageview;
	}
	
	public Slider minutesSlider() {
		// Creates a slider
  
        // enable the marks
        slider.setShowTickMarks(true);
  
        // enable the Labels
        slider.setShowTickLabels(true);
  
        // sets the value of the property
        // blockIncrement
        slider.setBlockIncrement(0.1f);
        
		slider.setCache(true);
		slider.setTranslateX(200);
		slider.setTranslateY(200);
		slider.setMin(0);
		slider.setMax(1);
		slider.setScaleX(1.5);
		slider.setScaleY(1.5);
		slider.setShowTickLabels(false);
		slider.setShowTickMarks(false);
		slider.setOnMouseReleased(event -> {
			track.get(getTrackNum()).stop();
			track.get(getTrackNum()).setStartTime(Duration.seconds(slider.getValue()));
			if (Track.getSTATUS().equals("PLAYING") || Track.getSTATUS().equals("STOPPED")) {				
				track.get(getTrackNum()).play();
			}
			SliderDragged = false;
			System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        });
		slider.setOnMousePressed(event -> {
        	Gui.slider.setValue(event.getX());
        	SliderDragged = true;
		});
        
        return slider;
	}
	
	public void givenUsingTimer_whenSchedulingTaskOnce_thenCorrect() {
	    TimerTask task = new TimerTask(){
	        public void run() {
	        	Duration currentTime = track.get(getTrackNum()).getCurrentTime();
	        	Duration totalTime = track.get(getTrackNum()).getTotalDuration().add(currentTime);
	        	if (totalTime.compareTo(currentTime) <= 0) {
	        		Gui.addTrackNum(); 
					track.get(getTrackNum()).setStartTime(Duration.seconds(0));
					track.get(Gui.getTrackNum()).play();
	        	} else {
	        		if (!SliderDragged) {
		        		Double c = currentTime.toSeconds();
			        	Gui.slider.setValue(c);
			        	System.out.println(track.get(getTrackNum()).getStatus());
			        	System.out.println(totalTime);
			            System.out.println(currentTime);
	        		}
	        	}
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 1000L;
	    timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
	public static Double setTotalDuration(ArrayList<MediaPlayer> mediaplayer) {
		return mediaplayer.get(Gui.getTrackNum()).getMedia().getDuration().toSeconds();
	}
	
	public static void setSliderMax(Double sliderMax) {
		slider.setMax(sliderMax);
	}
	
	public static void onPause() {
		pause_imageview.setVisible(false);
	}
	
	public static void onPlay() {
		pause_imageview.setVisible(true);
	}
	
	public static void onStop() {
		stop_imageview.setOpacity(0.5);
	}
	
	public static void onResume() {
		stop_imageview.setOpacity(1);
	}

	public ArrayList<MediaPlayer> getTrack() {
		return track;
	}

	public void setTrack(ArrayList<MediaPlayer> track) {
		track = Gui.track;
	}

	public Tracklist getTracklist() {
		return Tracklist;
	}

	public void setTracklist(Tracklist Tracklist) {
		Tracklist = Gui.Tracklist;
	}

	public static void addTrackNum() {
		TrackNum = TrackNum + 1;
	}
	
	public static void substracTrackNum() {
		TrackNum = TrackNum - 1;
	}
	
	public static int getTrackNum() {
		return TrackNum;
	}

	public static void setTrackNum(int TrackNum) {
		TrackNum = Gui.TrackNum;
	}

	public static int getTrackList_length() {
		return TrackList_length;
	}

	public static void setTrackList_length(int TrackList_length) {
		Gui.TrackList_length = TrackList_length;
	}
	
	public Slider getSlider() {
		return slider;
	}
	
	public void setSlider(Slider slider) {
		Gui.slider = slider;
	}
	
}
