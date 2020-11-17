package com.ejercito.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.ejercito.Auxiliar;
import com.ejercito.excepciones.DanioNegativoExcepcion;

public class TestAuxiliares {

	@Test
	public void sePuedeCrearUnAuxiliarQueEstaVivo() {
		Auxiliar uno = new Auxiliar();
		assertFalse(uno.estaFueraDeCombate());
	}

	@Test
	public void elCostoDeUnAuxiliarEsDe50() {
		Auxiliar uno = new Auxiliar();
		assertEquals(50, uno.obtenerCosto(), 0.1);

	}

	@Test
	public void almenosEl40porcientoDelosAtaquesDeUnAuxiliarAciertan() {
		Auxiliar uno = new Auxiliar();
		int ataquesExitosos = 0;
		for(int i = 0 ; i<100 ; i++) {
			if(uno.calcularCapacidadDeAtaque() == 0.7f) {
				ataquesExitosos++;
			}
		}
	assertTrue(ataquesExitosos > 40);
	
	}

	@Test
	public void noMasDel60porcientoDelosAtaquesDeUnAuxiliarAciertan() {
		Auxiliar uno = new Auxiliar();
		int ataquesExitosos = 0;
		for(int i = 0 ; i<100 ; i++) {
			if(uno.calcularCapacidadDeAtaque() == 0.7f) {
				ataquesExitosos++;
			}
		}
	assertTrue(ataquesExitosos < 60);
	
	}

	@Test
	public void elDanoDeUnAuxiliarMuertoEs0Nulo() throws DanioNegativoExcepcion {
		Auxiliar uno = new Auxiliar();
		uno.recibirAtaque(100);
		uno.recibirAtaque(100);
		uno.recibirAtaque(100);
		assertTrue(uno.estaFueraDeCombate());
		assertEquals(0, uno.calcularCapacidadDeAtaque(), 0.1);
	}

	@Test
	public void LaBonificaionDeDanoDeUnAuxiliar0Nulo() {
		Auxiliar uno = new Auxiliar();
		assertEquals(0, uno.calcularIncrementoAlAtaqueTotal(), 0.1);
	}

	@Test(expected = DanioNegativoExcepcion.class)
	public void unAuxiliarNopuedeRecibirDanioNegativo() throws DanioNegativoExcepcion {
		Auxiliar uno = new Auxiliar();
		uno.recibirAtaque(-15);

	}

	@Test
	public void losAuxiliaresAtacanYrecibenAtaquesCorrectamente() throws DanioNegativoExcepcion {
		Auxiliar atacante = new Auxiliar();
		Auxiliar defensor = new Auxiliar();
		defensor.recibirAtaque(99.3f);
		assertFalse(defensor.estaFueraDeCombate());
		defensor.recibirAtaque(atacante.calcularCapacidadDeAtaque() + atacante.calcularCapacidadDeAtaque()
				+ atacante.calcularCapacidadDeAtaque() + atacante.calcularCapacidadDeAtaque());
		assertTrue(defensor.estaFueraDeCombate());

	}

}
