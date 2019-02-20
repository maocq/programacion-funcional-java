package com.maocq.vavr.control;

import io.vavr.*;
import io.vavr.control.Option;
import org.junit.Test;

import static io.vavr.API.None;
import static io.vavr.API.Some;
import static org.junit.Assert.assertEquals;

public class FunctionsSuite {

    @Test
    public void funciones() {
        Function2<Integer, Integer, Integer> sum2 = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };

        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

        Integer resultado = 3;
        assertEquals("", resultado, sum.apply(1, 2));

        /*
            val sum: (Int, Int) => Int = (a, b) => a + b
            sum(2 ,3)
         */
    }

    @Test
    public void composition() {
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        Integer resultado = 6;
        assertEquals("", resultado, add1AndMultiplyBy2.apply(2));
    }

    @Test
    public void lifting() {
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        Option<Integer> r1 = safeDivide.apply(4, 2);
        assertEquals("", Some(2), r1);

        Option<Integer> r2 = safeDivide.apply(4, 0);
        assertEquals("", None(), r2);
    }

    @Test
    public void currying() {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2);

        Integer resultado1 = 4;
        assertEquals("", resultado1, add2.apply(2));

        Integer resultado2 = 3;
        assertEquals("", resultado2, add2.apply(1));
    }

    @Test
    public void memoization() {
        Function0<Double> hashCache = Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        assertEquals("", randomValue1, randomValue2, 0);
    }

    @Test
    public void tupla() {
        Tuple2<String, Integer> tupla = Tuple.of("Mauricio", 23);

        Integer resultado = 23;
        assertEquals("", "Mauricio", tupla._1);
        assertEquals("", resultado, tupla._2);
    }
}
