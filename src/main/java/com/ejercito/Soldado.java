package com.ejercito;

import java.util.PriorityQueue;
import java.util.Random;

import com.ejercito.excepciones.DanioNegativoExcepcion;

public abstract class Soldado implements UnidadDeEjercito, Comparable<Soldado> {
	protected float estadoVital = 100;
	protected float capacidadDeAtaque;
	protected float incrementoAlAtaqueTotal;
	protected int ordenEnElFrente;
	protected float probabilidadDeAtacar;
	protected float probabilidadDeDefenderse;
	protected int costo;

	public int obtenerCosto() {
		return costo;
	}

	public float obtenerEstadoVital() {
		return estadoVital;
	}

	public void recibirAtaque(float danioARecibir) throws DanioNegativoExcepcion {
		if (danioARecibir < 0) {
			throw new DanioNegativoExcepcion("El danio no puede ser negativo");
		}
		if ((new Random()).nextDouble() > probabilidadDeDefenderse) {
			estadoVital -= danioARecibir;
		}
	}

	@Override
	public float calcularCapacidadDeAtaque() {

		return (estaFueraDeCombate() || new Random().nextDouble() > probabilidadDeAtacar) ? 0 : capacidadDeAtaque;

	}

	@Override
	public float calcularIncrementoAlAtaqueTotal() {
		return incrementoAlAtaqueTotal;
	}

	@Override
	public boolean estaFueraDeCombate() {
		return estadoVital <= 0;
	}

	@Override
	public void tomarPosicion(PriorityQueue<Soldado> frente) {
		if (estadoVital != 0) {
			frente.add(this);
		}
	}

	@Override
	public int compareTo(Soldado o) {
		return this.ordenEnElFrente - o.ordenEnElFrente;
	}
}
