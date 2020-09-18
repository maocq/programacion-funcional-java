package com.maocq;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.asynchttpclient.Dsl.*;



public class Testtt {

    public static void main(String[] args) throws InterruptedException {
        AsyncHttpClient asyncHttpClient = asyncHttpClient();
        ExecutorService ec = Executors.newCachedThreadPool();

        System.out.println("Iniciando");
        Thread.sleep(15000);

        Future.traverse(ec, List.range(0, 100), e -> {
            return Future.fromJavaFuture(
              asyncHttpClient.prepareGet("https://jsonplaceholder.typicode.com/todos/1").execute()
            );
        });

        System.out.println("Hello");
    }

}
