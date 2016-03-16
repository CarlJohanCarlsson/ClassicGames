package GamePong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import GamePong.Graphics.WindowFrame;
import GamePong.Objects.Ball;
import GamePong.Objects.GameObjects;
import GamePong.Objects.PaddleAI;
import GamePong.Objects.PaddlePlayer;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 640;
	public final static int HEIGHT = WIDTH / 12 * 9;
	private int paddleWidth, paddleHeight;
	private static String title = "Pong";
	private Thread thread;
	private boolean running;
	private WindowFrame wFrame;
	private GameObjects p1,p2;
	private Ball ball;
	private ArrayList<GameObjects> listObjects; 
	
	public Game(){
		paddleWidth = 20;
		paddleHeight = 80;
		listObjects = new ArrayList<GameObjects>();
		p1 = new PaddlePlayer(0,HEIGHT/2,KeyEvent.VK_UP,KeyEvent.VK_DOWN,paddleWidth,paddleHeight);
		ball = new Ball(WIDTH/2,HEIGHT/2,20,20);
		p2 = new PaddleAI(WIDTH-paddleWidth,HEIGHT/2,paddleWidth,paddleHeight, ball);		
		listObjects.add(p1);
		listObjects.add(p2);
		listObjects.add(ball);
		this.addKeyListener((PaddlePlayer) p1);
		//Only add p2 to the games KeyListener if it is a player and not a AI
		if(p2.getClass().equals(PaddlePlayer.class)) this.addKeyListener((PaddlePlayer)p2);
		wFrame = new WindowFrame(WIDTH,HEIGHT,title,this);
		requestFocus();	//Makes the canvas focused when game starts, needs to be in the game class, won't work in the WindowFrame
	}
	
	public static void main(String args[]){
		new Game();
	}
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double nanoSec = (1000000000.0)/60.0;
		double delta = 60.0;
		int loops = 0;
		int frames=0;
		long currTime;
		while(running){
			currTime = System.nanoTime();
			delta += (currTime-lastTime)/nanoSec;
			lastTime = currTime;
			while(((PaddlePlayer) p1).startGame()&&delta>=1){
				update();
				loops++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000){
				timer+=1000;
				wFrame.updateTitle(title+" | "+loops+" ups, "+frames+" fps");
				loops=0;
				frames=0;
			}
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(Color.white);
		g.drawLine(WIDTH-paddleWidth, 0, WIDTH-paddleWidth, HEIGHT);
		g.drawLine(paddleWidth, 0, paddleWidth, HEIGHT);
		g.drawString("Score: "+((PaddlePlayer)p1).getScore()+"|"+((PaddleAI)p2).getScore(), WIDTH/2, 50);
		if(!((PaddlePlayer) p1).startGame()){
			Font font= new Font ("Courier New", 1, 17);
			g.setFont(font);
			g.drawString("Press enter to begin!", WIDTH/2-90, 200);
		}else{
			for(GameObjects go : listObjects){
				go.render(g);
			}
		}
		g.dispose();
		bs.show();
	}

	public void update() {
		for(GameObjects go : listObjects){
			go.update();
			if(go.getClass().equals(ball.getClass())){
				//Case 1: If the ball collides with the roof or floor, change the velocity.
				if(ball.getY()<ball.getRadius()||ball.getY()>HEIGHT-ball.getRadius()){
					ball.collideY();
				}
				//Case 2: If the ball collides on the left side of the screen, check if player did catch the ball.
				else if(ball.getX()<=paddleWidth+ball.getRadius()){
					//Is the ball within the p1's reach?
					if(ball.getY()>=p1.getY()&&ball.getY()<=p1.getHeight()+p1.getY()){
						ball.collideX();
					}else{
						//p1 missed the ball and therefore p2 wins this match.
						((PaddleAI)p2).incScore();
						//Spawns a new ball aimed at p2
						ball.spawn(WIDTH/2,HEIGHT/2,2);
					}
				}
				else if(ball.getX()>=WIDTH-paddleWidth-ball.getRadius()){
					//Is the ball within the p2's reach?
					if(ball.getY()>=p2.getY()&&ball.getY()<=p2.getHeight()+p2.getY()){
						ball.collideX();
					}else{
						//p1 missed the ball and therefore p2 wins this match.
						((PaddlePlayer)p1).incScore();
						//Spawns a new ball aimed at p1
						ball.spawn(WIDTH/2,HEIGHT/2,1);
					}
				}
			}
		}
	}
}