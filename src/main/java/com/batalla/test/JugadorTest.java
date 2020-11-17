package com.batalla.test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.PriorityQueue;
import com.ejercito.*;
import com.batalla.*;
import com.interfaz.files.*;

public class JugadorTest {

	@Test
	public void sePuedeCrearUnJugadorConSoloElNombre() {
		new Jugador("jose");
	}

	@Test
	public void sePuedeCrearUnJugadorConSoloElNombreYDevuelveElMismo() {
		Jugador miJugador = new Jugador("jose");
		assertEquals("jose", miJugador.obtenerNombre());
	}

	@Test
	public void sePuedeCrearUnJugadorConSoloElNombreYDevuelveUnEjercito() {
		Jugador miJugador = new Jugador("jose");
		assertNotNull(miJugador.obtenerEjercito());
	}

	@Test
	public void unJugadorRecienCreadoDevuelveUnEjercitoVacio() {
		Jugador miJugador = new Jugador("jose");
		PriorityQueue<Soldado> frente = new PriorityQueue<>();
		miJugador.obtenerEjercito().tomarPosicion(frente);
		assertTrue(frente.isEmpty());
	}

	@Test
	public void unJugadorRecienCreadoPuedeComprarAlgunaLegion() throws Exception {
		Jugador miJugador = new Jugador("jose");
		RegistroDeLegion legionParaComprar = null;
		for(RegistroDeLegion legion : BaseDeLegiones.getInstance().obtenerLegiones()) {
			if(legion.calcularCosto() <= miJugador.obtenerDineroActual()) {
				legionParaComprar = legion;
				break;
			}
		}
		assertTrue(miJugador.comprarUnidad(legionParaComprar));
	}
}
