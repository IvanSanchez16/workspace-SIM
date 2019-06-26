package sim_Cartas;

import javax.swing.*;

import utileria.Rutinas;

public class Carta extends JLabel {
	private int valor;
	private int tipo;
	

	public Carta(int valor, int tipo) {
		super(Rutinas.AjustarImagen("Cartas/"+Rutinas.PonCeros(valor,2)+tipo+".jpg", 80, 120),0);
		this.valor=valor;
		this.tipo=tipo;
	}
	
	public String getValor() {
		return Rutinas.PonCeros(valor,2)+tipo;
	}
}
