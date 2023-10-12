package com.example.taxmanagement.core.domain;

import com.example.taxmanagement.core.domain.valueobject.CompanyType;
import com.example.taxmanagement.core.domain.valueobject.Currency;
import com.example.taxmanagement.core.domain.valueobject.Denomination;
import com.example.taxmanagement.core.domain.valueobject.SiretNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseCompanyTest {

    private final Random random = new Random();

    private SiretNumber siretNo;
    private String name;

    @BeforeEach
    void setUp() {
        this.siretNo = new SiretNumber(UUID.randomUUID().toString());
        this.name = "Test-company-" + random.nextInt();
    }

    @Test
    @DisplayName("Change company name")
    void changeNameTest() {
        var denomination = new Denomination(Currency.DOLLAR, BigDecimal.TEN);
        BaseCompany domain = buildBaseCompany(CompanyType.SE, denomination);

        var updatedName = "Test-name after update";
        domain.changeName(updatedName);

        assertNotNull(domain);
        assertEquals(updatedName, domain.name());
    }

    @Test
    @DisplayName("Update company denomination")
    void updateDenominationTest() {
        var denomination = new Denomination(Currency.EURO, BigDecimal.ONE);
        BaseCompany domain = buildBaseCompany(CompanyType.SAS, denomination);

        var newCurrency = Currency.DOLLAR;
        var newAnnualTurnOver = BigDecimal.TEN;
        domain.updateDenomination(newCurrency, newAnnualTurnOver);

        assertNotNull(domain);
        assertEquals(newCurrency, domain.currency());
        assertEquals(newAnnualTurnOver, domain.annualTurnOver());
    }

    private BaseCompany buildBaseCompany(CompanyType companyType, Denomination denomination) {
        return new BaseCompany(companyType, name, siretNo, denomination) {
            @Override
            public BigDecimal taxCalculation() {
                return null;
            }
        };
    }
}