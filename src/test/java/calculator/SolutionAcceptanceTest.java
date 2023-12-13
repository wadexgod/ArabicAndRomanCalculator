package calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SolutionAcceptanceTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @ParameterizedTest (name = "{0} = {1}")
    @DisplayName("Calculate the expected result on the correct expression")
    @CsvSource(delimiter = '=', textBlock = """
        2 + 2 = 4
        3 + 7 = 10
        V + VII = XII
        1 + 1 = 2
        1 * 1 = 1
        1 * 2 = 2
        III * IX = XXVII
        X / II = V
        10 / 3 = 3
        X - I = IX
        VII * IX = LXIII
        6 * 6 = 36
        5 * 8 = 40
        8 / 2 = 4
        9 - 1 = 8
        X - III = VII
        X * X = C
        X * IX = XC
        X * VIII = LXXX
        X * VII = LXX
        """)
    void correct_expressions(String input, String expected) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Solution.main(null);
        assertEquals(expected, out.toString().stripTrailing());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inputMismatch.csv")
    @DisplayName("Should throw InputMismatchException exception")
    void input_mismatch(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThrows(InputMismatchException.class, () -> Solution.main(null));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/noSuchElement.csv")
    @DisplayName("Should throw NoSuchElementException exception")
    void no_such_element(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThrows(NoSuchElementException.class, () -> Solution.main(null));
    }
}
