package proySS;

import java.text.DecimalFormat;
import java.util.*;

public class Turno {
	DecimalFormat df=new DecimalFormat("0.00000");
	DecimalFormat df2=new DecimalFormat("00");
	DecimalFormat df3=new DecimalFormat("#.00");
	int camiones;
	int camionesIniciales;
	int personal;
	boolean comer=true;
	double numeroAleatorio1,numeroAleatorio2,horasExtras,salarioExtra,ocioCamion,totalTiempoEsperaCamion,operacionAlmacen,costosTotales,totalTiempoServicio;
	int salarioNormal,tiempoEntreLlegadas,tiempoServicio,ocioDelPersonal,tiempoEsperaCamion,longitudCola;
	Tiempo horaComida=new Tiempo(15,0),horaSalida=new Tiempo(19,30),tiempoLlegada=new Tiempo(11,0),iniciacionServicio=new Tiempo(11,0),terminacionServicio;
	
	public Turno() {
		System.out.println("Ingrese el tamaño del personal");
		personal=new Scanner(System.in).nextInt();
		double p=new Random().nextDouble();
		camionesIniciales=p<=0.10?3:p<=0.25?2:p<=0.50?1:0;
		
		System.out.println("Numero aleatorio\t"+
				"Tiempo entre llegadas\t"+
				"Tiempo de llegada\t"+
				"Iniciación del servicio\t\t"+
				"Número aleatorio\t"+
				"Tiempo de servicio\t"+
				"Terminación del servicio\t"+
				"Ocio del personal\t"+
				"Tiempo de espera del camión\t"+
				"Longitud de la cola\t");
		while(true) {
			if(camionesIniciales!=0) {
				numeroAleatorio1=0;
				tiempoEntreLlegadas=0;
				longitudCola=camionesIniciales;
				while(longitudCola!=0) {
					numeroAleatorio2=new Random().nextDouble();
					tiempoServicio=new Metodos().tiempoServicio(numeroAleatorio2,personal);
					if(iniciacionServicio.horas==11 && iniciacionServicio.minutos==0) {
						terminacionServicio=iniciacionServicio.sumarTiempos(new Tiempo(0,tiempoServicio));
					}	
					else {
						iniciacionServicio=terminacionServicio;
						tiempoEsperaCamion=Math.abs(terminacionServicio.compareToTiempos(tiempoLlegada));
						ocioDelPersonal=0;
						terminacionServicio=iniciacionServicio.sumarTiempos(new Tiempo(0,tiempoServicio));
					}
					longitudCola--;
					System.out.println(df.format(numeroAleatorio1)+
							"\t\t\t\t"+df2.format(tiempoEntreLlegadas)+
							"\t\t\t"+tiempoLlegada+
							"\t\t\t"+iniciacionServicio+
							"\t\t\t"+df.format(numeroAleatorio2)+
							"\t\t\t\t"+tiempoServicio+
							"\t\t\t"+terminacionServicio+
							"\t\t\t\t"+ocioDelPersonal+
							"\t\t\t     "+tiempoEsperaCamion+
							"\t\t\t\t"+longitudCola);
					iniciacionServicio=terminacionServicio;
				}
				camionesIniciales=0;
			}
			else {
				numeroAleatorio1=new Random().nextDouble();
				tiempoEntreLlegadas=new Metodos().tiempoLlegada(numeroAleatorio1);
				tiempoLlegada=tiempoLlegada.sumarTiempos(new Tiempo(0,tiempoEntreLlegadas));
				numeroAleatorio2=new Random().nextDouble();
				tiempoServicio=new Metodos().tiempoServicio(numeroAleatorio2,personal);
				totalTiempoServicio+=tiempoServicio;
				if(iniciacionServicio.horas==11 && iniciacionServicio.minutos==0) {
					iniciacionServicio=tiempoLlegada;
					terminacionServicio=iniciacionServicio.sumarTiempos(new Tiempo(0,tiempoServicio));
				}
				else if(terminacionServicio.compareToTiempos(tiempoLlegada)>0) {
					iniciacionServicio=tiempoLlegada;
					ocioDelPersonal=terminacionServicio.compareToTiempos(tiempoLlegada);
					tiempoEsperaCamion=0;
				}
				else if(terminacionServicio.compareToTiempos(tiempoLlegada)<0){
					int aux=tiempoEsperaCamion;
					iniciacionServicio=terminacionServicio;
					tiempoEsperaCamion=Math.abs(terminacionServicio.compareToTiempos(tiempoLlegada));
					totalTiempoEsperaCamion+=tiempoEsperaCamion;
					ocioDelPersonal=0;
					longitudCola=tiempoEsperaCamion<aux && longitudCola>0?longitudCola-1:longitudCola+1;
				}
				else {
					iniciacionServicio=terminacionServicio;
					tiempoEsperaCamion=0;
					ocioDelPersonal=0;
				}
				if((iniciacionServicio.compareToTiempos(horaComida)<=0 || tiempoLlegada.compareToTiempos(horaComida)<=0) && comer) {	
					if(terminacionServicio.compareToTiempos(horaComida)>0)
						iniciacionServicio=horaComida.sumarTiempos(new Tiempo(0,30));
					else	
						iniciacionServicio=terminacionServicio.sumarTiempos(new Tiempo(0,30));
					comer=false;
				}
				terminacionServicio=iniciacionServicio.sumarTiempos(new Tiempo(0,tiempoServicio));
				horasExtras=terminacionServicio.compareToTiempos(horaSalida);
				System.out.println(df.format(numeroAleatorio1)+
						"\t\t\t\t"+df2.format(tiempoEntreLlegadas)+
						"\t\t\t"+tiempoLlegada+
						"\t\t\t"+iniciacionServicio+
						"\t\t\t"+df.format(numeroAleatorio2)+
						"\t\t\t\t"+tiempoServicio+
						"\t\t\t"+terminacionServicio+
						"\t\t\t\t"+ocioDelPersonal+
						(ocioDelPersonal>=100?"\t\t     ":"\t\t\t     ")+tiempoEsperaCamion+
						(tiempoEsperaCamion>=100?"\t\t\t":"\t\t\t\t")+longitudCola);
				if(horasExtras<=0) {
					horasExtras=Math.abs(horasExtras)/60;
					break;
				}
			}
		}
		System.out.println("\nTamaño del equipo\t"+"Salario normal\t\t"+"Salario extra\t\t"+"Ocio del camión\t\t"+"Operación del almacen\t\t"+"Costos totales");
		salarioNormal=personal*8*25;
		salarioExtra=personal*horasExtras*37.50;
		ocioCamion=(totalTiempoEsperaCamion/60f)*100;
		operacionAlmacen=(totalTiempoServicio/60)*500;
		costosTotales=salarioNormal+salarioExtra+ocioCamion+operacionAlmacen;
		System.out.println(personal+"\t\t\t"+salarioNormal+"\t\t\t"+df3.format(salarioExtra)+"\t\t\t"+df3.format(ocioCamion)+"\t\t\t"+df3.format(operacionAlmacen)+"\t\t\t\t"+df3.format(costosTotales));
	}

	public static void main(String [] args) {
		new Turno();
	}
	
}
