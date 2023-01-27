import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Tabuleiro {

	public static final int LINHA = 6, COLUNA = 6;
	public static int[][] Tabuleiro;
	public static int ponto;
	
	public static BufferedImage spritesheet;
	
	public static int GRID_SIZE = 40;
	
	public static int DOCE_0 = 0, DOCE_1 = 1, DOCE_2 = 2, DOCE_3 = 3, DOCE_4 = 4, DOCE_5 = 5;
	

	public BufferedImage DOCE_0_SPRITE = getSprite(1021,1526,164,164);
	public BufferedImage DOCE_1_SPRITE = getSprite(1278,786,164,164);
	
	public Tabuleiro() {
		
		Tabuleiro = new int[LINHA][COLUNA];
		
		for(int x = 0; x < LINHA; x++) {
			for(int y = 0; y < COLUNA; y++) {
				Tabuleiro[x][y] = new Random().nextInt(6);
				
			}
		}
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height) {
		
		if(spritesheet == null) {
			try {
				spritesheet = ImageIO.read(Tabuleiro.class.getResource("/spritesheet.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return spritesheet.getSubimage(x, y, width, height);
	}
	
	public void tick() {
		
		ArrayList<Candy> combos = new ArrayList<Candy>();
		for(int yy = 0; yy < COLUNA; yy++) {
			if(combos.size() == 3) {
				for(int i = 0; i < combos.size(); i++) {
					int xtemp = combos.get(i).x;
					int ytemp = combos.get(i).y;
					Tabuleiro[xtemp][ytemp] = new Random().nextInt(6);
				}
				combos.clear();
				System.out.println("Ganhou um ponto");
				ponto++;
				return;
			}
				combos.clear();
				for(int xx = 0; xx < LINHA; xx++) {
					int cor = Tabuleiro[xx][yy];
					if(combos.size() == 3) {
						for(int i = 0; i < combos.size(); i++) {
							int xtemp = combos.get(i).x;
							int ytemp = combos.get(i).y;
							Tabuleiro[xtemp][ytemp] = new Random().nextInt(6);
						}
						combos.clear();
						System.out.println("Ganhou um ponto");
						ponto++;
						return;
				}
					if(combos.size() == 0) {
						combos.add(new Candy(xx,yy,cor));
	
					}else if(combos.size() > 0) {
						if(combos.get(combos.size() - 1).CANDY_TYPE == cor) {
							combos.add(new Candy(xx,yy,cor));
						}else {
							combos.clear();
							combos.add(new Candy(xx,yy,cor));
					}
				}
			}
		}
		
		combos = new ArrayList<Candy>();
		for(int xx = 0; xx < LINHA; xx++) {
			if(combos.size() == 3) {
				for(int i = 0; i < combos.size(); i++) {
					int xtemp = combos.get(i).x;
					int ytemp = combos.get(i).y;
					Tabuleiro[xtemp][ytemp] = new Random().nextInt(6);
				}
				combos.clear();
				System.out.println("Ganhou um ponto");
				ponto++;
				return;
			}
				combos.clear();
				for(int yy = 0; yy < COLUNA; yy++) {
					int cor = Tabuleiro[xx][yy];
					if(combos.size() == 3) {
						for(int i = 0; i < combos.size(); i++) {
							int xtemp = combos.get(i).x;
							int ytemp = combos.get(i).y;
							Tabuleiro[xtemp][ytemp] = new Random().nextInt(6);
						}
						combos.clear();
						System.out.println("Ganhou um ponto");
						ponto++;
						return;
				}
					if(combos.size() == 0) {
						combos.add(new Candy(xx,yy,cor));
	
					}else if(combos.size() > 0) {
						if(combos.get(combos.size() - 1).CANDY_TYPE == cor) {
							combos.add(new Candy(xx,yy,cor));
						}else {
							combos.clear();
							combos.add(new Candy(xx,yy,cor));
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		for(int x = 0; x < LINHA; x++) {
			for(int y = 0; y < COLUNA; y++) {
				g.setColor(Color.black);
				g.drawRect(x*GRID_SIZE + 24, y*GRID_SIZE + 24, GRID_SIZE, GRID_SIZE);
				
				int doce = Tabuleiro[x][y];
				if(doce == DOCE_0) {
					//g.setColor(new Color(0,82,255));//azul
					g.drawImage(DOCE_0_SPRITE,x*GRID_SIZE + 12 + 24, y*GRID_SIZE + 12 + 24, 25, 25, null);
				}
				if(doce == DOCE_1) {
					g.setColor(new Color(0,255,33));//verde
					g.fillRect(x*GRID_SIZE + 12 + 24, y*GRID_SIZE + 12 + 24, 24, 24);
				}
				if(doce == DOCE_2) {
					g.setColor(new Color(255,106,0));//laranja
					g.fillRect(x*GRID_SIZE + 12 + 24, y*GRID_SIZE + 12 + 24, 24, 24);
				}
				
				if(doce == DOCE_3) {
					g.setColor(Color.red);//vermelho
					g.fillRect(x*GRID_SIZE + 12 + 24, y*GRID_SIZE + 12 + 24, 24, 24);
				}
				if(doce == DOCE_4) {
					g.setColor(new Color(178,0,255));//roxo
					g.fillRect(x*GRID_SIZE + 12 + 24, y*GRID_SIZE + 12 + 24, 24, 24);
				}
				if(doce == DOCE_5) {
					g.setColor(new Color(255,205,0));//amarelo
					g.fillRect(x*GRID_SIZE + 12 + 24, y*GRID_SIZE + 12 + 24, 24, 24);
				}
				
				if(Game.selected) {
					
					int posx = Game.previousx / GRID_SIZE;
					int posy = Game.previousy / GRID_SIZE;
					
					g.setColor(Color.white);
					g.drawRect(posx*GRID_SIZE + 24, posy*GRID_SIZE + 24, GRID_SIZE, GRID_SIZE);
				}
				
			}
		}	
	}
	
	
}
