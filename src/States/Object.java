package States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Enemy handles the enemy behavior including its ImageView and variables. This
 * is a superclass to the EnemyShooter class.
 */

public class Object {

	protected ImageView objectImageView;
	protected double X;
	protected double Y;

	public Object(double x, double y, String image) {
		X = x;
		Y = y;

		try {
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

	public ImageView getObjectImageView() {
		return objectImageView;
	}

	public void move() {
		Y += Constants.objectSpeed;
		objectImageView.setY(Y);
	}

	public boolean slipsByPlayer() {
		if (this.Y >= 400) {
			return true;
		} else {
			return false;
		}
	}

	public boolean playerObjectCollision(Player player) {
		boolean hit = false;

		Rectangle playerRect = new Rectangle(player.getPlayerImageView().getX(), player.getPlayerImageView().getY(),
				Constants.playerWidth, Constants.playerHeight);

		Rectangle enemyRect = new Rectangle(this.getObjectImageView().getX(), this.getObjectImageView().getY(),
				Constants.objectWidth, Constants.objectHeight);

		if (playerRect.getBoundsInParent().intersects(enemyRect.getBoundsInParent())) {
			hit = true;
			player.setScore(1);
		}

		return hit;
	}

}
