 package com.interfaz.files;

import com.ejercito.UnidadDeEjercito;
import com.ejercito.excepciones.ExcepcionAlAgregarUnidadNulaOIgual;

public class RegistroDeLegion {
	public String nombreLegion;
	public String[] sublegiones;
	public int auxiliares;
	public int legionarios;
	public int centuriones;

	public RegistroDeLegion(String nombreLegion, String[] subLegiones, int auxiliares, int legionarios,
			int centuriones) {
		this.nombreLegion = nombreLegion;
		this.sublegiones = subLegiones;
		this.auxiliares = Math.abs(auxiliares);
		this.legionarios = Math.abs(legionarios);
		this.centuriones = Math.abs(centuriones);

	}

	public int calcularCosto() {
		return auxiliares * 50 + legionarios * 100 + centuriones * 200;
	}

	public String toString() {
		return "Legion " + nombreLegion + ": " + auxiliares + " Auxiliares, " + legionarios + " Legionarios, "
				+ centuriones + " Centuriones. Costo: " + calcularCosto();
	}

	public UnidadDeEjercito crear() throws ExcepcionAlAgregarUnidadNulaOIgual {
		return BaseDeLegiones.getInstance().crearLegion(this);
	}
}
