package States;

import java.util.Random;

import Constants.Constants;

/**
 * PowerUp is a abstract subclass to the Object class and provides the behavioral functionality for PowerUps.
 */

public abstract class PowerUp extends GameEnity{
	private Boolean active = false;
	private int scoreSinceActive = 0;
	private Random random = new Random();

	public PowerUp(double x, double y, String image, String sound) {
		super(x, y, image, sound);
	}
	
	protected Boolean getStatus() {
		return active;
	}
	
	protected int getScoreSinceActive() {
		return scoreSinceActive;
	}

	protected void activate() {
		active = true;
		getObjectImageView().setY(random.nextDouble() * (Constants.screenWidth - 40));
		getObjectImageView().setX(random.nextDouble((Constants.screenHeight - 40)));
				
//				- (Constants.screenHeight / 2))+ Constants.screenHeight / 2);
	}
	
	private void deactivate() {
		active = false;
		getObjectImageView().setY(-100);
		getObjectImageView().setX(-100);
	}
	
	
	private void checkCollision(Player player) {
		if (this.playerObjectCollision(player)) {
			use(player);
			deactivate();
			scoreSinceActive = player.getScore();
		}	
	}
	
	public void handle(Player player) {
		this.spawn(player);
		this.checkCollision(player);
	}
	
	protected abstract void use(Player player);
	
	protected abstract void spawn(Player player);

}
