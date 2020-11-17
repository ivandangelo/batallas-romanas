package com.ejercito;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import com.ejercito.excepciones.ExcepcionAlAgregarUnidadNulaOIgual;

public class Legion implements UnidadDeEjercito {

	private LinkedList<UnidadDeEjercito> tropa;

	public Legion() {
		tropa = new LinkedList<UnidadDeEjercito>();
	}

	public void enlistarUnidad(UnidadDeEjercito a) throws ExcepcionAlAgregarUnidadNulaOIgual {
		if (a == null || a == this) {
			throw new ExcepcionAlAgregarUnidadNulaOIgual("No se puede agregar la misma unidad");
			
		}
		
		tropa.add(a);
		
		
	}

	@Override
	public float calcularCapacidadDeAtaque() {
		float capacidadDeAtaqueTotal = 0;
		for (UnidadDeEjercito unidadDeEjercito : tropa) {
			capacidadDeAtaqueTotal += unidadDeEjercito.calcularCapacidadDeAtaque();
		}
		return capacidadDeAtaqueTotal;
	}

	@Override
	public float calcularIncrementoAlAtaqueTotal() {
		float incrementoTotal = 0;
		for (UnidadDeEjercito unidadDeEjercito : tropa) {
			incrementoTotal += unidadDeEjercito.calcularIncrementoAlAtaqueTotal();
		}
		return incrementoTotal;
	}

	@Override
	public void tomarPosicion(PriorityQueue<Soldado> frenteDeBatalla) {
		for (UnidadDeEjercito unidad : tropa) {
			unidad.tomarPosicion(frenteDeBatalla);
		}
	}

	@Override
	public boolean estaFueraDeCombate() {
		boolean resultado = false;
		int cantidadDeBajas = 0;
		Iterator<UnidadDeEjercito> iterator = tropa.iterator();
		while (iterator.hasNext()) {
			UnidadDeEjercito aux = iterator.next();
			// si en vez de las 3 lineas de arriba metes un for-each queda mas bonito
			if (aux.estaFueraDeCombate()) {
				cantidadDeBajas++;
			}
		}
		if (cantidadDeBajas == tropa.size()) {
			resultado = true;
		}
		return resultado;
	}
}
