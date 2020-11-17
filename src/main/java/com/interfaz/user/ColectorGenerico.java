package com.interfaz.user;

public abstract class ColectorGenerico implements Colector {
	@Override
	public void colectar(Pantalla pantalla) {
		for (Componente componente : pantalla) {
			componente.colectarse(this);
		}
	}

	@Override
	public void colectar(Menu menu) {
	}

	@Override
	public void colectar(Mensaje mensaje) {
	}

	@Override
	public void colectar(Ingreso ingreso) {
	}

	@Override
	public void colectar(Dado dado) {
	}
}
