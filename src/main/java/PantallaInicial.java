import com.batalla.Batalla;
import com.interfaz.user.Ingreso;
import com.interfaz.user.Mensaje;
import com.interfaz.user.Pantalla;

public class PantallaInicial extends Pantalla {
	public PantallaInicial() {
		agregar(new Mensaje("Bienvenido a las Batallas Romanas (Version 0.1)"));
		for (int i = 0; i < Batalla.CANTIDAD_JUGADORES; i++) {
			agregar(new Mensaje("Ingrese el nombre del jugador N° " + (i + 1)));
			agregar(new Ingreso());
		}
	}
}
