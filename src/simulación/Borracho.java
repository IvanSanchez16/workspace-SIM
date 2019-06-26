package simulación;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.JFrame;

public class Borracho{
	DecimalFormat df= new DecimalFormat("#0.0000");
	Random r = new Random();
	public Borracho() {
		System.out.println("Ingrese numero de iteraciones");
		int PN=0,N=new Scanner(System.in).nextInt();
		for(int i=0;i<N;i++) {
			System.out.println("Iteración "+(i+1)+"\nPaso\tIntervalo\tDirección");
			int x = 0,y=0;
			for(int j=0;j<10;j++) {
				double na=r.nextDouble();
				System.out.print((j+1)+"\t"+df.format(na));
				if(na<0.25) {
					System.out.print("\t\tEste\n");
					x++;
					continue;
				}
				if(na<0.5) {
					System.out.print("\t\tOeste\n");
					x--;
					continue;
				}
				if(na<0.75) {
					System.out.print("\t\tNorte\n");
					y++;
					continue;
				}
				System.out.print("\t\tSur\n");
				y--;
			}
			System.out.println("("+x+","+y+")\n");
			if(y>=2) {
				PN++;
			}
		}
		System.out.println("Porcentaje de ocaciones que termino 2 pasos al norte: "+(( (float)PN/(float)N )*100)+"%");
	}
	
	public static void main(String[] args) {
		new Borracho();
	}

}
