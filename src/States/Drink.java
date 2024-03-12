package States;

import Constants.Constants;

public class Drink extends Object {
	
	public Drink(double x, double y, String image, String sound) {
		super(x, y, image, sound);
		getSoundEffect().setVolume(0.3);
	}
	
	public void move() {
		setY(getY() + Constants.objectSpeed);
		getObjectImageView().setY(getY());
	}
	
	public boolean slipsByPlayer() {
		if (getY() >= 400) {
			return true;
		} else {
			return false;
		}
	}

}
