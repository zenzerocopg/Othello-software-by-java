package othello.software.javaproject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

// to draw table 
public class Table extends Rectangle2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int BOCK_SIZE = 40;
	private TableStatus tableStatus = TableStatus.NORMAL;

	// call super to set dimension for draw image 
	public Table(int x, int y) {
		super(x, y, BOCK_SIZE, BOCK_SIZE);
	}

	public TableStatus getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(TableStatus newTableStatus) {
		tableStatus = newTableStatus;
	}

	// method to draw table 
	public void getTable(Graphics2D g2d) {
		if (tableStatus == TableStatus.NORMAL)
			g2d.setColor(Color.GREEN);
		if (tableStatus == TableStatus.SELECT)
			g2d.setColor(Color.BLUE);
		if (tableStatus == TableStatus.ACTIVE)
			g2d.setColor(Color.RED);
		if (tableStatus == TableStatus.FINAL)
			g2d.setColor(Color.GREEN);
		if (tableStatus == TableStatus.MARGIN)
			g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(this);
	}

}
