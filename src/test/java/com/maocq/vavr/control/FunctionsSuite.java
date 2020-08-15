package com.maocq.vavr.control;

import com.maocq.Persona;
import io.vavr.*;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Predicate;

import static io.vavr.API.None;
import static io.vavr.API.Some;
import static org.junit.Assert.assertEquals;

public class FunctionsSuite {

    @Test
    public void testFunciones() {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        assertEquals(Integer.valueOf(3), sum.apply(1, 2));

        /*
            val sum: (Int, Int) => Int = (a, b) => a + b
            sum(2 ,3)
         */
    }

    @Test
    public void testCompose() {
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2 = multiplyByTwo.compose(plusOne);
        // f(g(x))

        assertEquals(Integer.valueOf(6), add1AndMultiplyBy2.apply(2));
    }

    @Test
    public void testAndThen() {
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        Integer resultado = 6;
        assertEquals(resultado, add1AndMultiplyBy2.apply(2));
    }

    @Test
    public void testAnd() {
        Predicate<Persona> mayorEdad = p -> p.getEdad() >= 18;
        Predicate<Persona> noSuperaLimiteEdad = p -> p.getEdad() <= 65;
        Predicate<Persona> superIngresoMinimo = p -> p.getSalario().compareTo(new BigDecimal("100")) > 0;

        Predicate<Persona> validacion = mayorEdad.and(noSuperaLimiteEdad).and(superIngresoMinimo);

        List<Persona> personas = List.of(
          new Persona("A", 70, new BigDecimal("200")),
          new Persona("B", 20, new BigDecimal("150")),
          new Persona("C", 45, new BigDecimal("90")));

        List<Persona> filtrados = personas.filter(validacion);

        assertEquals(1, filtrados.size());
        assertEquals("B", filtrados.head().getNombre());
    }

    @Test
    public void testOr() {
        Predicate<Persona> esB = p -> p.getNombre().equals("B");
        Predicate<Persona> esC = p -> p.getNombre().equals("C");

        Predicate<Persona> validacion = esB.or(esC);

        List<Persona> personas = List.of(
          new Persona("A", 70, new BigDecimal("200")),
          new Persona("B", 20, new BigDecimal("150")),
          new Persona("C", 45, new BigDecimal("90")));

        List<Persona> filtrados = personas.filter(validacion);

        assertEquals(2, filtrados.size());
        assertEquals("C", filtrados.last().getNombre());
    }

    @Test
    public void lifting() {
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);
        //Function2<Integer, Integer, Try<Integer>> safeDiv = Function2.liftTry(divide);

        //Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift((a, b) -> a / b);

        Option<Integer> r1 = safeDivide.apply(4, 2);
        assertEquals(Some(2), r1);

        Option<Integer> r2 = safeDivide.apply(4, 0);
        assertEquals(None(), r2);
    }

    @Test
    public void currying() {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2);

        Integer resultado1 = 4;
        assertEquals(resultado1, add2.apply(2));

        Integer resultado2 = 3;
        assertEquals(resultado2, add2.apply(1));
    }

    @Test
    public void memoization() {
        Function0<Double> hashCache = Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        assertEquals(randomValue1, randomValue2, 0);
    }

    @Test
    public void tupla() {
        Tuple2<String, Integer> tupla = Tuple.of("Mauricio", 23);

        Integer resultado = 23;
        assertEquals("Mauricio", tupla._1);
        assertEquals(resultado, tupla._2);
    }
}
