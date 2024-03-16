package Game;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class TileManager{
	
	GamePanel gp;
	Tile[] tile;							// array of Tile class
	
	int[][] mapTileNum;
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];				// how many different types of tiles are present
		mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];	// initialize the array
		
		getTileImages();
		loadMap("res\\tiles.txt");
	}
	
	public void getTileImages() {
		
		try {

			tile[0] = new Tile();			// initialization each index  to tile type
			tile[0].image = ImageIO.read(new File("res\\BrownBrick1.png"));
			
			tile[1] = new Tile();			// initialization each index  to tile type
			tile[1].image = ImageIO.read(new File("res\\transparent.png"));
//			tile[1].image = ImageIO.read(new File("res\\BrownTiles1.png"));
			
			tile[2] = new Tile();			// initialization each index  to tile type
			tile[2].image = ImageIO.read(new File("res\\BrownTiles2.png"));
			tile[2].collision = true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public void loadMap(String path) {
		
		try {
			
			//import the file
			// read the file tiles.txt
			Scanner scanner = new Scanner(new File(path));
			int col;
			int row;
			
			for(row = 0; row < gp.maxWorldRow; row++) {
				
				// read single row from the file as a string
				String line = scanner.nextLine();
				// spilt the row (string) into words when get a space and store them in numbers[] array
				// number[0] = word1 number[1] = word2 and so on
				String numbers[] = line.split(" ");
				
				for(col = 0; col < gp.maxWorldCol; col++) {
					// convert word i.e. "0" into 0, use col as index of numbers[] array
					mapTileNum[row][col] = Integer.parseInt(numbers[col]);
					
				}
			}
		}catch(Exception e) {
			
		}
		for(int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++){
			
			for(int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {
				
				System.out.print(mapTileNum[worldRow][worldCol] + " ");
			}
			System.out.println();
		}
	}
	
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		int worldCol , worldRow;
		
		for(worldRow = 0; worldRow < gp.maxWorldRow; worldRow++){
			
			for(worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {
				
				int tileNum = mapTileNum[worldRow][worldCol];
				
				int worldX = worldCol * gp.tileSize;
				int worldY = worldRow * gp.tileSize;
				int screenX = worldX - gp.player.worldX + gp.player.screenX;
				int screenY = worldY - gp.player.worldY + gp.player.screenY;
				
				if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && 
				   worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
				   worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
				   worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
					
					g.drawImage(tile[tileNum].image, screenX, screenY, null);
				}
				
			}
		}
	}
}
