package Pruebas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class LienzoKolmogorov extends Canvas{
	
	float[] Ui,isobren;
	
	public LienzoKolmogorov(float[] u,float[] i) {
		Ui=u;
		isobren=i;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0,0,0,180);
		g.drawLine(0,180,180,180);
		g.setColor(Color.BLUE);
		int xa=0,ya=180,x,y;
		for(int i=0 ; i<isobren.length ; i++) {
			y=(int) (180f-(isobren[i]*180f));
			x=(int) (180f*((float)(i+1)/(float)isobren.length));
			g.fillOval(x-1,y-1,3,3);
			g.drawLine(xa,ya,x,y);
			xa=x;
			ya=y;
		}
		g.setColor(Color.RED);
		xa=0;
		ya=180;
		for(int i=0 ; i<Ui.length ; i++) {
			y=(int) (180f-(Ui[i]*180f));
			x=(int) (180f*((float)(i+1)/(float)Ui.length));
			g.fillOval(x-1,y-1,3,3);
			g.drawLine(xa,ya,x,y);
			xa=x;
			ya=y;
		}
	}
}
