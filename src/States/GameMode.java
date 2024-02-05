package States;

import Constants.Constants;

/**
 * GameMode handles the game mode state and updates its variables depending on
 * the current state. The class acts as a translator between the Constants and
 * the application.
 */

public class GameMode {
	private String backgroundColor = Constants.blackbackground;
	private String playerImg = Constants.playerImg;
	private String drinkImg = Constants.beerImg;
	private String bouncerImg = Constants.bouncerImg;
	private String ScoreMultiplierImg = Constants.scoreMultiplierImg;
	private String currentGameMode;

	public GameMode(String GameMode) {
		setGameMode(GameMode);
	}

	private void setGameMode(String GameMode) {
		this.currentGameMode = GameMode;
		if (this.currentGameMode == "Party") {
			this.backgroundColor = Constants.blackbackground;
			this.playerImg = Constants.playerImg;
			this.drinkImg = Constants.beerImg;
			this.bouncerImg = Constants.bouncerImg;
			this.ScoreMultiplierImg = Constants.scoreMultiplierImg;
		} else if (this.currentGameMode == "Hangover") {
			this.backgroundColor = Constants.pinkbackground;
			this.playerImg = Constants.specialPlayerImg;
			this.drinkImg = Constants.specialDrinkImg;
			this.bouncerImg = Constants.specialBouncerImg;
			this.ScoreMultiplierImg = Constants.specialScoreMultiplierImg;
		}
	}

	public void switchGameMode() {
		if (currentGameMode == "Party") {
			setGameMode("Hangover");
		} else if (currentGameMode == "Hangover") {
			setGameMode("Party");
		}
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

	public String getCurrentGameMode() {
		return this.currentGameMode;
	}
}
