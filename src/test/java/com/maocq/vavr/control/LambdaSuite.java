package com.maocq.vavr.control;

import com.maocq.InterfaceGenerica;
import com.maocq.MyInterface;
import com.maocq.Persona;
import io.vavr.Function3;
import io.vavr.collection.List;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class LambdaSuite {

    @Test
    public void testClaseAnonima() {

        MyInterface claseAnonima = new MyInterface() {

            public String apply(String arg) {
                return arg + ".";
            }
        };

        assertEquals("Ok.", claseAnonima.apply("Ok"));
    }

    @Test
    public void testLambda() {
        MyInterface lamda = texto -> texto + ".";
        assertEquals("Ok.", lamda.apply("Ok"));
    }

    @Test
    public void testGenericos() {
        InterfaceGenerica lamda = texto -> texto + ".";
        assertEquals("Ok.", lamda.apply("Ok"));
    }

    @Test
    public void testReferenciaMetodo() {
        Function<String, String> metodo = this::metodo;
        assertEquals("Ok.", metodo.apply("Ok"));

        List<String> lista = List.of("1", "2", "3").map(this::metodo);
        assertEquals("1.", lista.head());

        Function3<String, Integer, BigDecimal, Persona> constructor = Persona::new;

        Persona persona = constructor.apply("A", 18, new BigDecimal("100"));
        assertEquals("A", persona.getNombre());
    }

    private String metodo(String texto) {
        return texto + ".";
    }
}
