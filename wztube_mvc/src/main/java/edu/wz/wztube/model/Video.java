package edu.wz.wztube.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Video {
    @Id
    @SequenceGenerator(name = "videoIdGenerator", sequenceName = "video_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videoIdGenerator")
    private Long id;
    
    @EqualsAndHashCode.Include
    @Column(nullable = false, unique = true)
    private String name;
    
    @Lob
    @Column(nullable = false)
    private byte[] contents;
    
}
