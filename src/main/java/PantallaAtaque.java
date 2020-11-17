import java.util.PriorityQueue;
import com.batalla.Batalla;
import com.batalla.Jugador;
import com.ejercito.Ejercito;
import com.ejercito.Soldado;
import com.interfaz.user.Ingreso;
import com.interfaz.user.Mensaje;
import com.interfaz.user.Pantalla;

public class PantallaAtaque extends Pantalla {
	public PantallaAtaque(Batalla batalla) {
		String nombreJugadorAlTurno = batalla.obtenerJugadorAlTurno().obtenerNombre();
		agregar(new Mensaje("Turno del jugador " + nombreJugadorAlTurno + "\n"));
		for (Jugador jugador : batalla.obtenerJugadores()) {
			Ejercito ejercito = jugador.obtenerEjercito();
			PriorityQueue<Soldado> frente = new PriorityQueue<>();
			ejercito.tomarPosicion(frente);
			agregar(new Mensaje("Pasando revista al ejercito del jugador " + jugador.obtenerNombre()));
			agregar(new Mensaje("\tCantidad de tropas: " + frente.size()));
			agregar(new Mensaje("\tCapacidad de ataque: " + ejercito.calcularCapacidadDeAtaque() + "\n"));
		}
		agregar(new Mensaje(nombreJugadorAlTurno + ", desea comprar otra unidad antes de atacar? [Si/No]"));
		agregar(new Ingreso());
	}
}
