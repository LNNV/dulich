package com.dulich.dulich.model;

import java.util.Date;


import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import lombok.Data;

@Entity
@Table
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @Nationalized 
    private String name;

    @Column(name = "picture", nullable = false)
    @Nationalized 
    private String picture;

    @Column(name = "startday", nullable = false, columnDefinition = "DATE")
    private Date startday;

    @Column(name = "endday", nullable = false, columnDefinition = "DATE")
    private Date endday;

    @Column(name = "startplace", nullable = false)
    @Nationalized 
    private String startplace;

    @Column(name = "description", nullable = false)
    @Nationalized 
    private String description;

    @Column(name = "numseat", nullable = false)
    private long numseat;

    @Column(name = "cost", nullable = false)
    private double cost;
}
