package GamePong.Objects;
import java.awt.Graphics;


public abstract class GameObjects{
	private double x,y; 
	private int width, height;
	private double[] vel;
	
	public GameObjects(int x, int y,int width, int height){
		this.x = x;
		this.y = y;
		this.vel = new double[2];
		this.width = width;
		this.height = height;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	public void setVel(double d,double e){
		this.vel[0] = d;
		this.vel[1] = e;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setX(double d){
		this.x = d;
	}
	
	public double[] getVel(){
		return this.vel;
	}
		
	public void setY(double y){
		this.y = y;
	}
	/*
	 * Returns the height of the gameobject
	 */
	public int getHeight(){
		return this.height;
	}
	/*
	 * Returns the width of the gameobject
	 */
	public int getWidth(){
		return this.width;
	}
	
}
