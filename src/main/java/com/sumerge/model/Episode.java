package com.sumerge.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class Episode {

    private Long id;
    private String name;
    private Integer season;
    private Integer number;
    private String airdate;
    private String airtime;
    private String summary;
    private URL url;

}
