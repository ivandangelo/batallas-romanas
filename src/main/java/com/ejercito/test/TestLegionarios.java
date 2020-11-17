package com.ejercito.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import com.ejercito.Legionario;
import com.ejercito.excepciones.DanioNegativoExcepcion;


public class TestLegionarios {
	@Test
	public void sePuedeCrearUnLegionarioQueEstaVivo() {
		Legionario uno = new Legionario();
		assertFalse(uno.estaFueraDeCombate());
	}
	
	@Test
	public void elCostoDeUnLegionarioEsDe100() {
		Legionario uno = new Legionario();
		assertEquals(100 , uno.obtenerCosto(), 0.1);
		
	}
	
	@Test
	public void elDanioDeUnLegionarioEs1punto4() {
		Legionario uno = new Legionario();
		assertEquals(1.4 , uno.calcularCapacidadDeAtaque() , 0.1);
	}
	@Test
	public void elDanioDeUnLegionarioMuertoEs0Nulo() throws DanioNegativoExcepcion {
		Legionario uno = new Legionario();
		uno.recibirAtaque(100);
		assertTrue(uno.estaFueraDeCombate());
		assertEquals(0 , uno.calcularCapacidadDeAtaque() , 0.1);
	}
	
	@Test
	public void LaBonificaionDeDaoDeUnLegionario0Nulo() {
		Legionario uno = new Legionario();
		assertEquals(0 , uno.calcularIncrementoAlAtaqueTotal() , 0.1);
	}
	
	@Test (expected = Exception.class)
	public void unLegionarioNopuedeRecibirDanioNegativo() throws DanioNegativoExcepcion {
		Legionario uno = new Legionario();
		uno.recibirAtaque(-15);
	}

	@Test
	public void losLegionarioAtacanYrecibenAtaquesCorrectamente() throws DanioNegativoExcepcion {
		Legionario atacante = new Legionario();
		Legionario defensor = new Legionario();
		defensor.recibirAtaque(98.6f);
		assertFalse(defensor.estaFueraDeCombate());
		defensor.recibirAtaque(atacante.calcularCapacidadDeAtaque());
		assertFalse(defensor.estaFueraDeCombate());

	}
	
}
