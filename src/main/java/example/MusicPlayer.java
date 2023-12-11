package example;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer
{
	private String filename;
	private MediaPlayer mediaPlayer;

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	public MusicPlayer(String filename)
	{
		this.filename = filename;
		Media media = new Media(new File(filename).toURI().toString());
		setMediaPlayer(new MediaPlayer(media));
	}

	public void play()
	{
		if(getMediaPlayer().getStatus() != MediaPlayer.Status.PLAYING){
			getMediaPlayer().play();
		}
	}

	public void pause() {
		if(getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING){
			getMediaPlayer().pause();
		}
	}
	//Stops the music when prompted
	public void stopMusic() {
		if (getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
			getMediaPlayer().stop();
		}
	}
}
