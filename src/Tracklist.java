import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.json.*;

import javafx.scene.media.MediaPlayer;

public class Tracklist {
	private ArrayList<Track> Tracklist;
	
	public Tracklist() {
		Tracklist = new ArrayList<Track>();
	}
	
	public void addTrack(Track Track) {
		Tracklist.add(Track);
	}
	
	public ArrayList<String> getTrackTitles() {
		
		ArrayList<String> TracklistTitles = new ArrayList<String>();
        for (int i = 0; i < Tracklist.size(); i++) {
        	TracklistTitles.add(Tracklist.get(i).getMediaTitle());
        }
        
        return TracklistTitles;
	}
	
	public ArrayList<MediaPlayer> getTrackMedias() {
		
		ArrayList<MediaPlayer> TracklistMedias = new ArrayList<MediaPlayer>();
		for (int i = 0; i < Tracklist.size(); i++) {
			TracklistMedias.add(Tracklist.get(i).getMediaPlayer());
		}
		
		return TracklistMedias;
	}

	public ArrayList<Track> getTracklist() {
		return Tracklist;
	}

	public void setTracklist(ArrayList<Track> Tracklist) {
		Tracklist = this.Tracklist;
	}
	
}
