package GamePong.Graphics;

import java.util.Random;

public class Screen {
	private int width, height;
	public int[] pixels;
	private int[] tiles;
	private int tileSize = 64;
	private int tile_MASK = (int) ( Math.log(tileSize)/Math.log(2));
	private Random ran;
	
	public Screen(int width, int height){
		this.width=width;
		this.height=height;
		pixels = new int[width*height];
		tiles = new int[(int) Math.pow(tileSize,2)];
		ran = new Random();
		for(int i = 0; i<tiles.length;i++){
			tiles[i] = ran.nextInt(0xffffff);
		}
	}
	
	public void clear(){
		for(int i = 0;i<pixels.length;i++){
			pixels[i] = 0;
		}
	}

	public void render(int xm, int ym){
		int tileNr=0;
		for(int y = 0; y<height;y++){
			tileNr = ((y+ym>>4)&tileSize-1)<<tile_MASK;	//Size of the tiles 2^4 = 16, precompute one part of the tiles here.
			for(int x = 0; x<width;x++){
				pixels[x+y*width] = tiles[(tileNr+((x+xm>>4)&tileSize-1))];
				//pixels[x+y*width] = 0xff00ff;
			}
		}
	}
}
