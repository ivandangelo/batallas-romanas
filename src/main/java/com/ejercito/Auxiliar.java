package com.ejercito;

public class Auxiliar extends Soldado {

	public Auxiliar() {
		super();
		costo = 50;
		capacidadDeAtaque = 0.7f;
		probabilidadDeAtacar = 0.5f; // tiene 50% de acertar el ataque
		incrementoAlAtaqueTotal = 0;
		ordenEnElFrente = 1;
		probabilidadDeDefenderse = 0;
	}
}
