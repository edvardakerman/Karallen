package States;

import Constants.Constants;

public class Drink extends GameEnity {
	
	public Drink(double x, double y, String image, String sound) {
		super(x, y, image, sound);
		getSoundEffect().setVolume(0.3);
	}
	
	public void move() {
		setY(getY() + Constants.objectSpeed);
		getObjectImageView().setY(getY());
	}
	
	public boolean slipsByPlayer() {
		if (getY() >= Constants.screenHeight) {
			return true;
		} else {
			return false;
		}
	}

}
