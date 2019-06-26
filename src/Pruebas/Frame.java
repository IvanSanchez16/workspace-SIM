package Pruebas;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import utileria.*;

public class Frame extends JFrame{
	
	JLabel LbArriba,LbPrueba,LbFallo;
	JIntegerBox TxtNNumeros;
	JButton BtnChi,BtnKolmogorov,BtnSeries,BtnDistancias,BtnPoker,Btn5,Btn10,BtnSi,BtnNo;
	JTable TbNumeros;
	JScrollPane sp;
	Font f,fn;
	Border b;
	
	public Frame() {
		super("Pruebas");
		hazInterfaz();
		/*
		try {
			setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception e) {}
	*/
	}
	
	public void setEscuchadores(Escuchador e) {
		BtnChi.addActionListener(e);
		BtnKolmogorov.addActionListener(e);
		BtnSeries.addActionListener(e);
		BtnDistancias.addActionListener(e);
		BtnPoker.addActionListener(e);
		Btn5.addActionListener(e);
		Btn10.addActionListener(e);
		BtnSi.addActionListener(e);
		BtnNo.addActionListener(e);
		TxtNNumeros.addActionListener(e);
	}
	
	public void estadoInicial() {
		sp.setVisible(false);
		TxtNNumeros.setEditable(true);
		LbPrueba.setVisible(false);
		BtnChi.setVisible(false);
		BtnKolmogorov.setVisible(false);
		BtnDistancias.setVisible(false);
		BtnPoker.setVisible(false);
		BtnSeries.setVisible(false);
		BtnChi.setEnabled(true);
		BtnKolmogorov.setEnabled(true);
		BtnSeries.setEnabled(true);
		BtnDistancias.setEnabled(true);
		BtnPoker.setEnabled(true);
		LbFallo.setVisible(false);
		Btn5.setVisible(false);
		Btn10.setVisible(false);
		BtnSi.setVisible(false);
		BtnNo.setVisible(false);
		TxtNNumeros.setText("");
		TxtNNumeros.setVisible(true);
		LbArriba.setText("¿Cuantos números aleatorios desea generar?");
		BtnChi.setBorder(b);
		BtnKolmogorov.setBorder(b);
		BtnSeries.setBorder(b);
		BtnDistancias.setBorder(b);
		BtnPoker.setBorder(b);
	}
	
	public void llenarJTable(String[][] m) {
		String[] a= {"1","2","3","4","5"};
		TbNumeros=new JTable(m,a);
		TbNumeros.setFont(fn);
		TbNumeros.setEnabled(false);
		sp=new JScrollPane(TbNumeros);
		sp.setBounds(5,50,485,200);
		add(sp);
	}
	
	private void hazInterfaz() {
		setSize(500,450);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		f=new Font("Candara",1,14);
		fn=new Font("Dubai",0,14);

		LbArriba=new JLabel("¿Cuantos números aleatorios desea generar?");
		LbArriba.setFont(f);
		TxtNNumeros=new JIntegerBox();
		TxtNNumeros.setFont(fn);
		LbArriba.setBounds(5,5,280,40);
		TxtNNumeros.setBounds(290,5,195,40);
		add(LbArriba);
		add(TxtNNumeros);
		
		LbPrueba=new JLabel("Que prueba desea usar:");
		LbPrueba.setFont(f);
		LbPrueba.setVisible(false);
		BtnChi=new JButton("Chi Cuadrada");
		BtnChi.setFont(f);
		BtnChi.setVisible(false);
		BtnKolmogorov=new JButton("Kolmogorov");
		BtnKolmogorov.setFont(f);
		BtnKolmogorov.setVisible(false);
		BtnSeries=new JButton("Series");
		BtnSeries.setFont(f);
		BtnSeries.setVisible(false);
		BtnDistancias=new JButton("Distancias");
		BtnDistancias.setFont(f);
		BtnDistancias.setVisible(false);
		BtnPoker=new JButton("Poker");
		BtnPoker.setFont(f);
		BtnPoker.setVisible(false);
		LbPrueba.setBounds(5,245,200,40);
		BtnChi.setBounds(35,280,120,30);
		BtnKolmogorov.setBounds(190,280,120,30);
		BtnSeries.setBounds(345,280,120,30);
		BtnDistancias.setBounds(113,315,120,30);
		BtnPoker.setBounds(268,315,120,30);
		b=BtnChi.getBorder();
		
		LbFallo=new JLabel("Que porcentaje de fallo desea utilizar: ");
		LbFallo.setFont(f);
		LbFallo.setVisible(false);
		Btn5=new JButton("5 %");
		Btn5.setFont(fn);
		Btn5.setVisible(false);
		Btn10=new JButton("10 %");
		Btn10.setFont(fn);
		Btn10.setVisible(false);
		LbFallo.setBounds(5,345,300,40);
		Btn5.setBounds(5,380,100,30);
		Btn10.setBounds(110,380,100,30);
		
		BtnSi=new JButton("Sí");
		BtnNo=new JButton("No");
		BtnSi.setFont(f);
		BtnNo.setFont(f);
		BtnSi.setBounds(250, 10, 80, 30);
		BtnNo.setBounds(350, 10, 80, 30);
		BtnSi.setVisible(false);
		BtnNo.setVisible(false);
		
		add(LbPrueba);
		add(BtnChi);
		add(BtnKolmogorov);
		add(BtnSeries);
		add(BtnDistancias);
		add(BtnPoker);
		add(LbFallo);
		add(Btn5);
		add(Btn10);
		add(BtnSi);
		add(BtnNo);
		setVisible(true);
	}
	
	public static void main(String[]a) {
		Frame f=new Frame();
		Modelo m=new Modelo();
		Escuchador e=new Escuchador(m,f);
		f.setEscuchadores(e);
	}
}
