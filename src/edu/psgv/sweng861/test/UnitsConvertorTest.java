package edu.psgv.sweng861.test;

import edu.psgv.sweng861.main.UnitsConvertor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

class UnitsConvertorTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    private String normalizeExpectedOutput(String expectedOutput) {
        // Replace any platform-specific line separators with a consistent line separator
        expectedOutput = expectedOutput.replaceAll("\\r\\n|\\r|\\n", System.lineSeparator());

        // Remove leading/trailing whitespaces from each line
        String[] lines = expectedOutput.split("\\r?\\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
        }

        // Reconstruct the normalized output
        return String.join(System.lineSeparator(), lines);
    }

    @Test
    void main_ValidInput_Metric() {
        // Arrange
        String input = "10 cm\n";
        String expectedOutput = "Please Enter the input value followed by the unit:\n" +
                "10 cm is:\n" +
                "3.9e+03 mil\n" +
                "3.9 inch\n" +
                "0.33 ft\n" +
                "0.11 yard\n" +
                "6.2e-05 mile\n"
        ;

        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Act
            UnitsConvertor.main(null);

            // Assert
            assertEquals(expectedOutput, outContent.toString());
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    //Tests if 1 meter unit is converted to mil
    @Test
    void toMil() {
        int numberInput = 1;
        /*
        1 m is:
        39370.1 mil
        39 inch
        3.3 ft
        1.1 yard
        0.00062 mile
         */
        //TODO convert all outputs to normalized expected outputs
        assertEquals(39.3701, UnitsConvertor.toMil(numberInput ,"mm"));
        assertEquals(39.3701, UnitsConvertor.toMil(numberInput ,"millimeter"));
        assertEquals(393.701, UnitsConvertor.toMil(numberInput ,"cm"));
        assertEquals(393.701, UnitsConvertor.toMil(numberInput ,"centimeter"));
        assertEquals(39370.1, UnitsConvertor.toMil(numberInput ,"m"));
        assertEquals(39370.1, UnitsConvertor.toMil(numberInput ,"meter"));
        assertEquals(3.93701E7, UnitsConvertor.toMil(numberInput ,"km"));
        assertEquals(3.93701E7, UnitsConvertor.toMil(numberInput ,"kilometer"));
    }

    @Test
    void toMm() {
        int numberInput = 1;
        /*
        1 ft is:
        304.800000 mm
        30.480000 cm
        0.304800 m
        0.000305 km
         */
        assertEquals(0.0254, UnitsConvertor.toMm(numberInput ,"mil"));
        assertEquals(25.4, UnitsConvertor.toMm(numberInput ,"in"));
        assertEquals(25.4, UnitsConvertor.toMm(numberInput ,"inch"));
        assertEquals(304.8, UnitsConvertor.toMm(numberInput ,"ft"));
        assertEquals(304.8, UnitsConvertor.toMm(numberInput ,"foot"));
        assertEquals(304.8, UnitsConvertor.toMm(numberInput ,"feet"));
        assertEquals(914.4, UnitsConvertor.toMm(numberInput ,"yard"));
        assertEquals(1609344.0, UnitsConvertor.toMm(numberInput ,"mi"));
        assertEquals(1609344.0, UnitsConvertor.toMm(numberInput ,"mile"));
    }

    @Test
    void invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            UnitsConvertor.toMm(1, "test");
        }, "Illegal unit value.");
        assertThrows(IllegalArgumentException.class, () -> {
            UnitsConvertor.toMil(1, "test");
        }, "Illegal unit value.");
    }
}