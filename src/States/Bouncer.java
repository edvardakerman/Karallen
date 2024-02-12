package States;

import java.util.Random;

import Constants.Constants;

/**
 * Bouncer is a subclass to the Object class and provides more behavioral functionality like movement.
 */

public class Bouncer extends Object{
	private Random random = new Random();
	private int currentDirection = random.nextInt(4);
	private double destination = random.nextDouble() * (Constants.screenWidth - 40);
	private double speed = Constants.objectSpeed;
	int lastScore = 0;
	
	public Bouncer(String image) {		
		super((Constants.screenWidth - Constants.playerWidth) / 2, Constants.screenHeight - 300, image);
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		String direction[] = {"up", "right", "down", "left"};
		
		if (direction[currentDirection] == "up" && destination < Y) {
			Y -= speed;
			objectImageView.setY(Y);
		} else if (direction[currentDirection] == "right" && destination > X) {
			X += speed;
			objectImageView.setX(X);
		} else if (direction[currentDirection] == "down" && destination > Y) {
			Y += speed;
			objectImageView.setY(Y);
		} else if (direction[currentDirection] == "left" && destination < X) {
			X -= speed;
			objectImageView.setX(X);
		} else {
			Random random = new Random();
			currentDirection = random.nextInt(4);
			destination = random.nextDouble() * (Constants.screenWidth - 40);
		}

	}
	
	public void increaseSpeed() {
		speed += 0.25;
	}
	
	public void increaseBouncerSpeed(Player player) {
		if ((player.getScore() - lastScore) / player.getScoreBonus() >= 15) {
			increaseSpeed();
			lastScore = player.getScore();
		}
	}

}
