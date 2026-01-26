package com.advance.common.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @AfterEach
    void clear() {
        System.out.println("테스트 끝");
    }

    // 테스트 작성 팁 !!
    @Test
    @DisplayName("add_2더하기3_결과5")
    void add() {
        // Given
        int num1 = 2;
        int num2 = 3;

        // When
        int result = calculator.add(2, 3);

        // Then
        assertEquals(5, result);
    }

    @Test
    void subtract() {
        int result = calculator.subtract(5, 3);
        assertEquals(2,result);
    }

    @Test
    @DisplayName("나누기 실패 케이스 테스트")
    void divide_fail() {
        // Given
        int num1 = 10;
        int num2 = 0;

        // When & Then
        assertThrows(ArithmeticException.class,
                () -> calculator.divide(num1, num2));
    }

    @Test
    @DisplayName("나누기 성공 케이스 테스트")
    void divide_success() {
        int result = calculator.divide(10, 2);
        assertEquals(5, result);
    }
}