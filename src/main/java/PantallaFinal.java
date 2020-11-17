import com.batalla.Batalla;
import com.interfaz.user.Mensaje;
import com.interfaz.user.Pantalla;

public class PantallaFinal extends Pantalla {
	public PantallaFinal(Batalla batalla) {
		agregar(new Mensaje("El ganador es el jugador " + batalla.ganador().obtenerNombre()));
		agregar(new Mensaje("Gracias por participar y vuelva a jugar pronto."));
	}
}
