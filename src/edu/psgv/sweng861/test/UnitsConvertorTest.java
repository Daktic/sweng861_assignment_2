package edu.psgv.sweng861.test;

import edu.psgv.sweng861.main.UnitsConvertor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class UnitsConvertorTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
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
    void invalidInputNumber() {
        int invalidNumber = -1;
        assertNotEquals(24,UnitsConvertor.toMm(invalidNumber ,"mil"));
    }
}