package Game;

public class collisionChecker {
	
	GamePanel gp;
	
	public collisionChecker(GamePanel gp) {
		
		this.gp = gp;
	}
//	 check collision for any object
	public void checkTile(Entity entity) {
		
		// co-ordinate of rectangle
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		// find boundary column and row
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2, tileNum3, tileNum4, tileNum5, tileNum6, tileNum7, tileNum8;
		
		
		switch(entity.direction) {
			
			// find co-ordinate of collision tiles
			case "up":
				entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					
					entity.collisionOn = true;
				}
				break;
			case "down":
				entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					
					entity.collisionOn = true;
				}
				break;
			case "left":
				entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					
					entity.collisionOn = true;
				}
				break;
			case "right":
				entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
				tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
					
					entity.collisionOn = true;
				}
				break;
			case "jump":
				entityTopRow = (entityTopWorldY - (4*entity.speed))/gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
				tileNum3 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
				tileNum4 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
				tileNum5 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
				tileNum6 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
				tileNum7 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
				tileNum8 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
				if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true ||
				   gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true ||
				   gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true ||
				   gp.tileM.tile[tileNum3].collision == true || gp.tileM.tile[tileNum4].collision == true) {
					
					entity.collisionOn = true;
				}
				break;
		}
	}
	
	public boolean onPlatform(Entity entity) {
		
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		
		entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
		
		int tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
		int tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
		
		if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
			
			return true;
		}
		return false;
		
	}
}
