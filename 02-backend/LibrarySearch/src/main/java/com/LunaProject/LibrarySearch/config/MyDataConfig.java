package com.LunaProject.LibrarySearch.config;

import com.LunaProject.LibrarySearch.entity.Book;
import com.LunaProject.LibrarySearch.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:3000";
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors){
        HttpMethod[] theUnsupportedMethods={
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.PUT,
                HttpMethod.DELETE
        };
        config.exposeIdsFor(Book.class);
        config.exposeIdsFor(Review.class);

        disableHttpMethods(Book.class, config, theUnsupportedMethods);
        disableHttpMethods(Review.class,config, theUnsupportedMethods);

        cors.addMapping(config.getBasePath()+"/**")
                .allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
}
