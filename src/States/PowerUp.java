package States;

import java.util.Random;

import Constants.Constants;

/**
 * PowerUp is a abstract subclass to the Object class and provides the behavioral functionality for PowerUps.
 */

public abstract class PowerUp extends Object{
	private Boolean active = false;
	private int scoreSinceActive = 0;
	private Random random = new Random();

	public PowerUp(double x, double y, String image) {
		super(x, y, image);
	}
	
	protected Boolean getStatus() {
		return active;
	}
	
	protected int getScoreSinceActive() {
		return scoreSinceActive;
	}
	
	protected void deactivate() {
		active = false;
		this.objectImageView.setY(-100);
		this.objectImageView.setX(-100);
	}
	
	protected void activate() {
		active = true;
		this.objectImageView.setY(random.nextDouble() * (Constants.screenWidth - 40));
		this.objectImageView.setX(random.nextDouble((Constants.screenHeight - 40) - (Constants.screenHeight / 2))
				+ Constants.screenHeight / 2);
	}
	
	private void checkCollision(Player player) {
		if (this.playerObjectCollision(player)) {
			this.objectImageView.setY(-100);
			this.objectImageView.setX(-100);
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
