package Pruebas;

import javax.swing.*;
import java.awt.*;

public class LienzoSeries extends Canvas{
	
	String[][] pares;
	public LienzoSeries(String[][] ls) {
		pares=ls;
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(0,0,188,110);
		
		g.drawLine(37,0,37,110);
		g.drawLine(75,0,75,110);
		g.drawLine(112,0,112,110);
		g.drawLine(150,0,150,110);
		
		g.drawLine(0,22,188,22);
		g.drawLine(0,44,188,44);
		g.drawLine(0,66,188,66);
		g.drawLine(0,88,188,88);
		
		g.setColor(Color.RED);
		for(int i=0 ; i<pares.length ; i++) {
			g.fillOval( ((int)(Float.parseFloat(pares[i][1])*188f))-2 , ((int)((1f-Float.parseFloat(pares[i][0]))*110f))-2 ,4,4);
		}
	}
}
