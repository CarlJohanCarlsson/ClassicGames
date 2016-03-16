package GamePong.Objects;

import java.awt.Color;
import java.awt.Graphics;
/**
 * The paddle class controlled by the AI, its cheating btw.
 * @param x	Where the paddle will be drawn in x position.
 * @param y	Where the paddle will be drawn in y position.
 * @param up The button used for steering the paddle up.
 * @param down The button used for steering the paddle down.
 * @param height Used for collision detection
 */
public class PaddleAI extends GameObjects{
	private int score;
	private Ball ball;


	public PaddleAI(int x, int y, int paddleWidth, int paddleHeight, Ball ball){
		super(x,y, paddleWidth, paddleHeight);
		this.ball = ball;
		score=0;
	}
	
	/**
	 * The PaddlePlayer won the ball! Increase score with 1.
	 */
	public void incScore(){
		score++;
	}
	
	/**
	 * @return Score
	 */
	public int getScore(){
		return score;
	}

	@Override
	public void update() {
		setY(ball.getY()-this.getHeight()/2);	//Updates the position after the ball, the AI is cheating =(
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)this.getX(),(int)this.getY(),this.getWidth(),this.getHeight());
	}
}
