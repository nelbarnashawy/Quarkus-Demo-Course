package com.sumerge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
@Schema(name = "Movie", description = "Movie entity")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(name = "movie_seq", sequenceName = "movies_sequence", allocationSize = 1)
    @Schema(required = true)
    private Long id;
    
    @NotNull
    @Schema(required = true)
    private String name;


}
