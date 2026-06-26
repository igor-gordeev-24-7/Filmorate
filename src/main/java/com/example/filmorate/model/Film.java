package com.example.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private Integer id;
    @NotBlank(message = "Название фильма не может быть пустым")
    @Size(min = 1, max = 100, message = "Название фильма должно быть от 1 до 100 символов")
    private String name;
    @Size(max = 50, min = 5)
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @Positive
    private Double duration;
}
