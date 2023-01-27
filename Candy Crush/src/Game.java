

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;



public class Game extends Canvas implements Runnable,MouseListener,MouseMotionListener{

	
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 288, HEIGHT = 288, SCALE = 2;
	
	public static final int FPS = 1000/60;
	
	public Tabuleiro tabuleiro;
	
	public UI ui;
	
	
	public BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	
	public static boolean selected = false;
	public static int previousx, previousy;
	public static int nextx = -1, nexty = -1;
	
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		tabuleiro = new Tabuleiro();
		
		ui = new UI();
		
	}

	public void tick() {
		
		tabuleiro.tick();
       
		
	
		
		if(selected && (nextx != -1 && nexty != -1)) {
			
			if(Game.previousx < 0 || Game.previousx >= Tabuleiro.GRID_SIZE*Tabuleiro.LINHA
					|| Game.previousy < 0 || Game.previousy >= Tabuleiro.GRID_SIZE*Tabuleiro.COLUNA) {
				
				Game.nextx = -1;
				Game.nexty = -1;
				Game.selected = false;
				
			}
			
			int posx1 = previousx / Tabuleiro.GRID_SIZE;
			int posy1 = previousy / Tabuleiro.GRID_SIZE;
			
			int posx2 = nextx / Tabuleiro.GRID_SIZE;
			int posy2 = nexty / Tabuleiro.GRID_SIZE;
			
			if((posx2 == posx1 + 1 || posx2 == posx1 - 1) && posy2 == posy1 ||
					posy2 == posy1 - 1 || posy2 == posy1 + 1) {
				
				if((posx2 >= posx1 + 1 || posx2 <= posx1 - 1) && 
						(posy2 >= posy1 + 1 || posy2 <= posy1 - 1)) {
					
					System.out.println("Não pode mover");
					return;
				}
				int val1 = Tabuleiro.Tabuleiro[posx2][posy2];
				int val2 = Tabuleiro.Tabuleiro[posx1][posy1];
				Tabuleiro.Tabuleiro[posx1][posy1] = val1;
				Tabuleiro.Tabuleiro[posx2][posy2] = val2;	
					nextx = -1;
					nexty = -1;
					selected = false;
					System.out.println("pode mover");
				
			}else {
				System.out.println("nao pode mover");
			}
		}
		
		
	}

	
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(105,0,201));
		g.fillRect(0, 0, WIDTH,HEIGHT);
		tabuleiro.render(g);
		ui.render(g);
		g = bs.getDrawGraphics();
		g.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
		
		g.dispose();
		bs.show();
	}

	
	
	public static void main(String args[]) {
		Game game = new Game();
		JFrame frame = new JFrame("Candy Crush");
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(game).start();
	}
	
	@Override
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(selected == false) {
			selected = true;
			previousx = e.getX() / SCALE - 24;
			previousy = e.getY() / SCALE - 24;
		}else {
			nextx = e.getX() / SCALE - 24;
			nexty = e.getY() / SCALE - 24;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
