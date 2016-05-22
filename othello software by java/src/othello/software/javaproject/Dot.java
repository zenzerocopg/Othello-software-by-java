package othello.software.javaproject;


	// this class for draw dot and and adding dot status   
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dot extends Ellipse2D.Double {

	//generate serial version default UID
	private static final long serialVersionUID = 1L;
	BufferedImage image;
	
	int dX ,dY;
	
	// specify value
	static final int RADIUS = 40;
	private DotStatus dotStauts = DotStatus.NON;

	// call super class to draw circle in table 
	public Dot(int x, int y) {
		super(x, y, RADIUS, RADIUS);
		dX = x;
		dY = y;
		
	}

	// to send dot status 
	public DotStatus getDotStatus() {
		return dotStauts;
	} // end of  getDotStatus

	// to add dot status 
	public void setDotStatus(DotStatus newDotStatus) {
		dotStauts = newDotStatus;
	} // end of setDotStatus

	// to draw dot when other class call this
	public void getDot(Graphics2D g2d) {
		if (dotStauts != DotStatus.NON) {
			if (dotStauts == DotStatus.BLACK )
				//g2d.setColor(Color.BLACK);
			try {
				image = ResizeImage.createResizedCopy(ImageIO.read(new File("nr-gear-bk.png")), RADIUS, RADIUS, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			g2d.drawImage(image, dX, dY, null);
			if (dotStauts == DotStatus.WHITE)
				//g2d.setColor(Color.WHITE);
			try {
				image = ResizeImage.createResizedCopy(ImageIO.read(new File("nr-gear-wh.png")), RADIUS, RADIUS, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			g2d.drawImage(image, dX, dY, null);
		}
		
	} // end of getDot

} // end of class 
