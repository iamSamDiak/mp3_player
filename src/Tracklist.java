import java.util.ArrayList;
import java.util.Iterator;

public class Tracklist {
	private ArrayList<Track> tracklist;
	
	public Tracklist() {
		this.tracklist = new ArrayList<Track>();
	}
	
	public void addTrack(Track track) {
		this.tracklist.add(track);
	}
	
	public void listAllTracks() {
		Iterator<Track> iter = this.tracklist.iterator();
		  
        // Displaying the values
        // after iterating through the list
        System.out.println("\nThe iterator values"
                           + " of list are: ");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
	}

	public ArrayList<Track> getTracklist() {
		return tracklist;
	}

	public void setTracklist(ArrayList<Track> tracklist) {
		this.tracklist = tracklist;
	}
	
}
