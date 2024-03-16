package Game;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;
	}
	
	void setObject() {
		
		gp.obj[0] = new OBJ_Pipe();
		gp.obj[0].worldX = gp.tileSize * 2;
		gp.obj[0].worldY = gp.tileSize * 6;
		
		gp.obj[1] = new OBJ_Pipe();
		gp.obj[1].worldX = gp.tileSize * 3;
		gp.obj[1].worldY = gp.tileSize * 6;
	}
}
