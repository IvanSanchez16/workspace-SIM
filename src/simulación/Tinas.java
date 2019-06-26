package simulación;

import java.text.DecimalFormat;
import java.util.*;

public class Tinas {

	static Scanner S=new Scanner(System.in);
	static Random R=new Random();
	static DecimalFormat df=new DecimalFormat("#.0000");
	public static void main(String[] args) {
		System.out.print("Ingrese el número de simulaciones: ");
		int nsim=S.nextInt();
		for(int i=0; i<nsim ; i++)
			Simulacion(i+1);
		
	}

	public static void Simulacion(int nsim) {
		double vu;
		int pacum,p,ndias=0;
		System.out.println("Simulación #"+nsim);
		System.out.println("# Día\t|Tina\t|# generado\t|Peso calc.\t|Peso acum.\t|Exede?\t|Acumula");
		for(int i=0 ; i<260 ; i++) {
			pacum=0;
			for(int j=1 ; j<=5 ; j++) {
				vu=R.nextDouble();
				if(vu<0.5) 
					p=(int)(190+Math.sqrt(800*vu));
				else 
					p=(int)(230-Math.sqrt(800*(1-vu)));
				pacum+=p;
				System.out.print((i+1)+"\t|"+j+"\t|"+df.format(vu)+"\t\t|"+p+"\t\t|"+pacum);
				if(j!=5)
					System.out.println("\t\t|\t|");
			}
			boolean aux=pacum>1000;
			System.out.println("\t\t|"+(aux?"Sí":"No")+"\t|"+(aux?"200":""));
			if(aux)
				ndias++;
		}
		System.out.println("\nTotal gastado en renta: "+(ndias*200));
		System.out.println("¿Vale la pena comprar otro camión? "+(ndias>200?"No":"Sí"));
	}
}
