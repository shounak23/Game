package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Player extends Entity{
	
	int POSITION = 0;			// 0 indicates right and 1 indicates left
	KeyHandler keyH;
	GamePanel gp;
	
	final int screenX, screenY;		// position in current window or screen

	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);	// position of player on screen always in middle
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(0, 12, 46, 46);
//		solidArea = new Rectangle(10, 20, 76, 76);
//		solidArea.x = 0;
//		solidArea.y = 0;
//		solidArea.width = gp.tileSize;
//		solidArea.height = gp.tileSize;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 1;
		worldY = gp.tileSize * 6;
		speed = 2;
		
		direction = "right";
	}
	public void getPlayerImage() {
		
		try {
			right = ImageIO.read(new File("res\\player_right.png"));
			left = ImageIO.read(new File("res\\player_left.png"));;
			up = ImageIO.read(new File("res\\player_right.png"));;
			down= ImageIO.read(new File("res\\player_right.png"));;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public void update() {
		
		if(keyH.upPressed == true) {
			
			direction = "up";
			
			this.collisionOn = false;
			gp.cChecker.checkTile(this);
			if(collisionOn == false)
				worldY -= speed;
		}
		else if (keyH.downPressed == true) {
			
			direction = "down";
			
			this.collisionOn = false;
			gp.cChecker.checkTile(this);
			if(collisionOn == false)
				worldY += speed;
		}
		else if(keyH.leftPressed == true) {
			
			POSITION = 1;
			direction = "left";
			
			this.collisionOn = false;
			gp.cChecker.checkTile(this);
			if(collisionOn == false)
				worldX -= speed;
		}
		else if(keyH.rightPressed == true) {
			
			POSITION = 0;
			direction = "right";
			
			this.collisionOn = false;
			gp.cChecker.checkTile(this);
			if(collisionOn == false) {
				
				worldX += speed;
				while(gp.cChecker.onPlatform(this) == false) {
				
					direction = "down";
					worldY += speed;;
				}
			}
				
		}
		else if(keyH.jump == true) {
			
			direction = "jump";
			
			this.collisionOn = false;
			gp.cChecker.checkTile(this);
			if(collisionOn == false)
				jump();
		}
		
		// check tile collision
		
//		this.collisionOn = false;
//		gp.cChecker.checkTile(this);
//		if(collisionOn == false){
//			
//			switch(direction) {
//			
//				case "up": worldY -= speed; break;
//				case "down": worldY += speed; break;
//				case "left": worldX -= speed; break;
//				case "right": worldX += speed; break;
//				case "jump": jump(); break;
//			}
//		}
	}
	

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		BufferedImage image = null;
		
		switch(direction) {
		
			case "up":
				POSITION = 0;
				image = up;
				break;
			case "down":
				POSITION = 0;
				image = down;
				break;
			case "left":
				POSITION = 1;
				image = left;
				break;
			case "right":
				POSITION = 0;
				image = right;
				break;
			case "jump":
				if(POSITION == 0) {
					image = right;
				}
				else if(POSITION == 1) {
					image = left;
				}
				break;
		}
		
		g.drawImage(image, screenX, screenY, null);
		g.drawRect(screenX, screenY+12, 46, 46);
	}

	public void jump() {
		// TODO Auto-generated method stub
		worldY -= (4*speed);
		
		Timer timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				worldY += (4*speed);
			}
			
		});
		timer.setRepeats(false);
		timer.start();
	}
}
