package com.sumerge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {

    @NotBlank(message = "Name is required!")
    private String name;

}
