package com.example.taxmanagement.core.domain.valueobject;

import com.example.taxmanagement.core.exception.TaxException;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public record Denomination(
        Currency currency,
        BigDecimal annualTurnOver
) implements Serializable {

    public Denomination {
        if (currency == null) {
            throw new TaxException("Company currency is null");
        }
        if (ObjectUtils.isEmpty(annualTurnOver)) {
            throw new TaxException("Company annual turn over is null");
        }
    }

    public BigDecimal taxPercentage(BigDecimal ptc) {
        //todo: ask ptc can be zero!
        if (ObjectUtils.isEmpty(ptc)) {
            throw new IllegalArgumentException("Ptc of company must not be null!");
        }
        //todo: rounding method must be asked!!
        return this.annualTurnOver.multiply(ptc).divide(BigDecimal.valueOf(100), RoundingMode.DOWN);
    }
}
