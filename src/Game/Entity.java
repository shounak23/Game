package Game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// parents class of Player

public class Entity {

	int worldX;						// position in world map
	int worldY;
	int speed;
	
	BufferedImage right, left, up, down;
	String direction;
	
	// an rectangle over player
	Rectangle solidArea;
	
	boolean collisionOn = false;
}
