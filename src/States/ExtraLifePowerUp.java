package States;

/**
 * ExtraLifePowerUp is a subclass to the PowerUp class, this class overrides the
 * method use() and spawn().
 */

public class ExtraLifePowerUp extends PowerUp {
	public ExtraLifePowerUp(double x, double y, String image, String sound) {
		super(x, y, image, sound);
	}

	protected void use(Player player) {
		player.setLives(player.getLives() + 1);
		makeSound();
	}
	
	protected void spawn(Player player) {
		if (player.getLives() < 3 && (player.getScore() - getScoreSinceActive()) / player.getScoreBonus() >= 10 && !getStatus()) {
			activate();
		}
	}

}
