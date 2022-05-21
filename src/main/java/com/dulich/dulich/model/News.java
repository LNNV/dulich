package com.dulich.dulich.model;

import java.time.LocalDateTime;

import javax.persistence.*;
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
    private String topic;

    @Column(name = "intro", nullable = false)
    private String intro;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "source", nullable = false)
    private String source;
    
    @Column(name = "posted_at", nullable = false)
    private LocalDateTime postedAt;
}
