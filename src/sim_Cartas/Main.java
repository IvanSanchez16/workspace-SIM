package sim_Cartas;

public class Main {

	public static void main(String[] args) {
		Vista view=new Vista();
		Modelo model=new Modelo();
		Controlador control=new Controlador(view,model);
	}

}
