package com.interfaz.user;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Simple {
	private List<String> items = new ArrayList<>();
	private int opcion;

	public int opcion() {
		return opcion;
	}

	public boolean agregar(String item) {
		return items.add(item);
	}

	@Override
	public void colectarse(Colector colector) {
		colector.colectar(this);
	}

	@Override
	public void mostrar() {
		for (int i = 0; i < items.size(); i++) {
			new Mensaje("[" + (i + 1) + "] " + items.get(i)).mostrar();
		}
		Ingreso ingreso = new Ingreso();
		while (opcion < 1 || opcion > items.size()) {
			new Mensaje("Elija su opcion ingrese un numero entre 1 y " + items.size()).mostrar();
			ingreso.mostrar();
			try {
				opcion = Integer.parseInt(ingreso.dato());
			} catch (NumberFormatException e) {
				new Mensaje("Hubo un problema. Intentelo nuevamente.").mostrar();
			}
		}
	}
}
