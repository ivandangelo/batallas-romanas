package com.ejercito;

public class Centurion extends Soldado {
	public Centurion() {
		super();
		costo = 200;
		capacidadDeAtaque = 1;
		probabilidadDeAtacar = 1; // tiene 100% de acercar el ataque
		probabilidadDeDefenderse = 0.5f; // tiene 50% de esquivar un ataque
		incrementoAlAtaqueTotal = 0.1f;
		ordenEnElFrente = 3;
	}
}
