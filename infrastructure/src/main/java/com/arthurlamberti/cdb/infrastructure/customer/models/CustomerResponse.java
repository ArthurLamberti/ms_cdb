package com.arthurlamberti.cdb.infrastructure.customer.models;

import com.arthurlamberti.cdb.domain.adapters.models.CustomerExternalDomain;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("document") String document,
        @JsonProperty("email") String email,
        @JsonProperty("balance") Double balance
) {
    public CustomerExternalDomain toAggregate() {
        return CustomerExternalDomain.with(
                id,
                name,
                document,
                email,
                balance
        );
    }
}
