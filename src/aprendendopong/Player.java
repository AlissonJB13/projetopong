package aprendendopong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean right, left;
	public int x, y; //eixos do jogo horizontal e vertical respectivamente
	public int width, height; //tamanho jogador
	
	//posição inicial
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
	}
	
	public void tick() {
		if(right) {
			x++; //movimento a direta
		}else if(left) {
			x--; //movimento a esquerda
		}
		
		//colisão para evitar que o jogador "suma" da tela
		//se x + o tamanho do jogador for maior que o tamanho da tela
		if(x+width > Game.WIDHT) {
			x = Game.WIDHT - width;
		}else if(x<0) {
			x=0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height); //dimensão do jogador e posição
	}
}
