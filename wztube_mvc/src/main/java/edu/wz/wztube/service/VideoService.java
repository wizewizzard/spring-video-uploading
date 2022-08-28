package edu.wz.wztube.service;

import edu.wz.wztube.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    Optional<Video> findVideoByName(String name);
    
    Video uploadVideo(String videoName, MultipartFile content);
    
    List<String> getVideoNames();
}
