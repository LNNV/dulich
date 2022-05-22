package com.dulich.dulich.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "topic", nullable = false)
    @Nationalized 
    private String topic;

    @Column(name = "intro", nullable = false)
    @Nationalized 
    private String intro;

    @Column(name = "content", nullable = false)
    @Nationalized 
    private String content;

    @Column(name = "source", nullable = false)
    @Nationalized 
    private String source;
    
    @Column(name = "posted_at", nullable = false)
    private LocalDateTime postedAt;
}
