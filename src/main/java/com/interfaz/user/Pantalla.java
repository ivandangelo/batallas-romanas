package com.interfaz.user;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Pantalla implements Componente, Iterable<Simple> {

	private List<Simple> componentes = new LinkedList<Simple>();

	public boolean agregar(Simple componente) {
		return componentes.add(componente);
	}

	@Override
	public void mostrar() {
		for (Componente componente : componentes) {
			componente.mostrar();
		}
	}

	@Override
	public void colectarse(Colector colector) {
		colector.colectar(this);
	}

	@Override
	public Iterator<Simple> iterator() {
		return componentes.iterator();
	}
}
