package com.maocq.vavr.control;

import io.vavr.collection.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListSuite {

    @Test
    public void list() {
        List<Integer> lista = List.of(1, 2, 3, 4, 5);

        Integer total = lista
          .filter(n -> n < 3)
          .map(n -> n + n)
          .sum().intValue();

        Integer resultado = 6;

        assertEquals("", resultado, total);
    }
}
