package othello.software.javaproject;


	// this class for draw dot and and adding dot status   

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Dot extends Ellipse2D.Double {

	//generate serial version default UID
	private static final long serialVersionUID = 1L;
	
	// specify value
	static final int RADIUS = 40;
	private DotStatus dotStauts = DotStatus.NON;

	// call super class to draw circle in table 
	public Dot(int x, int y) {
		super(x, y, RADIUS, RADIUS);
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
			if (dotStauts == DotStatus.BLACK)
				g2d.setColor(Color.BLACK);
			if (dotStauts == DotStatus.WHITE)
				g2d.setColor(Color.WHITE);
			g2d.fill(this);
			g2d.setColor(Color.RED);
			g2d.draw(this);
		}
	} // end of getDot

} // end of class 
