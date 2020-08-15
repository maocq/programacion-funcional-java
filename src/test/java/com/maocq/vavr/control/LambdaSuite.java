package com.maocq.vavr.control;

import com.maocq.InterfaceGenerica;
import com.maocq.MyInterface;
import io.vavr.collection.List;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class LambdaSuite {

    @Test
    public void claseAnonima() {

        MyInterface claseAnonima = new MyInterface() {

            public String apply(String arg) {
                return arg + ".";
            }
        };

        assertEquals("", "Ok.", claseAnonima.apply("Ok"));
    }

    @Test
    public void lambda() {
        MyInterface lamda = texto -> texto + ".";
        assertEquals("", "Ok.", lamda.apply("Ok"));
    }

    @Test
    public void genericos() {
        InterfaceGenerica lamda = texto -> texto + ".";
        assertEquals("", "Ok.", lamda.apply("Ok"));
    }

    @Test
    public void referenciaMetodo() {
        Function<String, String> metodo = this::metodo;
        assertEquals("", "Ok.", metodo.apply("Ok"));

        List<String> lista = List.of("1", "2", "3").map(this::metodo);
        assertEquals("", "1.", lista.head());
    }

    private String metodo(String texto) {
        return texto + ".";
    }
}
