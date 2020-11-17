package com.batalla;

import com.ejercito.*;
import com.ejercito.excepciones.ExcepcionAlAgregarUnidadNulaOIgual;
import com.interfaz.files.RegistroDeLegion;

public class Jugador {

	private String nombreDelJugador;
	private double dineroPorJugador = 500000;
	private Ejercito ejercito = new Ejercito();

	public Jugador(String nombre) {
		this.nombreDelJugador = nombre;
	}

	public String obtenerNombre() {
		return nombreDelJugador;
	}

	public Ejercito obtenerEjercito() {
		return ejercito;
	}

	public double obtenerDineroActual() {
		return dineroPorJugador;
	}
// permite comprar una legion de la base de legiones si le alcanza el dinero y luego lo enlista a su ejercito
	public boolean comprarUnidad(RegistroDeLegion miLegion) throws ExcepcionAlAgregarUnidadNulaOIgual {
		boolean puedeComprar = miLegion != null && miLegion.calcularCosto() <= dineroPorJugador;
		if (puedeComprar) {
			dineroPorJugador -= miLegion.calcularCosto();
			ejercito.enlistarUnidad(miLegion.crear());
		}
		return puedeComprar;
	}
}