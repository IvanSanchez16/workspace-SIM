package proySS;

public class Tiempo {
	int horas;
	int minutos;
	public Tiempo(int horas, int minutos){
		this.horas=horas;
		if(minutos>=60)
		{
			this.horas+=minutos/60;
			this.minutos=minutos%60;
			return;
		}
		
		this.minutos=minutos;
		
	}
	
	public Tiempo sumarTiempos(Tiempo tiempo){
		int horas,minutos;
		horas=this.horas+tiempo.horas;
		if((this.minutos+tiempo.minutos)>=60)
		{
			horas+=(this.minutos+tiempo.minutos)/60;
			minutos=(this.minutos+tiempo.minutos)%60;
			return new Tiempo(horas,minutos);
		}
		minutos=this.minutos+tiempo.minutos;
		return new Tiempo(horas,minutos);
	}
	
	public int compareToTiempos(Tiempo tiempo) {
		int minutos;
		minutos=(tiempo.horas*60)+tiempo.minutos;
		minutos-=(this.horas*60)+this.minutos;
		return minutos;
	}
	
	static public String PonCeros(int Numero,int Tamaño){
		   String Texto=Numero+"";
		   
		   while(Texto.length()<Tamaño)
			   Texto="0"+Texto;
		   return Texto;
		   
		   
	}
	public String toString(){
		return PonCeros(horas>12?horas-12:horas, 2)+":"+PonCeros(minutos, 2);
	}
	
	
}	
