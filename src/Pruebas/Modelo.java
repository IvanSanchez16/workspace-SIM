package Pruebas;

import java.text.DecimalFormat;
import java.util.Random;

public class Modelo {
	
	Random R=new Random();
	DecimalFormat df=new DecimalFormat("0.00000");
	
	public float[] generarNumeros(long cantidad) {
		float numeros[]=new float[(int)cantidad];
		double aux;
		String aux2;
		for(int i=0 ; i<cantidad ; i++) {
			aux=R.nextDouble();
			aux2=df.format(aux);
			aux2="0."+aux2.substring(2);
			numeros[i]=Float.parseFloat(aux2);
		}
		return numeros;
	}
}
