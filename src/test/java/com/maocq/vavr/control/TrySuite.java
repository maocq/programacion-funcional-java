package com.maocq.vavr.control;

import io.vavr.control.Try;
import org.junit.Test;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Failure;
import static io.vavr.Patterns.$Success;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrySuite {

    @Test
    public void testTrytoSuccesAndFailure(){
        Try<Integer> success = Try.of(() -> 15 / 5 );
        Try<Integer> failure = Try.of(() -> 15 / 0 );

        assertEquals(Success(3), success);
        assertTrue(failure.isFailure());
    }

    @Test
    public void testTryToPatternMatching() {
        Try<Integer> success = Try.of(() -> 15 / 5 );

        String resultado = Match(success).of(
          Case($Success($()), "Ok"),
          Case($Failure($()), "Error"));

        assertEquals("Ok", resultado);
    }

    @Test
    public void testTryToRecoverException() {
        Try<Integer> success = Try.of(() -> 15 / 5 ).recover(ArithmeticException.class, x -> 0);
        Try<Integer> failed = Try.of(() -> 15 / 0 ).recover(ArithmeticException.class, x -> 0);

        assertEquals(Success(3), success);
        assertEquals(Success(0), failed);
    }

    @Test
    public void testTryToRecover() {
        Try<Integer> success = Try.of(() -> 15 / 5 ).recover(x -> 0);
        Try<Integer> failed = Try.of(() -> 15 / 0 ).recover(x -> 0);

        assertEquals(Success(3), success);
        assertEquals(Success(0), failed);
    }

    @Test
    public void testMap() {
        Try<Integer> success = Try.of(() -> 15 / 5);

        Try<Integer> successMap = success.map(it -> it + 2);

        assertEquals(Success(5), successMap);

        Try<Integer> failed = Try.of(() -> 15 / 0);
        Try<Integer> failedMap = failed.map(it -> it + 2);

        assertTrue(failedMap.isFailure());
    }

    @Test
    public void testFlatMap() {
        Try<Integer> resultado = restar(3).flatMap(this::dividir);
        assertEquals(Success(5), resultado);

        Try<Integer> resultadoFallido = restar(1).flatMap(this::dividir);
        assertTrue(resultadoFallido.isFailure());


        Try<Integer> success = Try.of(() -> 15 / 5);
        Try<Integer> successFlatMap = success.flatMap(it -> Try.of(() -> it + 2));

        assertEquals(Success(5), successFlatMap);

        Try<Integer> failed = Try.of(() -> 15 / 5);
        Try<Integer> failedFlatMap = failed.map(it -> it/0);

        assertTrue(failedFlatMap.isFailure());
    }

    private Try<Integer> restar(Integer numero) {
        return Try.of(() -> libreriaExterna(numero));
    }

    private Try<Integer> dividir(Integer numero) {
        return Try.of(() -> 10 / numero);
    }

    private Integer libreriaExterna(Integer numero) {
        if (numero % 2 == 0)
            throw new IllegalArgumentException("Numero no valido");

        return numero - 1;
    }

    @Test
    public void testFlatMapRecover() {
        Try<Integer> resultadoFallido = restar(4)
          .flatMap(this::dividir);

        assertTrue(resultadoFallido.isFailure());

        Try<Integer> resultado =
          restar(4).recover(e -> 1)
          .flatMap(this::dividir);

        assertEquals(Success(10), resultado);
    }

    @Test
    public void testFilterToTry() {
        Try<Integer> myFilterSuccess =  Try.of(()-> 12/2 ).filter(x -> x%3==0);
        Try<Integer> myFilterFailure =  Try.of(()-> 12/0 ).filter(x -> x%3==0);

        assertEquals(Success(6), myFilterSuccess);
        assertTrue(myFilterFailure.isFailure());
    }
}
