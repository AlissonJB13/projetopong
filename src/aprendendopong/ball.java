package aprendendopong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class ball {
	
	//intelegencia artificial para alterar a direção por isso uso boolean 
	public double x, y, dx, dy;
	public int width, height;
	//velocidade
	public double speed = 1.0;
	
	public ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 5;
		this.height = 5;
		
		/*vai permitir a bola se movimente automaticamente
		this.dx = new Random().nextGaussian();
		this.dy = new Random().nextGaussian();*/
		
		
		int angle = new Random().nextInt(120 - 45) + 45 + 1;
		this.dx = StrictMath.cos(StrictMath.toRadians(angle));
		this.dy = StrictMath.sin(StrictMath.toRadians(angle));
	}
	
	public void tick() {
		//colisao dos lados
		if(x+(dx*speed) + width >= Game.WIDHT) {	
			dx*=-1;
		}else if(x+(dx*speed) < 0) {
			dx*=-1;
		}
		
		if(y >= Game.HEIGHT) {
			//ponto do inimigo
			System.out.println("Ponto do Inimigo");
			new Game();
			return;
		}else if(y<0) {
			//ponto do jogador
			System.out.println("Ponto do Jogador");
			new Game();
			return;
		}
		
		//testes de colisões
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			
			
			
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			this.dx = StrictMath.cos(StrictMath.toRadians(angle));
			this.dy = StrictMath.sin(StrictMath.toRadians(angle));
			if(dy>0)
			dy*=-1;
		}else if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			this.dx = StrictMath.cos(StrictMath.toRadians(angle));
			this.dy = StrictMath.sin(StrictMath.toRadians(angle));
			if(dy<0)
			dy*=-1;
		}
		x+=dx*speed;
		y+=dy*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, width, height);
	}
}
