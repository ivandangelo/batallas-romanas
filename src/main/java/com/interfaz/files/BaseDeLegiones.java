package com.interfaz.files;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import com.ejercito.Auxiliar;
import com.ejercito.Centurion;
import com.ejercito.Legion;
import com.ejercito.Legionario;
import com.ejercito.excepciones.ExcepcionAlAgregarUnidadNulaOIgual;

public class BaseDeLegiones {
	private final String PATH = "./res/";
	private HashMap<String, RegistroDeLegion> base;
	private LectorDeLegiones lector;
	private HashSet<String> legionesBorradas;
	private static BaseDeLegiones unicaBase;

	private BaseDeLegiones() {
		try {
			legionesBorradas = new HashSet<>();
			lector = new LectorPCS(PATH);
			base = lector.leerLegion();
			lector = new LectorCS(PATH);
			base.putAll(lector.leerLegion());
			for (Entry<String, RegistroDeLegion> legionEnBase : base.entrySet()) {
				unificarLegion(legionEnBase.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public static BaseDeLegiones getInstance() {
		if (unicaBase == null) {
			unicaBase = new BaseDeLegiones();
		}
		return unicaBase;
	}

	public static void CargarDatos() {
		try {
			unicaBase = new BaseDeLegiones();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public RegistroDeLegion[] obtenerLegiones() {
		return base.values().toArray(new RegistroDeLegion[base.size()]);
	}

	public Legion crearLegion(RegistroDeLegion rl) throws ExcepcionAlAgregarUnidadNulaOIgual {
		Legion legionCreada = null;
		if (base.containsKey(rl.nombreLegion)) {
			legionCreada = new Legion();
			if (rl.sublegiones != null) {
				restoLasUnidadesDeMisSubLegiones(rl);
				for (String miSubLegion : rl.sublegiones) {
					if (base.containsKey(miSubLegion)) {
						legionCreada.enlistarUnidad(crearLegion(base.get(miSubLegion)));
					}
				}
			}
			sumoMisUnidadesAlaLegionCreada(legionCreada, rl);
			borrarRegistroCreado(rl.nombreLegion);
			borrarLegionesIncompletas();
		}
			return legionCreada;
	}

	// le suma a la clase padre las unidades de sus hijos
	private void unificarLegion(RegistroDeLegion rl) {
		if (rl.sublegiones != null) {
			for (String subrl : rl.sublegiones) {
				if (base.containsKey(subrl)) {
					sumarUnidadesAlRegistro(rl, base.get(subrl));
				}
			}
		}
	}

	private void sumarUnidadesAlRegistro(RegistroDeLegion actual, RegistroDeLegion subLegion) {
		actual.auxiliares += subLegion.auxiliares;
		actual.centuriones += subLegion.centuriones;
		actual.legionarios += subLegion.legionarios;
		if (subLegion.sublegiones != null) {
			for (String subrl : subLegion.sublegiones) {
				if (base.containsKey(subrl)) {
					sumarUnidadesAlRegistro(actual, base.get(subrl));
				}
			}
		}
	}

	// le agrega a una legion los elementos de un registro
	private void sumoMisUnidadesAlaLegionCreada(Legion legionCreada, RegistroDeLegion rl) throws ExcepcionAlAgregarUnidadNulaOIgual {
		for (int i = 0; i < rl.auxiliares; i++) {
			legionCreada.enlistarUnidad(new Auxiliar());
		}
		for (int i = 0; i < rl.centuriones; i++) {
			legionCreada.enlistarUnidad(new Centurion());
		}
		for (int i = 0; i < rl.legionarios; i++) {
			legionCreada.enlistarUnidad(new Legionario());
		}
	}

	// resta las unidades de las sublegiones de la legion que se esta creando
	private void restoLasUnidadesDeMisSubLegiones(RegistroDeLegion rl) {
		if (rl.sublegiones != null) {
			for (String sublegion : rl.sublegiones) {
				if (base.containsKey(sublegion)) {
					rl.auxiliares -= base.get(sublegion).auxiliares;
					rl.centuriones -= base.get(sublegion).centuriones;
					rl.legionarios -= base.get(sublegion).legionarios;
					restoLasUnidadesDeMisSubLegiones(base.get(sublegion));
				}
			}
		}
	}

	// borra una legion y ademas la agrega a la lista que contiene las legiones
	// borradas para eliminarlas de otras legiones.
	private void borrarRegistroCreado(String name) {
		if (base.containsKey(name)) {
			base.remove(name);
		}
		legionesBorradas.add(name);
	}

	// borra todas las legiones que contengan a una de la lista de las borradas
	@SuppressWarnings("unchecked")
	private void borrarLegionesIncompletas() {
		while (!legionesBorradas.isEmpty() && !base.isEmpty()) {
			LinkedList<String> BaseEnLista = new LinkedList<>();
			BaseEnLista.addAll(base.keySet());
			Set<String> setBorradas = (Set<String>) legionesBorradas.clone();
			for (String nombre : BaseEnLista) {
				for (String legionb : setBorradas) {
					if (esUnaSubLegion(nombre, legionb)) {
						borrarRegistroCreado(nombre);
					}
					legionesBorradas.remove(legionb);
				}
			}
		}
	}

	// me dice se la legion b esta dentro de la legion a
	private boolean esUnaSubLegion(String posiblePadre, String posibleHijo) {
		boolean resultado = false;
		if (base.get(posiblePadre).sublegiones != null) {
			for (String miSubLegion : base.get(posiblePadre).sublegiones) {
				if (miSubLegion.equals(posibleHijo) || !base.containsKey(miSubLegion)) {
					return true;
				} else
					resultado = (resultado || esUnaSubLegion(miSubLegion, posibleHijo));
			}
		}
		return resultado;
	}
}
