package aprendendopong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable, KeyListener {
	
	public static final long serialVersionUID = 1L;
	public static int WIDHT = 240;
	public static int HEIGHT = 120;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDHT, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	//static para poder acessar na classe Enemy
	public static ball Ball;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDHT*SCALE, HEIGHT*SCALE));
		//adicionando movimento ao jogador
		this.addKeyListener(this);
		player = new Player(100, HEIGHT-5);// posicionando jogador no centro e na altura menos 10 pq é a altura do jogador
		enemy = new Enemy(100, 0);
		Ball = new ball(100, HEIGHT/2 - 1);
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	public void tick(){
		player.tick();
		enemy.tick();
		Ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return; // preciso retornar para utilizar o buffer
		}
		Graphics g = layer.getGraphics();
		/*procedimento para limpar a imagem, pq caso não fosse feito isso o retangulo player iria
		 "esticar" conforme as teclas fossem apertadas*/ 
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDHT, HEIGHT);
		
		//renderizar os elementos
		player.render(g);
		enemy.render(g);
		Ball.render(g);
		
		
		g = bs.getDrawGraphics();
		g.drawImage(layer,0, 0, WIDHT*SCALE, HEIGHT*SCALE,null); //desenhar o jogador e o fundo da tela
		
		bs.show();
	}
	
	public void run() {
		while(true) {
			tick();
			render();			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//getKeyCode verifica se a tecla pressionada é equivalente ao valor solitado no caso tecla do lado direito
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.right = true;
				}
				else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.left = true;
				}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//detectar quando parei de pressionar o botao
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
	}
}