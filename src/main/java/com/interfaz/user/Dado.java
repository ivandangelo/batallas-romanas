package com.interfaz.user;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Dado implements Simple {
	private int tirada;

	public int tirada() {
		return tirada;
	}

	@Override
	public void mostrar() {
		System.out.print("Presione ENTER para arrojar el dado.");
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		System.out.print("Aguarde el resultado");
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		System.out.println(" En su tirada obtuvo el numero " + (tirada = new Random().nextInt(6) + 1));
	}

	@Override
	public void colectarse(Colector colector) {
		colector.colectar(this);
	}
}
