package com.ejercito;

import java.util.PriorityQueue;

import com.ejercito.excepciones.DanioNegativoExcepcion;
import com.ejercito.excepciones.EjercitoFueraDeCombateExcepcion;
import com.ejercito.excepciones.ElEjercitoNoPuedoRecibirUnAtaqueNull;

public class Ejercito extends Legion {

	public void recibirAtaque(Ejercito enemigo) throws DanioNegativoExcepcion, ElEjercitoNoPuedoRecibirUnAtaqueNull, EjercitoFueraDeCombateExcepcion {
		
		if(enemigo == null || enemigo == this){
			throw new ElEjercitoNoPuedoRecibirUnAtaqueNull("El ejercito atacante es null");
		}
		if(enemigo.estaFueraDeCombate()){
			throw new EjercitoFueraDeCombateExcepcion("El ejercito atacante esta fuera de combate");	
		}
		float ataqueTotal = enemigo.calcularCapacidadDeAtaque() * (1 + enemigo.calcularIncrementoAlAtaqueTotal());
		PriorityQueue<Soldado> frente = new PriorityQueue<>();
		tomarPosicion(frente);
		while(ataqueTotal > 0 && !frente.isEmpty()) {
			Soldado soldado = frente.poll();
			float ataque = Math.min(ataqueTotal, soldado.obtenerEstadoVital());
			soldado.recibirAtaque(ataque);
			ataqueTotal -= ataque;
			
			
		}

		
	}
	
}
