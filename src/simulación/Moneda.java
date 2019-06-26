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

public class Moneda {
	DecimalFormat df=new DecimalFormat("0.0000000");
	Random r= new Random();
	Scanner L= new Scanner(System.in);
	DefaultPieDataset DPD;
	int cara;
	int cruz;
	int N;
	
	public Moneda(){
		System.out.println("Ingrese número de veces a tirar la moneda:");
		N=L.nextInt();
		System.out.println("-------------------------------------------------");
		System.out.println("N\t|\tDato\t\t|\tMoneda\t|");
		System.out.println("-------------------------------------------------");
		for(int i=0;i<N;i++){
			double Tiro=r.nextDouble();
			System.out.print(i+1+"\t|\t"+df.format(Tiro)+"\t|\t");
			if(Tiro<0.5){
				cara++;
				System.out.println("Cara\t|");
				continue;
			}
			cruz++;
			System.out.println("Cruz\t|");
		}
		System.out.println("\nNúmero total de Cara: "+cara);
		System.out.println("Numero total de Cruz: "+cruz);
		System.out.println("Porcentaje de Cara: "+(((double)cara/N)*100)+"%");
		System.out.println("Porcentaje de Cruz: "+(((double)cruz/N)*100)+"%");
		Grafica();
	}
	
	private void Grafica() {
		 DPD = new DefaultPieDataset();
		 DPD.setValue("Cara", cara);
		 DPD.setValue("Cruz", cruz);
		 
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
		new Moneda();
	}

}
