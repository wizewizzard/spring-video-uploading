package edu.wz.wztube.controller;

import edu.wz.wztube.model.Video;
import edu.wz.wztube.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/video")
@AllArgsConstructor
public class VideoController {
    
    private final VideoService videoService;
    
    @GetMapping(value = "{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getVideoContentsByName(@PathVariable String name){
        Optional<Video> videoByName = videoService.findVideoByName(name);

        if(videoByName.isPresent())
            return ResponseEntity.ok().body(new ByteArrayResource(videoByName.get().getContents()));
        else
            return ResponseEntity.notFound().build();
        
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<String>> getVideoNames(){
        return ResponseEntity.ok().body(videoService.getVideoNames());
    }
    
    @PostMapping
    public ResponseEntity<?> uploadVideo(@RequestParam("name") String name, @RequestParam("file") MultipartFile file){
        Video video = videoService.uploadVideo(name, file);
        return ResponseEntity.ok(URI.create(String.format("/api/video/%s", video.getName())));
    }
}
