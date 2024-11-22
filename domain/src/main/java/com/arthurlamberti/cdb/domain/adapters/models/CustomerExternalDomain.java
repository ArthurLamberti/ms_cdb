package com.arthurlamberti.cdb.domain.adapters.models;

public record CustomerExternalDomain(
        String id,
        String name,
        String document,
        String email,
        Double balance
) {

    public static CustomerExternalDomain with(String id, String name, String document, String email, Double balance) {
        return new CustomerExternalDomain(id, name, document, email, balance);
    }
}
