package simDardos;

import java.awt.event.*;
import java.util.Random;

import javax.swing.JButton;

import utileria.Rutinas;

public class Controlador implements ActionListener{

	Ventana vista;
	double xp,yp;
	Random R=new Random();
	
	public Controlador(Ventana ventana) {
		vista=ventana;
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==vista.T) {
			if(vista.nintentos>=vista.NDardos) {
				vista.T.stop();
				vista.MostrarResultados();
				return;
			}
			xp=R.nextDouble();
			yp=R.nextDouble();
			vista.HacerMarca(xp,yp);
			return;
		}
		JButton aux=(JButton)evt.getSource();
		if(aux.getText().compareTo("x1")==0) {
			aux.setText("x2");
			vista.T.setDelay(1000);
			return;
		}
		if(aux.getText().compareTo("x2")==0) {
			aux.setText("x5");
			vista.T.setDelay(400);
			return;
		}
		if(aux.getText().compareTo("x5")==0) {
			aux.setText("x10");
			vista.T.setDelay(200);
			return;
		}
		if(aux.getText().compareTo("x10")==0) {
			aux.setText("x20");
			vista.T.setDelay(100);
			return;
		}
		if(aux.getText().compareTo("x20")==0) {
			aux.setText("x100");
			vista.T.setDelay(20);
			return;
		}
		if(aux.getText().compareTo("x100")==0) {
			aux.setText("Max");
			vista.l.setVisible(false);
			vista.T.setDelay(0);
			vista.l.Band=false;
			return;
		}
		if(aux.getText().compareTo("Max")==0) {
			aux.setText("x1");
			vista.l.setVisible(true);
			vista.T.setDelay(2000);
			vista.l.Band=true;
			return;
		}
	}

}
