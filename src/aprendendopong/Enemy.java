package aprendendopong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
	
	//inimigo vai ter intelegencia artificial para alterar a velocidade por isso uso boolean
	public double x, y;
	public int width, height;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}
	public void tick() {
		//inimigo vai seguir a bola
		x+= (Game.Ball.x-x-6) * 0.07;
		
		//colisao
		/*Rectangle rect1 = new Rectangle((int)x, (int)y, width, height);
		if() {
			
		}*/
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, width, height);
	}
}
