import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;



public class UI {
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString("Pontuação: "+ Tabuleiro.ponto, 5, 20);
	}

}
