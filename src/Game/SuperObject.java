package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	
	public void draw(Graphics g, GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
			
			g.drawImage(image, screenX, screenY, null);
		}
	}
}
