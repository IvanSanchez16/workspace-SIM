package simulación;

import java.util.Scanner;

public class Paracaidista {
	
	Scanner S=new Scanner(System.in);
	//DecimalFormat df=new DecimalFormat("0.0000000000");
	double m,c,V,va=1;
	int t=0;
	public Paracaidista() {
		PedirDatos();
		while(true) {
			double part1=((9.8*m)/c);
			double part2=(1-Math.exp( -1*((c/m)*t) ));
			V=(part1*part2);
			if(t==0)
				System.out.println("|\tt\t|\tv\t| ");
			System.out.println("|\t"+t+"\t|\t"+V+"\t|");
			if(V==va)
				break;
			va=V;
			t++;
		}
	}
	
	private void PedirDatos() {
		System.out.print("Ingrese la masa del objeto: ");
		m=S.nextDouble();
		System.out.print("Ingrese la constante de fricción: ");
		c=S.nextDouble();
	}
	
	public static void main(String[] args) {
		new Paracaidista();
	}

}
