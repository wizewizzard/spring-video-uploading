package edu.wz.wztube.repository;

import edu.wz.wztube.model.Video;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoRepository extends ReactiveCrudRepository<Video, Long> {
    Mono<Video> findVideoByName(String name);
    
    @Query("SELECT v.name FROM Video v")
    Flux<String> getVideoNames();
}
