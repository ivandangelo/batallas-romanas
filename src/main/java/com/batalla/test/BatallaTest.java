package com.batalla.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.batalla.Batalla;
import com.batalla.Jugador;
import com.ejercito.excepciones.DanioNegativoExcepcion;
import com.interfaz.files.BaseDeLegiones;
import com.interfaz.files.RegistroDeLegion;

public class BatallaTest {

	@Test
	public void unaBatallaSePuedeCrearConDosJugadores() {
		new Batalla(new String[] { "juan", "pedro" });
	}

	@Test
	public void unaBatallaConDosJugadoresDevuelveDosJugadoresConLosMismosNombresEnElMismoOrden() {
		String[] nombresJugadoresEsperados = new String[] { "juan", "jose" };
		String[] nombresJugadoresObtenidos = new String[nombresJugadoresEsperados.length];
		Batalla miBatalla = new Batalla(nombresJugadoresEsperados);
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		for (int i = 0; i < jugadores.length; i++) {
			nombresJugadoresObtenidos[i] = jugadores[i].obtenerNombre();
		}
		assertArrayEquals(nombresJugadoresEsperados, nombresJugadoresObtenidos);
	}

	@Test
	public void unaBatallaQueNoInicioNoTermino() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		assertFalse(miBatalla.termino());
	}

	@Test
	public void unaBatallaQueNoInicioNoTieneGanador() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		assertNull(miBatalla.ganador());
	}

	@Test
	public void unaBatallaQueNoInicioNoPuedeAtacar() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		assertFalse(miBatalla.atacar());
	}

	@Test
	public void unaBatallaQueNoInicioDevuelveJugadorAlTurnoAvanzando() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		assertFalse(miBatalla.obtenerJugadorAlTurno().obtenerNombre()
				.equals(miBatalla.obtenerJugadorAlTurno().obtenerNombre()));
	}

	@Test
	public void unaBatallaQueNoInicioDevuelveJugadorAlTurnoEnElOrdenCreado() throws Exception {
		String[] nombresJugadoresEsperados = new String[] { "juan", "jose" };
		String[] nombresJugadoresObtenidos = new String[nombresJugadoresEsperados.length];
		Batalla miBatalla = new Batalla(nombresJugadoresEsperados);
		for (int i = 0; i < nombresJugadoresEsperados.length; i++) {
			nombresJugadoresObtenidos[i] = miBatalla.obtenerJugadorAlTurno().obtenerNombre();
		}
		assertArrayEquals(nombresJugadoresEsperados, nombresJugadoresObtenidos);
	}

	@Test
	public void unaBatallaConJugadoresSinTropasNoPuedeIniciar() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		assertFalse(miBatalla.iniciar());
	}

	@Test
	public void alIniciarLaBatallaNoSePuedeIniciar() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		assertFalse(miBatalla.iniciar());
	}

	@Test
	public void alIniciarLaBatallaNoTermino() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		assertFalse(miBatalla.termino());
	}

	@Test
	public void alIniciarLaBatallaNoHayGanador() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		assertNull(miBatalla.ganador());
	}

	@Test
	public void alIniciarLaBatallaDevuelveJugadorAlTurnoSinAvanzar() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		assertTrue(miBatalla.obtenerJugadorAlTurno().obtenerNombre()
				.equals(miBatalla.obtenerJugadorAlTurno().obtenerNombre()));
	}

	@Test
	public void alIniciarLaBatallaElPrimerJugadorAlTurnoEsElUltimoCreado() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		assertEquals("jose", miBatalla.obtenerJugadorAlTurno().obtenerNombre());
	}

	@Test
	public void alIniciarLaBatallaAtacarAvanzaElTurno() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		Jugador miJugador = miBatalla.obtenerJugadorAlTurno();
		miBatalla.atacar();
		assertFalse(miJugador.obtenerNombre().equals(miBatalla.obtenerJugadorAlTurno().obtenerNombre()));
	}

	@Test
	public void alIniciarLaBatallaAtacarAvanzaElTurnoEnElOrdenInversoAlCreado() throws Exception {
		String[] nombresJugadoresEsperados = new String[] { "juan", "jose" };
		String[] nombresJugadoresObtenidos = new String[nombresJugadoresEsperados.length];
		Batalla miBatalla = new Batalla(nombresJugadoresEsperados);
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		for (int i = 0; i < nombresJugadoresEsperados.length; i++) {
			nombresJugadoresObtenidos[nombresJugadoresEsperados.length - i - 1] = miBatalla.obtenerJugadorAlTurno()
					.obtenerNombre();
			miBatalla.atacar();
		}
		assertArrayEquals(nombresJugadoresEsperados, nombresJugadoresObtenidos);
	}

	@Test
	public void alTerminarLaBatallaNoSePuedeIniciar() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		while (!miBatalla.termino()) {
			miBatalla.atacar();
		}
		assertFalse(miBatalla.iniciar());
	}

	@Test
	public void alTerminarLaBatallaNoSePuedeAtacar() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		while (!miBatalla.termino()) {
			miBatalla.atacar();
		}
		Jugador ultimoJugador = miBatalla.obtenerJugadorAlTurno();
		miBatalla.atacar();
		assertTrue(ultimoJugador == miBatalla.obtenerJugadorAlTurno());
	}

	@Test
	public void alTerminarLaBatallaHayUnGanador() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		while (!miBatalla.termino()) {
			miBatalla.atacar();
		}
		assertNotNull(miBatalla.ganador());
	}

	@Test
	public void alTerminarLaBatallaJugadorAlTurnoEsElGanador() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		RegistroDeLegion[] legiones = BaseDeLegiones.getInstance().obtenerLegiones();
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i].comprarUnidad(legiones[i]);
		}
		miBatalla.iniciar();
		while (!miBatalla.termino()) {
			miBatalla.atacar();
		}
		assertTrue(miBatalla.obtenerJugadorAlTurno() == miBatalla.ganador());
	}

	@Test
	public void alTerminarLaBatallaSiCompraUnJugadorSigueTerminada() throws Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		BaseDeLegiones base = BaseDeLegiones.getInstance();
		for (Jugador jugador : jugadores) {
			jugador.comprarUnidad(base.obtenerLegiones()[0]);
		}
		miBatalla.iniciar();
		while (!miBatalla.termino()) {
			miBatalla.atacar();
		}
		for (Jugador jugador : jugadores) {
			jugador.comprarUnidad(base.obtenerLegiones()[0]);
		}
		assertTrue(miBatalla.termino());
	}

	@Test
	public void alTerminarLaBatallaSiCompraUnJugadorNoCambiaElGanador() throws DanioNegativoExcepcion, Exception {
		Batalla miBatalla = new Batalla(new String[] { "juan", "jose" });
		Jugador[] jugadores = miBatalla.obtenerJugadores();
		BaseDeLegiones.CargarDatos();
		BaseDeLegiones base = BaseDeLegiones.getInstance();
		for (Jugador jugador : jugadores) {
			jugador.comprarUnidad(base.obtenerLegiones()[0]);
		}
		miBatalla.iniciar();
		while (!miBatalla.termino()) {
			miBatalla.atacar();
		}
		Jugador ganador = miBatalla.ganador();
		for (Jugador jugador : jugadores) {
			jugador.comprarUnidad(base.obtenerLegiones()[0]);
		}
		assertEquals(ganador, miBatalla.ganador());
	}
}
