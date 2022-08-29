package edu.wz.wztube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Video {
    @Id
    private Long id;
    
    private String name;
    
    private byte[] contents;
}
