package example;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer
{
	private String filename;
	private MediaPlayer mediaPlayer;

	public MusicPlayer(String filename)
	{
		this.filename = filename;
		Media media = new Media(new File(filename).toURI().toString());
		this.mediaPlayer = new MediaPlayer(media);
	}

	public void play()
	{
		if(mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING){
			mediaPlayer.play();
		}
	}

	//Stops the music when prompted
	public void stopMusic() {
		if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
			mediaPlayer.stop();
		}
	}
}
