package com.arthurlamberti.cdb.application;

public abstract class UseCase <IN, OUT> {
    public UseCase() {

    }
    public abstract OUT execute(IN input);
}
