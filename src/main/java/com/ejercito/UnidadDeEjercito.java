package com.ejercito;

import java.util.PriorityQueue;

public interface UnidadDeEjercito {

	public float calcularCapacidadDeAtaque();

	public float calcularIncrementoAlAtaqueTotal();

	public void tomarPosicion(PriorityQueue<Soldado> frente);

	public boolean estaFueraDeCombate();

}
