package com.coremail.cat.restful;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ext.ContextResolver;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FQY on 2016/11/1.
 */
public class Server {

    public static void main(String[] args) {
        try {
            String ip = "";
            ip = InetAddress.getLocalHost().getHostAddress();
            URI BASE_URI = URI.create("http://" + ip + ":8888/Test");

            System.out.println("启动成功！请访问http://" + ip + ":8888/Test");

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, createApp(), false);
            Runtime.getRuntime().addShutdownHook(
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            server.shutdownNow();
                        }
                    }));
            server.start();

            System.out.println(String.format("Application started.%nStop the application using CTRL+C"));

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static ResourceConfig createApp() {
        return new ResourceConfig()
                .packages(Server.class.getPackage().getName())
                .register(createMoxyJsonResolver());
    }

    public static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
        Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
        return moxyJsonConfig.resolver();
    }
}
