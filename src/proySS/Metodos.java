package proySS;

public class Metodos {
	
	public int tiempoLlegada(double numeroAleatorio) {
		if(numeroAleatorio<=0.02)
			return 20;
		if(numeroAleatorio<=0.10)
			return 25;
		if(numeroAleatorio<=0.22)
			return 30;
		if(numeroAleatorio<=0.47)
			return 35;
		if(numeroAleatorio<=0.67)
			return 40;
		if(numeroAleatorio<=0.82)
			return 45;
		if(numeroAleatorio<=0.92)
			return 50;
		if(numeroAleatorio<=0.97)
			return 55;
		return 60;
	}
	
	public int tiempoServicio(double numeroAleatorio,int Personal) {
		int T = 0;
		switch(Personal) {
			case 3:
				if(numeroAleatorio<=0.05) {
					T=20;
					break;
				}
				if(numeroAleatorio<=0.15) {
					T=25;
					break;
				}	
				if(numeroAleatorio<=0.35) {
					T=30;
					break;
				}	
				if(numeroAleatorio<=0.60) {
					T=35;
					break;
				}	
				if(numeroAleatorio<=0.72) {
					T=40;
					break;
				}
				if(numeroAleatorio<=0.82) {
					T=45;
					break;
				}	
				if(numeroAleatorio<=0.90) {
					T=50;
					break;
				}	
				if(numeroAleatorio<=0.96) {
					T=55;
					break;
				}	
				T=60;
				break;
	
			case 4:
				if(numeroAleatorio<=0.05) {
					T=15;
					break;
				}
				if(numeroAleatorio<=0.20) {
					T=20;
					break;
				}	
				if(numeroAleatorio<=0.40) {
					T=25;
					break;
				}	
				if(numeroAleatorio<=0.60) {
					T=30;
					break;
				}	
				if(numeroAleatorio<=0.75) {
					T=35;
					break;
				}
				if(numeroAleatorio<=0.87) {
					T=40;
					break;
				}	
				if(numeroAleatorio<=0.95) {
					T=45;
					break;
				}	
				if(numeroAleatorio<=0.99) {
					T=50;
					break;
				}	
				T=55;
				break;
				
			case 5:
				if(numeroAleatorio<=0.10) {
					T=10;
					break;
				}
				if(numeroAleatorio<=0.28) {
					T=15;
					break;
				}	
				if(numeroAleatorio<=0.50) {
					T=20;
					break;
				}	
				if(numeroAleatorio<=0.68) {
					T=25;
					break;
				}	
				if(numeroAleatorio<=0.78) {
					T=30;
					break;
				}
				if(numeroAleatorio<=0.86) {
					T=35;
					break;
				}	
				if(numeroAleatorio<=0.92) {
					T=40;
					break;
				}	
				if(numeroAleatorio<=0.97) {
					T=45;
					break;
				}	
				T=50;
				break;
				
			case 6:
				if(numeroAleatorio<=0.12) {
					T=5;
					break;
				}
				if(numeroAleatorio<=0.27) {
					T=10;
					break;
				}	
				if(numeroAleatorio<=0.53) {
					T=15;
					break;
				}	
				if(numeroAleatorio<=0.68) {
					T=20;
					break;
				}	
				if(numeroAleatorio<=0.80) {
					T=25;
					break;
				}
				if(numeroAleatorio<=0.88) {
					T=30;
					break;
				}	
				if(numeroAleatorio<=0.94) {
					T=35;
					break;
				}	
				if(numeroAleatorio<=0.98) {
					T=40;
					break;
				}	
				T=45;
				break;
		}
		return T;
	}
}
