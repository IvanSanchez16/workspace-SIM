package Agujas;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Vector;

import javax.swing.*;

import utileria.Rutinas;
public class Lienzo extends Canvas
{
	Graphics2D g;
	Image backImage;
	Vector<Aguja> agujas=new Vector<Aguja>();
	
	public Lienzo(Image graphics, int ancho, int alto)
	{
		Dimension size=new Dimension(ancho,alto);
		setSize(size);
		backImage=graphics;
		g=(Graphics2D)backImage.getGraphics();
		dibuja();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(backImage, 0, 0, null);
	}
	
	public void dibuja()
	{		
		
		g.setColor(Color.RED);
		g.drawImage(Rutinas.AjustarImagen("img/Mesa.jpg", 600, 465).getImage(), 0, 0, null);
		g.drawLine(0, 0, 600, 0);
		g.drawLine(0, 150, 600, 150);
		g.drawLine(0, 300, 600, 300);
		g.drawLine(0, 450, 600, 450);
		
		g.setColor(Color.WHITE);
		for(int i=0; i<agujas.size(); i++)
		{
			g.draw(new Line2D.Double(
					
				agujas.get(i).Inicial.x,
				agujas.get(i).Inicial.y,
				agujas.get(i).Final.x,
				agujas.get(i).Final.y
			));
		}
		repaint();
	}
	public boolean lanzarAguja(int longitud)
	{
		double x1,y1,x2,y2,m;
		
		x2=Rutinas.nextInt(0,600);
		y2=Rutinas.nextInt(0,450);
		do{
			
			x1=Rutinas.nextInt(-longitud,longitud);
			m=Rutinas.nextInt(0,1)==0?-1:1;
			y1=m*Math.pow((longitud*longitud)-(x1*x1),0.5);
			
		}while((x1+x2)<0 || (x1+x2)>600 || (y1+y2<0) || (y1+y2)>450);
		
		x1=x1+x2;
		y1=y1+y2;
		
		Punta in= new Punta(x1,y1), fin= new Punta(x2,y2);
		Aguja aguja=new Aguja(in,fin);
		agujas.add(aguja);
		if( (y1<150 && y2>150)||(y2<150 && y1>150) || (y1<300 && y2>300)||(y2<300 && y1>300))
			return true;
		
		return false;
	}
}
