import java.util.TreeMap;
import com.batalla.Batalla;
import com.batalla.Jugador;
import com.ejercito.excepciones.DanioNegativoExcepcion;
import com.ejercito.excepciones.EjercitoFueraDeCombateExcepcion;
import com.ejercito.excepciones.ElEjercitoNoPuedoRecibirUnAtaqueNull;
import com.ejercito.excepciones.ExcepcionAlAgregarUnidadNulaOIgual;
import com.interfaz.files.BaseDeLegiones;
import com.interfaz.files.RegistroDeLegion;
import com.interfaz.user.Colector;
import com.interfaz.user.Pantalla;

public class App {
	private static BaseDeLegiones base = BaseDeLegiones.getInstance();

	public static void main(String[] args)
			throws DanioNegativoExcepcion, ElEjercitoNoPuedoRecibirUnAtaqueNull, EjercitoFueraDeCombateExcepcion, ExcepcionAlAgregarUnidadNulaOIgual {
		ColectaInicial colectaInicial = new ColectaInicial();
		ColectaDado colectaDado = new ColectaDado();
		ColectaAtaque colectaAtaque = new ColectaAtaque();
		do {
			mostrarColectar(new PantallaInicial(), colectaInicial);
		} while (colectaInicial.esInvalido());
		mostrarColectar(new PantallaDado(colectaInicial.nombres()), colectaDado);
		Batalla batalla = new Batalla(ordenarPorTurnos(colectaInicial.nombres(), colectaDado.tiradas()));
		do {
			Jugador jugador = batalla.obtenerJugadorAlTurno();
			compraJugador(jugador);
		} while (!batalla.iniciar());
		do {
			mostrarColectar(new PantallaAtaque(batalla), colectaAtaque);
			if (colectaAtaque.deseaComprar()) {
				compraJugador(batalla.obtenerJugadorAlTurno());
			}
			batalla.atacar();
		} while (!batalla.termino());
		new PantallaFinal(batalla).mostrar();
	}

	private static void compraJugador(Jugador jugador) throws ExcepcionAlAgregarUnidadNulaOIgual {
		ColectaCompras colectaCompras = new ColectaCompras();
		RegistroDeLegion[] legiones = base.obtenerLegiones();
		mostrarColectar(new PantallaCompras(jugador, legiones), colectaCompras);
		if (colectaCompras.pudoElegir()) {
			jugador.comprarUnidad(legiones[colectaCompras.opcion() - 1]);
		}
	}

	private static String[] ordenarPorTurnos(String[] nombres, Integer[] tiradas) {
		TreeMap<Integer, String> tree = new TreeMap<>();
		for (int i = 0; i < tiradas.length; i++) {
			tree.put(tiradas[i], nombres[i]);
		}
		return tree.descendingMap().values().toArray(new String[0]);
	}

	private static void mostrarColectar(Pantalla p, Colector c) {
		p.mostrar();
		p.colectarse(c);
	}
}
