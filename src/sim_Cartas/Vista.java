package sim_Cartas;

import javax.swing.*;

import utileria.Rutinas;

import java.awt.*;

public class Vista extends JFrame {
	
	Timer T;
	JPanel PCentro,PNorte,PSur,PEste,POeste;
	PanelFondo pf;
	Controlador C;
	static int njuego,rg1,rg2,rg3,rg4;
	int[] AG= {0,0,0,0};
	
	public Vista() {
		super("Juego de cartas");
		pf=new PanelFondo();
		setContentPane(pf);
		HazInterfaz();
		setVisible(true);
	}
	
	private void HazInterfaz() {
		setSize(800,600);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PCentro=new JPanel();
		PCentro.setLayout(new BorderLayout());
		PCentro.setBounds(150,100,500,350);
		PCentro.setBackground(new Color(10,90,15));
		add(PCentro,BorderLayout.CENTER);
	}
	
	public void setControlador(Controlador c) {
		C=c;
		T=new Timer(1500,C);
	}
	
	public void MostrarCartas(Carta[] acartas) {
		PCentro.removeAll();
		for(int i=0 ; i<acartas.length ; i++)
			PCentro.add(acartas[i],(i==0)?BorderLayout.NORTH:(i==1)?BorderLayout.SOUTH:(i==2)?BorderLayout.EAST:BorderLayout.WEST);
	}
	
	public void Mexicanada() {
		if(getHeight()==600)
			setSize(800,601);
		else
			setSize(800,600);
	}
}

class PanelFondo extends JPanel {
	int turno=1;
	public void paint(Graphics g) {
		g.drawImage(Rutinas.AjustarImagen("img/fondo.png",getWidth(),getHeight()).getImage(),0,0,getWidth(),getHeight(),null);
		g.setColor(Color.WHITE);
		g.drawString("Juego número: "+Vista.njuego,10,30);
		g.drawString("Rondas ganadas: "+Vista.rg1,510,50);
		g.drawString("Rondas ganadas: "+Vista.rg2,510,520);
		g.drawString("Rondas ganadas: "+Vista.rg4,10,170);
		g.drawString("Rondas ganadas: "+Vista.rg3,670,190);
		switch(turno) {
		case 1:
			g.drawImage(Rutinas.AjustarImagen("img/cx10.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx10.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy10.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy10.png",80,200).getImage(),700,200,null);
			break;
		case 2:
			g.drawImage(Rutinas.AjustarImagen("img/cx9.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx9.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy9.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy9.png",80,200).getImage(),700,200,null);
			break;
		case 3:
			g.drawImage(Rutinas.AjustarImagen("img/cx8.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx8.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy8.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy8.png",80,200).getImage(),700,200,null);
			break;
		case 4:
			g.drawImage(Rutinas.AjustarImagen("img/cx7.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx7.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy7.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy7.png",80,200).getImage(),700,200,null);
			break;
		case 5:
			g.drawImage(Rutinas.AjustarImagen("img/cx6.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx6.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy6.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy6.png",80,200).getImage(),700,200,null);
			break;
		case 6:
			g.drawImage(Rutinas.AjustarImagen("img/cx5.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx5.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy5.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy5.png",80,200).getImage(),700,200,null);
			break;
		case 7:
			g.drawImage(Rutinas.AjustarImagen("img/cx4.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx4.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy4.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy4.png",80,200).getImage(),700,200,null);
			break;
		case 8:
			g.drawImage(Rutinas.AjustarImagen("img/cx3.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx3.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy3.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy3.png",80,200).getImage(),700,200,null);
			break;
		case 9: 
			g.drawImage(Rutinas.AjustarImagen("img/cx2.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx2.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy2.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy2.png",80,200).getImage(),700,200,null);
			break;
		case 10: 
			g.drawImage(Rutinas.AjustarImagen("img/cx1.png",200,80).getImage(),300,10,null);
			g.drawImage(Rutinas.AjustarImagen("img/cx1.png",200,80).getImage(),300,480,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy1.png",80,200).getImage(),10,200,null);
			g.drawImage(Rutinas.AjustarImagen("img/cy1.png",80,200).getImage(),700,200,null);
			break;
		}
		setOpaque(false);
		super.paint(g);
	}
}
