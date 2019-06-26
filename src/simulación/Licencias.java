package simulación;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
public class Licencias {
	
	int C100,C150,C200,C250,C300;
	double promedio;
	DefaultPieDataset DPD;

	public Licencias(){
		
		Scanner read= new Scanner(System.in);
		DecimalFormat f= new DecimalFormat("0.0000");
		Random r= new Random();
		int costolic= 75, lv, ld, inglv=100, ingld=25,costo ,ingxven, ingxdev, n, com, u;
		do {
		System.out.println("¿Cuantas veces deseas realizar la simulacion?: ");
		n= read.nextInt();
			if(n<=1)
				System.out.println("Solo Numeros Positivos mayores a 0");
		}while(n<=1);
		do {
		System.out.println("¿Cuantas licencias vas a comprar?: ");
		com= read.nextInt();
			if(com<=99)
				System.out.println("Cantidad minima de 100");
		}while(com<=99);
		System.out.println("N   |#Num Aleatorio| |lic. Vendidas| |Lic. devueltas|  |Costo|    |ingreso x Venta|    |ingreso x Devolucion| |Utilidad|");
		for (int i=0; i < n; i++) {
			double res =r.nextDouble();
				if(res>0 && res<0.30) {
					lv=100;
						if(lv>com) 
							lv=com;
					ld=com-lv;
					costo=(com*costolic);
					ingxven=lv*inglv;
					ingxdev=ld*ingld;
					u=(ingxven+ingxdev)-costo;
					C100++;
					promedio+=u;
					System.out.println((i+1)+"\t"+f.format(res)+"\t\t   "+lv+" \t\t "+ld+"\t\t"+costo+"\t\t"+ingxven+"\t\t\t"+ingxdev+"\t\t"+u);
					
				}
				if(res>0.30 && res<0.50) {
					lv=150;
					if(lv>com) 
						lv=com;
					ld=com-lv;
					costo=(com*costolic);
					ingxven=lv*inglv;
					ingxdev=ld*ingld;
					u=(ingxven+ingxdev)-costo;
					C150++;
					promedio+=u;
					System.out.println((i+1)+"\t"+f.format(res)+"\t\t   "+lv+" \t\t "+ld+"\t\t"+costo+"\t\t"+ingxven+"\t\t\t"+ingxdev+"\t\t"+u);
				}
				if(res>0.50 && res<0.80) {
					lv=200;
					if(lv>com) 
						lv=com;
					ld=com-lv;
					costo=(com*costolic);
					ingxven=lv*inglv;
					ingxdev=ld*ingld;
					u=(ingxven+ingxdev)-costo;
					C200++;
					promedio+=u;
					System.out.println((i+1)+"\t"+f.format(res)+"\t\t   "+lv+" \t\t "+ld+"\t\t"+costo+"\t\t"+ingxven+"\t\t\t"+ingxdev+"\t\t"+u);
				}
				if(res>0.80 && res<0.95) {
					lv=250;
					if(lv>com) 
						lv=com;
					ld=com-lv;
					costo=(com*costolic);
					ingxven=lv*inglv;
					ingxdev=ld*ingld;
					u=(ingxven+ingxdev)-costo;
					C250++;
					promedio+=u;
					System.out.println((i+1)+"\t"+f.format(res)+"\t\t   "+lv+" \t\t "+ld+"\t\t"+costo+"\t\t"+ingxven+"\t\t\t"+ingxdev+"\t\t"+u);
				}
				if(res>0.95 && res<1) {
					lv=300;
					if(lv>com) 
						lv=com;
					ld=com-lv;
					costo=(com*costolic);
					ingxven=lv*inglv;
					ingxdev=ld*ingld;
					u=(ingxven+ingxdev)-costo;
					C300++;
					promedio+=u;
					System.out.println((i+1)+"\t"+f.format(res)+"\t\t   "+lv+" \t\t "+ld+"\t\t"+costo+"\t\t"+ingxven+"\t\t\t"+ingxdev+"\t\t"+u);
				}
		}
		read.close();
		System.out.println("Promedio utilidad= "+(promedio/n));
		Grafica();
		
	}
	
	private void Grafica() {
		 DPD = new DefaultPieDataset();
		 DPD.setValue("100", C100);
		 DPD.setValue("150", C150);
		 DPD.setValue("200", C200);
		 DPD.setValue("250", C250);
		 DPD.setValue("300", C300);

		 
		 JFreeChart Ch = ChartFactory.createPieChart("Metodo de Montecristo",DPD,true,true,false);
		 LegendTitle Leg = Ch.getLegend();
		 Leg.setPosition(RectangleEdge.BOTTOM);
		 
		 PiePlot Pp = (PiePlot) Ch.getPlot();
	     Pp.setLabelFont(new Font("Arial", Font.PLAIN, 14));
	     Pp.setNoDataMessage("No data available");
	     Pp.setLabelGap(0.02);
		 
		 ChartFrame ventana = new ChartFrame("Metodo Montecristo",Ch);
		 ventana.pack();
		 ventana.setLocationRelativeTo(null);
		 ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
		Licencias tony= new Licencias();
		tony.toString();
	}

	
}
