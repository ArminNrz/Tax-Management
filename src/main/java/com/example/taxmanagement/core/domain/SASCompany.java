package com.example.taxmanagement.core.domain;

import com.example.taxmanagement.core.domain.valueobject.*;
import lombok.ToString;

import java.math.BigDecimal;

import static com.example.taxmanagement.core.domain.valueobject.CompanyType.SAS;

@ToString
public class SASCompany extends BaseCompany {
    private Address address;

    private static final BigDecimal PTC = new BigDecimal(33);

    private SASCompany(CompanyType companyType, String name, SiretNumber siretNumber, Denomination denomination) {
        super(companyType, name, siretNumber, denomination);
    }

    public static SASCompany of(String siretNumber, String name, BigDecimal annualTurnOver, Currency currency) {
        var siretNo = new SiretNumber(siretNumber);
        var denomination = new Denomination(currency, annualTurnOver);
        return new SASCompany(SAS, name, siretNo, denomination);
    }

    ///////////////////////
    //  FUNC
    /////////////////////

    @Override
    public BigDecimal taxCalculation() {
        return this.denomination().taxPercentage(PTC);
    }

    public void setAddress(String street, String city) {
        this.address = new Address(street, city);
    }

    ///////////////////////
    //  GETTER
    /////////////////////

    public Address address() {
        return address;
    }
}
