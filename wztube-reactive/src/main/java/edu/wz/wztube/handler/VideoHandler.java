package edu.wz.wztube.handler;

import edu.wz.wztube.model.Video;
import edu.wz.wztube.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@AllArgsConstructor
@Component
public class VideoHandler {
    private final VideoService videoService;
    
    public Mono<ServerResponse> getAllFilms(ServerRequest request) {
        return ServerResponse.ok().body(videoService.getVideoNames(), String.class);
    }
    
    public Mono<ServerResponse> getFilmByName(ServerRequest request) {
        return videoService
                .findVideoByName(request.pathVariable("name"))
                .flatMap(v -> ServerResponse.ok().body(v, Video.class))
                .onErrorResume(e -> ServerResponse.notFound().build());
    }
    
    public Mono<ServerResponse> uploadVideo(ServerRequest request) {
        return request
                .body(BodyExtractors.toMultipartData())
                .flatMap(parts -> {
                    Map<String, Part> singleValueMap = parts.toSingleValueMap();
                    FormFieldPart name = (FormFieldPart) singleValueMap.get("name");
                    FilePart filePart = (FilePart) singleValueMap.get("file");
                    return videoService.uploadVideo(name.value(), filePart);
                })
                .flatMap(v -> ServerResponse.ok().body(Mono.just("Uploaded"), String.class))
                .onErrorResume(e ->
                        ServerResponse.badRequest().body(Mono.just("Failed to upload the video. " + e.getMessage()), String.class));
    }
}
