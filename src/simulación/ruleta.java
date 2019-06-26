package simulación;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
public class ruleta {

	public static void main(String[] args) {
		Scanner S= new Scanner(System.in);	
		DecimalFormat f=new DecimalFormat("#.#####");
		System.out.print("Cuantas simulaciones desear realizar: ");
		int nsim= S.nextInt();
		int j1=200, j2=200;
		int apuesta1=1, apuesta2=1;
		Random r= new Random();
		boolean j1vivo=true,j2vivo=true;
		float res = 0;
		int d1=j1,d2=j2;
		int df1=0,df2=0;
		char color = 0;
		int w1=0,w2=0;
		
		for (int i=1 ; i<nsim+1 ; i++) {
			System.out.println("n\t$J1\tApue\tN#Aleato\tColor\tNewApue\t Gano?|n\t$J2\tApue\tN#Aleato\tColor\tNewApue\t Gano? ");
			j1=200;j2=200;df1=0;df2=0;d1=j1;d2=j2;j1vivo=true;j2vivo=true;apuesta1=1;apuesta2=1;
			do {
				res=r.nextFloat(); //# aleatorio uniforme

				if(j2<apuesta2 && j2vivo) {
					apuesta2=j2;
					continue;
				}
				if(res>0f && res<0.45) { //rojo
					if(j1vivo) {
						d1+=apuesta1;
						df1=d1;
					}
					if(j2vivo) {
						d2+=apuesta2;
						df2=d2;			
						apuesta2=1;
					}
					color='R';
				}		

				if(res>0.45 && res<0.9) { //negro
					if(j1vivo) {
						d1-=apuesta1;
						df1=d1;
					}
					if(j2vivo) {
						d2-=apuesta2;
						df2=d2;
						apuesta2=apuesta2*2;
					}
					color='N';
				}

				if(res>0.9 && res<1)  //verde
					color='V';
				if(j1vivo)
					System.out.print(i+"\t"+j1+"\t"+apuesta1+"\t"+f.format(res)+"\t\t"+color+"\t"+df1+"\t "+(j1vivo?"Sigue":"Perdió")+"|");
				else
					System.out.print("\t\t\t\t\t\t\t\t\t\t");
				if(j2vivo)
					System.out.println(i+"\t"+j2+"\t"+apuesta2+"\t"+f.format(res)+"\t\t"+color+"\t"+df2+"\t "+(j2vivo?"Sigue":"Perdió"));
				else
					System.out.println();
				if(color=='V')
					continue;
				j1=df1;
				j2=df2;
				if(j1<=0 || j1>500)
					j1vivo=false;
				if(j2<=0 || j2>500)
					j2vivo=false;
			}while(j1vivo || j2vivo);
			System.out.println();
			if(j1>=500)
				w1++;
			if(j2>=500)
				w2++;
		}

		if(w1>w2)
			System.out.println("El jugador con mas posibilidades de ganar es el: 1");
		else
			System.out.println("El jugador con mas posibilidades de ganar es el: 2");
	}
						

}
