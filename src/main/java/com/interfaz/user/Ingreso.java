package com.interfaz.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ingreso implements Simple {

	private String dato;

	public String dato() {
		return dato;
	}

	@Override
	public void mostrar() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dato = null;
		while (dato == null) {
			try {
				this.dato = br.readLine();
			} catch (Exception e) {
				System.out.println("Hubo un problema. Intente nuevamente.");
			}
		}
	}

	@Override
	public void colectarse(Colector colector) {
		colector.colectar(this);
	}

}
