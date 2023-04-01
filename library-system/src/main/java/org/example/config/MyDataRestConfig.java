//package org.example.config;
//
//import org.example.entity.Book;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
//import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//
//@Configuration
//public class MyDataRestConfig implements RepositoryRestConfigurer {
//
//    private String theAllowedOrigins = "http://localhost:3000";
//
//    @Override
//    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
//        HttpMethod[] unsupportedActions = {
//                HttpMethod.POST,
//                HttpMethod.PATCH,
//                HttpMethod.DELETE,
//                HttpMethod.PUT
//        };
//
//        config.exposeIdsFor(Book.class);
//
//        disableHttpMethods(Book.class, config, unsupportedActions);
//
//        /* Configure CORS Mapping */
//        cors.addMapping(config.getBasePath() + "/**")
//                .allowedOrigins(theAllowedOrigins);
//    }
//
//    private void disableHttpMethods(
//            Class<Book> bookClass,
//            RepositoryRestConfiguration config,
//            HttpMethod[] unsupportedActions) {
//
//        config.getExposureConfiguration()
//                .forDomainType(bookClass)
//                .withItemExposure((metaData, httpMethods) ->
//                        httpMethods.disable(unsupportedActions))
//                .withCollectionExposure((metaData, httpMethods) ->
//                        httpMethods.disable(unsupportedActions));
//    }
//}
