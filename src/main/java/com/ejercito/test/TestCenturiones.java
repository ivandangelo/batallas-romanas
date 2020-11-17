package com.ejercito.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.ejercito.Auxiliar;
import com.ejercito.Centurion;
import com.ejercito.excepciones.DanioNegativoExcepcion;

public class TestCenturiones {

	@Test
	public void sePuedeCrearUnCenturionQueEstaVivo() {
		Centurion uno = new Centurion();
		assertFalse(uno.estaFueraDeCombate());
	}

	@Test
	public void elCostoDeUnCenturionEsDe200() {
		Centurion uno = new Centurion();
		assertEquals(200, uno.obtenerCosto(), 0.1);

	}

	@Test
	public void elDanioDeUnCenturionEs1() {
		Centurion uno = new Centurion();
		assertEquals(1, uno.calcularCapacidadDeAtaque(), 0.1);
	}

	@Test
	public void elDanioDeUnCenturionMuertoEs0Nulo() throws DanioNegativoExcepcion {
		Centurion uno = new Centurion();
		for (int i = 0; i < 10; i++) {
			uno.recibirAtaque(100);
		}
		assertTrue(uno.estaFueraDeCombate());
		assertEquals(0.0f, uno.calcularCapacidadDeAtaque(), 0.1);
	}

	@Test
	public void LaBonificaionDeDanioDeUnAuxiliar0Nulo() {
		Centurion uno = new Centurion();
		assertEquals(0.1, uno.calcularIncrementoAlAtaqueTotal(), 0.1);
	}

	@Test(expected = Exception.class)
	public void unACenturionNopuedeRecibirDanioNegativo() throws DanioNegativoExcepcion {
		Centurion uno = new Centurion();
		uno.recibirAtaque(-15);
	}

	@Test
	public void losCenturionesAtacanCorrectamente() throws DanioNegativoExcepcion {
		Centurion atacante = new Centurion();
		Auxiliar defensor = new Auxiliar();
		defensor.recibirAtaque(99.0f);
		assertFalse(defensor.estaFueraDeCombate());
		defensor.recibirAtaque(atacante.calcularCapacidadDeAtaque());
		assertTrue(defensor.estaFueraDeCombate());

	}

	@Test
	public void almenosEl40porcientoDelosAtaquesAUnCenturionSonEsquivados() throws DanioNegativoExcepcion {
		int EsquivesExitosos = 0;
		for (int i = 0; i < 100; i++) {
			Centurion defensor = new Centurion();
			defensor.recibirAtaque(100);
			if (defensor.estaFueraDeCombate() == false) {
				EsquivesExitosos++;
			}
		}
		assertTrue(EsquivesExitosos > 40);

	}

	@Test
	public void noMasDel60porcientoDelosAtaquesAUnCenturionSonEsquivados() throws DanioNegativoExcepcion {
		int EsquivesExitosos = 0;
		for (int i = 0; i < 100; i++) {
			Centurion defensor = new Centurion();
			defensor.recibirAtaque(100);
			if (defensor.estaFueraDeCombate() == false) {
				EsquivesExitosos++;
			}
		}
		assertTrue(EsquivesExitosos < 60);

	}
}
