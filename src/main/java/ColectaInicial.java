import java.util.LinkedList;
import com.interfaz.user.ColectorGenerico;
import com.interfaz.user.Ingreso;
import com.interfaz.user.Pantalla;

public class ColectaInicial extends ColectorGenerico {
	private LinkedList<String> nombres = new LinkedList<>();
	private boolean esInvalido;

	public String[] nombres() {
		return nombres.toArray(new String[0]);
	}

	public boolean esInvalido() {
		return esInvalido;
	}

	public void colectar(Ingreso ingreso) {
		if (!esInvalido) {
			String nombre = ingreso.dato(), error = null;
			if (esInvalido = nombre.isEmpty()) {
				error = " no tiene nombre.";
			} else if (esInvalido = nombre.length() < 3) {
				error = " tiene un nombre con menos de 3 letras:  '" + nombre + "'.";
			} else if (esInvalido = nombres.contains(nombre)) {
				error = " tiene el mismo nombre que otro jugador.";
			}
			nombres.add(nombre);
			if (esInvalido) {
				System.out.println("El jugador " + nombres.size() + error);
			}
		}
	}

	public void colectar(Pantalla pantalla) {
		nombres.clear();
		esInvalido = false;
		super.colectar(pantalla);
	}
}