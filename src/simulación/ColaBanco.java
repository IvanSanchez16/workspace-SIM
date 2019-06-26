package simulación;

import java.text.DecimalFormat;
import java.util.*;
import utileria.Rutinas;
class Operacion
{
	String nombre;
	double minutos;
	public Operacion(String nombre, double minutos)
	{
		this.nombre=nombre;
		this.minutos=minutos;
	}
}
class Tiempo
{
	int minutos;
	int segundos;
	public Tiempo(int minutos, int segundos)
	{
		this.minutos=minutos;
		if(segundos>=60)
		{
			this.minutos+=segundos/60;
			this.segundos=segundos%60;
			return;
		}
		
		this.segundos=segundos;
		
	}
	public Tiempo sumarTiempos(Tiempo tiempo)
	{
		int minutos,segundos;
		minutos=this.minutos+tiempo.minutos;
	
		if((this.segundos+tiempo.segundos)>=60)
		{
			minutos+=(this.segundos+tiempo.segundos)/60;
			segundos=(this.segundos+tiempo.segundos)%60;
			return new Tiempo(minutos,segundos);
		}
		segundos=this.segundos+tiempo.segundos;
		return new Tiempo(minutos,segundos);
	}
	public Tiempo restarTiempos(Tiempo tiempo)
	{
		int minutos,segundos;
		
		minutos = this.minutos - tiempo.minutos;
		if((this.segundos-tiempo.segundos)<0)
		{
			minutos--;
			segundos=60+(this.segundos-tiempo.segundos);
			return new Tiempo(minutos,segundos);
		}
		segundos = this.segundos-tiempo.segundos;
		return new Tiempo(minutos,segundos);
	}
	public String toString()
	{
		return Rutinas.PonCeros(minutos, 3)+":"+Rutinas.PonCeros(segundos, 2);
	}
	public static Tiempo convertirMinSeg(double tiempo)
	{
		
		int aux = (int)tiempo;
		double aux2=tiempo-aux;
		
		aux2=aux2*60;	
				
		return new Tiempo(aux,(int)aux2);
	}
	public  double convertirDecimal()
	{
		double aux;
		aux= minutos+((segundos)/60);
		return aux;
	}
}
public class ColaBanco 
{
	static String operaciones[] = {"Consulta saldo ","Otros ","Retiros ","Transferencias "};
	
	
	static double tardanza[] = {1.333334, 0.833334,2,1};// 
	final static double SEGUNDO = 0.0167;
	
 	public static void main(String[] args) 
	{
		Scanner lector = new Scanner(System.in);
		Random random = new Random();
		double r,r2;
		Tiempo tiempoEL, momentoDL=new Tiempo(0,0), tiempoEsp=null, tiempoTerm=null, tiempoInicio;
		Tiempo tiempoPromedio = new Tiempo(0,0);
		int n, operacion;
		System.out.println("Ingrese el número de simulaciones");
		n=lector.nextInt();
		DecimalFormat df = new DecimalFormat("#.00000");
		
		
		System.out.println("Usuario\t#Aleatorio1\tTEL\tMDL\tTI\tTE\t#Aleatorio2\tOperacion\tTOP\tTT");
		for(int i=0; i<n ; i++)
		{
			
			r = random.nextDouble();
			tiempoEL=Tiempo.convertirMinSeg(calcularMinutos(r));
			momentoDL=momentoDL.sumarTiempos(tiempoEL);
			if(tiempoEsp==null)
			{
				tiempoInicio= new Tiempo(momentoDL.minutos,momentoDL.segundos);
				tiempoEsp = new Tiempo(0,0);
			}
			else
			{
				
				if(momentoDL.toString().compareTo(tiempoTerm.toString())<0)
				{
					tiempoInicio = tiempoTerm;
					tiempoEsp=tiempoTerm.restarTiempos(momentoDL);
				}else {
					tiempoEsp=new Tiempo(0,0);
					tiempoInicio = momentoDL;
				}
				
			}
			tiempoPromedio=tiempoPromedio.sumarTiempos(tiempoEsp);
			r2 = random.nextDouble();
			operacion = monteCarlo(r);
			tiempoTerm = tiempoInicio.sumarTiempos(Tiempo.convertirMinSeg(tardanza[operacion]));
			
			System.out.println((i+1)+"\t"+df.format(r)+"\t\t"+tiempoEL+"\t"+momentoDL+"\t"+tiempoInicio+"\t"+tiempoEsp+"\t"+df.format(r2)+
								 "\t\t"+operaciones[operacion]+(operacion==1?"\t\t":"\t")+Tiempo.convertirMinSeg(tardanza[operacion])+"\t"+tiempoTerm);
		}
		double promedio=tiempoPromedio.convertirDecimal()/n;
		System.out.println("\nPromedio de el tiempo de espera : "+Tiempo.convertirMinSeg(promedio));
	}
	public static double calcularMinutos(double r)
	{
		double aux;
		aux = -Math.log(1-r)*2;
		
		return aux;
	}
	public static int monteCarlo(double r)
	{
		if(r<=0.25)
			return 0;
		if( r>0.25 && r<=0.50 )
			return 1;
		if( r>0.50 && r<=0.85 )
			return 2;
		if( r>0.85 && r<=1)
			return 3;
		return -1;
	}
}
