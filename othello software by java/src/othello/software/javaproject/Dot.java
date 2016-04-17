package othello.software.javaproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Dot extends Ellipse2D.Double {

	static final int RADIUS = 40;
	private DotStatus dotStauts = DotStatus.NON;

	public Dot(int x, int y) {
		super(x, y, RADIUS, RADIUS);
	}

	public Dot() {
		super();
	}

	public DotStatus getDotStatus() {
		return dotStauts;
	}

	public void setDotStatus(DotStatus newDotStatus) {
		dotStauts = newDotStatus;
	}

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
	}

}
