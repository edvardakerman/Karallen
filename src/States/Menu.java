package States;
import Constants.Constants;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Menu handles the menu which is viewed from Main. This includes the options to
 * start the game and the switch game mode, and also displaying the highscore
 * and logo.
 */

public class Menu extends VBox {

	private Button startBtn;
	private Button exitBtn;
	private Button switchGameModeBtn;
	private Text highScoreText;
	private Text karallen;
	private HighScore highScore = new HighScore();
	private GameMode gameMode = new GameMode("Party");

	public Menu() {
		setStyle(Constants.blackbackground);
		setSpacing(20);
		setAlignment(Pos.CENTER);
		
		karallen = new Text("Kårallen!!");
		karallen.setFont(Font.font (Constants.font, 30));
		karallen.setFill(Color.WHITE);
		highScoreText = new Text("HighScore: " + highScore.getHighScore());
		highScoreText.setFont(Font.font(Constants.font, 15));
		highScoreText.setFill(Color.WHITE);

		startBtn = createButton("Start Game", 20);

		switchGameModeBtn = createButton("Party", 15);
		switchGameModeBtn.setOnAction(event -> {
			switchGameModeBtnAction();
		});

		exitBtn = createButton("Exit", 15);
		exitBtn.setOnAction(event -> {
			System.exit(0);
		});

		getChildren().addAll(karallen, startBtn, switchGameModeBtn, exitBtn, highScoreText);
	}

	public Button getStartBtn() {
		return startBtn;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public void updateHighScore() {
		highScoreText.setText("HighScore: " + highScore.getHighScore());
	}

	private void switchGameModeBtnAction() {
		gameMode.switchGameMode();
		setStyle(gameMode.getBackgroundColor());
		switchGameModeBtn.setText(gameMode.getCurrentGameMode());
	}

	private Button createButton(String text, int size) {
		Button btn = new Button(text);
		btn.setFont(Font.font(Constants.font, size));
		btn.setStyle("-fx-focus-color: transparent;");
		return btn;
	}

}