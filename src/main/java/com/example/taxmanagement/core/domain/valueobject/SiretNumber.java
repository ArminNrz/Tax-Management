package com.example.taxmanagement.core.domain.valueobject;

import com.example.taxmanagement.core.exception.TaxException;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

public record SiretNumber(String no) implements Serializable {
    public SiretNumber {
        if (ObjectUtils.isEmpty(no)) {
            throw new TaxException("Siret number must not be empty");
        }
    }
}
