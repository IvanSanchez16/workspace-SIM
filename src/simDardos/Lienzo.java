package simDardos;

import java.awt.*;
import java.util.ArrayList;

import utileria.Rutinas;

public class Lienzo extends Canvas{
	
	Graphics g,gaux;
	Image backimage,imageAux;
	private int width=600,height=600;
	boolean Band=true;
	ArrayList<Marca> AM=new ArrayList<Marca>();
	
	public Lienzo(Image graphics) {
		Dimension size=new Dimension(width,height);
		setSize(size);
		backimage=graphics;
		g=backimage.getGraphics();
		imageAux=graphics;
		gaux=imageAux.getGraphics();
		Dibuja();
	}
	
	public void Dibuja() {
		boolean band=true;
		for(int i=0;i<600;i+=20) {
			for(int j=0;j<600;j+=20) {
				if(band) {
					g.setColor(new Color(50,50,50));
					g.fillRect(j,i,20,20);
				}else {
					g.setColor(Color.WHITE);
					g.fillRect(j,i,20,20);
				}
				band=!band;
			}
			band=!band;
		}
		g.drawRect(0,0,599,599);
		g.fillOval(0,0,599,599);
		g.drawImage(Rutinas.AjustarImagen("img/diana.png",614,618).getImage(),-8,-3,614,618,null);
		if(Band) {
			for(int i=0; i<AM.size(); i++) {
				g.drawImage(AM.get(i).getImg(),AM.get(i).getX(),AM.get(i).getY(),16,16,null);
			}
		}
		repaint();
	}
	
	public void AgregaMarca(int x,int y) {
		if(Band) {
			AM.add(new Marca(x-8,y-8));
			Dibuja();
		}else {
			Marca marca=new Marca(x-8,y-8);
			g.drawImage(marca.getImg(),marca.getX(),marca.getY(),16,16,null);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backimage,0,0,getWidth(),getHeight(),null);
	}
}
