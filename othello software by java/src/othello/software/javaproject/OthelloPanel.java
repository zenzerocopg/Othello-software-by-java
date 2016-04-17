package othello.software.javaproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class OthelloPanel extends JPanel {

	//generate serial version  uid
	private static final long serialVersionUID = 1L;
	// specify value and object
	static final int WIDTH = 437, HEIGHT = 437;
	protected Dot[][] dot;
	protected Table[][] table;
	protected Table marginTable;
	protected boolean blackTurn = true;

	
	public OthelloPanel() {
		// create object in array of dot and table 
		dot = new Dot[8][8];
		table = new Table[8][8];

		// set option of object dot and table
		for (int i = 0, x = 48, y; i < 8; i++) {
			
			y = 48;
			
			for (int j = 0; j < 8; j++) {
				dot[i][j] = new Dot(x, y);
				table[i][j] = new Table(x, y);
				
				// set star of white dot 
				if (i == j && (i == 3 || i == 4)) {
					dot[i][j].setDotStatus(DotStatus.WHITE);
					table[i][j].setTableStatus(TableStatus.FINAL);
				}
				
				// set star of white dot 
				if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
					dot[i][j].setDotStatus(DotStatus.BLACK);
					table[i][j].setTableStatus(TableStatus.FINAL);
				}
				y += 43;
			}
			x += 43;
		}

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;

		if (blackTurn) {
			checkSelect(DotStatus.BLACK);
			blackTurn = false;
		} else {

			blackTurn = true;
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				table[i][j].getTable(g2d);
				dot[i][j].getDot(g2d);
			}
		}
		this.createMarginTable(g2d);
	}
	

	protected void createMarginTable(Graphics2D g2d) {
		for (int i = 0, x = 5, y; i < 10; i++) {
			y = 5;
			for (int j = 0; j < 10; j++) {
				if (i == 0 || i == 9 || j == 0 || j == 9) {
					marginTable = new Table(x, y);
					marginTable.setTableStatus(TableStatus.MARGIN);
					marginTable.getTable(g2d);
				}
				y += 43;
			}
			x += 43;
		}
	}

	protected void checkSelect (DotStatus dotStatus) {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (dot[i][j].getDotStatus() != dotStatus && dot[i][j].getDotStatus() != DotStatus.NON) {
					rulesCheck (dotStatus, i, j);
				}
			}
		}
	}

	protected void rulesCheck (DotStatus dotStatus, int i, int j) {
		if (getSideTableStatus(CheckTable.NORTH, i, j) == TableStatus.NORMAL) {
			for (int k = 0 ; j + k < 8 || j - k >= 0 ; k++) {
				if (getSideTableStatus(CheckTable.SOUTH, i, j + k) == TableStatus.NORMAL) {
					break;
				}
				if (getSideTableStatus(CheckTable.SOUTH, i, j + k) == TableStatus.FINAL && dot[i][j + k].getDotStatus() != dotStatus) {
					table[i][j - 1].setTableStatus(TableStatus.SELECT);
				}
			}
		}
		if (getSideTableStatus(CheckTable.SOUTH, i, j) == TableStatus.NORMAL) {
			
		}
		if (getSideTableStatus(CheckTable.EAST, i, j) == TableStatus.NORMAL) {
	
		}
		if (getSideTableStatus(CheckTable.WEST, i, j) == TableStatus.NORMAL) {
			
		}
		if (getSideTableStatus(CheckTable.NORTHEAST, i, j) == TableStatus.NORMAL) {
			
		}
		if (getSideTableStatus(CheckTable.NORTHWEST, i, j) == TableStatus.NORMAL) {
			
		}
		if (getSideTableStatus(CheckTable.SOUTHEAST, i, j) == TableStatus.NORMAL) {
			
		}
		if (getSideTableStatus(CheckTable.SOUTHWEST, i, j) == TableStatus.NORMAL) {
			
		}
	}

	protected TableStatus getSideTableStatus(CheckTable checkTable, int i, int j) {
		if (checkTable == CheckTable.NORTH && j > 0) {
			return table[i][j - 1].getTableStatus();
		}
		else if (checkTable == CheckTable.SOUTH && j < 7) {
			return table[i][j + 1].getTableStatus();
		}
		else if (checkTable == CheckTable.EAST && i < 7) {
			return table[i + 1][j].getTableStatus();
		}
		else if (checkTable == CheckTable.WEST && i > 0) {
			return table[i - 1][j].getTableStatus();
		}
		else if (checkTable == CheckTable.NORTHEAST && i < 7 && j > 0) {
			return table[i + 1][j - 1].getTableStatus();
		}
		else if (checkTable == CheckTable.NORTHWEST && i > 0 && j > 0) {
			return table[i - 1][j - 1].getTableStatus();
		}
		else if (checkTable == CheckTable.SOUTHEAST && i < 7 && j < 7) {
			return table[i + 1][j + 1].getTableStatus();
		}
		else if (checkTable == CheckTable.SOUTHWEST && i > 0 && j < 7) {
			return table[i - 1][j + 1].getTableStatus();
		}
		else
			return null;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

}
