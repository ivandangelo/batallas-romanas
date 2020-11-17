

import com.interfaz.user.ColectorGenerico;
import com.interfaz.user.Ingreso;

public class ColectaAtaque extends ColectorGenerico {
	private boolean deseaComprar;

	public void colectar(Ingreso ingreso) {
		deseaComprar = ingreso.dato().equalsIgnoreCase("SI");
	}

	public boolean deseaComprar() {
		return deseaComprar;
	}
}
