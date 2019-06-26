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

public class Dado {
	DecimalFormat df=new DecimalFormat("0.0000000");
	Random r= new Random();
	Scanner L= new Scanner(System.in);
	DefaultPieDataset DPD;
	int d1;
	int d2;
	int d3;
	int d4;
	int d5;
	int d6;
	int N;
	
	public Dado(){
		System.out.println("Ingrese número de veces a tirar el dado:");
		N=L.nextInt();
		System.out.println("-------------------------------------------------");
		System.out.println("N\t|\tDato\t\t|\tCara\t|");
		System.out.println("-------------------------------------------------");
		for(int i=0;i<N;i++){
			double Tiro=r.nextDouble();
			System.out.print(i+1+"\t|\t"+df.format(Tiro)+"\t|\t");
			if(Tiro<0.1666666){
				d1++;
				System.out.println("1\t|");
				continue;
			}
			if(Tiro<0.3333333){
				d2++;
				System.out.println("2\t|");
				continue;
			}
			if(Tiro<0.5){
				d3++;
				System.out.println("3\t|");
				continue;
			}
			if(Tiro<0.6666666){
				d4++;
				System.out.println("4\t|");
				continue;
			}
			if(Tiro<0.8333333){
				d5++;
				System.out.println("5\t|");
				continue;
			}
			d6++;
			System.out.println("6\t|");
		}
		System.out.println("\nNúmero total de 1: "+d1);
		System.out.println("Número total de 2: "+d2);
		System.out.println("Número total de 3: "+d3);
		System.out.println("Número total de 4: "+d4);
		System.out.println("Número total de 5: "+d5);
		System.out.println("Número total de 6: "+d6);
		System.out.println("\nPorcentaje de 1: "+(((double)d1/N)*100)+"%");
		System.out.println("Porcentaje de 2: "+(((double)d2/N)*100)+"%");
		System.out.println("Porcentaje de 3: "+(((double)d3/N)*100)+"%");
		System.out.println("Porcentaje de 4: "+(((double)d4/N)*100)+"%");
		System.out.println("Porcentaje de 5: "+(((double)d5/N)*100)+"%");
		System.out.println("Porcentaje de 6: "+(((double)d6/N)*100)+"%");

		Grafica();
	}
	
	private void Grafica() {
		 DPD = new DefaultPieDataset();
		 DPD.setValue("Cara 1", d1);
		 DPD.setValue("Cara 2", d2);
		 DPD.setValue("Cara 3", d3);
		 DPD.setValue("Cara 4", d4);
		 DPD.setValue("Cara 5", d5);
		 DPD.setValue("Cara 6", d6);

		 
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
		new Dado();
	}

}
