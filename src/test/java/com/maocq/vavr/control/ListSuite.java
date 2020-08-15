package com.maocq.vavr.control;

import com.maocq.Persona;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ListSuite {

    @Test
    public void testListOf() {
        List<Integer> lista = List.of(1, 2, 3, 4, 5);
        assertEquals(5, lista.size());
    }

    @Test
    public void testListRange() {
        List<Integer> lista = List.range(0, 10);
        assertEquals(10, lista.size());

        List<Integer> rangoCerrado = List.rangeClosed(0, 9);
        assertEquals(10, rangoCerrado.size());
    }

    @Test
    public void testPrepend() {
        List<Integer> lista = List.of(1, 2, 3);

        Integer nuevoElemento = 0;
        List<Integer> nuevaLista = lista.prepend(nuevoElemento);

        assertEquals(3, lista.size());
        assertEquals(4, nuevaLista.size());
        assertEquals(nuevoElemento , nuevaLista.head());
    }

    @Test
    public void grouped() {
        List<Integer> lista = List.rangeClosed(1, 9);

        List<List<Integer>> grupos = lista.grouped(3).toList();
        // List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))

        assertEquals(3, grupos.head().size());
    }

    @Test
    public void groupBy() {
        List<Persona> personas = List.of(
          new Persona("", 20, new BigDecimal("100")),
          new Persona("", 25, new BigDecimal("100")),
          new Persona("", 20, new BigDecimal("100")));

        Map<Integer, List<Persona>> personasPorEdades =
          personas.groupBy(Persona::getEdad);

        assertEquals(2, personasPorEdades.get(20).get().size());
    }

    @Test
    public void map() {
        List<Integer> lista = List.of(1, 2, 3);

        List<Integer> nuevaLista = lista.map(e -> e + 1);
        assertEquals(9, nuevaLista.sum().intValue());
    }

    @Test
    public void filter() {
        List<Integer> lista = List.of(1, 2, 3);

        List<Integer> pares = lista.filter(e -> e % 2 == 0);
        assertEquals(1, pares.size());
    }

    @Test
    public void list() {
        List<Integer> lista = List.of(1, 2, 3, 4, 5);

        Integer total = lista
          .filter(n -> n < 3)
          .map(n -> n + n)
          .sum().intValue();

        Integer resultado = 6;

        assertEquals(resultado, total);
    }
}
