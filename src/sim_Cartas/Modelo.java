package sim_Cartas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import utileria.Rutinas;

public class Modelo {

	Jugador[] jugadores;
	ArrayList<Carta> baraja;
	
	public void IniciarJuego() {
		Carta carta;
		jugadores=new Jugador[4];
		baraja=new ArrayList<Carta>();
		for(int i=0;i<4;i++) {
			double r1,r2;
			Random R=new Random();
			Vector<Carta> vaux=new Vector<Carta>();
			int tipo=0,valor=0;
			for(int j=0;j<10;j++) {
				do {
				r1=R.nextDouble();
				if(r1<0.25) {
					tipo=1;
				}
				if(r1>=0.25 && r1<0.5) {
					tipo=2;
				}
				if(r1>=0.5 && r1<0.75) {
					tipo=3;
				}
				if(r1>=0.75 && r1<=1) {
					tipo=4;
				}
				
				r2=R.nextDouble();
				if(r2<0.1)
					valor=1;
				if(r2>=0.1 && r2<0.2)
					valor=2;
				if(r2>=0.2 && r2<0.3)
					valor=3;
				if(r2>=0.3 && r2<0.4)
					valor=4;
				if(r2>=0.4 && r2<0.5)
					valor=5;
				if(r2>=0.5 && r2<0.6)
					valor=6;
				if(r2>=0.6 && r2<0.7)
					valor=7;
				if(r2>=0.7 && r2<0.8)
					valor=8;
				if(r2>=0.8 && r2<0.9)
					valor=9;
				if(r2>=0.9)
					valor=10;
				carta=new Carta(valor,tipo);
				}while(existeEnBaraja(carta));
				baraja.add(carta);
				vaux.add(carta);
			}
			jugadores[i]=new Jugador(vaux,i+1);
		}
	}
	
	public boolean existeEnBaraja(Carta carta) {
		if(baraja.size()==0)
			return false;
		for(int i=0 ; i<baraja.size() ; i++) {
			Carta aux=baraja.get(i);
			if(aux.getValor().equals(carta.getValor()))
				return true;
		}
		return false;
	}
	
	
	public int compararCartas(Carta[] cartas) {
		int cartaMayor=0;
		for(int i=1; i<cartas.length ; i++){
			if(cartas[cartaMayor].getValor().compareTo(cartas[i].getValor())<0)
				cartaMayor=i;
		}
		return cartaMayor;
	}
}
