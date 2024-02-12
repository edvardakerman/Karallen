package States;

import Constants.Constants;

public class Drink extends Object {
	
	/**
	 * Drink is a subclass to the Object class and provides more behavioral functionality like movement.
	 */
	
	public Drink(double x, double y, String image) {
		super(x, y, image);
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
