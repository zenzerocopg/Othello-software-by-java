package othello.software.javaproject;

import javax.swing.SwingUtilities;

public class OthelloGame extends MyFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	OthelloGame(String windtitle) {
		super(windtitle);
		
	}

	// create object to run program
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				OthelloGame othelloGame = new OthelloGame("Othello Software by Java");
				othelloGame.createAndShowGUI();
			}
		});

	}

	// set GUI
	protected void createAndShowGUI() {
		createComponents();
		setFrameFeatures();
	}

	// set component 
	protected void createComponents() {
		OthelloPanel othelloPanel = new OthelloPanel();
		this.add(othelloPanel);
	}

} // end of class 
