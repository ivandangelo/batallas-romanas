import com.batalla.Jugador;
import com.interfaz.files.RegistroDeLegion;
import com.interfaz.user.Mensaje;
import com.interfaz.user.Menu;
import com.interfaz.user.Pantalla;

public class PantallaCompras extends Pantalla {
	public PantallaCompras(Jugador jugador, RegistroDeLegion[] legiones) {
		if (legiones.length == 0) {
			agregar(new Mensaje("Ya no hay mas legiones para comprar"));
		} else {
			Menu menu = new Menu();
			for (RegistroDeLegion legion : legiones) {
				menu.agregar(legion.toString());
			}
			agregar(new Mensaje("Jugador " + jugador.obtenerNombre() + " elija que desea comprar:"));
			agregar(menu);
		}
	}
}
