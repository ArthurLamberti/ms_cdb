package com.arthurlamberti.cdb.domain;

import com.arthurlamberti.cdb.domain.utils.IdUtils;
import com.github.javafaker.Faker;


public final class Fixture {

    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static String email() {
        return FAKER.internet().emailAddress();
    }

    public static String document() {
        return FAKER.idNumber().valid();
    }

    public static String characters(Integer qty) {
        return FAKER.lorem().characters(qty);
    }

    public static String characters(Integer qtyMin, Integer qtyMax) {
        return FAKER.lorem().characters(qtyMin, qtyMax);
    }

    public static String imageUrl() {
        return characters(80);
    }

    public static String uuid() {
        return IdUtils.uuid();
    }

    public static String sellerName() {
        return FAKER.name().firstName();
    }

    public static String sellerDescription() {
        return FAKER.lorem().characters(0, 2999);
    }

    public static Boolean isActive() {
        return FAKER.bool().bool();
    }

    public static Double positiveNumber() {
        return FAKER.number().randomDouble(2,1, Integer.MAX_VALUE);
    }

    public static Double negativeNumber() {
        return FAKER.number().randomDouble(2,Integer.MIN_VALUE, -1);
    }
}
