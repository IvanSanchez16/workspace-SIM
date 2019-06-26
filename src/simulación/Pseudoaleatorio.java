package simulación;
import java.util.*;

public class Pseudoaleatorio{
	
	public static void main(String a[]) {
		int o=0;
		while(o!=3) {
			System.out.println("Elija opción:\n1.Opción 1\n2.Opción 2\n3.Salir");
			o=new Scanner(System.in).nextInt();
			switch(o){
			case 1:
				System.out.println("Ingrese cantidad de números pseudoaleatorios a crear");
				int N=new Scanner(System.in).nextInt();
				for(int i=0;i<N;i++)
					System.out.println((i+1)+"\t"+new Random().nextDouble());
				System.out.println();
				break;
			case 2:
				int R=Math.abs(new Random().nextInt());
				System.out.println("Numero pseudoaleatorio generado: "+R);
				for(int i=0;i<R;i++)
					System.out.println((i+1)+"\t"+new Random().nextDouble());
				System.out.println();
				break;
			}	
		}
	}
}



	
	

