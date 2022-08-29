package edu.wz.wztube.config;

import edu.wz.wztube.handler.VideoHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@AllArgsConstructor
public class RoutesConfig {
    
    private final VideoHandler videoHandler;
    
    @Bean
    RouterFunction<ServerResponse> composedApiRoutes(){
        return route(RequestPredicates.GET("/api/video/{name}"), videoHandler::getFilmByName)
                .and(route(RequestPredicates.GET("/api/video"), videoHandler::getAllFilms))
                .and(route(RequestPredicates.POST("/api/video"), videoHandler::uploadVideo));
    }
}
