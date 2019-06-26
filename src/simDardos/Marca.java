package simDardos;

import java.awt.*;

import utileria.Rutinas;
public class Marca {
	private Image img;
	private int x;
	private int y;
	
	public Marca(int equis,int lle) {
		img=Rutinas.AjustarImagen("img/cruzazul2.png",16,16).getImage();
		x=equis;
		y=lle;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImg() {
		return img;
	}
	
	
}
