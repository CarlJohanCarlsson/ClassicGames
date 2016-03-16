package GamePong.Graphics;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import GamePong.Game;

@SuppressWarnings("serial")
public class WindowFrame extends Canvas {
	private JFrame frame;
	public WindowFrame(int width, int height, String title, Game game){
		Dimension size = new Dimension(width, height);
		frame = new JFrame(title);
		frame.getContentPane().setPreferredSize(size);	//Sets the canvas to size, not including the title bar.
		frame.setResizable(true);
		frame.add(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Close application on exit
		frame.setLocationRelativeTo(null);	//Frame is centered
		frame.setVisible(true);
		frame.pack();	//Same size on canvas as Dimension size.
		game.start();
	}
	
	public void updateTitle(String title){
		frame.setTitle(title);
	}
}
