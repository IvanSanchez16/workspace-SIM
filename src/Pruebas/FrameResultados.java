package Pruebas;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Arrays;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.*;
import utileria.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class FrameResultados extends JDialog{
	
	private int porFallo;
	private byte prueba;
	private float[] muestra;
	private final double[] chic5= {0,3.8415,5.9915,7.8147,9.4877,11.0705,12.5916,14.0671,15.5073,16.9190,18.3070,19.6752,
			21.0261,22.3620,23.6848,24.9958,26.2962,27.5871,28.8693,30.1435,31.4104,32.6706,33.9245,35.1725,36.4150},
			chic10= {0,2.7055,4.6052,6.2514,7.7794,9.2363,10.6446,12.0170,13.3616,14.6837,15.9872,17.2750,18.5493,19.8119,
					21.06411,22.3071,23.5418,24.7690,25.9894,27.2036,28.4120,29.6151,30.8133,32.0069,33.1962},
			kol10= {0.21472,0.20185,0.19910,0.19646,0.19392,0.19148,0.18913,0.18687,0.18468,
					0.18257,0.18051,0.17856,0.17665,0.17481,0.17301,0.17128,0.16959},
			kol5= {0.22743,0.22425,0.22119,0.21826,0.21544,0.21273,0.21012,0.20760,0.20517,
					0.20283,0.20056,0.19837,0.19625,0.19420,0.19221,0.19028,0.18841};
			
	static final byte CHI=0,KOLMOGOROV=1,SERIES=2,DISTANCIAS=3,POKER=4;
	Font f,fn;
	DecimalFormat df=new DecimalFormat("#.00000");
	DecimalFormat df2=new DecimalFormat("#.0");
	
	public FrameResultados(float[] m,byte p,int f,JFrame frame) {
		super(frame,"Resultados",true);
		muestra=m;
		prueba=p;
		porFallo=f;
		hazInterfaz();
	}
	
	private void hazInterfaz() {
		setSize(400,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		f=new Font("Candara",1,14);
		fn=new Font("Dubai",0,14);
		switch(prueba) {
		case CHI:
			chi();
			break;
		case KOLMOGOROV:
			kolmogorov();
			break;
		case SERIES:
			series();
			break;
		case DISTANCIAS:
			setSize(450,400);
			distancias();
			break;
		case POKER:
			poker();
			break;
		}
		setVisible(true);
	}
	
	private void chi() {
		float sumchi=0;
		float[] i= {0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f,1f};
		int[] O=contarIntervalos();
		float e=(float)muestra.length/10f;
		String aux=df2.format(e);
		aux=aux.replace(',','.');
		e=Float.parseFloat(aux);
		float[] E= {e,e,e,e,e,e,e,e,e,e};
		float[] O_E=new float[10];
		for(int j=0 ; j<10 ; j++) {
			O_E[j]=(float)O[j]-E[j];
			
		}
		float[] chi=new float[10];
		for(int j=0 ; j<10 ; j++) {
			chi[j]=((float)Math.pow(O_E[j],2)/e);
			sumchi+=chi[j];
		}
		
		String[][] m=new String[10][5];
		for(int j=0 ; j<10 ; j++) {
			m[j][0]=i[j]+"";
			m[j][1]=O[j]+"";
			m[j][2]=E[j]+"";
			m[j][3]=O_E[j]+"";
			m[j][4]=df.format(chi[j]).replace(',','.');
		}
		String[] c= {"i","O","E","O-E","(O-E)^2 / E"};
		JTable t=new JTable(m,c);
		t.setFont(fn);
		t.setEnabled(false);
		JScrollPane sp=new JScrollPane(t);
		sp.setBounds(5,5,380,183);
		add(sp);
		
		JLabel lb=new JLabel("X^2 = "+df.format(sumchi));
		double portabla=(porFallo==5?chic5[9]:chic10[9]);
		JLabel lb2=new JLabel("X^2 (9,"+porFallo+"%)="+portabla);
		lb.setFont(new Font("Dubai",1,15));
		lb2.setFont(new Font("Dubai",1,15));
		lb.setBounds(60,200,140,30);
		lb2.setBounds(200,200,160,30);
		add(lb);
		add(lb2);
		String msg=(sumchi<portabla?"Los números están uniformemente distribuidos":"Los números NO están uniformemente distribuidos");
		JMensajeGira lb3=new JMensajeGira(msg);
		lb3.setFont(f);
		lb3.setBounds(0,230,415,30);
		add(lb3);
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(O[0],"Intervalos","0.1");
		dataset.setValue(O[1],"Intervalos","0.2");
		dataset.setValue(O[2],"Intervalos","0.3");
		dataset.setValue(O[3],"Intervalos","0.4");
		dataset.setValue(O[4],"Intervalos","0.5");
		dataset.setValue(O[5],"Intervalos","0.6");
		dataset.setValue(O[6],"Intervalos","0.7");
		dataset.setValue(O[7],"Intervalos","0.8");
		dataset.setValue(O[8],"Intervalos","0.9");
		dataset.setValue(O[9],"Intervalos","1.0");
		
		JFreeChart fc=ChartFactory.createBarChart("Observado",null,null,dataset,PlotOrientation.VERTICAL,true,true,false);
		ChartPanel cp=new ChartPanel(fc);
		cp.setBounds(25,260,350,130);
		add(cp);
	}
	
	private int[] contarIntervalos() {
		int[] O=new int[10];
		for(int i=0 ; i<muestra.length ; i++) {
			float num=muestra[i];
			if(num<=0.1) {
				O[0]++;
				continue;
			}
			if(num<=0.2) {
				O[1]++;
				continue;
			}
			if(num<=0.3) {
				O[2]++;
				continue;
			}
			if(num<=0.4) {
				O[3]++;
				continue;
			}
			if(num<=0.5) {
				O[4]++;
				continue;
			}
			if(num<=0.6) {
				O[5]++;
				continue;
			}
			if(num<=0.7) {
				O[6]++;
				continue;
			}if(num<=0.8) {
				O[7]++;
				continue;
			}
			if(num<=0.9) {
				O[8]++;
				continue;
			}
			O[9]++;
		}
		return O;
	}
	
	private void kolmogorov() {
		float[] muestrav2=muestra.clone();
		Arrays.sort(muestrav2);
		DecimalFormat df=new DecimalFormat("0.00000");
		float[] isobren=new float[muestrav2.length];
		float unosobren=1f/(float)muestrav2.length;
		float[] Di=new float[muestrav2.length];
		String[][] tb=new String[muestrav2.length][4];
		float sum=0,mayor=0;
		int idelm=0;
		for(int i=0 ; i<isobren.length ; i++) {
			sum+=unosobren;
			isobren[i]=sum;
			Di[i]=Math.abs(muestrav2[i]-isobren[i]);
			if(Di[i]>mayor) {
				mayor=Di[i];
				idelm=i+1;
			}
			tb[i][0]=(i+1)+"";
			tb[i][1]=df.format(muestrav2[i]).replace(',', '.');
			tb[i][2]=df.format(isobren[i]).replace(',','.');
			tb[i][3]=df.format(Di[i]).replace(',','.');
		}
		String[] c= {"i","Ui","i/n","Di"};
		JTable t=new JTable(tb,c);
		TableColumn col=t.getColumn("i");
		col.setPreferredWidth(40);
		t.setFont(new Font("Dubai",0,12));
		t.setEnabled(false);
		JScrollPane sp=new JScrollPane(t);
		sp.setBounds(5,5,200,363);
		add(sp);
		
		JLabel lb=new JLabel("i de Di mayor="+idelm);
		JLabel lb2=new JLabel("D= "+df.format(mayor).replace(',','.'));
		float kol;
		try {
			kol=(float) (porFallo==5?kol5[muestrav2.length-34]:kol10[muestrav2.length-34]);
		}catch(ArrayIndexOutOfBoundsException e) {
			if(porFallo==5) 
				kol=(float) (1.36f/Math.sqrt(muestrav2.length));
			else
				kol=(float) (1.22f/Math.sqrt(muestrav2.length));
		}
		JLabel lb3=new JLabel("D("+muestrav2.length+","+porFallo+"%)= "+kol);
		lb.setFont(new Font("Dubai",1,15));
		lb2.setFont(new Font("Dubai",1,15));
		lb3.setFont(new Font("Dubai",1,15));
		lb.setBounds(210,5,200,30);
		lb2.setBounds(210,35,200,30);
		lb3.setBounds(210,65,200,30);
		add(lb);
		add(lb2);
		add(lb3);
		
		String msg=(mayor<kol?"Los números están uniformemente distribuidos":"Los números NO están uniformemente distribuidos");
		JMensajeGira lb5=new JMensajeGira(msg);
		lb5.setFont(f);
		lb5.setBounds(210,95,200,30);
		add(lb5);
		
		JLabel lb4=new JLabel("Azul= i/n | Rojo=Ui");
		lb4.setBounds(208,155,185,30);
		lb4.setFont(new Font("Candara",1,16));
		add(lb4);
		LienzoKolmogorov lk=new LienzoKolmogorov(muestrav2,isobren);
		lk.setBounds(208,185,185,185);
		add(lk);
	}
	
	private void series() {
		float[] muestrav2=muestra.clone();
		for(int i=0 ; i<muestrav2.length-1 ; i++)
			muestrav2[i]=muestrav2[i+1];
		String[][] tb=new String[muestra.length][2];
		DecimalFormat df=new DecimalFormat("0.00000");
		for(int i=0 ; i<muestra.length ; i++) {
			tb[i][0]=df.format(muestra[i]).replace(',','.');
			tb[i][1]=df.format(muestrav2[i]).replace(',','.');
		}
		String[] c= {"Pares","Ordenados"};
		JTable jtb=new JTable(tb,c);
		jtb.setFont(fn);
		jtb.setEnabled(false);
		JScrollPane sp=new JScrollPane(jtb);
		sp.setBounds(5,5,180,300);
		add(sp);
		
		int[][] mc=crearMatriz(tb);
		String[] co= {" ","0.2","0.4","0.6","0.8","1"};
		String[][] tb2=new String[5][6];
		for(int i=0 ; i<5 ; i++) {
			tb2[i][0]=co[5-i];
			for(int j=1 ; j<6 ; j++)
				tb2[i][j]=mc[i][j-1]+"";
		}
		JTable jtb2=new JTable(tb2,co);
		jtb2.setFont(fn);
		jtb2.setEnabled(false);
		JScrollPane sp2=new JScrollPane(jtb2);
		sp2.setBounds(200,23,190,103);
		add(sp2);
		
		JLabel lb=new JLabel("Tabla de O(ij):");
		lb.setFont(f);
		lb.setBounds(200,0,200,30);
		add(lb);
		
		JLabel lb2=new JLabel("Tabla de ( O(ij) - E(ij) )^2 / E(ij) :");
		lb2.setFont(f);
		lb2.setBounds(200,123,200,30);
		add(lb2);
		
		DecimalFormat df2=new DecimalFormat("#.00");
		float e=(float)muestra.length/25f;
		float sum=0;
		double cal;
		String[][] tb3=new String[5][6];
		for(int i=0 ; i<5 ; i++) {
			tb3[i][0]=co[5-i];
			for(int j=1 ; j<6 ; j++) {
				cal=Math.pow(mc[i][j-1]-e,2)/e;
				tb3[i][j]=df2.format( cal ).replace(',','.');
				sum+=cal;
			}
		}
		JTable jtb3=new JTable(tb3,co);
		jtb3.setFont(fn);
		jtb3.setEnabled(false);
		JScrollPane sp3=new JScrollPane(jtb3);
		sp3.setBounds(200,148,190,103);
		add(sp3);
		
		LienzoSeries l=new LienzoSeries(tb);
		l.setBounds(200,255,192,112);
		add(l);
		
		JLabel lb3=new JLabel("X^2 = "+df.format(sum));
		double portabla=(porFallo==5?chic5[24]:chic10[24]);
		JLabel lb4=new JLabel("X^2 (24,"+porFallo+"%)="+portabla);
		lb3.setFont(new Font("Dubai",1,13));
		lb4.setFont(new Font("Dubai",1,13));
		lb3.setBounds(5,300,140,30);
		lb4.setBounds(5,320,160,30);
		add(lb3);
		add(lb4);
		
		String msg=(sum<portabla?"Los números están uniformemente distribuidos":"Los números NO están uniformemente distribuidos");
		JMensajeGira lb5=new JMensajeGira(msg);
		lb5.setFont(f);
		lb5.setBounds(0,340,180,30);
		add(lb5);
	}
	
	private int[][] crearMatriz(String[][] tb){
		int[][] matriz=new int[5][5];
		float v1,v2;
		int f=0,c=0;
		for(int i=0 ; i<tb.length ; i++) {
			v1=Float.parseFloat(tb[i][0]);
			v2=Float.parseFloat(tb[i][1]);
			if(v1<0.2)
				f=4;
			if(v1>=0.2 && v1<0.4)
				f=3;
			if(v1>=0.4 && v1<0.6)
				f=2;
			if(v1>=0.6 && v1<0.8)
				f=1;
			if(v1>=0.8 && v1<=1)
				f=0;
			if(v2<0.2)
				c=0;
			if(v2>=0.2 && v2<0.4)
				c=1;
			if(v2>=0.4 && v2<0.6)
				c=2;
			if(v2>=0.6 && v2<0.8)
				c=3;
			if(v2>=0.8 && v2<=1)
				c=4;
			matriz[f][c]++;
		}
		return matriz;
	}
	
	private void distancias() {
		String[] c= {"n","Ui","E","i"};
		float alfa,beta,teta;
		float[] aaux=definirAByT();
		alfa=aaux[0];
		beta=aaux[1];
		teta=aaux[2];
		byte[] E=new byte[muestra.length];
		for(int i=0 ; i<E.length ; i++)
			E[i]=(byte) ((muestra[i]>=alfa && muestra[i]<=beta)?1:0);
		int[] I=new int[muestra.length];
		int acum=0;
		for(int i=0 ; i<muestra.length ; i++) {
			if(E[i]==1) {
				for(int j=acum,k=i-1 ; j>0 ; j--,k--)
					I[k]=acum;
				I[i]=acum=0;
				continue;
			}
			acum++;
		}
		if(acum!=0) {
			for(int i=muestra.length-1,j=acum ; j>0 ; i--,j--)
				I[i]=acum;
		}
		DecimalFormat df=new DecimalFormat("0.00000");
		String[][] tb=new String[muestra.length][4];
		for(int i=0 ; i<muestra.length ; i++) {
			tb[i][0]=(i+1)+"";
			tb[i][1]=df.format(muestra[i]).replace(',', '.');
			tb[i][2]=E[i]+"";
			tb[i][3]=I[i]+"";
		}
		JTable jtb=new JTable(tb,c);
		TableColumn colUi=jtb.getColumn("Ui");
		colUi.setPreferredWidth(200);
		jtb.setFont(fn);
		jtb.setEnabled(false);
		JScrollPane sp=new JScrollPane(jtb);
		sp.setBounds(5,5,170,360);
		add(sp);
		
		JLabel lb=new JLabel("a="+alfa+"  ß="+beta+"  θ="+teta);
		lb.setFont(new Font("Dubai",1,16));
		lb.setBounds(211,5,200,25);
		add(lb);
		
		int mayor=0;
		r:while(true){
			for(int i=0 ; i<I.length ; i++) {
				if(I[i]==mayor+1) {
					mayor++;
					continue r;
				}
			}
			break;
		}
		String[] co= {"i","Pi","Oi","Ei","(Ei-Oi)^2/Ei"};
		float[] Pi=new float[mayor+2];
		for(int i=0 ; i<Pi.length ; i++) {
			if(i==Pi.length-1) {
				Pi[i]=(float) Math.pow((1f-teta),mayor+1);
				continue;
			}
			Pi[i]=(float) (Math.pow((1f-teta),i)*teta);
		}
		int[] Oi=new int[mayor+2];
		for(int i=0 ; i<I.length ; i++) {
			try {
				Oi[I[i]]++;
			}catch(ArrayIndexOutOfBoundsException e) {
				Oi[mayor+1]++;
			}
			if(I[i]>=1) {
				i+=I[i]-1;
				continue;
			}
			try {
				while(I[i+1]==0) 
					i++;
			}catch(ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
		float[] Ei=new float[mayor+2];
		int totalHuecos=0;
		for(int i=0 ; i<Oi.length ; i++)
			totalHuecos+=Oi[i];
		for(int i=0 ; i<Ei.length ; i++) 
			Ei[i]=(float)totalHuecos*Pi[i];
		float[] chic=new float[mayor+2];
		float aux;
		float sumchi=0;
		for(int i=0 ; i<chic.length ; i++) {
			aux=(float) Math.pow(Ei[i]-Oi[i],2);
			chic[i]=aux/Ei[i];
			sumchi+=chic[i];
		}
		String[][] tbla=new String[mayor+2][5];
		DecimalFormat df2=new DecimalFormat("0.000");
		for(int i=0 ; i<tbla.length ; i++) {
			if(i==tbla.length-1)
				tbla[i][0]="i>="+i;
			else
				tbla[i][0]=i+"";
			tbla[i][1]=df2.format(Pi[i]).replace(',', '.');
			tbla[i][2]=Oi[i]+"";
			tbla[i][3]=this.df.format(Ei[i]).replace(',', '.');
			tbla[i][4]=this.df.format(chic[i]).replace(',', '.');
		}
		JTable jtb2=new JTable(tbla,co);
		TableColumn colOi=jtb2.getColumn("Oi");
		colOi.setPreferredWidth(27);
		TableColumn coli=jtb2.getColumn("i");
		coli.setPreferredWidth(40);
		TableColumn colPi=jtb2.getColumn("Pi");
		colPi.setPreferredWidth(52);
		TableColumn colEi=jtb2.getColumn("Ei");
		colEi.setPreferredWidth(70);
		jtb2.setFont(new Font("Dubai",0,12));
		jtb2.setEnabled(false);
		JScrollPane sp2=new JScrollPane(jtb2);
		sp2.setBounds(190,40,240,23+((mayor+2)*16));
		add(sp2);
		
		JLabel lb4=new JLabel("X^2 = "+df.format(sumchi));
		double portabla=(porFallo==5?chic5[mayor+1]:chic10[mayor+1]);
		JLabel lb2=new JLabel("X^2 ("+(mayor+1)+","+porFallo+"%)="+portabla);
		lb4.setFont(new Font("Dubai",1,15));
		lb2.setFont(new Font("Dubai",1,15));
		lb4.setBounds(190,200,140,30);
		lb2.setBounds(190,220,160,30);
		add(lb4);
		add(lb2);
		String msg=(sumchi<portabla?"Los números están uniformemente distribuidos":"Los números NO están uniformemente distribuidos");
		JMensajeGira lb3=new JMensajeGira(msg);
		lb3.setFont(f);
		lb3.setBounds(185,240,415,30);
		add(lb3);
	}
	
	private float[] definirAByT() {
		float[] aux=new float[3];
		int n1,n2;
		DecimalFormat df=new DecimalFormat("0.00");
		while(aux[2]<0.3f || aux[2]>0.5f) {
			n1=Rutinas.nextInt(0,17);
			n2=Rutinas.nextInt(0,17);
			aux[0]=Float.parseFloat(df.format((float) ((0.05f*n1)+0.05)).replace(',','.'));
			aux[1]=Float.parseFloat(df.format((float) ((0.05f*n2)+0.05)).replace(',','.'));
			aux[2]=Float.parseFloat(df.format(Math.abs(aux[1]-aux[0])).replace(',','.'));
		}
		if(aux[0]>aux[1]) {
			float auxaux=aux[0];
			aux[0]=aux[1];
			aux[1]=auxaux;
		}
		return aux;
	}
	
	private void poker() {
		String[] colum= {"Evento","FO","PE","FE","(FE-FO)^2/FE"};
		String[] evnt= {"Pachuca","Un par","Tercia","Dos pares","Full","Poker","Quintilla"};
		String[] FO=contarEventos();
		String[] PE= {"0.3024","0.5040","0.0720","0.1080","0.0090","0.0045","0.0001"};
		String[] FE= new String[7];
		int tam=muestra.length;
		DecimalFormat df=new DecimalFormat("#.000");
		for(int i=0 ; i<FE.length ; i++)
			FE[i]=df.format((float)tam*Float.parseFloat(PE[i])).replace(',','.');
		String[] op=new String[7];
		float sum=0;
		for(int i=0 ; i<op.length ; i++) {
			op[i]=df.format(  Math.pow( Float.parseFloat(FE[i])-Float.parseFloat(FO[i]) ,2)/Float.parseFloat(FE[i])  ).replace(',','.');
			sum+=Float.parseFloat(op[i]);
		}
		String[][] tabla=new String[7][5];
		for(int i=0 ; i<7 ; i++) {
			tabla[i][0]=evnt[i];
			tabla[i][1]=FO[i];
			tabla[i][2]=PE[i];
			tabla[i][3]=FE[i];
			tabla[i][4]=op[i];
		}
		JTable tb=new JTable(tabla,colum);
		tb.setFont(fn);
		tb.setEnabled(false);
		JScrollPane sp=new JScrollPane(tb);
		sp.setBounds(5,5,380,135);
		add(sp);
		double portabla=(porFallo==5?chic5[6]:chic10[6]);
		JLabel lb=new JLabel("X^2= "+df.format(sum).replace(',','.')+"          X^2(6,"+porFallo+"%)= "+portabla);
		lb.setFont(new Font("Dubai",1,15));
		lb.setBounds(50,150,300,30);
		add(lb);
		String msg=(sum<portabla?"Los números están uniformemente distribuidos":"Los números NO están uniformemente distribuidos");
		JMensajeGira lb3=new JMensajeGira(msg);
		lb3.setFont(f);
		lb3.setBounds(0,190,415,30);
		JLabel lbimg=new JLabel("");
		lbimg.setIcon(Rutinas.AjustarImagen("img/poker.png",350,100));
		lbimg.setBounds(25,230,350,100);
		add(lbimg);
		add(lb3);
	}
	
	private String[] contarEventos() {
		int[] contar=new int[7];
		for(int i=0 ; i<muestra.length ; i++) 
			contar[evento(muestra[i])]++;
		String[] a= {contar[0]+"",contar[1]+"",contar[2]+"",contar[3]+"",contar[4]+"",contar[5]+"",contar[6]+""};
		return a;
	}

	private int evento(float num) {
		String aux=df.format(num).replace(',','.');
		int[] ar=new int[10];
		char n;
		for(int i=1 ; i<aux.length() ; i++) {
			n=aux.charAt(i);
			ar[Integer.parseInt(n+"")]++;
		}
		Arrays.sort(ar);
		if(ar[ar.length-1]==1)
			return 0;
		if(ar[ar.length-1]==5)
			return 6;
		if(ar[ar.length-1]==4)
			return 5;
		if(ar[ar.length-1]==3) {
			if(ar[ar.length-2]==2)
				return 4;
			return 2;
		}
		if(ar[ar.length-2]==2)
			return 3;
		return 1;
	}
}
