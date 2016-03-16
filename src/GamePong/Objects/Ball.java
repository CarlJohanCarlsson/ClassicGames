package GamePong.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball extends GameObjects{
	private double incVel = 1.1;	//Everytime the ball bounces it will increase in velocity.
	
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setVel(2*setInitialVel(),2*setInitialVel());
	}
	
	private int setInitialVel(){
		Random rand = new Random();
		int pos = rand.nextInt(2);
		return (pos==0?-1:1);
	}

	@Override
	public void update() {
		double vel = this.getX()+this.getVel()[0];
		this.setX(vel);
		vel = this.getY()+this.getVel()[1];
		this.setY(vel);
	}

	/**
	 * Only handles the collision with roof and floor.
	 */
	public void collideY(){
		double vel = this.getVel()[1];
		vel = -vel;
		this.setVel(this.getVel()[0]*incVel, vel*incVel);
	}
	
	/**
	 * Only handles the collision with the walls.
	 */
	public void collideX(){
		double vel = this.getVel()[0];
		vel = -vel;
		this.setVel(vel*incVel,this.getVel()[1]*incVel);
	}
	
	/**
	 * Spawns a new ball in the center of the field aimed at a player.
	 * @param i either 0 or 1, determines the velocity in x direction 
	 */
	public void spawn(int x,int y,int i){
		setX(x);
		setY(y);
		if(i==1){
			setVel(2*setInitialVel(),2*setInitialVel());
		}else{
			setVel(2*setInitialVel(),2*setInitialVel());
		}		
	}
	
	public int getRadius(){
		return this.getHeight()/2;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)(this.getX()-(this.getWidth()/2)), (int)(this.getY()-(this.getWidth()/2)), (int)this.getWidth(), (int)this.getHeight());
	}
}