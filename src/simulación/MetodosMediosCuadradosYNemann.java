package Simulación;

import java.util.Random;
import java.util.Scanner;

public class MetodosMediosCuadradosYNemann {

	static Scanner S=new Scanner(System.in);
	static Random R=new Random();
	public static void main(String[] args) {
		System.out.println("Ingrese el método a utilizar");
		int opc=S.nextInt();
		if(opc==1)
			MetodoMediosCuadrados();
		else
			MetodoNewmann();
		
	}
	
	public static void MetodoMediosCuadrados() {
		int sem,x;
		
		System.out.println("Desea ingresar la semilla(1-. Si / 2-. No");
		int opc=S.nextInt();
		if(opc==1) {
			while(true) {
				System.out.print("Ingrese la semilla: ");
				sem=S.nextInt();
				String aux=sem+"";
				if(aux.length()!=4)
					System.out.println("Ingrese una semilla de 4 digitos");
				else
					break;
			}
		}else 
			sem=R.nextInt(8999)+1000;
		x=sem;
		while(true) {
			x=x*x;
			String aux=x+"";
			while(aux.length()<8)
				aux="0"+aux;
			aux=aux.substring(2,6);
			System.out.println("Número generado: "+aux);
			if(aux.compareTo("0000")==0)
				break;
			x=Integer.parseInt(aux);
			
		}
	}

	public static void MetodoNewmann() {
		
	}

}
