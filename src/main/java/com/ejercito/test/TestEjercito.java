package com.ejercito.test;

import static org.junit.Assert.*;

import com.ejercito.*;
import com.ejercito.excepciones.DanioNegativoExcepcion;
import com.ejercito.excepciones.EjercitoFueraDeCombateExcepcion;
import com.ejercito.excepciones.ElEjercitoNoPuedoRecibirUnAtaqueNull;
import com.ejercito.excepciones.ExcepcionAlAgregarUnidadNulaOIgual;

import org.junit.Test;

public class TestEjercito {
	@Test
	public void sePuedeCrearUnaLegion() {
		new Legion();
	}

	@Test
	public void unEjercitoSinSoladosEstaFueraDeCombate() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Ejercito EjercitoVacio = new Ejercito();
		Legion legionVacio = new Legion();
		EjercitoVacio.enlistarUnidad(legionVacio);
		assertTrue(EjercitoVacio.estaFueraDeCombate());
	}

	@Test
	public void UnEjercitoConSoldadosNoEstaMuerta() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Ejercito miEjercito = new Ejercito();
		miEjercito.enlistarUnidad(new Auxiliar());
		assertTrue(!miEjercito.estaFueraDeCombate());
	}
	@Test
	public void UnEjercitopuedeIncorporarTodoTipoDeUnidades() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Ejercito miEjercito = new Ejercito();
		miEjercito.enlistarUnidad(new Auxiliar());
		miEjercito.enlistarUnidad(new Centurion());
		miEjercito.enlistarUnidad(new Legionario());
		miEjercito.enlistarUnidad(new Legion());
		assertTrue(!miEjercito.estaFueraDeCombate());
	}

	@Test
	public void aUnEjercitoSeLePuedeIncorporarOtraLEgion() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Ejercito miEjercito = new Ejercito();
		Legion laDoce = new Legion();
		laDoce.enlistarUnidad(new Auxiliar());
		miEjercito.enlistarUnidad(laDoce);
		assertTrue(!miEjercito.estaFueraDeCombate());
	}
	@Test (expected = Exception.class)
	public void aUnEjercitoNoSeLePuedeIncorporarElMismoEjercito() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Ejercito miEjercito = new Ejercito();
		miEjercito.enlistarUnidad(new Auxiliar());
		miEjercito.enlistarUnidad(miEjercito);
	}

	@Test
	public void calcularCapacidadDeAtaqueRealizaBienLosCalculos() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Ejercito miEjercito = new Ejercito();
		miEjercito.enlistarUnidad(new Legionario());
		miEjercito.enlistarUnidad(new Centurion());
		
		float resultado = miEjercito.calcularCapacidadDeAtaque() + 
				miEjercito.calcularCapacidadDeAtaque()*miEjercito.calcularIncrementoAlAtaqueTotal();
		
		assertEquals((2.4f)+(2.4f/10), resultado, 0.1f);
	}

	@Test
	public void aUnEjercitoSePuedeExterminar() throws DanioNegativoExcepcion, ExcepcionAlAgregarUnidadNulaOIgual, ElEjercitoNoPuedoRecibirUnAtaqueNull, EjercitoFueraDeCombateExcepcion {
		Ejercito miEjercito = new Ejercito();
		Ejercito ejercitoEnemigo = new Ejercito();
		for(int i = 0; i<100; i++) {
			ejercitoEnemigo.enlistarUnidad(new Centurion());
		}
		miEjercito.enlistarUnidad(new Auxiliar());
		miEjercito.recibirAtaque(ejercitoEnemigo);
		assertTrue(miEjercito.estaFueraDeCombate());
	}


	@Test (expected = Exception.class)
	public void ElEjercitoNoPuedeRecibirUnAtaqueDeSiMismo() throws DanioNegativoExcepcion, ExcepcionAlAgregarUnidadNulaOIgual, ElEjercitoNoPuedoRecibirUnAtaqueNull, EjercitoFueraDeCombateExcepcion {
		Ejercito miEjercito = new Ejercito();
		Ejercito otroEjercito = new Ejercito();
		for(int i = 0; i<90; i++) {
			otroEjercito.enlistarUnidad(new Centurion());
		}
		miEjercito.enlistarUnidad(new Legionario());
		miEjercito.recibirAtaque(otroEjercito);
		miEjercito.recibirAtaque(miEjercito);
		assertFalse(miEjercito.estaFueraDeCombate());
		
	}
	@Test (expected = Exception.class)
	public void ElEjercitoNoPuedeRecibirUnAtaqueDeUnEjercitoFueraDeCombate() throws DanioNegativoExcepcion, ExcepcionAlAgregarUnidadNulaOIgual, ElEjercitoNoPuedoRecibirUnAtaqueNull, EjercitoFueraDeCombateExcepcion {
		Ejercito miEjercito = new Ejercito();
		Ejercito otroEjercito = new Ejercito();
		miEjercito.enlistarUnidad(new Legionario());
		miEjercito.recibirAtaque(otroEjercito);
	}
}