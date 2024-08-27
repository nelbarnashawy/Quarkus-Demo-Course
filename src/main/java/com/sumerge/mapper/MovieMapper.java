package com.sumerge.mapper;

import com.sumerge.dto.MovieDTO;
import com.sumerge.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "cdi",
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE
)
public interface MovieMapper {

    // DAO to DTO
    MovieDTO toDTO(Movie movie);

    // DTO to DAO
    @Mapping(target = "id", ignore = true)
    Movie toDAO(MovieDTO movieDTO);

    // Merge
    @Mapping(target = "id", ignore = true)
    void merge(@MappingTarget Movie target, Movie source);

}
