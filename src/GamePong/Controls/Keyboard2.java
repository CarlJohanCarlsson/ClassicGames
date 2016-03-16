package GamePong.Controls;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Keyboard2 extends KeyAdapter{
public boolean up,down,left,right;
public Map<Integer,Keys> keyMap;
	
public class Keys{
	private boolean isPushed;
	private int key;
	private boolean up,down;
	
	public Keys(int key){
		this.key=key;
		isPushed = false;
	}
	
	public void setKeyUp(){
		this.up = true;
		this.down = false;
	}
	
	public void setKeyDown(){
		this.up = false;
		this.down = true;
	}
	
	public boolean isUp(){
		return this.up;
	}
	
	public boolean isDown(){
		return this.down;
	}
	
	public int getKey(){
		return key;
	}
	public boolean isPushed(){
		return isPushed;
	}
	public void setPushed(boolean set){
		this.isPushed = set;
	}
}

	public Keyboard2(){
		keyMap = new HashMap<Integer, Keys>();
		Keys k = new Keys(KeyEvent.VK_UP);
		keyMap.put(k.getKey(),k);
		k = new Keys(KeyEvent.VK_DOWN);
		keyMap.put(k.getKey(),k);
		k = new Keys(KeyEvent.VK_LEFT);
		keyMap.put(k.getKey(),k);
		k = new Keys(KeyEvent.VK_RIGHT);
		keyMap.put(k.getKey(),k);
	}

	public void update(){
	}
	
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if(keyMap.containsKey(e.getKeyCode()))keyMap.get(e.getKeyCode()).setPushed(true);
		
	}

	public void keyReleased(KeyEvent e) {
		if(keyMap.containsKey(e.getKeyCode()))keyMap.get(e.getKeyCode()).setPushed(false);
	}
}
