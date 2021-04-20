import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class Track {
	private Media media;
	private MediaPlayer mediaPlayer;
	private Duration timeDuration;
	private double currentTime;
	private static Timer timer = new Timer("Timer");
    
	//constructor
    public Track(String media) {
    	this.media = new Media(media);
        this.mediaPlayer = new MediaPlayer(this.media);
        this.timeDuration = this.mediaPlayer.getTotalDuration();
        this.currentTime = 0;
    }
    
    public double getMediaDuration() {
    	return this.media.getDuration().toSeconds();
    }
    
    public String getMediaTitle() {
    	return (String) this.media.getMetadata().get("title");
    }
    
    public void onPlay() {
		if (Status.UNKNOWN != null || Status.PAUSED != null || Status.STOPPED != null) {
    		this.mediaPlayer.setStartTime(Duration.seconds(245));
			this.mediaPlayer.play();
    	}
    	this.mediaPlayer.setOnPlaying(new Runnable() {
    		  public void run() {
    			  timer.schedule(secondsPlaying(), 0, 100L);
    			  System.out.println("playing");
    		  }}
    	);
    }
    
    public void onPause() {
    	if (Status.PLAYING != null) {
    		this.mediaPlayer.pause();
    	}
    	this.mediaPlayer.setOnPaused(new Runnable() {
    		public void run() {
    			//status and timeStatus are both Labels with default text()
    			//mediaLocation is a default string created from a File object
    			//status.setText("Playing: " + mediaLocation);
    			//timeStatus.setText("Time: " + mediaPlayer.getCurrentTime());
    			System.out.println("pause");
    		}
    	});
    }
    
    public void onStop() {
    	if (Status.PLAYING != null || Status.PAUSED != null) {
    		this.mediaPlayer.stop();
    	}
		this.getStatus();
    	this.mediaPlayer.setOnStopped(new Runnable() {
    		public void run() {
  			  System.out.println("stop");
    		}
    	});
    }
    
    public void ready() {
    	this.mediaPlayer.setOnReady(new Runnable() {
    		public void run() {
    			//status and timeStatus are both Labels with default text()
    			//mediaLocation is a default string created from a File object
    			//status.setText("Playing: " + mediaLocation);
    			//timeStatus.setText("Time: " + mediaPlayer.getCurrentTime());
    			System.out.println("ready");
    		}
    	});
    }
    
    public Status getStatus() {
    	System.out.println(this.mediaPlayer.getStatus());
    	return this.mediaPlayer.getStatus();
    }
    
    public TimerTask secondsPlaying() {
    	TimerTask task = new TimerTask() {
    		
    		MediaPlayer m = getMediaPlayer();

			public void run() {
	        	 double time = m.getCurrentTime().toSeconds();
	        	 System.out.println(time);
	        	 getStatus();
	        	 if (time > 252) {
	        		 cancel();
	        		 getStatus();
	        		 m.stop();
	        	 }
	         }
	     };
	     
	     return task;
    }
    
    //getters setters
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}


	public Duration getTimeDuration() {
		return timeDuration;
	}


	public void setTimeDuration(Duration timeDuration) {
		this.timeDuration = timeDuration;
	}


	public double getCurrentTime() {
		return currentTime;
	}


	public void setCurrentTime(double currentTime) {
		this.currentTime = currentTime;
	}
    
}
