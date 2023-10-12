package com.example.taxmanagement.core.domain;

import com.example.taxmanagement.core.domain.valueobject.CompanyType;
import com.example.taxmanagement.core.domain.valueobject.Currency;
import com.example.taxmanagement.core.domain.valueobject.Denomination;
import com.example.taxmanagement.core.domain.valueobject.SiretNumber;
import lombok.ToString;

import java.math.BigDecimal;

import static com.example.taxmanagement.core.domain.valueobject.CompanyType.SE;

@ToString
public class SECompany extends BaseCompany {
    private static final BigDecimal PTC = new BigDecimal(25);

    private SECompany(CompanyType companyType, String name, SiretNumber siretNumber, Denomination denomination) {
        super(companyType, name, siretNumber, denomination);
    }

    public static SECompany of(String siretNumber, String name, BigDecimal annualTurnOver, Currency currency) {
        var siretNo = new SiretNumber(siretNumber);
        var denomination = new Denomination(currency, annualTurnOver);
        return new SECompany(SE, name, siretNo, denomination);
    }

    ///////////////////////
    //  FUNC
    /////////////////////

    @Override
    public BigDecimal taxCalculation() {
        return this.denomination().taxPercentage(PTC);
    }
}
