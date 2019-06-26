package simulación;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Volados {
	
	static Scanner S=new Scanner(System.in);
	static Random R=new Random();
	static DecimalFormat df=new DecimalFormat("#.0000");
	public static void main(String[] args) {
		System.out.print("¿Cúantas veces deseas lanzar los dados?: ");
		int n=S.nextInt();
		double d1,d2;
		int n1=0,n2=0,suma8=0;
		System.out.println("n\t|#alea1\t|cara1\t|#alea2\t|cara2\t|Suma");
		for(int i=1 ; i<=n ; i++) {
			d1=R.nextDouble();
			d2=R.nextDouble();
			if(d1<=1f/6f) 
				n1=1;
			if(d1>1f/6f && d1<=2f/6f) 
				n1=2;
			if(d1>2f/6f && d1<=3f/6f) 
				n1=3;
			if(d1>3f/6f && d1<=4f/6f) 
				n1=4;
			if(d1>4f/6f && d1<=5f/6f) 
				n1=5;
			if(d1>5f/6f && d1<=6f/6f) 
				n1=6;
			if(d2<=1f/6f) 
				n2=1;
			if(d2>1f/6f && d2<=2f/6f) 
				n2=2;
			if(d2>2f/6f && d2<=3f/6f) 
				n2=3;
			if(d2>3f/6f && d2<=4f/6f) 
				n2=4;
			if(d2>4f/6f && d2<=5f/6f) 
				n2=5;
			if(d2>5f/6f && d2<=6f/6f) 
				n2=6;
			System.out.println(i+"\t|"+df.format(d1)+"\t|"+n1+"\t|"+df.format(d2)+"\t|"+n2+"\t|"+(n1+n2));
			if(n1+n2>=8)
				suma8++;
		}
		double prob8=(float)suma8/(float)n;
		System.out.println("\nProbabilidad de que sea >=8: "+(prob8*100)+"%");
	}

}
