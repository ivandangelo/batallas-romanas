package com.ejercito;

public class Legionario extends Soldado {
	public Legionario() {
		super();
		costo = 100;
		capacidadDeAtaque = 1.4f;
		probabilidadDeAtacar = 1;
		incrementoAlAtaqueTotal = 0;
		ordenEnElFrente = 2;
		probabilidadDeDefenderse = 0;
	}
}
