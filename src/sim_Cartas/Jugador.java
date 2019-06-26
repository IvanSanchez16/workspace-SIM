package sim_Cartas;

import java.util.Vector;

public class Jugador {
	private Vector<Carta> cartas;
	private int Id;
	private int rondasGanadas;
	
	public Jugador(Vector<Carta> cartas,int id) {
		this.cartas=cartas;
		Id=id;
		rondasGanadas=0;
	}
	
	public void ganar() {
		rondasGanadas++;
	}

	public Vector<Carta> getCartas() {
		return cartas;
	}

	public int getId() {
		return Id;
	}

	public int getRondasGanadas() {
		return rondasGanadas;
	}
}
