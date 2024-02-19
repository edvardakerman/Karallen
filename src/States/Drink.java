package States;

import Constants.Constants;

public class Drink extends Object {
	
	public Drink(double x, double y, String image, String sound) {
		super(x, y, image, sound);
	}
	
	public void move() {
		Y += Constants.objectSpeed;
		objectImageView.setY(Y);
	}
	
	public boolean slipsByPlayer() {
		if (this.Y >= 400) {
			return true;
		} else {
			return false;
		}
	}

}
