package com.maocq.vavr.control;

import io.vavr.control.Option;
import org.junit.Test;

import java.util.Optional;

import static io.vavr.API.$;
import static io.vavr.API.None;
import static io.vavr.Patterns.*;
import static org.junit.Assert.assertEquals;
import static io.vavr.API.*;

public class OptionSuite {

    @Test
    public void testOption() {
        Option<Integer> option = Option.of(3);
        Option<Integer> none = None();
        Option<Integer> optionNull = Option.of(null);

        assertEquals("", Some(3), option);
        assertEquals("", None(), none);
        assertEquals("", None(), optionNull);
    }

    @Test
    public void testOptionFromNull(){
        Option<String> optionNull = Option.of(null);

        assertEquals(optionNull, None());
    }

    @Test
    public void testOptionFromOptional(){
        Optional optional = Optional.of(1);
        Option option = Option.ofOptional(optional);

        assertEquals("", Some(1), option);
    }

    @Test
    public void testOptionMap() {
        Option<String> valor = Option.of("ok");
        Option<String> nuevoValor = valor.map(it -> it + ".");

        assertEquals("", Some("ok."), nuevoValor);

        Option<String> valor2 = None();
        Option<String> nuevoValor2 = valor2.map(it -> it + ".");

        assertEquals("", None(), nuevoValor2);
    }

    @Test
    public void testOptionFlatMap() {
        Option<String> valor = Option.of("ok");
        Option<String> nuevoValor = valor.flatMap(it -> Option.of(it + "."));

        assertEquals("", Some("ok."), nuevoValor);

        Option<String> valor2 = None();
        Option<String> nuevoValor2 = valor2.map(it -> it + ".");

        assertEquals("", None(), nuevoValor2);
    }

    @Test
    public void testOptionWithFilter() {
        Option<Integer> option = Option.of(3);

        assertEquals("", Some(3), option.filter(it -> it >= 3));
        assertEquals("", None(), option.filter(it -> it > 3));
    }

    @Test
    public void testPatternMatch() {
        Option<Integer> numero = Option(1);

        String resultado = Match(numero).of(
          Case($Some($()),"defined"),
          Case($None(),"empty")
        );

        assertEquals("", "defined", resultado);
    }

    @Test
    public void testGetOrElse(){
        Option<String> option = Option.of("Hello!");
        Option<String> none = None();

        assertEquals("", "Hello!", option.getOrElse("Goodbye!"));
        assertEquals("", "Goodbye!", none.getOrElse("Goodbye!"));
    }

    @Test
    public void testWhenMethod(){
        Option<String> valido = Option.when(true, "Good!");
        Option<String> invalido = Option.when(false, "Bad!");
        assertEquals("", Some("Good!"), valido);
        assertEquals("", None(), invalido);
    }
}
