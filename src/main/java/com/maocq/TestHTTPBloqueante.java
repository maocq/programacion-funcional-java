package com.maocq;

import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test222 {

    public static void main(String[] args) throws InterruptedException, IOException {
        OkHttpClient client = new OkHttpClient();
        ExecutorService ec = Executors.newCachedThreadPool();


        System.out.println("Iniciando");
        Thread.sleep(15000);

        Future.traverse(List.range(0, 100), e -> {
            return Future.of(ec, () -> {
                Request request = new Request.Builder()
                  .url("https://jsonplaceholder.typicode.com/todos/1")
                  .build();

                try (Response response = client.newCall(request).execute()) {
                    String s = response.body().string();
                    System.out.println(s);
                }
                return e;
            });
        });

        System.out.println("Hello");

    }

}
