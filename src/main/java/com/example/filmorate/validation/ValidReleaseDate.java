package com.example.filmorate.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Кастомная аннотация для валидации даты релиза фильма.
 * Проверяет, что дата не раньше 28 декабря 1895 года.
 * Используется совместно с {@link ReleaseDateValidator}.
 */

@Target({ElementType.FIELD})          // Аннотация применяется к полям
@Retention(RetentionPolicy.RUNTIME)   // Аннотация будет доступна во время выполнения
@Constraint(validatedBy = ReleaseDateValidator.class) // Указываем класс-валидатор
@Documented
public @interface ValidReleaseDate {

    // Сообщение об ошибке по умолчанию
    String message() default "Дата релиза не может быть раньше 28 декабря 1895 года";

    // Группы валидации (опционально)
    Class<?>[] groups() default {};

    // Дополнительная информация (опционально)
    Class<? extends Payload>[] payload() default {};
}
