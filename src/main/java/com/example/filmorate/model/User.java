package com.example.filmorate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String email;

    private String login;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
