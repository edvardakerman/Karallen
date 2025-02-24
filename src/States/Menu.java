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
	private Button switchMapBtn;
	private Text highScoreText;
	private Text karallen;
	private HighScore highScore = new HighScore();
	private Map map = new Map();	

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

		switchMapBtn = createButton("Party (Hard)", 15);
		switchMapBtn.setOnAction(event -> {
			switchMapBtnAction();
		});

		exitBtn = createButton("Exit", 15);
		exitBtn.setOnAction(event -> {
			System.exit(0);
		});

		getChildren().addAll(karallen, startBtn, switchMapBtn, exitBtn, highScoreText);
	}

	public Button getStartBtn() {
		return startBtn;
	}

	public Map getMap() {
		return map;
	}

	public void updateHighScore() {
		highScoreText.setText("HighScore: " + highScore.getHighScore());
	}

	private void switchMapBtnAction() {
		if (map.getMap() == "Party (hard)") {
			map = new MapExtra();
		} else {
			map = new Map();
		}
		setStyle(map.getBackgroundColor());
		switchMapBtn.setText(map.getMap());
	}

	private Button createButton(String text, int size) {
		Button btn = new Button(text);
		btn.setFont(Font.font(Constants.font, size));
		btn.setStyle("-fx-focus-color: transparent;");
		return btn;
	}

}