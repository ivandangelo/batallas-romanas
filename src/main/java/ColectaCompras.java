import com.interfaz.user.ColectorGenerico;
import com.interfaz.user.Menu;
import com.interfaz.user.Pantalla;

public class ColectaCompras extends ColectorGenerico {
	private int opcion;
	private boolean pudoElegir;

	public void colectar(Pantalla pantalla) {
		opcion = 0;
		pudoElegir = false;
		super.colectar(pantalla);
	}

	public void colectar(Menu menu) {
		opcion = menu.opcion();
		pudoElegir = true;
	}

	public int opcion() {
		return opcion;
	}

	public boolean pudoElegir() {
		return pudoElegir;
	}
}
