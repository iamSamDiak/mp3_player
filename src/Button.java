import java.io.InputStream;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Button {
	Class<?> resource = Mp3_player.class;
	
	private InputStream input;
	private Image inputImage;
	private ImageView viewImage;
	
	public Button(String input) {
		this.input = resource.getResourceAsStream(input);
		this.inputImage = new Image(this.input);
		this.viewImage = new ImageView(this.inputImage);
	}
	
	public EventHandler<MouseEvent> play(ArrayList<MediaPlayer> mediaplayer) {
		EventHandler<MouseEvent> played = new EventHandler<MouseEvent>() {
	         @Override 
	         public void handle(MouseEvent e) {
	        	 mediaplayer.get(Gui.getTrackNum()).play();
	        	 Track.setSTATUS("PLAYING");
	        	 System.out.println("play!");
	        	 //set slider here, because...reasons..
	        	 Double m = Gui.setTotalDuration(mediaplayer);
	        	 Gui.setSliderMax(m);
	        	 //
	        	 Gui.onPlay();
	        	 Gui.onResume();
	        }
	      };
		this.getViewImage().addEventFilter(MouseEvent.MOUSE_CLICKED, played);
		return played;
	}
	
	public EventHandler<MouseEvent> stop(ArrayList<MediaPlayer> mediaplayer) {
		EventHandler<MouseEvent> stopped = new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent e) {
				mediaplayer.get(Gui.getTrackNum()).stop();
				Track.setSTATUS("STOPPED");
				System.out.println("stop!");
				mediaplayer.get(Gui.getTrackNum()).setStartTime(Duration.seconds(0));
				Gui.onStop();
				Gui.onPause();
			}
		};
		this.getViewImage().addEventFilter(MouseEvent.MOUSE_CLICKED, stopped);
		return stopped;
	}
	
	public EventHandler<MouseEvent> pause(ArrayList<MediaPlayer> mediaplayer, int TrackNum) {
		EventHandler<MouseEvent> paused = new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent e) {
				mediaplayer.get(Gui.getTrackNum()).pause();
				Track.setSTATUS("PAUSED");
				Gui.onPause();
				Double m = mediaplayer.get(Gui.getTrackNum()).getCurrentTime().toSeconds();
				mediaplayer.get(Gui.getTrackNum()).setStartTime(Duration.seconds(m));
				System.out.println("pause!");
			}
		};
		this.getViewImage().addEventFilter(MouseEvent.MOUSE_CLICKED, paused);
		return paused;
	}
	
	public EventHandler<MouseEvent> rewind(ArrayList<MediaPlayer> mediaplayer, int TrackNum) {
		EventHandler<MouseEvent> rewinded = new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent e) {
				Duration seconds = mediaplayer.get(Gui.getTrackNum()).getCurrentTime();
				if (seconds.compareTo(Duration.seconds(5.0)) >= 0) {
					mediaplayer.get(Gui.getTrackNum()).stop();
					mediaplayer.get(Gui.getTrackNum()).setStartTime(Duration.seconds(0));
					mediaplayer.get(Gui.getTrackNum()).play();
					Gui.onPlay();
					Gui.onResume();
					Track.setSTATUS("PLAYING");
					System.out.println("rewind!");
				} else if (Gui.getTrackNum() + 1 == 1) {
					mediaplayer.get(Gui.getTrackNum()).stop();
					Gui.onStop();
					Gui.onPause();
					Track.setSTATUS("STOPPED");
				} else {
					mediaplayer.get(Gui.getTrackNum()).stop();
					Gui.substracTrackNum();
					mediaplayer.get(Gui.getTrackNum()).setStartTime(Duration.seconds(0));
					mediaplayer.get(Gui.getTrackNum()).play();
					Gui.onPlay();
					Gui.onResume();
					Track.setSTATUS("PLAYING");
					System.out.println("rewind!");
				}
			}
		};
		this.getViewImage().addEventFilter(MouseEvent.MOUSE_CLICKED, rewinded);
		return rewinded;
	}
	
	public EventHandler<MouseEvent> fast_forward(ArrayList<MediaPlayer> mediaplayer) {
		EventHandler<MouseEvent> fast_forward_f = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (Gui.getTrackNum() + 1 == Gui.getTrackList_length()) {
					mediaplayer.get(Gui.getTrackNum()).stop();
					Track.setSTATUS("STOPPED");
					Gui.onStop();
					Gui.onPause();
				} else {
					mediaplayer.get(Gui.getTrackNum()).stop();
					Gui.addTrackNum(); 
					mediaplayer.get(Gui.getTrackNum()).play();
					Gui.onPlay();
					Gui.onResume();
					Track.setSTATUS("PLAYING");
					System.out.println("fast forward!");
				}
			}
		};
		this.getViewImage().addEventFilter(MouseEvent.MOUSE_CLICKED, fast_forward_f);
		return fast_forward_f;
	}
	
	public Class<?> getResource() {
		return resource;
	}

	public void setResource(Class<?> resource) {
		this.resource = resource;
	}

	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public Image getInputImage() {
		return inputImage;
	}

	public void setInputImage(Image inputImage) {
		this.inputImage = inputImage;
	}

	public ImageView getViewImage() {
		return viewImage;
	}

	public void setViewImage(ImageView viewImage) {
		this.viewImage = viewImage;
	}
	
	
	
}
