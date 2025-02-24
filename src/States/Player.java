package States;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * Player handles the players behavior including its ImageView, positioning and
 * other variables.
 */

public class Player {

	private ImageView playerImageView;
	private double playerX = (Constants.screenWidth - Constants.playerWidth) / 2;
	private double playerY = Constants.screenHeight - 50;
	private int score = 0;
	private int lives = 3;
	private int scoreBonus = 1;

	public Player(String playerImg) {

		try {
			Image playerImage = new Image(new FileInputStream(playerImg));
			playerImageView = new ImageView(playerImage);
			playerImageView.setX(playerX);
			playerImageView.setY(playerY);
			playerImageView.setFitWidth(Constants.playerWidth);
			playerImageView.setFitHeight(Constants.playerHeight);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ImageView getPlayerImageView() {
		return playerImageView;
	}

	public void move(KeyCode keyCode) {
		if (keyCode == KeyCode.UP) {
			this.moveUp();
		} else if (keyCode == KeyCode.DOWN) {
			this.moveDown();
		} else if (keyCode == KeyCode.LEFT) {
			this.moveLeft();
		} else if (keyCode == KeyCode.RIGHT) {
			this.moveRight();
		}
	}

	private void moveUp() {
		if (playerY > 10) {
			playerY -= Constants.playerSpeed;
			playerImageView.setY(playerY);
		}
	}

	private void moveDown() {
		if (playerY < Constants.screenHeight - Constants.playerHeight) {
			playerY += Constants.playerSpeed;
			playerImageView.setY(playerY);
		}
	}

	private void moveLeft() {
		if (playerX > 0) {
			playerX -= Constants.playerSpeed;
			playerImageView.setX(playerX);
		}
	}

	private void moveRight() {
		if (playerX < Constants.screenWidth - Constants.playerWidth) {
			playerX += Constants.playerSpeed;
			playerImageView.setX(playerX);
		}
	}
	
	public void relocate() {
		playerX = (Constants.screenWidth - Constants.playerWidth) / 2;
		playerY = Constants.screenHeight - 50;
		playerImageView.setX(playerX);
		playerImageView.setY(playerY);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int Score) {
		this.score += Score * scoreBonus;
	}

	public void setScoreBonus(int newValue) {
		this.scoreBonus = newValue;
	}

	public int getScoreBonus() {
		return scoreBonus;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
}