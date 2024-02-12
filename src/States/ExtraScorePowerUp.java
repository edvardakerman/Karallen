package States;

/**
 * ExtraScorePowerUp is a subclass to the PowerUp class, this class overrides
 * the method use() and spawn().
 */

public class ExtraScorePowerUp extends PowerUp {

	public ExtraScorePowerUp(double x, double y, String image) {
		super(x, y, image);
	}

	public void use(Player player) {
		player.setScoreBonus(player.getScoreBonus() + 1);
	}
	
	public void spawn(Player player) {
		if ((player.getScore() - getScoreSinceActive()) / player.getScoreBonus() >= 20 && !getStatus()) {
			activate();
		}
	}
	
}