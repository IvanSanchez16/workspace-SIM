package Agujas;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Mesa extends JFrame implements ActionListener
{

	
	JPanel pnNorte;
	JButton btnIniciar;
	JTextField txtRepeticiones;
	Lienzo lienzo;
	double aciertos=0, fallos=0,tiros,contador=0;
	Timer t1;
	public Mesa()
	{
		hazInterfaz();
		hazEscuchadores();
	}
	private void hazEscuchadores() 
	{
		btnIniciar.addActionListener(this);
	}
	private void hazInterfaz()
	{	
		setLayout(null);
		pnNorte=new JPanel(new GridLayout(0,3));
		pnNorte.setBounds(0, 0, 600, 30);
		
		add(pnNorte);
		btnIniciar=new JButton("Iniciar");
		txtRepeticiones=new JTextField();
		pnNorte.add(new JLabel("Numero de repeticiones : ",JLabel.RIGHT));
		pnNorte.add(txtRepeticiones);
		pnNorte.add(btnIniciar);
		
		
		setSize(600,510);
		setLocationRelativeTo(null);
		setVisible(true);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println(this.createImage(600,600));
		lienzo= new Lienzo(createImage(600,450),600,450);
		lienzo.setLocation(0, 31);
		add(lienzo);
		t1=new Timer(1,this);
		
	}
	public static void main(String[] args) 
	{
		new Mesa();
	}
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource()==btnIniciar)
		{
			tiros=Integer.parseInt(txtRepeticiones.getText());
			t1.start();
			
			return;
		}
			
		if(contador!=tiros)
		{	
			if(lienzo.lanzarAguja(150))
				aciertos++;
			else
				fallos++;
		}
		else
		{
			lienzo.dibuja();
			t1.stop();
			System.out.println((2*(contador))/aciertos);
			return;
		}
		if(evt.getSource() instanceof Timer)
			System.out.println(++contador);
		System.out.println((2*(contador))/aciertos);
		
		System.out.println("Van : "+aciertos);
	}

}

