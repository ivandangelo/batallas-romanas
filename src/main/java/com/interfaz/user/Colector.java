package com.interfaz.user;

public interface Colector {
	public void colectar(Pantalla pantalla);

	public void colectar(Menu menu);

	public void colectar(Mensaje mensaje);

	public void colectar(Ingreso ingreso);

	public void colectar(Dado dado);
}
