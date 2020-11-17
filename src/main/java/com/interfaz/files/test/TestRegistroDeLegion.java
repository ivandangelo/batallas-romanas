package com.interfaz.files.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.ejercito.excepciones.ExcepcionAlAgregarUnidadNulaOIgual;
import com.interfaz.files.*;

public class TestRegistroDeLegion {

	@Test
	public void sePuedeInstanciarUnRegistroDeLegion() {
		new RegistroDeLegion("LegionPro", null, 15, 10, 5);
	}

	@Test
	public void UnRegistroDeLegionCalculaBienSuCosto() {
		RegistroDeLegion legion = new RegistroDeLegion("legion", null, 35, 20, 10);
		assertEquals(5750, legion.calcularCosto());
	}

	@Test(expected = Exception.class)
	public void EnUnRegistroDeLegionNoPuedenRepetirseLosNombresConSusSubLegiones() {
		BaseDeLegiones.CargarDatos();
		BaseDeLegiones base1 = BaseDeLegiones.getInstance();
		RegistroDeLegion[] rl = base1.obtenerLegiones();
		for (int i = 0; i < rl.length; i++) {
			for (int j = 0; j < rl[i].sublegiones.length; j++) {
				rl[i].nombreLegion.equalsIgnoreCase(rl[i].sublegiones[j]);
			}
		}
	}

	@Test
	public void alCrearUnaLegionLaEstoyBorrandoParaQueOtroJugadorNoLaUtilice() throws ExcepcionAlAgregarUnidadNulaOIgual {
		BaseDeLegiones.CargarDatos();
		BaseDeLegiones base1 = BaseDeLegiones.getInstance();
		RegistroDeLegion[] rl = base1.obtenerLegiones();
		RegistroDeLegion legionCreada = rl[1];
		base1.crearLegion(legionCreada);
		assertEquals(null, base1.crearLegion(legionCreada));
	}

	@Test
	public void alCrearLegionSeBorranTodasLasSubLegionesDeEsaLegion() throws ExcepcionAlAgregarUnidadNulaOIgual {
		BaseDeLegiones.CargarDatos();
		BaseDeLegiones base1 = BaseDeLegiones.getInstance();
		RegistroDeLegion[] rl = base1.obtenerLegiones();
		String legionCreada = rl[0].nombreLegion;
		base1.crearLegion(rl[0]);
		boolean legionCreadaYaNoExiste = false;
		for (int i = 0; i < rl.length; i++) {
			if (!(rl[i].nombreLegion.equals(legionCreada)))
				legionCreadaYaNoExiste = true;
			if (rl[i].sublegiones != null) {
				for (int j = 0; j < rl[i].sublegiones.length; j++) {
					if (!(rl[i].sublegiones[j].equals(legionCreada)))
						legionCreadaYaNoExiste = true;
				}
			}
		}
		assertTrue(legionCreadaYaNoExiste);
	}

	@Test
	public void alCargarLosDatosSeReiniciaLaBaseDeLegiones() throws ExcepcionAlAgregarUnidadNulaOIgual {
		BaseDeLegiones.CargarDatos();
		BaseDeLegiones base1 = BaseDeLegiones.getInstance();
		RegistroDeLegion[] rl = base1.obtenerLegiones();
		RegistroDeLegion legionCreada = rl[0];
		base1.crearLegion(legionCreada);
		BaseDeLegiones.CargarDatos();
		BaseDeLegiones baseConDatosReiniciados = BaseDeLegiones.getInstance();
		RegistroDeLegion[] rl2 = baseConDatosReiniciados.obtenerLegiones();
		assertEquals(legionCreada.nombreLegion, rl2[0].nombreLegion);
	}

	@Test
	public void SePuedeInstanciarALaBaseDeLegionesUnaSolaVez() {
		BaseDeLegiones.CargarDatos();
		BaseDeLegiones base1 = BaseDeLegiones.getInstance();
		BaseDeLegiones base2 = BaseDeLegiones.getInstance();
		Assert.assertTrue(base1 == base2);
	}

	@Test
	public void elCostoDeUnaBaseInstanciadaEsLaMismaQueLaDeOtraInstancia() {
		BaseDeLegiones.CargarDatos();
		BaseDeLegiones base1 = BaseDeLegiones.getInstance();
		BaseDeLegiones base2 = BaseDeLegiones.getInstance();
		int costosBase1 = 0;
		int costosBase2 = 0;
		for (RegistroDeLegion rl1 : base1.obtenerLegiones()) {
			costosBase1 += rl1.calcularCosto();
		}
		for (RegistroDeLegion rl2 : base2.obtenerLegiones()) {
			costosBase2 += rl2.calcularCosto();
		}
		assertTrue(costosBase1 == costosBase2);
	}
}