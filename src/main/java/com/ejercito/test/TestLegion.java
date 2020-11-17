package com.ejercito.test;

import static org.junit.Assert.*;
import com.ejercito.*;
import com.ejercito.excepciones.ExcepcionAlAgregarUnidadNulaOIgual;

import org.junit.Test;

public class TestLegion {
	@Test
	public void sePuedeCrearUnaLegion() {
		new Legion();
	}

	@Test
	public void unaLegionNulaEstaFueraDeConbate() {
		Legion nula = new Legion();
		assertTrue(nula.estaFueraDeCombate());
	}

	@Test
	public void unaLegionPuedeEnlistarTodoTipoDeUnidades() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Legion miLegion = new Legion();
		miLegion.enlistarUnidad(new Auxiliar());
		miLegion.enlistarUnidad(new Legionario());
		miLegion.enlistarUnidad(new Centurion());
	}
	@Test
	public void UnaLegionConSoldadosNoEstaMuerta() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Legion miLegion = new Legion();
		miLegion.enlistarUnidad(new Auxiliar());
		assertTrue(!miLegion.estaFueraDeCombate());
	}

	@Test
	public void aUnaLegionSeLePuedeIncorporarOtraLEgion() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Legion miLegion = new Legion();
		miLegion.enlistarUnidad(new Auxiliar());
		Legion miSEgundaLegion = new Legion();
		miSEgundaLegion.enlistarUnidad(miLegion);
		assertTrue(!miSEgundaLegion.estaFueraDeCombate());
	}
	@Test (expected = Exception.class)
	public void aUnaLegionNoSeLePuedeIncorporarLaMismaLEgion() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Legion miLegion = new Legion();
		miLegion.enlistarUnidad(new Auxiliar());
		miLegion.enlistarUnidad(miLegion);
	}

	@Test
	public void calcularCapacidadDeAtaqueRealizaBienLosCalculos() throws ExcepcionAlAgregarUnidadNulaOIgual {
		Legion miLegion = new Legion();
		miLegion.enlistarUnidad(new Legionario());
		miLegion.enlistarUnidad(new Centurion());
		
		float resultado = miLegion.calcularCapacidadDeAtaque() + 
				miLegion.calcularCapacidadDeAtaque()*miLegion.calcularIncrementoAlAtaqueTotal();
		
		assertEquals(2.4f+(2.4f/10), resultado, 0.1f);
	}


}
