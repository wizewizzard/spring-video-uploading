package edu.wz.wztube.service;

import edu.wz.wztube.model.Video;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoService {
    Mono<Video> findVideoByName(String name);
    
    Mono<?> uploadVideo(String videoName, FilePart filePart);
    
    Flux<String> getVideoNames();
}
