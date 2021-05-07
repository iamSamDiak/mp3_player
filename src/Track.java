import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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
	private static String STATUS;
    
	//constructor
    public Track(String string) {
    	this.media = new Media(string);
        this.mediaPlayer = new MediaPlayer(this.media);
        this.timeDuration = this.mediaPlayer.getTotalDuration();
        this.currentTime = 0;
    }
    
    public double getMediaDuration() {
    	return this.media.getDuration().toSeconds();
    }
    
    public Duration getCurrentSeconds() {
    	return this.mediaPlayer.getCurrentTime();
    }
    
    public String getMediaTitle() {
    	String source = this.media.getSource();
    	 
        // getBytes() method to convert string
        // into bytes[].
        byte[] strAsByteArray = source.getBytes();
 
        byte[] result = new byte[strAsByteArray.length];
        
        String s = new String(strAsByteArray, StandardCharsets.UTF_8);
        
        int i = 0;
        // Store result in reverse order into the
        // result byte[]
        while (s.charAt(strAsByteArray.length - i - 1) != '/'){
            result[strAsByteArray.length - i - 1] = strAsByteArray[strAsByteArray.length - i - 1];
            i++;
        }
 
        String r = new String(result);
        
        return r.substring(strAsByteArray.length - i);
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

	public static String getSTATUS() {
		return STATUS;
	}

	public static void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
    
}
