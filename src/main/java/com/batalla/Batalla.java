package com.batalla;

import com.ejercito.Ejercito;
import com.ejercito.excepciones.DanioNegativoExcepcion;
import com.ejercito.excepciones.EjercitoFueraDeCombateExcepcion;
import com.ejercito.excepciones.ElEjercitoNoPuedoRecibirUnAtaqueNull;

public class Batalla {

	public static final int CANTIDAD_JUGADORES = 2;
	private boolean inicio = false;
	private boolean termino = false;
	private Jugador[] jugadores = new Jugador[CANTIDAD_JUGADORES];
	private int posicionJugadorAlTurno = 0;
	private int direccion = 1;

	public Batalla(String[] nombresDeJugadores) {
		for (int i = 0; i < jugadores.length; i++) {
			jugadores[i] = new Jugador(nombresDeJugadores[i]);
		}
	}

	public Jugador[] obtenerJugadores() {
		return jugadores;
	}

	// indica en que circunstancias se puede iniciar una batalla
	public boolean iniciar() {
		boolean puedeIniciar = !(inicio || termino || algunoFueraDeCombate());
		if (puedeIniciar) {
			inicio = true;
			posicionJugadorAlTurno = jugadores.length - 1;
			direccion = -1;
		}
		return puedeIniciar;
	}

	// termina la batalla si algun jugador esta fuera de combate
	public boolean termino() {
		return termino;
	}

	// devuelve el ganador si es lo que hay
	public Jugador ganador() {
		return termino ? jugadores[posicionJugadorAlTurno] : null;
	}

	// el jugador al turno ataca al resto de los jugadores
	public boolean atacar()
			throws DanioNegativoExcepcion, ElEjercitoNoPuedoRecibirUnAtaqueNull, EjercitoFueraDeCombateExcepcion {
		boolean puedeAtacar = false;
		if (inicio && !termino) {
			Jugador jugadorAlTurno = jugadores[posicionJugadorAlTurno];
			Ejercito ejercitoAtacante = jugadorAlTurno.obtenerEjercito();
			for (int i = 0; i < jugadores.length; i++) {
				if (jugadores[i] != jugadorAlTurno) {
					jugadores[i].obtenerEjercito().recibirAtaque(ejercitoAtacante);
					puedeAtacar = true;
				}
			}
			if (!(termino = algunoFueraDeCombate()))
				posicionJugadorAlTurno = proximoJugador();
		}
		return puedeAtacar;
	}

	// devuelve el jugador proximo a atacar
	public Jugador obtenerJugadorAlTurno() {
		Jugador jugadorAlTurno = jugadores[posicionJugadorAlTurno];
		if (!inicio) {
			posicionJugadorAlTurno = proximoJugador();
		}
		return jugadorAlTurno;
	}

	// logica que delega la posicion al jugador al turno para su proximo jugador
	private int proximoJugador() {
		int proximoJugador = posicionJugadorAlTurno + direccion;
		if (proximoJugador >= jugadores.length) {
			proximoJugador = 0;
		} else if (proximoJugador < 0) {
			proximoJugador = jugadores.length - 1;
		}
		return proximoJugador;
	}

	// devuelve si existe algun jugador muerto
	private boolean algunoFueraDeCombate() {
		boolean hayUnEjercitoFueraDeCombate = false;
		for (int i = 0; !hayUnEjercitoFueraDeCombate && i < jugadores.length; i++) {
			hayUnEjercitoFueraDeCombate = jugadores[i].obtenerEjercito().estaFueraDeCombate();
		}
		return hayUnEjercitoFueraDeCombate;
	}
}