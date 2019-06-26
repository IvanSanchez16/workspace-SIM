package simulación;

import java.util.Scanner;

public class MetodoCongruencial {

	public static void main(String[] args) {
		int a=0,c=0,x1=0,n,x2,m,primero;
		Scanner S=new Scanner(System.in);
		do {
			System.out.print("Ingrese el valor de a: ");
			a=S.nextInt();
		}while((a%2==0)&&(a%3==0 || a%5==0));
		do {
			System.out.print("Ingrese el valor de c: ");
			c=S.nextInt();
		}while(c%8!=5);
		m=(int) Math.pow(2,16);
		while(true) {
			if(isPrimo(m))
				break;
			m++;
		}
		
		System.out.print("Ingrese la semilla inicial: ");
		primero=x1=S.nextInt();	
		n=1;
		System.out.println("N\tXn\taXn+C\t(aXn+C)/m\tXn+1");
		while(true) {
			x2=( ((a*x1)+c) %m);
			System.out.println(n+"\t"+x1+"\t"+((a*x1)+c)+"\t"+
					(((a*x1)+c)/m)+"\t\t"+x2);
			if(x2==primero)
				break;
			x1=x2;
			n++;
		}
	}
	
	public static boolean isPrimo(int n) {
		for(int i=n-1 ; i<=1 ;i--) 
			if(n%i==0)
				return false;
		return true;
	}
	
}
