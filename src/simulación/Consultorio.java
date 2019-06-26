package simulación;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import java.util.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Consultorio {

	public static void main(String[] args) 
	{
		DecimalFormat f= new DecimalFormat("0.00000");
		System.out.print("Teclee el numero de dias a simular: ");
		int dias = new Scanner(System.in).nextInt();
		
		double [] cita = new double [(int) dias];
		int i1=0, i2=0, i3=0, i4=0, i5=0, i6=0;
	    double p1, p2, p3, p4, p5, p6;
		
		System.out.println("\n-------------------------------------------");
		System.out.println("|Dia"+"\t|\tNúmero generado  |"+"  Citas |");
		System.out.println("-------------------------------------------");
		for(int i=0; i<dias; i++)
		{
			cita[i] = Math.random();
			
			if(cita[i]>=0.00 && cita[i]<=0.050)
			{
				i1++;
				System.out.println("|"+(i+1) +"\t|"+f.format( cita[i])+"\t\t | 0 citas|");
			}
			if(cita[i]>=0.051 && cita[i]<=0.150)
			{
				i2++;
				System.out.println("|"+(i+1) +"\t|"+f.format(cita[i])+"\t\t | 1 citas|");
			}
			if(cita[i]>=0.151 && cita[i]<=0.350)
			{
				i3++;
				System.out.println("|"+ (i+1) +"\t|"+f.format( cita[i])+"\t\t | 2 citas|");
			}
			if(cita[i]>=0.351 && cita[i]<=0.650)
			{
				i4++;
				System.out.println("|"+(i+1) +"\t|"+f.format(cita[i])+"\t\t | 3 citas|");
			}
			if(cita[i]>=0.651 && cita[i]<=0.850)
			{
				i5++;
				System.out.println("|"+(i+1)+"\t|"+f.format( cita[i])+"\t\t | 4 citas|");
			}
			
			if(cita[i]>=0.850 && cita[i]<=1.00)
			{
				i6++;
				System.out.println("|"+(i+1) +"\t|"+f.format( cita[i])+"\t\t | 5 citas|");
			}
		}
		System.out.println("-------------------------------------------");
		
		
		p1 = ((i1/(double)dias)*100);
		p2 = ((i2/(double)dias)*100);
		p3 = ((i3/(double)dias)*100);
		p4 = ((i4/(double)dias)*100);
		p5 = ((i5/(double)dias)*100);
		p6 = ((i6/(double)dias)*100);
	
		System.out.println("\nLos porcentajes obtenidos son:");
		System.out.println("-.0 citas por día: "+f.format( p1)+"%");
		System.out.println("-.1 cita por día:  "+f.format( p2)+"%");
		System.out.println("-.2 citas por día: "+f.format( p3)+"%");
		System.out.println("-.3 citas por día: "+f.format( p4)+"%");
		System.out.println("-.4 citas por día: "+f.format( p5)+"%");
		System.out.println("-.5 citas por día: "+f.format( p6)+"%");
		DefaultPieDataset porcentajes = new DefaultPieDataset();
        porcentajes.setValue("0 cita - "+p1+"%", p1);
        porcentajes.setValue("1 citas - "+p2+"%", p2);
        porcentajes.setValue("2 citas - "+p3+"%", p3);
        porcentajes.setValue("3 citas - "+p4+"%", p4);
        porcentajes.setValue("4 citas - "+p5+"%", p5);
        porcentajes.setValue("5 citas - "+p6+"%", p6);
           JFreeChart grafica = ChartFactory.createPieChart("Gráfica de resultados en "+dias+" dias", porcentajes, true, true, false);
        
               
            ChartPanel Panel = new ChartPanel(grafica);
   	        JFrame Ventana = new ChartFrame("Gráfica", grafica);
   	        Ventana.getContentPane().add(Panel);
   	        
   	        Ventana.pack();
   	        Ventana.setVisible(true);
   	        Ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}

}
