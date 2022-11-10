package by.issoft.store.helpers.api;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class HttpServerHelpers {

    private static HttpServer server;

    public static void createServer() {
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.setExecutor(Executors.newCachedThreadPool());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.createContext("/test", new MyHttpHandler()); // создаем контекст
    }

    public static void startServer() {
        server.start();
    }
}
