package Game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Pipe extends SuperObject{
	
	public OBJ_Pipe() {
		
		name = "Pipe";
		
		try {
			
			image = ImageIO.read(new File("res\\greenPipe.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
	}
}
