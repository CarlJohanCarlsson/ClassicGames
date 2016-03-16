package GamePong.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The paddle class controlled by the player(s).
 * @param x	Where the paddle will be drawn in x position.
 * @param y	Where the paddle will be drawn in y position.
 * @param up The button used for steering the paddle up.
 * @param down The button used for steering the paddle down.
 * @param height Used for collision detection
 */
public class PaddlePlayer extends GameObjects implements KeyListener {
	private int up,down, frameHeight,score;
	private boolean is_up, is_down, enter;


	public PaddlePlayer(int x, int y, int up, int down, int width, int height){
		super(x,y, width, height);
		this.up= up;
		this.down= down;
		this.frameHeight = 2*y;	//Used for collision detection
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
	
	/**
	 * Used for starting the game.
	 * @return true if enter is pressed.
	 */
	public boolean startGame(){
		return enter;
	}

	@Override
	public void update() {
		adjustPaddleVel();
		this.setY(this.getY()+this.getVel()[1]);	//Updates the position
		paddleCollission();
	}
	
	/**
	 * When the keys corresponding to up/down is pressed the velocity is determined and/or capped.
	 */
	private void adjustPaddleVel(){
		double vel = this.getVel()[1];
		if(is_up){
			vel-=2;
		}
		else if(is_down){
			vel+=2;
		}
		else if(!is_up && !is_down){
			vel*=0.80;
		}
		if(vel<-6)vel=-6;
		if(vel>6)vel=6;
		this.setVel(this.getVel()[0], vel);
	}
	
	/**
	 * Checks if the paddle will collide with the floor/roof
	 */
	private void paddleCollission(){
		if(getY()+this.getHeight()>=frameHeight){
			setY(frameHeight-this.getHeight());
		}
		else if(getY()<0){
			setY(0);
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)this.getX(),(int)this.getY(),this.getWidth(),this.getHeight());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==up){
			is_up=true;
		}
		else if(e.getKeyCode()==down){
			is_down=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_ENTER){
			enter = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==up){
			is_up=false;
		}
		else if(e.getKeyCode()==down){
			is_down=false;
		}
	}
}