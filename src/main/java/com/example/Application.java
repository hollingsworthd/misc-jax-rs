package com.example;

import io.micronaut.http.annotation.Body;
import io.micronaut.runtime.Micronaut;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/example")
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @POST
    @Path("/broken")
    @Consumes(MediaType.APPLICATION_JSON)
    public ExampleDto broken(ExampleDto body) {
        return new ExampleDto("output " + body.getSomeData());
    }

    @POST
    @Path("/workaround")
    @Consumes(MediaType.APPLICATION_JSON)
    public ExampleDto workaround(@Body ExampleDto body) {
        return new ExampleDto("output " + body.getSomeData());
    }

    public static class ExampleDto {
        private final String someData;

        public ExampleDto() {
            this.someData = null;
        }

        public ExampleDto(String someData) {
            this.someData = someData;
        }

        public String getSomeData() {
            return someData;
        }
    }
}
