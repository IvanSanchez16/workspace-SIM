package simulación;

import java.text.DecimalFormat;
import java.util.Random;

public class GeneraAleatorios {

	public static void main(String[] args) {
		Random R=new Random();
		DecimalFormat df=new DecimalFormat("0.00000");
		for(int i=0 ; i<6 ; i++) {
			for(int j=0 ; j<6 ; j++) {
				System.out.print(df.format(R.nextDouble())+"\t\t"); 
			}
			System.out.println("\n");
		}
	}

}
