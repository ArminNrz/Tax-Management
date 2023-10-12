package com.example.taxmanagement.core.domain;

import com.example.taxmanagement.core.domain.valueobject.CompanyType;
import com.example.taxmanagement.core.domain.valueobject.Currency;
import com.example.taxmanagement.core.domain.valueobject.Denomination;
import com.example.taxmanagement.core.domain.valueobject.SiretNumber;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@ToString
public abstract class BaseCompany implements Serializable {
    private final CompanyType companyType;
    private String name;
    private final SiretNumber siretNumber;
    private Denomination denomination;

    public BaseCompany(CompanyType companyType, String name, SiretNumber siretNumber, Denomination denomination) {
        this.companyType = companyType;
        this.name = name;
        this.siretNumber = siretNumber;
        this.denomination = denomination;
    }

    ///////////////////////
    //  FUNC
    /////////////////////

    public abstract BigDecimal taxCalculation();

    public void changeName(String newName) {
        this.name = newName;
    }

    public void updateDenomination(Currency currency, BigDecimal annualTurnOver) {
        this.denomination = new Denomination(currency, annualTurnOver);
    }

    ///////////////////////
    //  GETTER
    /////////////////////

    public CompanyType companyType() {
        return companyType;
    }

    public String name() {
        return name;
    }

    public SiretNumber siretNumber() {
        return siretNumber;
    }

    protected Denomination denomination() {
        return denomination;
    }

    public BigDecimal annualTurnOver() {
        return this.denomination.annualTurnOver();
    }

    public Currency currency() {
        return this.denomination.currency();
    }
}
