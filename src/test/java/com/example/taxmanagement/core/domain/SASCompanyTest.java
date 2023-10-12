package com.example.taxmanagement.core.domain;

import com.example.taxmanagement.core.domain.valueobject.CompanyType;
import com.example.taxmanagement.core.domain.valueobject.Currency;
import com.example.taxmanagement.core.domain.valueobject.SiretNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SASCompanyTest {

    private final Random random = new Random();

    private String name;
    private Currency currency;
    private String siretNo;

    @BeforeEach
    void init() {
        this.name = "Test-company-" + this.random.nextInt();
        this.currency = Currency.EURO;
        this.siretNo = UUID.randomUUID().toString();
    }

    @Test
    @DisplayName("Create SAS company normally")
    void createNormallyTest() {
        var annualTurnOver = BigDecimal.valueOf(100);
        var expectedDomain = SASCompany.of(siretNo, name, annualTurnOver, currency);

        assertNotNull(expectedDomain);
        assertEquals(CompanyType.SAS, expectedDomain.companyType());
        assertEquals(new SiretNumber(siretNo), expectedDomain.siretNumber());
        assertEquals(name, expectedDomain.name());
        assertEquals(annualTurnOver, expectedDomain.annualTurnOver());
        assertEquals(currency, expectedDomain.currency());
        assertNull(expectedDomain.address());
    }

    @Test
    @DisplayName("Create SAS company normally, then calculate tax")
    void calculateTaxTest() {
        var annualTurnOver = BigDecimal.valueOf(345_486);
        var domain = SASCompany.of(siretNo, name, annualTurnOver, currency);

        var expectedTax = domain.taxCalculation();

        assertNotNull(expectedTax);
        assertEquals(BigDecimal.valueOf(114_010), expectedTax);
    }

    @Test
    void addAddressTest() {
        var annualTurnOver = BigDecimal.valueOf(100);
        var domain = SASCompany.of(siretNo, name, annualTurnOver, currency);

        var street = "First street";
        var city = "Tehran";
        domain.setAddress(street, city);

        assertNotNull(domain.address());
        assertEquals(street, domain.address().street());
        assertEquals(city, domain.address().city());
    }
}