import java.util.LinkedList;
import com.interfaz.user.ColectorGenerico;
import com.interfaz.user.Dado;
import com.interfaz.user.Pantalla;

public class ColectaDado extends ColectorGenerico {
	private LinkedList<Integer> tiradas = new LinkedList<>();

	public void colectar(Pantalla pantalla) {
		tiradas.clear();
		super.colectar(pantalla);
	}

	public void colectar(Dado dado) {
		tiradas.add(dado.tirada());
	}

	public Integer[] tiradas() {
		return tiradas.toArray(new Integer[0]);
	}
}
