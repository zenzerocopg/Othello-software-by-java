package othello.software.javaproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class OthelloPanel extends JPanel implements MouseListener {

	// generate serial version uid
	private static final long serialVersionUID = 1L;
	// specify value and object
	static final int WIDTH = 437, HEIGHT = 437;
	protected Dot[][] dot;
	protected Table[][] table;
	protected Table marginTable;
	protected boolean blackTurn = true;
	int check = 0;

	public OthelloPanel() {
		addMouseListener(this);

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
		turnCheck();
	}

	protected void turnCheck() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (table[i][j].getTableStatus() == TableStatus.ACTIVE)
					return;
				else {
					if (blackTurn) {

						checkSelect(DotStatus.BLACK);

					} else {

						checkSelect(DotStatus.WHITE);

					}
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				table[i][j].getTable(g2d);
				dot[i][j].getDot(g2d);
			}
		}

		this.createMarginTable(g2d);

		/*
		 * g2d.setColor(Color.WHITE);
		 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 * RenderingHints.VALUE_ANTIALIAS_ON); g2d.setFont(new Font("Serif",
		 * Font.BOLD, 60)); g2d.drawString(Integer.toString(check), 20, HEIGHT /
		 * 2);
		 */
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

	protected void checkSelect(DotStatus dotStatus) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (dot[i][j].getDotStatus() != dotStatus && dot[i][j].getDotStatus() != DotStatus.NON) {
					rulesCheck(dotStatus, i, j);
				}
			}
		}
	}

	protected void rulesCheck(DotStatus dotStatus, int i, int j) {
		if (getSideTableStatus(CheckSide.NORTH, i, j) == TableStatus.NORMAL
				|| getSideTableStatus(CheckSide.NORTH, i, j) == TableStatus.ACTIVE) {
			for (int k = 0; j + k < 8 && j > 0; k++) {
				if (getSideTableStatus(CheckSide.SOUTH, i, j + k) == TableStatus.NORMAL) {
					break;
				}
				if (getSideTableStatus(CheckSide.SOUTH, i, j + k) == TableStatus.FINAL
						&& getSideDotStatus(CheckSide.SOUTH, i, j + k) == dotStatus) {
					table[i][j - 1].setTableStatus(TableStatus.SELECT);
					break;
				}
			}
		}
		if (getSideTableStatus(CheckSide.SOUTH, i, j) == TableStatus.NORMAL
				|| getSideTableStatus(CheckSide.SOUTH, i, j) == TableStatus.ACTIVE) {
			for (int k = 0; j < 7 && j - k >= 0; k++) {
				if (getSideTableStatus(CheckSide.NORTH, i, j - k) == TableStatus.NORMAL) {
					break;
				}
				if (getSideTableStatus(CheckSide.NORTH, i, j - k) == TableStatus.FINAL
						&& getSideDotStatus(CheckSide.NORTH, i, j - k) == dotStatus) {
					table[i][j + 1].setTableStatus(TableStatus.SELECT);
					break;
				}
			}
		}
		if (getSideTableStatus(CheckSide.EAST, i, j) == TableStatus.NORMAL
				|| getSideTableStatus(CheckSide.EAST, i, j) == TableStatus.ACTIVE) {
			for (int k = 0; i < 7 && i - k >= 0; k++) {
				if (getSideTableStatus(CheckSide.WEST, i - k, j) == TableStatus.NORMAL) {
					break;
				}
				if (getSideTableStatus(CheckSide.WEST, i - k, j) == TableStatus.FINAL
						&& getSideDotStatus(CheckSide.WEST, i - k, j) == dotStatus) {
					table[i + 1][j].setTableStatus(TableStatus.SELECT);
					break;
				}
			}
		}
		if (getSideTableStatus(CheckSide.WEST, i, j) == TableStatus.NORMAL
				|| getSideTableStatus(CheckSide.WEST, i, j) == TableStatus.ACTIVE) {
			for (int k = 0; i + k < 8 && i > 0; k++) {
				if (getSideTableStatus(CheckSide.EAST, i + k, j) == TableStatus.NORMAL) {
					break;
				}
				if (getSideTableStatus(CheckSide.EAST, i + k, j) == TableStatus.FINAL
						&& getSideDotStatus(CheckSide.EAST, i + k, j) == dotStatus) {
					table[i - 1][j].setTableStatus(TableStatus.SELECT);
					break;
				}
			}
		}
		if (getSideTableStatus(CheckSide.NORTHEAST, i, j) == TableStatus.NORMAL
				|| getSideTableStatus(CheckSide.NORTHEAST, i, j) == TableStatus.ACTIVE) {
			for (int k = 0; j + k < 8 && j > 0 && i < 7 && i - k >= 0; k++) {
				if (getSideTableStatus(CheckSide.SOUTHWEST, i - k, j + k) == TableStatus.NORMAL) {
					break;
				}
				if (getSideTableStatus(CheckSide.SOUTHWEST, i - k, j + k) == TableStatus.FINAL
						&& getSideDotStatus(CheckSide.SOUTHWEST, i - k, j + k) == dotStatus) {
					table[i + 1][j - 1].setTableStatus(TableStatus.SELECT);
					break;
				}
			}
		}
		if (getSideTableStatus(CheckSide.NORTHWEST, i, j) == TableStatus.NORMAL
				|| getSideTableStatus(CheckSide.NORTHWEST, i, j) == TableStatus.ACTIVE) {
			for (int k = 0; j + k < 8 && j > 0 && i + k < 8 && i > 0; k++) {
				if (getSideTableStatus(CheckSide.SOUTHEAST, i + k, j + k) == TableStatus.NORMAL) {
					break;
				}
				if (getSideTableStatus(CheckSide.SOUTHEAST, i + k, j + k) == TableStatus.FINAL
						&& getSideDotStatus(CheckSide.SOUTHEAST, i + k, j + k) == dotStatus) {
					table[i - 1][j - 1].setTableStatus(TableStatus.SELECT);
					break;
				}
			}
		}
		if (getSideTableStatus(CheckSide.SOUTHEAST, i, j) == TableStatus.NORMAL
				|| getSideTableStatus(CheckSide.SOUTHEAST, i, j) == TableStatus.ACTIVE) {
			for (int k = 0; j < 7 && j - k >= 0 && i < 7 && i - k >= 0; k++) {
				if (getSideTableStatus(CheckSide.NORTHWEST, i - k, j - k) == TableStatus.NORMAL) {
					break;
				}
				if (getSideTableStatus(CheckSide.NORTHWEST, i - k, j - k) == TableStatus.FINAL
						&& getSideDotStatus(CheckSide.NORTHWEST, i - k, j - k) == dotStatus) {
					table[i + 1][j + 1].setTableStatus(TableStatus.SELECT);
					break;
				}
			}
		}
		if (getSideTableStatus(CheckSide.SOUTHWEST, i, j) == TableStatus.NORMAL
				|| getSideTableStatus(CheckSide.SOUTHWEST, i, j) == TableStatus.ACTIVE) {
			for (int k = 0; j < 7 && j - k >= 0 && i + k < 8 && i > 0; k++) {
				if (getSideTableStatus(CheckSide.NORTHEAST, i + k, j - k) == TableStatus.NORMAL) {
					break;
				}
				if (getSideTableStatus(CheckSide.NORTHEAST, i + k, j - k) == TableStatus.FINAL
						&& getSideDotStatus(CheckSide.NORTHEAST, i + k, j - k) == dotStatus) {
					table[i - 1][j + 1].setTableStatus(TableStatus.SELECT);
					break;
				}
			}
		}
	}
	
	public void eatDot (DotStatus dotStatus, int i, int j) {
		
		if (getSideTableStatus(CheckSide.NORTH, i, j) == TableStatus.FINAL 
				&& getSideDotStatus(CheckSide.NORTH, i, j) != dotStatus) {
			for (int k = 0; j - k >= 0; k++) {
				if (getSideDotStatus(CheckSide.NORTH, i, j - k) == DotStatus.NON)
					break;
				if (getSideDotStatus(CheckSide.NORTH, i, j - k) == dotStatus) {
					for (int z = 0; j - z >= 0; z++) {
						dot[i][j - z].setDotStatus(dotStatus);
						if (getSideDotStatus(CheckSide.NORTH, i, j - z) == dotStatus) {
							break;
						}
					}
					break;
				}
			}	
		}
		if (getSideTableStatus(CheckSide.SOUTH, i, j) == TableStatus.FINAL
				&& getSideDotStatus(CheckSide.SOUTH, i, j) != dotStatus) {
			for (int k = 0; j + k < 8; k++) {
				if (getSideDotStatus(CheckSide.SOUTH, i, j + k) == DotStatus.NON) 
					break;
				if (getSideDotStatus(CheckSide.SOUTH, i, j + k) == dotStatus)
					for (int z = 0; j + z < 8; z++) {
						dot[i][j + z].setDotStatus(dotStatus);
						if (getSideDotStatus(CheckSide.SOUTH, i, j + z) == dotStatus) {
							break;
						}
					}
			}
		}
		if (getSideTableStatus(CheckSide.EAST, i, j) == TableStatus.FINAL
				&& getSideDotStatus(CheckSide.EAST, i, j) != dotStatus) {
			for (int k = 0; i + k < 8; k++) {
				if (getSideDotStatus(CheckSide.EAST, i + k, j) == DotStatus.NON)
					break;
				if (getSideDotStatus(CheckSide.EAST, i + k, j) == dotStatus) {
					for (int z = 0; i + z < 8; z++) {
						dot[i + z][j].setDotStatus(dotStatus);
						if (getSideDotStatus(CheckSide.EAST, i + z, j) == dotStatus) {
							break;
						}
					}
					break;
				}
			}	
		}
		if (getSideTableStatus(CheckSide.WEST, i, j) == TableStatus.FINAL
				&& getSideDotStatus(CheckSide.WEST, i, j) != dotStatus) {
			for (int k = 0; i - k >= 0; k++) {
				if (getSideDotStatus(CheckSide.WEST, i - k, j) == DotStatus.NON)
					break;
				if (getSideDotStatus(CheckSide.WEST, i - k, j) == dotStatus) {
					for (int z = 0; i - z >= 0; z++) {
						dot[i - z][j].setDotStatus(dotStatus);
						if (getSideDotStatus(CheckSide.WEST, i - z, j) == dotStatus) {
							break;
						}
					}
					break;
				}
			}	
		}
		if (getSideTableStatus(CheckSide.NORTHEAST, i, j) == TableStatus.FINAL
				&& getSideDotStatus(CheckSide.NORTHEAST, i, j) != dotStatus) {
			for (int k = 0; j - k >= 0 && i + k < 8; k++) {
				if (getSideDotStatus(CheckSide.NORTHEAST, i + k, j - k) == DotStatus.NON)
					break;
				if (getSideDotStatus(CheckSide.NORTHEAST, i + k, j - k) == dotStatus) 
					for (int z = 0; j - z >= 0 && i + z < 8; z++) {
						dot[i + z][j - z].setDotStatus(dotStatus);
						if (getSideDotStatus(CheckSide.NORTHEAST, i + z, j - z) == dotStatus) {
							break;
						}
				}
			}	
		}
		if (getSideTableStatus(CheckSide.NORTHWEST, i, j) == TableStatus.FINAL
				&& getSideDotStatus(CheckSide.NORTHWEST, i, j) != dotStatus) {
			for (int k = 0; j - k >= 0 && i - k >= 0; k++) {
				if (getSideDotStatus(CheckSide.NORTHWEST, i - k, j - k) == DotStatus.NON)
					break;
				if (getSideDotStatus(CheckSide.NORTHWEST, i - k, j - k) == dotStatus) {
					for (int z = 0; j - z >= 0 && i - z >= 0; z++) {
						dot[i - z][j - z].setDotStatus(dotStatus);
						if (getSideDotStatus(CheckSide.NORTHWEST, i - z, j - z) == dotStatus) {
							break;
						}
					}
					break;
				}
			}	
		}
		if (getSideTableStatus(CheckSide.SOUTHEAST, i, j) == TableStatus.FINAL
				&& getSideDotStatus(CheckSide.SOUTHEAST, i, j) != dotStatus) {
			for (int k = 0; j + k < 8 && i + k < 8; k++) {
				if (getSideDotStatus(CheckSide.SOUTHEAST, i + k, j + k) == DotStatus.NON)
					break;
				if (getSideDotStatus(CheckSide.SOUTHEAST, i + k, j + k) == dotStatus) {
					for (int z = 0; j + z < 8 && i + z < 8; z++) {
						dot[i + z][j + z].setDotStatus(dotStatus);
						if (getSideDotStatus(CheckSide.SOUTHEAST, i + z, j + z) == dotStatus) {
							break;
						}
					}
					break;
				}
			}	
		}
		if (getSideTableStatus(CheckSide.SOUTHWEST, i, j) == TableStatus.FINAL
				&& getSideDotStatus(CheckSide.SOUTHWEST, i, j) != dotStatus) {
			for (int k = 0; i - k >= 0 && j + k < 8; k++) {
				if (getSideDotStatus(CheckSide.SOUTHWEST, i - k, j + k) == DotStatus.NON)
					break;
				if (getSideDotStatus(CheckSide.SOUTHWEST, i - k, j + k) == dotStatus) {
					for (int z = 0; j + z < 8 && i - z >= 0; z++) {
						dot[i - z][j + z].setDotStatus(dotStatus);
						if (getSideDotStatus(CheckSide.SOUTHWEST, i - z, j + z) == dotStatus) {
							break;
						}
					}
					break;
				}
			}	
		}
	}
	
	protected TableStatus getSideTableStatus(CheckSide checkTable, int i, int j) {
		if (checkTable == CheckSide.NORTH && j > 0) {
			return table[i][j - 1].getTableStatus();
		} else if (checkTable == CheckSide.SOUTH && j < 7) {
			return table[i][j + 1].getTableStatus();
		} else if (checkTable == CheckSide.EAST && i < 7) {
			return table[i + 1][j].getTableStatus();
		} else if (checkTable == CheckSide.WEST && i > 0) {
			return table[i - 1][j].getTableStatus();
		} else if (checkTable == CheckSide.NORTHEAST && i < 7 && j > 0) {
			return table[i + 1][j - 1].getTableStatus();
		} else if (checkTable == CheckSide.NORTHWEST && i > 0 && j > 0) {
			return table[i - 1][j - 1].getTableStatus();
		} else if (checkTable == CheckSide.SOUTHEAST && i < 7 && j < 7) {
			return table[i + 1][j + 1].getTableStatus();
		} else if (checkTable == CheckSide.SOUTHWEST && i > 0 && j < 7) {
			return table[i - 1][j + 1].getTableStatus();
		} else
			return null;
	}

	protected DotStatus getSideDotStatus(CheckSide checkDot, int i, int j) {
		if (checkDot == CheckSide.NORTH && j > 0) {
			return dot[i][j - 1].getDotStatus();
		} else if (checkDot == CheckSide.SOUTH && j < 7) {
			return dot[i][j + 1].getDotStatus();
		} else if (checkDot == CheckSide.EAST && i < 7) {
			return dot[i + 1][j].getDotStatus();
		} else if (checkDot == CheckSide.WEST && i > 0) {
			return dot[i - 1][j].getDotStatus();
		} else if (checkDot == CheckSide.NORTHEAST && i < 7 && j > 0) {
			return dot[i + 1][j - 1].getDotStatus();
		} else if (checkDot == CheckSide.NORTHWEST && i > 0 && j > 0) {
			return dot[i - 1][j - 1].getDotStatus();
		} else if (checkDot == CheckSide.SOUTHEAST && i < 7 && j < 7) {
			return dot[i + 1][j + 1].getDotStatus();
		} else if (checkDot == CheckSide.SOUTHWEST && i > 0 && j < 7) {
			return dot[i - 1][j + 1].getDotStatus();
		} else
			return null;
	}

	protected void reTable() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (table[i][j].getTableStatus() != TableStatus.FINAL) {
					table[i][j].setTableStatus(TableStatus.NORMAL);

				}
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {

		// if mouse click table
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (table[i][j].contains(arg0.getPoint())) {
					
					if (table[i][j].getTableStatus() == TableStatus.SELECT) {
						if (blackTurn)
							checkSelect(DotStatus.BLACK);
						else
							checkSelect(DotStatus.WHITE);
						table[i][j].setTableStatus(TableStatus.ACTIVE);
						repaint();
					} else if (table[i][j].getTableStatus() == TableStatus.ACTIVE) {					
						if (blackTurn) {
							dot[i][j].setDotStatus(DotStatus.BLACK);
							blackTurn = false;
							eatDot(DotStatus.BLACK, i, j);
						}
						else {
							dot[i][j].setDotStatus(DotStatus.WHITE);
							blackTurn = true;
							eatDot(DotStatus.WHITE, i, j);
						}
						table[i][j].setTableStatus(TableStatus.FINAL);
						reTable();
						turnCheck();
						repaint();
					}
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}
}
