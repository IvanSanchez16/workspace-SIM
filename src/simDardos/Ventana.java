package simDardos;
import java.awt.*;
import java.text.DecimalFormat;

import javax.swing.*;

import utileria.Rutinas;
public class Ventana extends JFrame{
	
	int NDardos,naciertos=0,nintentos=0,nerrores=0;
	JLabel LbContAciertos,LbContIntentos,LbPorcentaje,LbContErrores;
	JButton BtnVelocidad;
	Timer T;
	Controlador C;
	Lienzo l;
	JPanelFondo fondo;
	DecimalFormat df=new DecimalFormat("#.00");
	
	public Ventana(){
		super("Dardos");
		while(true){
			try{
				NDardos=Integer.parseInt(JOptionPane.showInputDialog(this,"Número de dardos","Ingrese el número de dardos ha arrojar",JOptionPane.QUESTION_MESSAGE));
				if(NDardos<10 || NDardos>1000000) {
					JOptionPane.showMessageDialog(this, "Ingrese un número entre 10-1'000'000", "Error en el registro",JOptionPane.WARNING_MESSAGE);
					continue;
				}
				break;
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(this, "Ingrese un número", "Error en el registro",JOptionPane.WARNING_MESSAGE);
			}
		}
		setIconImage(Rutinas.AjustarImagen("img/dardo.png",15,15).getImage());
		fondo=new JPanelFondo();
		setContentPane(fondo);
		HazInterfaz();	
		setVisible(true);
	}

	public void HazInterfaz() {
		setSize(620,720);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LbContIntentos=new JLabel("0 Intentos");
		LbContIntentos.setFont(new Font("Berlin Sans FB",0,20));
		LbContIntentos.setBounds(10,618,140,30);
		LbContIntentos.setBackground(Color.WHITE);
		LbContIntentos.setOpaque(true);
		add(LbContIntentos);
		
		LbContAciertos=new JLabel("0 Aciertos");
		LbContAciertos.setFont(new Font("Berlin Sans FB",0,20));
		LbContAciertos.setBounds(160,618,140,30);
		LbContAciertos.setOpaque(true);
		LbContAciertos.setBackground(Color.WHITE);
		add(LbContAciertos);
		
		
		LbContErrores=new JLabel("0 Fallados");
		LbContErrores.setFont(new Font("Berlin Sans FB",0,20));
		LbContErrores.setBounds(310,618,140,30);
		LbContErrores.setOpaque(true);
		LbContErrores.setBackground(Color.WHITE);
		add(LbContErrores);
		
		LbPorcentaje=new JLabel("0% Acierto");
		LbPorcentaje.setFont(new Font("Berlin Sans FB",0,20));
		LbPorcentaje.setBounds(460,618,140,30);
		LbPorcentaje.setOpaque(true);
		LbPorcentaje.setBackground(Color.WHITE);
		add(LbPorcentaje);
		
		JLabel lb=new JLabel("Velocidad:",SwingConstants.CENTER);
		lb.setFont(new Font("Berlin Sans FB",0,20));
		lb.setBounds(185,658,140,30);
		lb.setOpaque(true);
		lb.setBackground(Color.WHITE);
		add(lb);
		
		BtnVelocidad=new JButton("x1");
		BtnVelocidad.setFont(new Font("Berlin Sans FB",0,20));
		BtnVelocidad.setBounds(326,658,90,30);
		add(BtnVelocidad);
		
		l=new Lienzo(createImage(600,600));
		l.setLocation(5,7);
		add(l);
	}
	
	public void setEscuchador(Controlador c){
		BtnVelocidad.addActionListener(c);
		C=c;
	}
	
	public void HacerMarca(double x,double y) {
		int x1=(int) (x*599);
		int y1=(int) (y*599);
		l.AgregaMarca(x1,y1);
		double a=Math.abs(x1-299.5);
		double b=Math.abs(y1-299.5);
		double h=Math.sqrt( (a*a) + (b*b) );
		if(h<=299.5)
			AumentaAcierto();
		else
			AumentaErrores();
		AumentaContador();
		ActualizaPorcentaje();
	}
	
	public void AumentaAcierto() {
		naciertos++;
		LbContAciertos.setText(naciertos+" Aciertos");
	}
	
	public void AumentaContador() {
		nintentos++;
		LbContIntentos.setText(nintentos+" Intentos");
	}
	
	public void AumentaErrores() {
		nerrores++;
		LbContErrores.setText(nerrores+" Fallados");
	}

	public void ActualizaPorcentaje() {
		float por=((float)naciertos/(float)nintentos)*100;
		LbPorcentaje.setText(df.format(por)+"% Acierto");
	}
	
	public void IniciarSimulacion() {
		T=new Timer(2000,C);
		T.start();
	}
	
	public void MostrarResultados() {
		l.repaint();
		l.setVisible(true);
		BtnVelocidad.setEnabled(false);
		JLabel l1,l2,l3,l4,l5,l6;
		JDialog res=new JDialog(this,"Resultados",true);
		res.setSize(400,220);
		res.setContentPane(new JPanelFondoDialog());
		res.setLayout(null);
		res.setResizable(false);
		res.setLocationRelativeTo(null);
		
		l1=new JLabel(LbContIntentos.getText());
		l1.setFont(LbContIntentos.getFont());
		l1.setBackground(Color.WHITE);
		l1.setOpaque(true);
		l1.setBounds(20,20,140,30);
		res.add(l1);
		
		l2=new JLabel(LbContAciertos.getText());
		l2.setFont(LbContAciertos.getFont());
		l2.setBackground(Color.WHITE);
		l2.setOpaque(true);
		l2.setBounds(20,60,140,30);
		res.add(l2);
		
		l3=new JLabel(LbContErrores.getText());
		l3.setFont(LbContErrores.getFont());
		l3.setBackground(Color.WHITE);
		l3.setOpaque(true);
		l3.setBounds(20,100,140,30);
		res.add(l3);
		
		l4=new JLabel(LbPorcentaje.getText());
		l4.setFont(LbPorcentaje.getFont());
		l4.setBackground(Color.WHITE);
		l4.setOpaque(true);
		l4.setBounds(20,140,140,30);
		res.add(l4);
		
		l5=new JLabel("Calculado:");
		l5.setFont(LbPorcentaje.getFont());
		l5.setBackground(Color.WHITE);
		l5.setOpaque(true);
		l5.setBounds(190,110,140,30);
		res.add(l5);
		
		l6=new JLabel("Pi =~ "+(4*( (float)naciertos/(float)nintentos) ));
		l6.setFont(LbPorcentaje.getFont());
		l6.setBackground(Color.WHITE);
		l6.setOpaque(true);
		l6.setBounds(190,140,140,30);
		res.add(l6);
		res.setVisible(true);
	}
}
