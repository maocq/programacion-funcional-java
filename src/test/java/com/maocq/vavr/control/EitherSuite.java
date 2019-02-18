package com.maocq.vavr.control;

import io.vavr.control.Either;
import org.junit.Test;

import static io.vavr.API.None;
import static org.junit.Assert.assertEquals;

public class EitherSuite {

    @Test
    public void testEither() {
        Either<Integer, String> eitherR = Either.right("Right");
        Integer numero = 5;
        Either<Integer, String> eitherL = Either.left(5);

        assertEquals("", "Right", eitherR.get());
        assertEquals("", numero, eitherL.getLeft());
    }

    @Test
    public void testSwapToEither() {
        Either<Integer, String> eitherR = Either.right("Right");
        Either<String, Integer> swap = eitherR.swap();

        assertEquals("", "Right", swap.getLeft());
    }

    @Test
    public void testEitherMap() {
        Either<String, Integer> valor = Either.right(3);
        Either<String, Integer> nuevoValor = valor.map(numero -> numero + 2);

        assertEquals("", Either.right(5), nuevoValor);


        Either<String, Integer> valor2 = Either.left("Left");
        Either<String, Integer> nuevoValor2 = valor2.map(numero -> numero + 2);

        assertEquals("", Either.left("Left"), nuevoValor2);
    }

    @Test
    public void testEitherFlatMap() {
        Either<String, Integer> valor = Either.right(3);
        Either<String, Integer> nuevoValor = valor.flatMap(numero -> Either.right(numero + 2));

        assertEquals("", Either.right(5), nuevoValor);


        Either<String, Integer> valor2 = Either.right(3);
        Either<String, Integer> nuevoValor2 = valor.flatMap(numero -> Either.left("Left"));

        assertEquals("", Either.left("Left"), nuevoValor2);
    }

    @Test
    public void testEitherFilter() {
        Either<String,Integer> valor = Either.right(7);
        assertEquals("", None(), valor.filter(it -> it % 2 == 0));
    }

    @Test
    public void testFoldRight(){
        Either<String, Integer> valor = Either.right(3);
        String nuevoValor = valor.fold(
          left -> "Error",
          right -> "Ok: " + right
        );

        assertEquals("", "Ok: 3", nuevoValor);
    }

    @Test
    public void testFoldLeft(){
        Either<String, Integer> valor = Either.left("Left");
        String nuevoValor = valor.fold(
          left -> "Error",
          right -> "Ok: " + right
        );

        assertEquals("", "Error", nuevoValor);
    }

}
