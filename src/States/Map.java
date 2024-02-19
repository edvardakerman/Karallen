package States;

import Constants.Constants;

/**
 * GameMode handles the game mode state and updates its variables depending on
 * the current state. The class acts as a translator between the Constants and
 * the application.
 */

public class Map {
	protected String map = "party";
	protected String backgroundColor = Constants.blackbackground;
	protected String playerImg = Constants.playerImg;
	protected String drinkImg = Constants.beerImg;
	protected String bouncerImg = Constants.bouncerImg;
	protected String ScoreMultiplierImg = Constants.scoreMultiplierImg;

	public Map() {
		
	}
	
	public String getMap() {
		return this.map;
	}


	public String getBackgroundColor() {
		return this.backgroundColor;
	}

	public String getPlayerImg() {
		return this.playerImg;
	}

	public String getDrinkImg() {
		return this.drinkImg;
	}

	public String getBouncerImg() {
		return this.bouncerImg;
	}
	
	public String getScoreMultiplierImg() {
		return this.ScoreMultiplierImg;
	}
	
	public void specialEffect() {
	}
}
