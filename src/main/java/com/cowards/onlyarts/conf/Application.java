package com.cowards.onlyarts.conf;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class Application extends ResourceConfig {
    public Application(){
        packages("com.cowards.onlyarts.resources");
    }
}

