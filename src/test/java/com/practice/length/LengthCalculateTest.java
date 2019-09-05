package com.practice.length;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LengthCalculateTest {

    @Test
    public void should_throw_if_input_is_null() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> LengthCalculate.calculate(null));
        assertEquals("Formula is null", exception.getMessage());
    }

    @Test
    public void should_throw_if_format_is_not_correct_0() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> LengthCalculate.calculate("1m+2cm"));
        assertEquals("Format is not correct0", exception.getMessage());

    }

    @Test
    public void should_throw_if_format_is_not_correct_1() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> LengthCalculate.calculate("1m + 3cm - 2"));
        assertEquals("Format is not correct1", exception.getMessage());
    }

    @Test
    public void should_throw_if_format_is_not_correct_2() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> LengthCalculate.calculate("1m + 2 * 3"));
        assertEquals("Format is not correct1", exception.getMessage());
    }

    @Test
    public void should_throw_if_format_is_not_correct_3() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> LengthCalculate.calculate("1m + 2cm / 3cm"));
        assertEquals("Format is not correct2", exception.getMessage());
    }

    @Test
    public void should_throw_if_divisor_is_0() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> LengthCalculate.calculate("1m / 0 + 2cm / 2"));
        assertEquals("Divisor is 0", exception.getMessage());
    }

    @Test
    public void should_get_result() {
        String result = LengthCalculate.calculate("1m / 4 + 10cm * 3 - 5mm");
        assertEquals("545mm = 54.5cm = 0.545m", result);
    }
}
