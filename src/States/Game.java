package States;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Constants.Constants;
import java.util.ArrayList;
import java.util.Random;

/**
 * Game handles the game including the player and enemies.
 * 
 * The class uses an animationTimer to handle graphics and time sensitive
 * changes. This includes spawning, removing and moving objects.
 */

public class Game extends Pane {

	private Player player;
	private Bouncer bouncer;
	private ArrayList<Object> drinks = new ArrayList<Object>();
	private Text scoreText;
	private Text livesText;
	private Text gameOverText;
	private Text instrcutionsText;
	private boolean isGameOver = false;
	private AnimationTimer gameLoop;

	public Game(GameMode gameMode) {

		setPrefSize(Constants.screenWidth, Constants.screenHeight);
		setStyle(gameMode.getBackgroundColor());

		player = new Player(gameMode.getPlayerImg());
		bouncer = new Bouncer(gameMode.getBouncerImg());
		getChildren().addAll(player.getPlayerImageView(), bouncer.getObjectImageView());

		gameOverText = createText(Constants.screenWidth / 2 - 50, Constants.screenHeight / 2 - 30, "Game Over", 20);
		instrcutionsText = createText(Constants.screenWidth / 2 - 70, Constants.screenHeight / 2, "Press ESC for menu",
				15);

		livesText = createText(10, 20, "Lives: " + player.getLives(), 18);
		scoreText = createText(10, 50, "Score: " + player.getScore(), 18);
		getChildren().addAll(livesText, scoreText);

		ExtraLifePowerUp extraLifePowerUp = new ExtraLifePowerUp(-100, -100, Constants.extraLifeImg);
		getChildren().add(extraLifePowerUp.getPowerUpImageView());

		ExtraScorePowerUp extraScorePowerUp = new ExtraScorePowerUp(-100, -100, gameMode.getScoreMultiplierImg());
		getChildren().add(extraScorePowerUp.getPowerUpImageView());

		gameLoop = new AnimationTimer() {
			private long lastDrinkSpawnTime, lastExtraLifePoweUpSpawnTime, lastExtraScorePoweUpSpawnTime = 10;
			private Random random = new Random();

			@Override
			public void handle(long now) {
				checkGameStatus();
				if (isGameOver) {
					this.stop();
					getChildren().addAll(gameOverText, instrcutionsText);
				}
				spawnEnemy(now);
				spawnPowerUps(now);
				moveEntities();
				extraLifePowerUp.use(player);
				extraScorePowerUp.use(player);
				livesText.setText("Lives: " + player.getLives());
				scoreText.setText("Score: " + player.getScore());
			}

			private void spawnEnemy(long now) {
				if (now - lastDrinkSpawnTime >= 3000000000L - player.getScore() * 10000000) {
					double enemyX = random.nextDouble() * (Constants.screenWidth - Constants.objectWidth);
					double enemyY = 0;
					Object drink = new Object(enemyX, enemyY, gameMode.getDrinkImg());
					getChildren().add(drink.getObjectImageView());
					drinks.add(drink);
					lastDrinkSpawnTime = now;

				}
			}

			private void spawnPowerUps(long now) {
				if (now - lastExtraLifePoweUpSpawnTime >= 30000000000L && player.getLives() < 3) {
					double powerUpX = random.nextDouble() * (Constants.screenWidth - Constants.powerUpWidth);
					double powerUpY = random.nextDouble(
							(Constants.screenHeight - Constants.powerUpHeight) - (Constants.screenHeight / 2))
							+ Constants.screenHeight / 2;
					extraLifePowerUp.getPowerUpImageView().setX(powerUpX);
					extraLifePowerUp.getPowerUpImageView().setY(powerUpY);
					lastExtraLifePoweUpSpawnTime = now;

				}

				if (now - lastExtraScorePoweUpSpawnTime >= 30000000000L && player.getScore() % 10 == 0 && player.getScore() > 10) {
					double powerUpX = random.nextDouble() * (Constants.screenWidth - 40);
					double powerUpY = random.nextDouble((Constants.screenHeight - 40) - (Constants.screenHeight / 2))
							+ Constants.screenHeight / 2;
					extraScorePowerUp.getPowerUpImageView().setX(powerUpX);
					extraScorePowerUp.getPowerUpImageView().setY(powerUpY);
					lastExtraScorePoweUpSpawnTime = now;

				}

			}

			private void moveEntities() {

				// Temporary variables
				Object objectTmp = new Object(-100, -100, Constants.beerImg);

				// Drinks
				for (Object drink : drinks) {
					drink.move();
					objectTmp = collisions(drink, objectTmp);
				}
				drinks.remove(objectTmp);

				// Bouncer
				bouncer.move();
				bouncer.playerObjectCollision(player);

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
	}

	public void handleKeyPress(KeyCode keyCode) {
		player.move(keyCode);
	}
	
	private void increaseBouncerSpeed() {
		if (player.getScore() % 20 == 0) {
			bouncer.increaseSpeed();
		}
	}

	private Object collisions(Object drink, Object objectTmp) {
		if (drink.playerObjectCollision(player)) {
			getChildren().remove(drink.getObjectImageView());
			objectTmp = drink;
			increaseBouncerSpeed();
		} else if (drink.slipsByPlayer()) {
			getChildren().remove(drink.getObjectImageView());
			objectTmp = drink;
			player.setLives(player.getLives() - 1);
		}

		return objectTmp;
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
		}
	}

}