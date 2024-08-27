package com.sumerge.mapper;

import com.sumerge.dto.MovieDTO;
import com.sumerge.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface MovieMapper {

    // DAO to DTO
    MovieDTO toDTO(Movie movie);

    // DTO to DAO
    Movie toDAO(MovieDTO movieDTO);

}
