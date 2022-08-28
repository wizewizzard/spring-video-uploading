package edu.wz.wztube.repository;

import edu.wz.wztube.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findVideoByName(String name);
    
    @Query("SELECT v.name FROM Video v")
    List<String> getVideoNames();
    
    boolean existsVideoByName(String name);
    
}
