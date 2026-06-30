package com.example.filmorate.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

/**
 * Валидатор для проверки даты релиза фильма.
 * Проверяет, что дата не раньше MIN_RELEASE_DATE.
 * <p>
 * Используется совместно с аннотацией {@link ValidReleaseDate}.
 * Если дата равна null, валидация пропускается (проверяется отдельно через {@NotNull}).
 *
 * @see ValidReleaseDate
 * @see ConstraintValidator
 */

public class ReleaseDateValidator implements ConstraintValidator<ValidReleaseDate, LocalDate> {

    // Константа с минимальной допустимой датой
    private static final LocalDate MIN_RELEASE_DATE = LocalDate.of(1895, 12, 28);

    @Override
    public void initialize(ValidReleaseDate constraintAnnotation) {
        // Метод инициализации (можно оставить пустым)
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        // Если дата null - пропускаем (проверка null отдельно через @NotNull)
        if (date == null) {
            return true;
        }

        // Проверяем, что дата не раньше 28 декабря 1895 года
        return !date.isBefore(MIN_RELEASE_DATE);
    }
}