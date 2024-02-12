package Constants;

/**
 * Constants stores constants like images and other values used trough out the
 * application
 */

public interface Constants {

	// Player
	String playerImg = "src/Public/player.png";
	String specialPlayerImg = "src/Public/hangover.png";
	double playerWidth = 40;
	double playerHeight = 40;
	double playerSpeed = 10;

	// Drink
	String beerImg = "src/Public/beer.png";
	String specialDrinkImg = "src/Public/soda.png";
	double objectWidth = 40;
	double objectHeight = 40;
	double objectSpeed = 0.5;
	
	// Bouncer
	String bouncerImg = "src/Public/bouncer.png";
	String specialBouncerImg = "src/Public/devil.png";

	// PowerUps
	String extraLifeImg = "src/Public/cheeseBurger.png";
	String scoreMultiplierImg = "src/Public/margarita.png";
	String specialScoreMultiplierImg = "src/Public/pill.png";

	// Screen
	final double screenWidth = 400;
	final double screenHeight = 400;

	// Other
	String font = "Impact";

	// Colors
	String pinkbackground = "-fx-background-color: #EFD9CE;";
	String blackbackground = "-fx-background-color: #111111;";


}
