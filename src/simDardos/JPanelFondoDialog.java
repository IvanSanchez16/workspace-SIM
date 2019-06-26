package simDardos;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import utileria.Rutinas;

public class JPanelFondoDialog extends JPanel{
	public void paint(Graphics g) {
		g.drawImage(Rutinas.AjustarImagen("img/madera.jpg",getWidth(),getHeight()).getImage(),0,0,getWidth(),getHeight(),null);
		g.fillRect(17,17,146,36);
		g.fillRect(17,57,146,36);
		g.fillRect(17,97,146,36);
		g.fillRect(17,137,146,36);
		g.fillRect(187,17,166,76);
		g.drawImage(Rutinas.AjustarImagen("img/formula.png",160,70).getImage(),190,20,160,70,null);
		g.fillRect(187,107,146,66);
		setOpaque(false);
		super.paint(g);
	}
}
