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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "tourid", nullable = false)
    private Tour tour;

    @Column(name = "bookat", nullable = false)
    private LocalDateTime bookat;

    @Column(name = "payment", nullable = false)
    @Nationalized 
    private String payment;

}
