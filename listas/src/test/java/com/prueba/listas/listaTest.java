package com.prueba.listas;

import java.util.Random;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class listaTest extends TestCase {
	@Rule
	public Timeout timeOut = Timeout.seconds(10);

	Lista lista;
	int total;

	public listaTest() {
		Random random = new Random();
		total = 10 + random.nextInt(50);
		lista = new Lista();
	}
	public void agregarLista() {
		lista.agregar(12);
	}

	@Test
	public void isNullTest()throws Exception {
		Assert.assertTrue(lista.isNull());
		lista.agregar(1);
		Assert.assertFalse(lista.isNull());
		lista.eliminar(1);
		Assert.assertTrue(lista.isNull());
	}

	@Test
	public void consultarTest()throws Exception {
		Assert.assertNull(lista.consultar(200));
		
	}
}
