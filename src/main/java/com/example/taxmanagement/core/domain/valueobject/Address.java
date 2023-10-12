package com.example.taxmanagement.core.domain.valueobject;

import java.io.Serializable;

public record Address(
        String street,
        String city
) implements Serializable {
}
