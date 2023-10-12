package com.example.taxmanagement.core.domain.valueobject;

import com.example.taxmanagement.core.exception.TaxException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DenominationTest {

    @Test
    @DisplayName("Create denomination normally")
    void createNormallyTest() {
        var expectedDomain = new Denomination(Currency.EURO, BigDecimal.TEN);

        assertNotNull(expectedDomain);
        assertEquals(BigDecimal.TEN, expectedDomain.annualTurnOver());
        assertEquals(Currency.EURO, expectedDomain.currency());
    }

    @Test
    @DisplayName("Create denomination with null turn over")
    void createWithNullAnnualTurnOverTest() {
        assertThrows(TaxException.class, () -> new Denomination(Currency.EURO, null));
    }

    @Test
    @DisplayName("Create denomination with null currency")
    void createWithNullCurrencyTest() {
        assertThrows(TaxException.class, () -> new Denomination(null, BigDecimal.TEN));
    }

    @Test
    @DisplayName("Create denomination normally, then calculate tax percentage")
    void calculateTaxPercentageTest() {
        var domain = new Denomination(Currency.EURO, BigDecimal.valueOf(100));
        var expectedTaxPercentage = domain.taxPercentage(BigDecimal.valueOf(20));

        assertNotNull(expectedTaxPercentage);
        assertEquals(BigDecimal.valueOf(20), expectedTaxPercentage);
    }

    @Test
    @DisplayName("Create denomination normally, then calculate tax percentage with down rounding")
    void calculateTaxPercentageDownRoundTest() {
        var domain = new Denomination(Currency.EURO, BigDecimal.valueOf(34.56));
        var expectedTaxPercentage = domain.taxPercentage(BigDecimal.valueOf(21));

        assertNotNull(expectedTaxPercentage);
        assertEquals(BigDecimal.valueOf(7.25), expectedTaxPercentage);
    }

    @Test
    @DisplayName("Create denomination normally, then calculate tax percentage with null ptc")
    void calculateTaxPercentageWithNullPtc() {
        var domain = new Denomination(Currency.DOLLAR, BigDecimal.TEN);

        assertThrows(IllegalArgumentException.class, () -> domain.taxPercentage(null));
    }
}