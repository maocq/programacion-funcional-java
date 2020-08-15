package com.maocq.vavr.control;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListJavaSuite {

    @Test
    public void list() {
        List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5);


        List<Integer> filtro = new ArrayList<>();
        for (Integer i : lista) {
            if (i < 3) {
                filtro.add(i);
            }
        }

        List<Integer> trasformar = new ArrayList<>();
        for (Integer i : filtro) {
            trasformar.add(i + i);
        }

        Integer total = 0;
        for (Integer i : trasformar) {
            total += i;
        }

        Integer resultado = 6;

        assertEquals("", resultado, total);
    }
}
