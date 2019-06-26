package Pruebas;

import java.awt.Color;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;

public class Escuchador implements ActionListener,WindowListener{

	Frame frame;
	Modelo modelo;
	float[] numeros;
	byte prueba;
	int fallo;
	
	public Escuchador(Modelo m,Frame f) {
		frame=f;
		modelo=m;
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {}
	
	public void windowDeactivated(WindowEvent evt) {
		frame.estadoInicial();
		frame.TxtNNumeros.setVisible(false);
		frame.LbArriba.setText("¿Desea realizar otra prueba?");
		frame.BtnSi.setEnabled(true);
		frame.BtnNo.setEnabled(true);
		frame.BtnSi.setVisible(true);
		frame.BtnNo.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==frame.TxtNNumeros) {
			long n=frame.TxtNNumeros.ObtenerCantidad();
			if(n<34) {
				JOptionPane.showMessageDialog(null,"El tamaño de la muestra debe ser mayor o igual a 34","Error", JOptionPane.WARNING_MESSAGE);
				return;
			}
			numeros=modelo.generarNumeros(n);
			int filas=(int) (n/5),pos=0;
			if(n%5>0)
				filas++;
			DecimalFormat df=new DecimalFormat("0.00000");
			String[][] mnumeros=new String[filas][5];
			for(int i=0 ; i<filas ; i++)
				for(int j=0 ; j<5 ; j++) { 
					if(pos>=n)
						break;
					mnumeros[i][j]=df.format(numeros[pos++]).replace(',','.');
				}
			frame.llenarJTable(mnumeros);
			frame.sp.setVisible(true);
			frame.TxtNNumeros.setEditable(false);
			frame.LbPrueba.setVisible(true);
			frame.BtnChi.setVisible(true);
			frame.BtnKolmogorov.setVisible(true);
			frame.BtnDistancias.setVisible(true);
			frame.BtnPoker.setVisible(true);
			frame.BtnSeries.setVisible(true);
			return;
		}
		eventosBtn((JButton)evt.getSource());
	}
	
	private void eventosBtn(JButton btn) {
		if(btn==frame.Btn5) {
			fallo=5;
			FrameResultados fr=new FrameResultados(numeros,prueba,fallo,frame);
			fr.addWindowListener(this);
			return;
		}
		if(btn==frame.Btn10) {
			fallo=10;
			FrameResultados fr=new FrameResultados(numeros,prueba,fallo,frame);
			fr.addWindowListener(this);
			return;
		}
		if(btn==frame.BtnSi) {
			if(frame.LbArriba.getText().equals("¿Desea realizar otra prueba?")) {
				frame.LbArriba.setText("¿Desea generar nuevos números?");
				frame.sp.setVisible(true);
				return;
			}
			frame.estadoInicial();
			return;
		}
		if(btn==frame.BtnNo) {
			if(frame.LbArriba.getText().equals("¿Desea realizar otra prueba?"))
				System.exit(0);
			frame.LbPrueba.setVisible(true);
			frame.BtnChi.setVisible(true);
			frame.BtnKolmogorov.setVisible(true);
			frame.BtnDistancias.setVisible(true);
			frame.BtnPoker.setVisible(true);
			frame.BtnSeries.setVisible(true);
			frame.BtnSi.setEnabled(false);
			frame.BtnNo.setEnabled(false);
			return;
		}
		frame.BtnChi.setEnabled(false);
		frame.BtnKolmogorov.setEnabled(false);
		frame.BtnSeries.setEnabled(false);
		frame.BtnDistancias.setEnabled(false);
		frame.BtnPoker.setEnabled(false);
		frame.LbFallo.setVisible(true);
		frame.Btn5.setVisible(true);
		frame.Btn10.setVisible(true);
		if(btn==frame.BtnChi) {
			prueba=FrameResultados.CHI;
			frame.BtnChi.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		}
		if(btn==frame.BtnKolmogorov) {
			prueba=FrameResultados.KOLMOGOROV;
			frame.BtnKolmogorov.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		}
		if(btn==frame.BtnSeries) {
			prueba=FrameResultados.SERIES;
			frame.BtnSeries.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		}
		if(btn==frame.BtnDistancias) {
			prueba=FrameResultados.DISTANCIAS;
			frame.BtnDistancias.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		}
		if(btn==frame.BtnPoker) {
			prueba=FrameResultados.POKER;
			frame.BtnPoker.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		}
	}
}
