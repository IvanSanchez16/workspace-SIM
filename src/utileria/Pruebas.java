package utileria;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Pruebas {

	public static void main(String[] args) {
		/*
		double[] a= {0.19714,0.26271,0.57680,0.36511,0.21685,0.74173,0.83093,0.17174,0.86792,0.27835,0.49202,0.55176,0.84635,0.71063,0.97273
				,0.08879,0.06500,0.67712,0.07196,0.67882,0.80611,0.06927,0.42773,0.65790,0.09167,0.08258,0.07695,0.65057,0.16552,0.44465,
				0.86989,0.27384,0.67418,0.96217,0.26065,0.31483};
		Arrays.sort(a);
		DecimalFormat df=new DecimalFormat("0.00000");
		double suma=1f/(float)a.length;
		for(int i=0 ; i<a.length ; i++) {
			System.out.println((i+1)+"\t"+df.format(a[i])+"\t"+df.format(suma)+"\t"+df.format((Math.abs(a[i]-suma))));
			suma+=1f/(float)a.length;
		}
		*/
		for(int i=0 ; i<255 ; i++)
			System.out.println(i+"\t"+(char)i);
	}
}
