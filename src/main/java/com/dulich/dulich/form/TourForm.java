package com.dulich.dulich.form;


import lombok.Data;

@Data
public class TourForm {
    private String name;
    private String picture;
    private String startday;
    private String endday;
    private String startplace;
    private String description;
    private long numseat;
    private double cost;

}
