package edu.wz.wztube.service;

import edu.wz.wztube.exception.VideoUploadException;
import edu.wz.wztube.model.Video;
import edu.wz.wztube.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoServiceImpl implements VideoService{
    
    private final VideoRepository videoRepository;
    
    @Transactional
    @Override
    public Optional<Video> findVideoByName(String name) {
        return videoRepository.findVideoByName(name);
    }
    
    @Transactional
    @Override
    public Video uploadVideo(String videoName, MultipartFile content) {
        if(videoRepository.existsVideoByName(videoName))
            throw new VideoUploadException("Video with given name already exists.");
        try{
            Video video = new Video();
            video.setName(videoName);
            video.setContents(content.getBytes());
            videoRepository.save(video);
            return video;
        }
        catch (IOException exception){
            throw new VideoUploadException("Unable to read the file");
        }
    }
    
    @Override
    public List<String> getVideoNames() {
        return videoRepository.getVideoNames();
    }
}
