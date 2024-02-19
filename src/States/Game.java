package States;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Constants.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game handles the game including the player and objects.
 * 
 * The class uses an animationTimer to handle graphics and time sensitive
 * changes. This includes spawning, removing and moving objects.
 */

public class Game extends Pane {

	private Player player;
	private Bouncer bouncer;
	private ArrayList<Drink> drinks = new ArrayList<Drink>();
	private Text scoreText;
	private Text livesText;
	private Text gameOverText;
	private Text instrcutionsText;
	private boolean isGameOver = false;
	private AnimationTimer gameLoop;
	private AudioClip technoSound;
	private AudioClip gameOverSound;

	public Game(Map map) {
		
		File sound = new File(Constants.techno);
		technoSound = new AudioClip(sound.toURI().toString());
		sound = new File(Constants.gameOver);
		gameOverSound = new AudioClip(sound.toURI().toString());
		
		technoSound.setCycleCount(10);
		technoSound.play();

		setPrefSize(Constants.screenWidth, Constants.screenHeight);
		setStyle(map.getBackgroundColor());

		player = new Player(map.getPlayerImg());
		bouncer = new Bouncer(map.getBouncerImg(), Constants.bouncerSound);
		getChildren().addAll(player.getPlayerImageView(), bouncer.getObjectImageView());

		gameOverText = createText(Constants.screenWidth / 2 - 50, Constants.screenHeight / 2 - 30, "Game Over", 20);
		instrcutionsText = createText(Constants.screenWidth / 2 - 70, Constants.screenHeight / 2, "Press ESC for menu",
				15);

		livesText = createText(10, 20, "Lives: " + player.getLives(), 18);
		scoreText = createText(10, 50, "Score: " + player.getScore(), 18);
		getChildren().addAll(livesText, scoreText);

		ExtraLifePowerUp extraLifePowerUp = new ExtraLifePowerUp(-100, -100, Constants.extraLifeImg, Constants.bite);
		getChildren().add(extraLifePowerUp.getObjectImageView());

		ExtraScorePowerUp extraScorePowerUp = new ExtraScorePowerUp(-100, -100, map.getScoreMultiplierImg(), Constants.yeah);
		getChildren().add(extraScorePowerUp.getObjectImageView());

		gameLoop = new AnimationTimer() {
			private long lastDrinkSpawnTime = 10;
			private Random random = new Random();

			@Override
			public void handle(long now) {
				checkGameStatus();
				if (isGameOver) {
					this.stop();
					getChildren().addAll(gameOverText, instrcutionsText);
				}
				spawnDrink(now);
				extraLifePowerUp.handle(player);
				extraScorePowerUp.handle(player);
				moveEntities();
				livesText.setText("Lives: " + player.getLives());
				scoreText.setText("Score: " + player.getScore());
			}

			private void spawnDrink(long now) {
				if (now - lastDrinkSpawnTime >= 3000000000L - player.getScore() * 10000000) {
					double drinkX = random.nextDouble() * (Constants.screenWidth - Constants.objectWidth);
					double drinkY = 0;
					Drink drink = new Drink(drinkX, drinkY, map.getDrinkImg(), Constants.slurp);
					getChildren().add(drink.getObjectImageView());
					drinks.add(drink);
					lastDrinkSpawnTime = now;
				}
			}

			private void moveEntities() {

				// Temporary drink
				Drink tmpDrink = new Drink(-100, -100, Constants.beerImg, Constants.slurp);

				// Drinks
				for (Drink drink : drinks) {
					drink.move();
					tmpDrink = collisions(drink, tmpDrink);
				}
				drinks.remove(tmpDrink);

				// Bouncer
				bouncer.move();
				if (bouncer.playerObjectCollision(player)) {
					player.setLives(0);
				}

			}
		};

	}
	


	public void startGame() {
		gameLoop.start();
	}

	public void stopGame() {
		isGameOver = true;
		HighScore highScore = new HighScore();
		highScore.saveScore(player.getScore());
		technoSound.stop();
	}

	public void handleKeyPress(KeyCode keyCode) {
		player.move(keyCode);
	}

	private Drink collisions(Drink drink, Drink tmpDrink) {
		if (drink.playerObjectCollision(player)) {
			getChildren().remove(drink.getObjectImageView());
			tmpDrink = drink;
			player.setScore(1);
			bouncer.increaseBouncerSpeed(player);
			drink.makeSound();
		} else if (drink.slipsByPlayer()) {
			getChildren().remove(drink.getObjectImageView());
			tmpDrink = drink;
			player.setLives(player.getLives() - 1);
		}

		return tmpDrink;
	}

	private Text createText(double x, double y, String text, int size) {
		Text t = new Text(x, y, text);
		t.setFont(Font.font(Constants.font, size));
		t.setFill(Color.WHITE);
		return t;
	}

	private void checkGameStatus() {
		if (player.getLives() == 0) {
			stopGame();
			gameOverSound.play();
		}
	}

}