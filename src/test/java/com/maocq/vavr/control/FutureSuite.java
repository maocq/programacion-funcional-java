package com.maocq.vavr.control;

import io.vavr.concurrent.Future;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class FutureSuite {

    @Test
    public void executor() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            sleep(2000);
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        executor.submit(() -> {
            sleep(2000);
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

        System.out.println("End");
        sleep(4000);
    }

    @Test
    public void testFuture() {
        Future<String> future = Future.of(() -> {
            Thread.sleep(500);
            return "Ok";
        });

        assertEquals("", "Ok", future.get());
    }

    @Test(expected = Error.class)
    public void testFutureWithError() {
        Future<String> future = Future.of(() -> {
            throw new Error("Failure");
        });
        future.get();
    }

    @Test
    public void testFutureMap() {
        Future<String> future = Future.of(() -> "Ok");
        Future<Integer> futureMap = future.map(String::length);
        Integer numero = 2;

        assertEquals("", numero, futureMap.get());
    }

    @Test
    public void testFutureFlatMap() {
        Future<String> future = Future.of(() -> "Ok");
        Future<Integer> futureFlatMap = future.flatMap(it -> Future.of(() -> it.length()));
        Integer numero = 2;

        assertEquals("", numero, futureFlatMap.get());
    }

    @Test
    public void testFutureRecover() {
        Future<String> future = Future.of(() -> {
            throw new Error("Failure =(");
        });
        Future<String> futureRecover = future.recover(error -> error.getMessage());

        assertEquals("", "Failure =(", futureRecover.get());
    }

    private Future<Integer> futures(String mensaje) {
        return Future.of(() -> {
            System.out.println("Inicio " + mensaje);
            sleep(5000);
            System.out.println("Fin " + mensaje);
            return 1;
        });
    }

    @Test
    public void testFutures() {
        Future<Integer> future1 = futures("Futuro 1");
        Future<Integer> future2 = futures("Futuro 2");
        Future<Integer> future3 = futures("Futuro 3");

        Future<Integer> resultado = future1
          .flatMap(uno -> future2
          .flatMap(dos -> future3
          .map(tres -> uno + dos + tres)));

        Integer numero = 3;

        assertEquals("", numero, resultado.get());
    }



    private void sleep(Integer tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
