package calculator;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RomanNumeralTest {

    @ParameterizedTest
    @CsvSource(delimiter = '=', textBlock = """
        I = 1
        II = 2
        III = 3
        IV = 4
        V = 5
        VI = 6
        VII = 7
        VIII = 8
        IX = 9
        X = 10
        """)
    void roman_to_arabic(String input, int expected) {
        assertEquals(expected, Solution.romanToArabic(input));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/arabicToRoman.csv", delimiter = '=')
    void arabic_to_roman(int input, String expected) {
        assertEquals(expected, Solution.arabicToRoman(input));
    }
}
