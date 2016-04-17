package othello.software.javaproject;

import javax.swing.SwingUtilities;

public class OthelloGame extends MyFrame {

	OthelloGame(String windtitle) {
		super(windtitle);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				OthelloGame othelloGame = new OthelloGame("Othello Software by Java");
				othelloGame.createAndShowGUI();
			}
		});

	}

	protected void createAndShowGUI() {
		createComponents();
		setFrameFeatures();
	}

	protected void createComponents() {
		OthelloPanel othelloPanel = new OthelloPanel();
		this.add(othelloPanel);
	}

}
