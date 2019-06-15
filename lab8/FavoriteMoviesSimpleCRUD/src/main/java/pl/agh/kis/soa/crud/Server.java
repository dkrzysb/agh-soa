package pl.agh.kis.soa.crud;

import io.swagger.jaxrs.config.BeanConfig;
import pl.agh.kis.soa.crud.services.MoviesService;
import pl.agh.kis.soa.crud.services.RedirectTest;
import pl.agh.kis.soa.crud.services.UsersService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class Server extends Application {
    public Server() {
        initSwagger();
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();

        classes.add(UsersService.class);
        classes.add(MoviesService.class);
        classes.add(RedirectTest.class);
        classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return classes;
    }

    private void initSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/favoritemovies/api");
        beanConfig.setResourcePackage(pl.agh.kis.soa.crud.services.UsersService.class.getPackage().getName());
        beanConfig.setTitle("RESTEasy, Swagger and Swagger UI Example");
        beanConfig.setDescription("Sample RESTful API built using RESTEasy, Swagger and Swagger UI");
        beanConfig.setScan(true);
    }
}