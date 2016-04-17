package othello.software.javaproject;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;



@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	
	MyFrame(String windtitle){
		super (windtitle);
	}
	
	protected void setFrameFeatures(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setVisible(true);
		pack();
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width - w)/2;
		int y = (dim.height - h)/2;
		setLocation(x,y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
