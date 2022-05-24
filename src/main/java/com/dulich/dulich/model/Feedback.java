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
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Account account;

    @Column(name = "content", nullable = false)
    @Nationalized 
    private String content;

    @Column(name = "upAt", nullable = false)
    private LocalDateTime upAt;
}
