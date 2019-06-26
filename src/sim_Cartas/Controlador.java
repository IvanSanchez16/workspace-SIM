package sim_Cartas;

import java.awt.event.*;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Controlador implements ActionListener {
	Modelo modelo;
	Vista vista;
	int njuegos;
	int juegosact=0;

	public Controlador(Vista v,Modelo m) {
		modelo=m;
		vista=v;
		while(true) {
			try{
				njuegos=Integer.parseInt(JOptionPane.showInputDialog(vista,"Número de juegos","Ingrese el número de juegos ha realizar",JOptionPane.QUESTION_MESSAGE));
				if(njuegos<5 || njuegos>100) {
					JOptionPane.showMessageDialog(vista, "Ingrese un número entre 5-100", "Error en el registro",JOptionPane.WARNING_MESSAGE);
					continue;
				}
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(vista, "Ingrese un número", "Error en el registro",JOptionPane.WARNING_MESSAGE);
			}
		}
		vista.setControlador(this);
		IniciarJuego();
	}

	public void actionPerformed(ActionEvent evt) {
		if(vista.pf.turno<11) {
			Carta[] cartas= new Carta[modelo.jugadores.length];
			for(int i=0 ; i<modelo.jugadores.length ; i++) {
				cartas[i]=modelo.jugadores[i].getCartas().lastElement();
				modelo.jugadores[i].getCartas().remove(cartas[i]);
			}
			vista.MostrarCartas(cartas);
			vista.Mexicanada();
			vista.pf.turno++;
			modelo.jugadores[modelo.compararCartas(cartas)].ganar();
			vista.rg1=modelo.jugadores[0].getRondasGanadas();
			vista.rg2=modelo.jugadores[1].getRondasGanadas();
			vista.rg3=modelo.jugadores[2].getRondasGanadas();
			vista.rg4=modelo.jugadores[3].getRondasGanadas();
		}
		else {
			vista.T.stop();
			juegosact++;
			SumaGanador();
			System.out.println("Juego #"+juegosact);
			System.out.println("Jugador 1 ha ganado: "+vista.AG[0]);
			System.out.println("Jugador 2 ha ganado: "+vista.AG[1]);
			System.out.println("Jugador 3 ha ganado: "+vista.AG[2]);
			System.out.println("Jugador 4 ha ganado: "+vista.AG[3]);
			if(juegosact<=njuegos)
				IniciarJuego();
		}
	}
	
	private void SumaGanador() {
		int[] arreglo= {Vista.rg1,Vista.rg2,Vista.rg3,Vista.rg4};
		int mayor=arreglo[0], index=0;
		for(int i=1; i<arreglo.length; i++)
		{
			if(mayor<arreglo[i])
			{
				mayor=arreglo[i];
				index=i;
			}
		}
		vista.AG[index]++;
	}
	
	private void IniciarJuego() {
		modelo.IniciarJuego();
		vista.pf.turno=1;
		Vista.njuego=juegosact+1;
		Vista.rg1=Vista.rg2=Vista.rg3=Vista.rg4=0;
		vista.PCentro.removeAll();
		vista.update(vista.getGraphics());
		vista.T.start();
	}
}
