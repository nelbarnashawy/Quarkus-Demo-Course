package com.sumerge.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;

@Getter
@Setter
public class TvSerie {

    private Long id;
    private String name;
    private String language;
    private List<String> genres;
    private URL url;
}
