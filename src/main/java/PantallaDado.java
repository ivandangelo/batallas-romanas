import com.interfaz.user.Dado;
import com.interfaz.user.Mensaje;
import com.interfaz.user.Pantalla;

public class PantallaDado extends Pantalla {
	public PantallaDado(String[] nombres) {
		agregar(new Mensaje("Los turnos se echaran a la suerte con el dado"));
		for (int i = 0; i < nombres.length; i++) {
			agregar(new Mensaje("Tirada del jugador " + nombres[i]));
			agregar(new Dado());
		}
	}
}
