import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Song {
   private Player player;
 
	public void player(String path) {
		 try {
			  String url = Song.class.getResource("songs/"+ path+".mp3").getPath();
		      FileInputStream fileInputStream = new FileInputStream(url);
		      player = new Player(fileInputStream);
		      
		      
		    }catch(FileNotFoundException e) {
		     e.printStackTrace();
		    }catch(JavaLayerException e) {
		     e.printStackTrace();
		    }
		 
		 	new Thread() {
	            public void run() {
	                try { player.play(); }
	                catch (Exception e) { System.out.println(e); }
	            }
	        }.start();
		   
	}

}
