package com.interfaz.user;

public class Mensaje implements Simple {

	private String texto;
	
	public Mensaje(String texto) {
		this.texto = texto;
	}

	@Override
	public void mostrar() {
		System.out.println(texto);
	}

	@Override
	public void colectarse(Colector colector) {
		colector.colectar(this);
	}

}
