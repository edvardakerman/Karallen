package States;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;

/**
 * GameEntity is a abstract superclass and handles the object behavior including its ImageView, collisions and variables. This
 * is a superclass to the Drink, Bouncer and PowerUp class.
 */

public abstract class GameEnity {

	private ImageView objectImageView;
	private double X;
	private double Y;
	private AudioClip soundEffect;

	public GameEnity(double x, double y, String image, String sound) {
		X = x;
		Y = y;

		try {
			File objectSound = new File(sound);
			soundEffect = new AudioClip(objectSound.toURI().toString());
			Image objectImage = new Image(new FileInputStream(image));
			objectImageView = new ImageView(objectImage);
			objectImageView.setX(X);
			objectImageView.setY(Y);
			objectImageView.setFitWidth(Constants.objectWidth);
			objectImageView.setFitHeight(Constants.objectHeight);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getX() {
		return X;
	}

	public double getY() {
		return Y;
	}
	
	public AudioClip getSoundEffect() {
		return soundEffect;
	}
	
	public void setX(double x) {
		X = x;
	}
	
	public void setY(double y) {
		Y = y;
	}

	public ImageView getObjectImageView() {
		return objectImageView;
	}
	

	public boolean playerObjectCollision(Player player) {
		boolean hit = false;

		Rectangle playerRect = new Rectangle(player.getPlayerImageView().getX(), player.getPlayerImageView().getY(),
				Constants.playerWidth, Constants.playerHeight);

		Rectangle objectRect = new Rectangle(this.getObjectImageView().getX(), this.getObjectImageView().getY(),
				Constants.objectWidth, Constants.objectHeight);

		if (playerRect.getBoundsInParent().intersects(objectRect.getBoundsInParent())) {
			hit = true;
		}

		return hit;
	}
	
	public void makeSound() {
		soundEffect.play();
	}
}
