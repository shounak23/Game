package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

		// screen settings for game window
		final int defaultTileSize = 16;
		
		final int tileSize = defaultTileSize * 4;		// 96X96
		final int maxScreenCol = 16;
		final int maxScreenRow = 12;
		final int screenWidth = maxScreenCol * tileSize;
		final int screenHeight = maxScreenRow * tileSize;
		
		// world setting
		final int maxWorldCol = 32;
		final int maxWorldRow = 13;
		final int worldWidth = maxWorldCol * tileSize;
		final int worldHeight = maxWorldRow * tileSize;
		
		// FPS
		int FPS = 60;
		
		Thread gameThread;
		
		KeyHandler keyH = new KeyHandler();
		
		TileManager tileM = new TileManager(this);
		
		AssetSetter aSetter = new AssetSetter(this);
		
		collisionChecker cChecker = new collisionChecker(this);
		
		Player player = new Player(this, keyH);
		
		SuperObject obj[] = new SuperObject[10];		// create 10 object at a time in screen
		
		BufferedImage backgroundImage;
		
		public GamePanel() {
			
			this.setPreferredSize(new Dimension(screenWidth, screenHeight));
			this.setBackground(Color.black);
			this.setDoubleBuffered(true);				// get better rendering
			this.addKeyListener(keyH);					// add key listener in j panel
			this.setFocusable(true);					// game play receive the key input
			
			try {
				backgroundImage = ImageIO.read(new File("res\\bg.jpeg"));
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
		}
		
		public void setupGame() {
			
			this.aSetter.setObject();
		}
		public void startGameThrade() {
			
			gameThread = new Thread(this);
			gameThread.start();
		}
		@Override
		public void run() {
			
			double drawInterval = 1000/FPS;				// interval between two draw, so 60 draw per second is maintained
			double nextDrawTime = System.currentTimeMillis() + drawInterval;
			
			while(gameThread != null) {
				
				update();
				
				repaint();
							
				try {
					
					double remaingTime = nextDrawTime - System.currentTimeMillis();
					
					if(remaingTime < 0)
						remaingTime = 0;
					
					Thread.sleep((long) remaingTime);
					
					nextDrawTime += drawInterval;
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// TODO Auto-generated method stub
		}
		
		public void update() {
			
			player.update();
		}
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			g.drawImage(backgroundImage, 0, 0, screenWidth, screenHeight, null);
			
 			tileM.draw(g);								// call tileM.draw first before player.draw
 			
 			for(int i = 0; i < obj.length; i++) {
 				
 				if(obj[i] != null) {
 					
 					obj[i].draw(g, this);
 				}
 			}
			
			player.draw(g);								// player stay upward of tiles
		}
}
