package example.Model.Utilities;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * The MusicPlayer class handles playing, pausing, and stopping background music
 * in the game. It also provides a method for playing short sound effects.
 *
 * <p>The class utilizes the JavaFX MediaPlayer and AudioClip for music and sound effect
 * playback, respectively.</p>
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */

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


	/**
	 * Constructor for creating a MusicPlayer with the specified filename.
	 *
	 * @param filename The path to the music file.
	 */
	public MusicPlayer(String filename)
	{
		this.filename = filename;
		Media media = new Media(new File(filename).toURI().toString());
		setMediaPlayer(new MediaPlayer(media));
	}

	/**
	 * Plays the background music if it is not already playing.
	 */
	public void play()
	{
		if(getMediaPlayer().getStatus() != MediaPlayer.Status.PLAYING){
			getMediaPlayer().play();
		}
	}

	/**
	 * Pauses the background music if it is currently playing.
	 */
	public void pause() {
		if(getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING){
			getMediaPlayer().pause();
		}
	}
	/**
	 * Stops the background music when prompted.
	 */
	public void stopMusic() {
		if (getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
			getMediaPlayer().stop();
		}
	}


	/**
	 * Plays a short sound effect.
	 *
	 * @param audioFileName The filename of the sound effect.
	 */
	public static void playSoundEffect(String audioFileName){
		new AudioClip(
				MusicPlayer.class
						.getResource("/Sounds/" +audioFileName)
						.toExternalForm())
				.play();
	}
}
