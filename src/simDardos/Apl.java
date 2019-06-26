package simDardos;

public class Apl {

	public static void main(String[] args) {
		Ventana vista=new Ventana();
		Controlador controlador=new Controlador(vista);
		vista.setEscuchador(controlador);
		vista.IniciarSimulacion();
	}

}
