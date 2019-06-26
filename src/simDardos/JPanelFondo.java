package simDardos;

import java.awt.Graphics;

import javax.swing.*;

import utileria.Rutinas;

public class JPanelFondo extends JPanel{
	
	public void paint(Graphics g) {
		g.drawImage(Rutinas.AjustarImagen("img/madera.jpg",getWidth(),getHeight()).getImage(),0,0,getWidth(),getHeight(),null);
		g.fillRect(7,615,146,36);
		g.fillRect(157,615,146,36);
		g.fillRect(307,615,146,36);
		g.fillRect(457,615,146,36);
		g.fillRect(182,655,237,36);
		setOpaque(false);
		super.paint(g);
	}
}
