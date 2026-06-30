package com.example.filmorate.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Валидатор для проверки даты релиза фильма.
 * Проверяет, что дата не раньше указанной минимальной даты.
 * <p>
 * Используется совместно с аннотацией {@link ValidReleaseDate}.
 *
 * @see ValidReleaseDate
 * @see ConstraintValidator
 */

public class ReleaseDateValidator implements ConstraintValidator<ValidReleaseDate, LocalDate> {
    private LocalDate minDate;
    private String minDateString;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public void initialize(ValidReleaseDate constraintAnnotation) {
        minDateString = constraintAnnotation.minDate();
        String pattern = constraintAnnotation.pattern();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            minDate = LocalDate.parse(minDateString, formatter);
        } catch (DateTimeParseException e) {
            minDate = LocalDate.of(1895, 12, 28);
            minDateString = "1895-12-28";
        }
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null) {
            return true;
        }

        if (date.isBefore(minDate)) {
            // Используем сообщение из аннотации
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    context.getDefaultConstraintMessageTemplate()
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}