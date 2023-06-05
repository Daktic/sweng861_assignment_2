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
        // Testing Metric converts to Imperial

        String inputMetric = "10 cm\n";
        String expectedOutputMetric = """
                Please Enter the input value followed by the unit:
                10 cm is:
                3.9e+03 mil
                3.9 inch
                0.33 ft
                0.11 yard
                6.2e-05 mile
                \s"""
        ;

        String normalizedExpectedOutputMetric = normalizeExpectedOutput(expectedOutputMetric);

        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            System.setIn(new ByteArrayInputStream(inputMetric.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            UnitsConvertor.main(null);

            assertEquals(normalizedExpectedOutputMetric, outContent.toString());
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }

        // Imperial
        String inputImperial = "10 feet\n";
        String expectedOutputImperial = """
                Please Enter the input value followed by the unit:
                10 feet is:
                3048.000000 mm
                304.800000 cm
                3.048000 m
                0.003048 km
                \s"""
                ;

        String normalizedExpectedOutput = normalizeExpectedOutput(expectedOutputImperial);
        
        try {
            System.setIn(new ByteArrayInputStream(inputImperial.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            UnitsConvertor.main(null);

            assertEquals(normalizedExpectedOutput, outContent.toString());
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    // Tests that the proper response is "Invalid input" if the code fails.
    @Test
    void main_InvalidInput_Metric() {

        String input = "1 boot\n";
        String expectedOutput = """
                Please Enter the input value followed by the unit:
                Invalid input
                \s""";

        String normalizedExpectedOutput = normalizeExpectedOutput(expectedOutput);

        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            UnitsConvertor.main(null);

            assertEquals(normalizedExpectedOutput, outContent.toString());
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }

        String noInput = "\n";
        String expectedOutputNoInput = """
                Please Enter the input value followed by the unit:
                Invalid input
                \s""";

        String normalizedExpectedOutputNoInput = normalizeExpectedOutput(expectedOutputNoInput);


        try {
            System.setIn(new ByteArrayInputStream(noInput.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            UnitsConvertor.main(null);

            assertEquals(normalizedExpectedOutputNoInput, outContent.toString());
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    // Tests invalid input in the methods and makes sure it throws an exception.
    @Test
    void invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> UnitsConvertor.toMm(1, "test"), "Illegal unit value.");
        assertThrows(IllegalArgumentException.class, () -> UnitsConvertor.toMil(1, "test"), "Illegal unit value.");
    }

    //Tests if 1 meter unit is converted to mil
    @Test
    void toMil() {
        int numberInput = 1;
        assertEquals(39.3701, UnitsConvertor.toMil(numberInput ,"mm"));
        assertEquals(39.3701, UnitsConvertor.toMil(numberInput ,"millimeter"));
        assertEquals(393.701, UnitsConvertor.toMil(numberInput ,"cm"));
        assertEquals(393.701, UnitsConvertor.toMil(numberInput ,"centimeter"));
        assertEquals(39370.1, UnitsConvertor.toMil(numberInput ,"m"));
        assertEquals(39370.1, UnitsConvertor.toMil(numberInput ,"meter"));
        assertEquals(3.93701E7, UnitsConvertor.toMil(numberInput ,"km"));
        assertEquals(3.93701E7, UnitsConvertor.toMil(numberInput ,"kilometer"));
    }

    // Tests 1 unit is converted to the proper number.
    @Test
    void toMm() {
        int numberInput = 1;
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
}