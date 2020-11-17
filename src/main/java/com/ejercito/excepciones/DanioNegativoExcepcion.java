package com.ejercito.excepciones;

public class DanioNegativoExcepcion extends Exception {
	private static final long serialVersionUID = 1L;

	public DanioNegativoExcepcion(String msj){
		super(msj);
	}

}
