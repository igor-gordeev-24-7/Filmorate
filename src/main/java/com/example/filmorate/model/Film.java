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
    @Size(min = 1, max = 30, message = "Название фильма должно быть от 1 до 30 символов")
    private String name;
    @NotBlank(message = "Описание фильма не может быть пустым")
    @Size(min = 5 ,max = 50, message = "Название фильма должно быть от 1 до 100 символов")
    private String description;
    @NotNull
    @ValidReleaseDate
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @Positive(message = "Длительность должна быть положительным числом")
    @DecimalMin(value = "1.0", message = "Минимальная длительность - 1 минута")
    @DecimalMax(value = "600.0", message = "Максимальная длительность - 600 минут")
    private Double duration;
}
