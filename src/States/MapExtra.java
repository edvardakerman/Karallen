package States;

import Constants.Constants;

public class MapExtra extends Map {
	public MapExtra() {
		super();
		this.map = "hangover";
		this.backgroundColor = Constants.pinkbackground;
		this.playerImg = Constants.specialPlayerImg;
		this.drinkImg = Constants.specialDrinkImg;
		this.bouncerImg = Constants.specialBouncerImg;
		this.ScoreMultiplierImg = Constants.specialScoreMultiplierImg;
	}
	
	public void bouncerCollision(Player player, Bouncer bouncer) {
		if (bouncer.playerObjectCollision(player)) {
			bouncer.relocate();
			player.relocate();
			player.setLives(player.getLives() - 1);
		}
	}
}
