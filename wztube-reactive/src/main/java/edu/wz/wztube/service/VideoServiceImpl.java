package edu.wz.wztube.service;

import edu.wz.wztube.model.Video;
import edu.wz.wztube.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService {
    
    private final VideoRepository videoRepository;
    
    @Override
    public Mono<Video> findVideoByName(String name) {
        return videoRepository.findVideoByName(name);
    }
    
    @Override
    public Mono<Video> uploadVideo(String videoName, FilePart filePart) {
        Video video = new Video();
        video.setName(videoName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        video.setContents(byteArrayOutputStream.toByteArray());
        return videoRepository.save(video);
    }
    
    @Override
    public Flux<String> getVideoNames() {
        return videoRepository.getVideoNames();
    }
}
